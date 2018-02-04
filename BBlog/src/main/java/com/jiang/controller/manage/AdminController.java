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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jiang.entity.Admin;
import com.jiang.entity.BlogType;
import com.jiang.entity.Blogger;
import com.jiang.service.AdminService;
import com.jiang.service.BlogTypeService;
import com.jiang.service.BloggerService;
import com.jiang.util.CryptographyUtil;
import com.jiang.util.PageUtil;
import com.jiang.util.VariateUtil;

@Controller
@RequestMapping("/manage/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private BloggerService bloggerService;
	@Autowired
	private BlogTypeService blogTypeService;
	
	@ResponseBody
	@PostMapping("/save")
	public Map<String, Object> save(Admin admin) {
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
	public Map<String, Object> update(Admin admin) {
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
	public Map<String, Object> delete(Integer id) {
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
	public ModelAndView list(String pageStr) {
		Integer page = VariateUtil.solveNullPage(pageStr);
		List<Admin> adminList = adminService.findByPage(page, 10);
		String pageCode = PageUtil.getPagination("manage/admin/list", 1, page, 10, "");
		ModelAndView mav = new ModelAndView("manage/index");
		mav.addObject("pagePath", "./admin/admin_list.ftl");
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
