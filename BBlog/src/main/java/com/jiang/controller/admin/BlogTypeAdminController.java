package com.jiang.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jiang.entity.BlogType;
import com.jiang.entity.PageBean;
import com.jiang.service.BlogTypeService;
import com.jiang.util.PageUtil;
import com.jiang.util.ResponseUtil;
import com.jiang.util.StringUtil;

@Controller
@RequestMapping("/admin/blogType")
public class BlogTypeAdminController {

	@Autowired
	private BlogTypeService blogTypeService;
	
	@RequestMapping("/list")
	public ModelAndView showList(HttpServletRequest request,BlogType s_blogType){
		ModelAndView mav = new ModelAndView("blog/blogTypeList");
		String page = request.getParameter("page");
		if (StringUtil.isEmpty(page)) {
			page = "1";
		} else {
			// s_blogType=(BlogType) session.getAttribute("s_blogType");
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 10);
		List<BlogType> blogTypeList = blogTypeService.find(pageBean, s_blogType);
		int total = blogTypeService.findAll().size();
		String pageCode = PageUtil.rootPageTion("blogType/list", total, pageBean.getPage(),
				pageBean.getPageSize(), null, null);
		mav.addObject("pageCode", pageCode);
		mav.addObject("blogTypeList", blogTypeList);
		return mav;
	}
	
	public boolean checkTypeName(String typeName, int id){
		List<BlogType> blogTypes = blogTypeService.findAll();
		for(BlogType blogType:blogTypes){
			if(blogType.getTypeName().equals(typeName) && blogType.getId() != id)
				return false;
		}
		return true;
	}
	
	@RequestMapping("/save")
	public void save(HttpServletRequest request,HttpServletResponse response){
		String typeName = request.getParameter("typeName");
		boolean result;
		String msg;
		if(checkTypeName(typeName, 0)){
			BlogType blogType = new BlogType(typeName);
			result = blogTypeService.save(blogType);
			msg = result?"":"保存失败";
		}else {
			msg = "类型名已存在";
			result = false;
		}
		JSONObject resultJson = new JSONObject();
		resultJson.put("result",result);
		resultJson.put("msg", msg);
		ResponseUtil.writeJson(response, resultJson);
	}
	
	@RequestMapping("/del")
	public void delete(HttpServletRequest request,HttpServletResponse response){
		int id = Integer.parseInt(request.getParameter("id"));
		boolean result = blogTypeService.delete(id);
		String msg = result?"":"删除失败";
		JSONObject resultJson = new JSONObject();
		resultJson.put("result",result);
		resultJson.put("msg", msg);
		ResponseUtil.writeJson(response, resultJson);
	}

	@RequestMapping("/update")
	public void update(HttpServletRequest request, HttpServletResponse response){
		int id = Integer.parseInt(request.getParameter("id"));
		String typeName = request.getParameter("typeName");
		boolean result;
		String msg;
		if(checkTypeName(typeName, 0)){
			BlogType blogType = blogTypeService.findById(id);
			blogType.setTypeName(typeName);
			result = blogTypeService.update(blogType);
			msg = result?"":"更新失败";
		}else {
			msg = "类型名已存在";
			result = false;
		}
		JSONObject resultJson = new JSONObject();
		resultJson.put("result",result);
		resultJson.put("msg", msg);
		ResponseUtil.writeJson(response, resultJson);
	}
	
}
