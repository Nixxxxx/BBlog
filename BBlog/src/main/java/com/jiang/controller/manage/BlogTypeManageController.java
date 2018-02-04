package com.jiang.controller.manage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jiang.entity.BlogType;
import com.jiang.service.BlogService;
import com.jiang.service.BlogTypeService;
import com.jiang.util.PageUtil;
import com.jiang.util.VariateUtil;

@Controller
@RequestMapping("/manage/blogType")
public class BlogTypeManageController {

	@Autowired
	private BlogTypeService blogTypeService;
	@Autowired
	private BlogService blogService;
	
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam("page")String pageStr) {
		Integer page = VariateUtil.solveNullPage(pageStr);
		List<BlogType> BlogTypeList = blogTypeService.findByPage(page, 10);
		String pageCode = PageUtil.getPagination("manage/admin/list", 1, page, 10, "");
		ModelAndView mav = new ModelAndView("manage/index");
		mav.addObject("pagePath", "./admin/admin_list.ftl");
		if(!BlogTypeList.isEmpty()){
			mav.addObject("pageCode", pageCode);
			mav.addObject("BlogTypeList", BlogTypeList);
		}
		return mav;
	}
	
	
	@ResponseBody
	@RequestMapping("/save")
	public Map<String, Object> save(@RequestParam String typeName, HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		if(blogTypeService.checkTypeName(typeName, 0)){
			BlogType blogType = new BlogType(typeName);
			boolean result = blogTypeService.save(blogType);
			map.put("result", result);
			map.put("msg", result?"保存成功":"保存失败");
		}else {
			map.put("result",false);
			map.put("msg", "类型名已存在");
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/del")
	public Map<String, Object> delete(@RequestParam Integer id, HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		if(blogService.findByTypeId(id).isEmpty()){
			boolean result = blogTypeService.delete(id);
			map.put("result", result);
			map.put("msg", result?"删除成功":"删除失败");
		}else {
			map.put("result", false);
			map.put("msg", "此博客类型下存在文章，不可删除");
		}
		return map;
	}

	@ResponseBody
	@PostMapping("/update")
	public Map<String, Object> update(BlogType blogType, HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> map = new HashMap<>();
		if(blogTypeService.checkTypeName(blogType.getTypeName(), 0)){
			boolean result = blogTypeService.save(blogType);
			map.put("result", result);
			map.put("msg", result?"更新成功":"更新失败");
		}else {
			map.put("result", false);
			map.put("msg", "类型名已存在");
		}
		return map;
	}
	
}
