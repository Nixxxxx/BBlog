package com.jiang.controller;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jiang.entity.Code;
import com.jiang.entity.User;
import com.jiang.service.CodeService;
import com.jiang.service.UserService;
import com.jiang.util.CryptographyUtil;
import com.jiang.util.EmailUtil;
import com.jiang.util.RequestUtil;
import com.jiang.util.ResponseUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private CodeService codeService;
	
	@RequestMapping("/signIn")
	public void signIn(User u, HttpServletRequest request, HttpServletResponse response){
		List<User> users = userService.findAll();
		String msg = "";
		boolean result = false;
		JSONObject resultJson=new JSONObject();
		for(User user:users){
			if(u.getEmail().equals(user.getEmail()) && CryptographyUtil.md5(u.getPassword(), "jiang").equals(user.getPassword())){
				if(user.getStatus() == 1){
					user.setSignInIP(RequestUtil.getRemoteIP(request));
					userService.update(user);
					request.getSession().setAttribute("user", user);
					result = true;
				}else{
					msg = "账号未激活";
				}
				break;
			}else {
				request.setAttribute("user", u);
				msg = "邮箱或密码错误";
			}
		}
		resultJson.put("result", result);
		resultJson.put("msg", msg);
		ResponseUtil.writeJson(response, resultJson);
	}
	
	
	public boolean checkUserName(String userName, int id){
		List<User> users = userService.findAll();
		for (User user : users) {
			if (user.getUserName().equals(userName) && user.getId() != id)
				return false;
		}
		return true;
	}
	
	public boolean checkEmail(String email, int id){
		List<User> users = userService.findAll();
		for(User user:users){
			if(user.getEmail().equals(email) && user.getId() != id)
				return false;
		}
		return true;
	}

	@RequestMapping("/signUp")
	public void signUp(User user, @RequestParam String captcha,@SessionAttribute String sRand,
			HttpServletRequest request, HttpServletResponse response) {
		String msg = "";
		boolean result = false;
		JSONObject resultJson=new JSONObject();
		if(captcha.equalsIgnoreCase(sRand)){
			if(!checkEmail(user.getEmail(), 0)){
				msg = "该邮箱已存在";
			}else if(!checkUserName(user.getUserName(), 0)){
				msg = "该用户名已存在";
			}else {
				user.setPassword(CryptographyUtil.md5(request.getParameter("password"), "jiang"));
				user.setSignUpIP(RequestUtil.getRemoteIP(request));
				user.setSignInIP(user.getSignUpIP());
				user.setStatus(0);
				if(userService.insert(user)){
					codeService.insert(new Code(user.getId(), UUID.randomUUID().toString().replace("-", "")
							+UUID.randomUUID().toString().replace("-", "")));
					Code code = codeService.findById(user.getId());
					StringBuffer content = new StringBuffer("<h3>点击下面链接激活账号，48小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！</h3></br>");
					content.append("<a href='http://localhost:8080/BBlog/user/active?email=");
					content.append(user.getEmail()+"&code="+code.getCode());
					content.append("'>http://www.jiangh.me/BBlog/user/active?email=");
					content.append(user.getEmail()+"&code="+code.getCode()+"</a>");
					try {
						EmailUtil.sendEmail(user.getEmail(), content.toString());
					} catch (GeneralSecurityException e) {
						e.printStackTrace();
					}
					result = true;
					msg = "注册成功，去邮箱激活";
				}else msg = "注册失败";
			}
		}else msg = "验证码错误";
		resultJson.put("result", result);
		resultJson.put("msg", msg);
		ResponseUtil.writeJson(response, resultJson);
	}
	
	@RequestMapping("/active")
	public void active(HttpServletRequest request, HttpServletResponse response){
		String code = request.getParameter("code");
		List<Code> codes = codeService.findAll();
		String msg = "";
		for(Code c:codes){
			if(code.equals(c.getCode())){
				User user = userService.findById(c.getId());
				if(user.getEmail().equals(request.getParameter("email"))){
					user.setStatus(1);
					if(userService.update(user)){
						msg = "激活成功";
						codeService.delete(c.getId());
					}
					else msg = "异常";
				}else msg = "邮箱不正确";
			}else msg = "激活码不正确";
		}
		JSONObject json = new JSONObject();
		json.put("信息：", msg);
		ResponseUtil.writeJson(response, json);
	}

	@RequestMapping("/signOut")
	public String signOut(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("user");
		return null;
	}

	@RequestMapping("/update")
	public void update(@RequestParam("imageFile") MultipartFile imageFile, User u,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String msg = "";
		boolean result = false;
		if (!checkEmail(u.getEmail(), u.getId())) {
			msg = "该邮箱已存在";
		} else if(!checkUserName(u.getUserName(), u.getId())){
			msg = "该用户名已存在";
		} else {
			if(!imageFile.isEmpty()){
				String fileName = u.getId()+"."+imageFile.getOriginalFilename().split("\\.")[1];
				String imagePath = "C:/image/avater/";
				u.setImagePath("/BBlog/image/avater/"+fileName);
				try {
					File file = new File(imagePath+fileName);
					if (!file.exists()) { // 如果路径不存在，创建  
						file.mkdirs();  
					} 
					imageFile.transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
					msg = "更新异常";
				}
			}else {
				u.setImagePath(userService.findById(u.getId()).getImagePath());
			}
			User user = userService.findById(u.getId());
			user.setEmail(u.getEmail());
			user.setUserName(u.getUserName());
			user.setImagePath(u.getImagePath());
			user.setMood(u.getMood());
			if(userService.update(user)){
				result = true;
				msg = "更新成功";
				request.getSession().setAttribute("user", userService.findById(u.getId()));
			}else msg = "更新失败";
		}
		if(result == true){
			response.sendRedirect("info");
		}else {
			JSONObject resultJson=new JSONObject();
			resultJson.put("result", result);
			resultJson.put("msg", msg);
			ResponseUtil.writeJson(response, resultJson);
		}
	}

	@RequestMapping("/info")
	public ModelAndView info() {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("pagePath", "./foreground/user/info.jsp");
		return mav;
	}
	
}
