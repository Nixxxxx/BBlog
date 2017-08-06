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

import com.jiang.entity.BlogType;
import com.jiang.entity.PageBean;
import com.jiang.service.BlogService;
import com.jiang.service.BlogTypeService;
import com.jiang.util.PageUtil;
import com.jiang.util.ResponseUtil;
import com.jiang.util.StringUtil;

@Controller
@RequestMapping("/admin/blogType")
public class BlogTypeAdminController {

	@Autowired
	private BlogTypeService blogTypeService;
	@Autowired
	private BlogService blogservice;
	
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(required = false)String page,
			HttpServletRequest request,BlogType s_blogType){
		if (StringUtil.isEmpty(page)) {
			page = "1";
		} else {
			// s_blogType=(BlogType) session.getAttribute("s_blogType");
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 10);
		List<BlogType> blogTypeList = blogTypeService.findList(pageBean);
		int total = blogTypeService.findAll().size();
		String pageCode = PageUtil.genPagination("admin/blogType/list", total, pageBean.getPage(),
				pageBean.getPageSize(), null);
		ModelAndView mav = new ModelAndView("admin/index");
		mav.addObject("pagePath","./blog/blogTypeManage.jsp");
		if(!blogTypeList.isEmpty()){
			mav.addObject("pageCode", pageCode);
			mav.addObject("blogTypeList", blogTypeList);
		}
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
	
	@RequestMapping("/insert")
	public void insert(@RequestParam String typeName, HttpServletRequest request,HttpServletResponse response){
		boolean result;
		String msg;
		if(checkTypeName(typeName, 0)){
			BlogType blogType = new BlogType(typeName);
			result = blogTypeService.insert(blogType);
			msg = result?"保存成功":"保存失败";
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
	public void delete(@RequestParam int id, HttpServletRequest request,HttpServletResponse response){
		boolean result = false;
		String msg;
		if(blogservice.findByTypeId(id).isEmpty()){
			result = blogTypeService.delete(id);
			msg = result?"删除成功":"删除失败";
		}else {
			msg = "此博客类型下存在文章，不可删除";
		}
		JSONObject resultJson = new JSONObject();
		resultJson.put("result",result);
		resultJson.put("msg", msg);
		ResponseUtil.writeJson(response, resultJson);
	}

	@RequestMapping("/update")
	public void update(BlogType blogType, HttpServletRequest request, HttpServletResponse response){
		boolean result;
		String msg;
		if(checkTypeName(blogType.getTypeName(), 0)){
			result = blogTypeService.update(blogType);
			msg = result?"更新成功":"更新失败";
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
