package com.jiang.controller.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

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
	
	@RequestMapping("/upload")
	public String imgUpload(File upload, HttpServletRequest request, HttpServletResponse response) throws IOException {  
        response.setCharacterEncoding("utf-8");  
        PrintWriter out = response.getWriter();  
        // CKEditor提交的很重要的一个参数  
        String callback = request.getParameter("CKEditorFuncNum");  
        String expandedName = ""; // 文件扩展名  
        System.out.println(request.getParameter("file"));
        System.out.println(request.getParameter("uploadContentType"));
        System.out.println(request.getParameter("uploadFileName"));
        System.out.println(request.getParameter("upload"));
//        File upload, String uploadContentType,String uploadFileName,
//		
//        if (uploadContentType.equals("image/pjpeg")  
//                || uploadContentType.equals("image/jpeg")) {  
//            // IE6上传jpg图片的headimageContentType是image/pjpeg，而IE9以及火狐上传的jpg图片是image/jpeg  
//            expandedName = ".jpg";  
//        } else if (uploadContentType.equals("image/png")  
//                || uploadContentType.equals("image/x-png")) {  
//            // IE6上传的png图片的headimageContentType是"image/x-png"  
//            expandedName = ".png";  
//        } else if (uploadContentType.equals("image/gif")) {  
//            expandedName = ".gif";  
//        } else if (uploadContentType.equals("image/bmp")) {  
//            expandedName = ".bmp";  
//        } else {  
//            out.println("<script type=\"text/javascript\">");  
//            out.println("window.parent.CKEDITOR.tools.callFunction(" + callback  
//                    + ",''," + "'文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）');");  
//            out.println("</script>");  
//            return null;  
//        }  
//        if (upload.length() > 600 * 1024) {  
//            out.println("<script type=\"text/javascript\">");  
//            out.println("window.parent.CKEDITOR.tools.callFunction(" + callback  
//                    + ",''," + "'文件大小不得大于600k');");  
//            out.println("</script>");  
//            return null;  
//        }  
//  
//        InputStream is = new FileInputStream(upload);  
//        //图片上传路径  
//        String uploadPath = "E:/uploadImage";  
//        String fileName = UUID.randomUUID().toString(); // 采用时间+UUID的方式随即命名  
//        fileName += expandedName;  
//        File file = new File(uploadPath);  
//        if (!file.exists()) { // 如果路径不存在，创建  
//            file.mkdirs();  
//        }  
//        File toFile = new File(uploadPath, fileName);  
//        OutputStream os = new FileOutputStream(toFile);  
//        byte[] buffer = new byte[1024];  
//        int length = 0;  
//        while ((length = is.read(buffer)) > 0) {  
//            os.write(buffer, 0, length);  
//        }  
//        is.close();  
//        os.close();  
//  
//        // 返回"图像"选项卡并显示图片  request.getContextPath()为web项目名   
//        out.println("<script type=\"text/javascript\">");  
//        out.println("window.parent.CKEDITOR.tools.callFunction(" + callback  
//                + ",'" + request.getContextPath() + "/img/uploadImg/" + fileName + "','')");  
//        out.println("</script>");  
        return null;  
    }
}
