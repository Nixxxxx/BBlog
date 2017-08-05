package com.jiang.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.jiang.entity.Admin;
import com.jiang.entity.PageBean;
import com.jiang.service.AdminService;
import com.jiang.util.MD5Util;
import com.jiang.util.PageUtil;
import com.jiang.util.ResponseUtil;
import com.jiang.util.StringUtil;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView("admin/index");
		return mav;
	}
	
	@RequestMapping("/login")
	public void signIn(Admin adm, @RequestParam String captcha,@SessionAttribute String sRand,
			HttpServletRequest request, HttpServletResponse response){
		List<Admin> admins = adminService.findAll();
		String msg = "";
		boolean result = false;
		if(captcha.equalsIgnoreCase(sRand)){
			for(Admin admin:admins){
				if(adm.getEmail().equals(admin.getEmail())&&MD5Util.getMD5Code(adm.getPassword()).equals(admin.getPassword())){
					request.getSession().setAttribute("admin", admin);
					result = true;
					break;
				}else msg = "邮箱或密码错误";
			}
		}else msg = "验证码错误";
		JSONObject resultJson = new JSONObject();
		resultJson.put("result", result);
		resultJson.put("msg", msg);
		ResponseUtil.writeJson(response, resultJson);
	}
	
	
	@RequestMapping(value = "/signUp")
	public void signUp(Admin adm,  @RequestParam String captcha,@SessionAttribute String sRand,
			HttpServletRequest request, HttpServletResponse response) {
		boolean result = false;
		String msg = "";
		if(captcha.equalsIgnoreCase(sRand)){
			if(!checkEmail(adm.getEmail(), 0)){
				msg = "该邮箱已存在";
			}else {
				adm.setPassword(MD5Util.getMD5Code(adm.getPassword()));
				if(adminService.insert(adm)){
					result = true;
				}else msg = "注册失败";
			}
		}else msg = "验证码错误";
		JSONObject resultJson = new JSONObject();
		resultJson.put("result", result);
		resultJson.put("msg", msg);
		ResponseUtil.writeJson(response, resultJson);
	}
	
	@RequestMapping(value = "/update")
	public void update(Admin adm, HttpServletRequest request, HttpServletResponse response) {
		boolean result = false;
		String msg = "";
		if (!checkEmail(adm.getEmail(), adm.getId())) {
			msg = "该邮箱已存在";
		}else {
			Admin admin = adminService.findById(adm.getId());
			admin.setEmail(adm.getEmail());
			if(adminService.update(admin)){
				result = true;
				request.getSession().setAttribute("user", adminService.findById(admin.getId()));
			}
			else msg = "更新失败";
		}
		JSONObject resultJson = new JSONObject();
		resultJson.put("result", result);
		resultJson.put("msg", msg);
		ResponseUtil.writeJson(response, resultJson);
	}
	
	@RequestMapping(value = "/list")
	public ModelAndView list(@RequestParam(required = false)String page, 
			HttpServletRequest request, HttpServletResponse response) {
		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 10);
		List<Admin> adminList = adminService.findList(pageBean);
		int total = adminList.size();
		String pageCode = PageUtil.genPagination("admin/admin/list", total, pageBean.getPage(),pageBean.getPageSize(), null);
		ModelAndView mav = new ModelAndView("admin/index");
		mav.addObject("pagePath", "./admin/admin/list.jsp");
		mav.addObject("pageCode", pageCode);
		mav.addObject("userList", adminList);
		return mav;
	}
	
	
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("admin");
		return null;
	}
	
	
	
	public boolean checkEmail(String email, int id){
		List<Admin> admins = adminService.findAll();
		for(Admin admin:admins){
			if(admin.getEmail().equals(email) && admin.getId() != id)
				return false;
		}
		return true;
	}
}
