package com.jiang.controller.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jiang.entity.Blog;
import com.jiang.entity.PageBean;
import com.jiang.lucene.BlogIndex;
import com.jiang.service.BlogService;
import com.jiang.service.BlogTypeService;
import com.jiang.util.PageUtil;
import com.jiang.util.ResponseUtil;
import com.jiang.util.StringUtil;

@Controller
@RequestMapping("/admin/blog")
public class BlogAdminController {

	@Autowired
	private BlogService blogService;
	@Autowired
	private BlogTypeService blogTypeService;
	private BlogIndex blogIndex = new BlogIndex();
	
	public boolean check(String title, int typeId, int id){
		List<Blog> blogs = blogService.findAll();
		for(Blog blog:blogs){
			if(blog.getTitle().equals(title) && blog.getBlogType().getId() == typeId && blog.getId() != id)
				return false;
		}
		return true;
	}
	
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
	
	@RequestMapping("/insert")
	public void insert(Blog bg, int typeId, HttpServletRequest request,HttpServletResponse response) throws Exception{
		boolean result;
		String msg;
		if(check(bg.getTitle(), typeId, 0)){
			Blog blog = new Blog(blogTypeService.findById(typeId), bg.getTitle(), bg.getContent(), new Date());
			blog.setContentNoTag(bg.getContentNoTag());
			blog.setSummary(bg.getSummary());
			result = blogService.insert(blog);
			msg = result?"保存成功":"保存失败";
			blogIndex.addIndex(blog); // 添加博客索引
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
	public void delete(@RequestParam Integer id, HttpServletRequest request,HttpServletResponse response) throws Exception{
		boolean result = blogService.delete(id);
		String msg = result?"删除成功":"删除失败";
		if(result)	blogIndex.deleteIndex(id+""); // 删除对应博客的索引
		JSONObject resultJson = new JSONObject();
		resultJson.put("result",result);
		resultJson.put("msg", msg);
		ResponseUtil.writeJson(response, resultJson);
	}

	@RequestMapping("/update")
	public void update(Blog bg, int typeId, HttpServletRequest request, HttpServletResponse response) throws Exception{
		boolean result;
		String msg;
		if(check(bg.getTitle(), typeId, bg.getId())){
			Blog blog = blogService.findById(bg.getId());
			blog.setBlogType(blogTypeService.findById(typeId));
			blog.setTitle(bg.getTitle());
			blog.setContent(bg.getContent());
			blog.setContentNoTag(bg.getContentNoTag());
			blog.setSummary(bg.getSummary());
			blog.setUpdateTime(new Date());
			result = blogService.update(blog);
			blogIndex.updateIndex(blog); // 添加博客索引
			msg = result?"更新成功":"更新失败";
		}else {
			msg = "文章已存在";
			result = false;
		}
		JSONObject resultJson = new JSONObject();
		resultJson.put("result",result);
		resultJson.put("msg", msg);
		ResponseUtil.writeJson(response, resultJson);
	}
	
	
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(required = false)String page, 
			@RequestParam(required = false)String typeId, HttpServletRequest request){
		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 10);
		int nowTypeId = (typeId == null?0:Integer.parseInt(typeId));
		List<Blog> blogList = blogService.findListByTypeId(pageBean, nowTypeId);
		int total;
		if(nowTypeId == 0){
			total = blogService.findAll().size();
		}else{
			total = blogService.findByTypeId(nowTypeId).size();
		}
		String pageCode = PageUtil.genPagination("admin/blog/list", total, pageBean.getPage(),pageBean.getPageSize(), "typeId="+nowTypeId+"&");
		ModelAndView mav = new ModelAndView("admin/index");
		mav.addObject("pagePath", "./blog/blogManage.jsp");
		if(!blogList.isEmpty()){
			mav.addObject("pageCode", pageCode);
			mav.addObject("blogList", blogList);
		}
		return mav;
	}

}