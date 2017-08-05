package com.jiang.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jiang.entity.PageBean;
import com.jiang.entity.User;
import com.jiang.service.UserService;
import com.jiang.util.PageUtil;
import com.jiang.util.StringUtil;

@Controller
@RequestMapping("/admin/user")
public class UserAdminController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/list")
	public ModelAndView list(@RequestParam(required = false)String page, 
			HttpServletRequest request, HttpServletResponse response) {
		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 10);
		List<User> userList = userService.findList(pageBean);
		int total = userList.size();
		String pageCode = PageUtil.genPagination("admin/user/list", total, pageBean.getPage(),pageBean.getPageSize(), null);
		ModelAndView mav = new ModelAndView("admin/index");
		mav.addObject("pagePath", "./admin/user/list.jsp");
		mav.addObject("pageCode", pageCode);
		mav.addObject("userList", userList);
		return mav;
	}
	
}
