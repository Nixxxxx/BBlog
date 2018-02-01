package com.jiang.controller.manage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jiang.entity.Admin;
import com.jiang.entity.BlogType;
import com.jiang.entity.Blogger;
import com.jiang.service.AdminService;
import com.jiang.service.BlogTypeService;
import com.jiang.service.BloggerService;
import com.jiang.util.CryptographyUtil;

@Controller
@RequestMapping("/manage/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private BloggerService bloggerService;
	@Autowired
	private BlogTypeService blogTypeService;
	
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView("manage/index");
		return mav;
	}
	
	@ResponseBody
	@PostMapping("/save")
	public Map<String, Object> save(Admin admin, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		if(!adminService.checkEmail(admin.getEmail(), 0)){
			map.put("msg", "该邮箱已存在");
		}else {
			admin.setPassword(CryptographyUtil.md5(admin.getPassword(), "jiang"));
			if(adminService.save(admin)){
				map.put("result", true);
				map.put("msg", "注册成功");
			}else {
				map.put("result", false);
				map.put("msg", "注册失败");
			}
		}
		return map;
	}
	
	@ResponseBody
	@PostMapping("/update")
	public Map<String, Object> update(Admin admin, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		if (!adminService.checkEmail(admin.getEmail(), admin.getId())) {
			map.put("result", false);
			map.put("msg", "该邮箱已存在");
		}else {
			admin.setPassword(CryptographyUtil.md5(admin.getPassword(), "jiang"));
			if(adminService.save(admin)){
				map.put("result", true);
				map.put("msg", "更新成功");
			}else {
				map.put("result", false);
				map.put("msg", "更新失败");
			}
		}
		return map;
	}
	
	@ResponseBody
	@PostMapping("/del")
	public Map<String, Object> delete(Integer id, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		if(adminService.delete(id)){
			map.put("result", true);
			map.put("msg", "删除成功");
		}else {
			map.put("result", false);
			map.put("msg", "删除失败");
		}
		return map;
	}
	
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(required = false)String page, 
			HttpServletRequest request) {
//		if (StringUtil.isEmpty(page)) {
//			page = "1";
//		}
//		PageBean pageBean = new PageBean(Integer.parseInt(page), 10);
//		List<Admin> adminList = adminService.findList(pageBean);
//		for(Admin admin:adminList){
//			admin.setPassword(CryptographyUtil.md5(admin.getPassword(), "jiang"));
//		}
//		int total = adminList.size();
//		String pageCode = PageUtil.genPagination("manage/list", total, pageBean.getPage(),pageBean.getPageSize(), null);
		ModelAndView mav = new ModelAndView("manage/index");
		mav.addObject("page", "in_list");
		mav.addObject("pagePath", "./admin/admin_list.ftl");
//		if(!adminList.isEmpty()){
//			mav.addObject("pageCode", pageCode);
//			mav.addObject("adminList", adminList);
//		}
		return mav;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("admin");
		return null;
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
		Blogger blogger = bloggerService.findById(1); // 查询博主信息
		application.setAttribute("blogger", blogger);
		
		List<BlogType> blogTypeCountList = blogTypeService.countList(); // 查询博客类别以及博客的数量
		application.setAttribute("blogTypeCountList", blogTypeCountList);
		
		map.put("msg", "更新成功");
		return map;
	}
}
