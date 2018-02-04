package com.jiang.controller.manage;

import java.util.Date;
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

import com.jiang.entity.Blog;
import com.jiang.lucene.BlogIndex;
import com.jiang.service.BlogService;
import com.jiang.service.BlogTypeService;
import com.jiang.util.PageUtil;
import com.jiang.util.VariateUtil;

@Controller
@RequestMapping("/manage/blog")
public class BlogManageController {

	@Autowired
	private BlogService blogService;
	@Autowired
	private BlogTypeService blogTypeService;
	private BlogIndex blogIndex = new BlogIndex();
	
	
	
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam(required = false) Integer id){
		ModelAndView mav = new ModelAndView("admin/index");
		if(id != null){
			Blog blog = blogService.findById(id);
			mav.addObject("blog", blog);
		}
		mav.addObject("pagePath", "./blog/write.jsp");
		mav.addObject("blogTypeList", blogTypeService.findAll());
		return mav;
	}
	
	@ResponseBody
	@PostMapping("/add")
	public Map<String, Object> add(Blog bg, int typeId, HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> map = new HashMap<>();
		if(blogService.check(bg.getTitle(), typeId, 0)){
			Blog blog = new Blog(blogTypeService.findById(typeId), bg.getTitle(), bg.getContent(), new Date());
			blog.setContentNoTag(bg.getContentNoTag());
			blog.setSummary(bg.getSummary());
			boolean result = blogService.save(blog);
			map.put("result", result);
			map.put("msg", result?"保存成功":"保存失败");
			blogIndex.addIndex(blog); // 添加博客索引
		}else {
			map.put("result", false);
			map.put("msg", "类型名已存在");
		}
		return map;
	}
	
	@ResponseBody
	@PostMapping("/del")
	public Map<String, Object> delete(@RequestParam Integer id, HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> map = new HashMap<>();
		boolean result = blogService.delete(id);
		String msg = result?"删除成功":"删除失败";
		if(result)	blogIndex.deleteIndex(id+""); // 删除对应博客的索引
		map.put("result",result);
		map.put("msg", msg);
		return map;
	}

	@ResponseBody
	@PostMapping("/update")
	public Map<String, Object> update(Blog bg, Integer typeId, HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> map = new HashMap<>();
		boolean result;
		String msg;
		if(blogService.check(bg.getTitle(), typeId, bg.getId())){
			Blog blog = blogService.findById(bg.getId());
			blog.setBlogType(blogTypeService.findById(typeId));
			blog.setTitle(bg.getTitle());
			blog.setContent(bg.getContent());
			blog.setContentNoTag(bg.getContentNoTag());
			blog.setSummary(bg.getSummary());
			blog.setUpdateTime(new Date());
			result = blogService.save(blog);
			blogIndex.updateIndex(blog); // 添加博客索引
			msg = result?"更新成功":"更新失败";
		}else {
			msg = "文章已存在";
			result = false;
		}
		map.put("result",result);
		map.put("msg", msg);
		return map;
	}
	
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam("page")String pageStr, String typeId) throws Exception{
		Integer page = VariateUtil.solveNullPage(pageStr);
		List<Blog> blogList = blogService.findListByTypeId(Integer.parseInt(typeId), page, 10);
		String pageCode = PageUtil.getPagination("blog/list", 1, page, 10, "typeId="+Integer.parseInt(typeId));
		ModelAndView mav = new ModelAndView("foreground/index");
		mav.addObject("pagePath", "./blog/main.ftl");
		mav.addObject("pageCode", pageCode);
		mav.addObject("blogList", blogList);
		return mav;
	}

}