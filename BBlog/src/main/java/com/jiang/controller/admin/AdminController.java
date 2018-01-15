package com.jiang.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jiang.entity.Admin;
import com.jiang.entity.BlogType;
import com.jiang.entity.Blogger;
import com.jiang.entity.PageBean;
import com.jiang.service.AdminService;
import com.jiang.service.BlogTypeService;
import com.jiang.service.BloggerService;
import com.jiang.util.CryptographyUtil;
import com.jiang.util.PageUtil;
import com.jiang.util.StringUtil;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private static Logger logger = Logger.getLogger(AdminController.class);
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private BloggerService bloggerService;
	@Autowired
	private BlogTypeService blogTypeService;
	
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView("admin/index");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping("/add")
	public Map<String, Object> insert(Admin adm, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		boolean result = false;
		String msg = "";
		if(!checkEmail(adm.getEmail(), 0)){
			msg = "该邮箱已存在";
		}else {
			adm.setPassword(CryptographyUtil.md5(adm.getPassword(), "jiang"));
			if(adminService.save(adm)){
				result = true;
				msg = "注册成功";
				logger.info("注册  - " + adm.getId() + " - " + adm.getEmail());
			}else msg = "注册失败";
		}
		map.put("result", result);
		map.put("msg", msg);
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(Admin adm, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		boolean result = false;
		String msg = "";
		if (!checkEmail(adm.getEmail(), adm.getId())) {
			msg = "该邮箱已存在";
		}else {
			adm.setPassword(CryptographyUtil.md5(adm.getPassword(), "jiang"));
			if(adminService.save(adm)){
				result = true;
				msg ="更新成功";
			}
			else msg = "更新失败";
		}
		map.put("result", result);
		map.put("msg", msg);
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/del")
	public Map<String, Object> delete(Integer id, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		boolean result = false;
		String msg = "";
		if(adminService.delete(id)){
			result = true;
			msg = "删除成功";
			logger.info("删除管理员  - " + "id：" + id);
		}
		else msg = "更新失败";
		map.put("result", result);
		map.put("msg", msg);
		return map;
	}
	
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(required = false)String page, 
			HttpServletRequest request) {
		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 10);
		List<Admin> adminList = adminService.findList(pageBean);
		for(Admin admin:adminList){
			admin.setPassword(CryptographyUtil.md5(admin.getPassword(), "jiang"));
		}
		int total = adminList.size();
		String pageCode = PageUtil.genPagination("admin/list", total, pageBean.getPage(),pageBean.getPageSize(), null);
		ModelAndView mav = new ModelAndView("admin/index");
		mav.addObject("pagePath", "./admin/list.jsp");
		if(!adminList.isEmpty()){
			mav.addObject("pageCode", pageCode);
			mav.addObject("adminList", adminList);
		}
		return mav;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
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
	
	/**
	 * 刷新系统缓存
	 * @param request
	 * @return 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/refreshSystem")
	public Map<String, String> refreshSystem(HttpServletRequest request)throws Exception{
		Map<String, String> map = new HashMap<>();
		ServletContext application = request.getServletContext();
		Blogger blogger = bloggerService.findOne(1); // 查询博主信息
		application.setAttribute("blogger", blogger);
		
		List<BlogType> blogTypeCountList = blogTypeService.countList(); // 查询博客类别以及博客的数量
		application.setAttribute("blogTypeCountList", blogTypeCountList);
		
		map.put("msg", "更新成功");
		return map;
	}
}
