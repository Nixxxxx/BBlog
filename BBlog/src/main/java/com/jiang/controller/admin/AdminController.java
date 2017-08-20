package com.jiang.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jiang.entity.Admin;
import com.jiang.entity.PageBean;
import com.jiang.service.AdminService;
import com.jiang.util.CryptographyUtil;
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
	
	
	@RequestMapping("/insert")
	public void insert(Admin adm, HttpServletRequest request, HttpServletResponse response) {
		boolean result = false;
		String msg = "";
		if(!checkEmail(adm.getEmail(), 0)){
			msg = "该邮箱已存在";
		}else {
			adm.setPassword(CryptographyUtil.md5(adm.getPassword(), "jiang"));
			if(adminService.insert(adm)){
				result = true;
				msg = "注册成功";
			}else msg = "注册失败";
		}
		JSONObject resultJson = new JSONObject();
		resultJson.put("result", result);
		resultJson.put("msg", msg);
		ResponseUtil.writeJson(response, resultJson);
	}
	
	@RequestMapping("/update")
	public void update(Admin adm, HttpServletRequest request, HttpServletResponse response) {
		boolean result = false;
		String msg = "";
		if (!checkEmail(adm.getEmail(), adm.getId())) {
			msg = "该邮箱已存在";
		}else {
			adm.setPassword(CryptographyUtil.md5(adm.getPassword(), "jiang"));
			if(adminService.update(adm)){
				result = true;
				msg ="更新成功";
			}
			else msg = "更新失败";
		}
		JSONObject resultJson = new JSONObject();
		resultJson.put("result", result);
		resultJson.put("msg", msg);
		ResponseUtil.writeJson(response, resultJson);
	}
	
	
	@RequestMapping("/del")
	public void delete(int id, HttpServletRequest request, HttpServletResponse response) {
		boolean result = false;
		String msg = "";
		if(adminService.delete(id)){
			result = true;
			msg = "删除成功";
		}
		else msg = "更新失败";
		JSONObject resultJson = new JSONObject();
		resultJson.put("result", result);
		resultJson.put("msg", msg);
		ResponseUtil.writeJson(response, resultJson);
	}
	
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(required = false)String page, 
			HttpServletRequest request, HttpServletResponse response) {
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
