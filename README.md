# BBlog v1.0
个人博客 v1.0
#### 项目基本信息(编码格式 UTF-8)
* jdk：1.8.0_73 64-bit MySQL：5.5.8 Tomcat：7.0.72
* 框架：Spring：4.3.9.RELEASE、SpringMVC：4.3.9.RELEASE、Hibernate4、AdminLTE2
* 运行环境：centos7.3  
* 编写环境：Eclipse：Oxygen.1 Release (4.7.1) maven：3.5.0

项目功能
---
> * 用户注册后发送邮件到用户邮箱，需要用户进入邮箱激活
> * 日志文件每天定时发送到指定邮箱（spring task）
> * tomcat配置虚拟路径，上传文件与服务器分离
> * shiro登陆  md5、base64加密
> * lucene文章搜索
> * jquery，ajax实现前端操作
> * 初始化组件 将部分信息放入application缓存，提高页面请求性能
> * CKeditor编辑器

tomcat配置虚拟路径，上传文件与服务器分离（修改apache-tomcat/conf/server.xml）
```xml
<Host name="www.jiangh.me"  appBase=""
		unpackWARs="true" autoDeploy="true">
	<Valve className="org.apache.catalina.valves.AccessLogValve" directory="logs"
		prefix="localhost_access_log" suffix=".txt"
	   pattern="%h %l %u %t &quot;%r&quot; %s %b" />
	<Context path="" debug="" docBase="/root/apache-tomcat-8.0.47/webapps/BBlog/"/>  <!-- 域名直接访问 -->
	<Context path="/BBlog/image" docBase="/root/BBlog/image" reloadable="false"></Context>  <!-- 虚拟路径 -->
</Host>
```

网站页面演示
---
![用户预览页](https://raw.githubusercontent.com/Nixxxxx/BBlog-Website/master/BBlog/readme/foreground.png)
![管理页](https://raw.githubusercontent.com/Nixxxxx/BBlog-Website/master/BBlog/readme/admin_page.png)
![管理员登录页](https://raw.githubusercontent.com/Nixxxxx/BBlog-Website/master/BBlog/readme/admin_login.png)
![用户登录页](https://raw.githubusercontent.com/Nixxxxx/BBlog-Website/master/BBlog/readme/user_signin.png)
![用户注册页](https://raw.githubusercontent.com/Nixxxxx/BBlog-Website/master/BBlog/readme/user_signup.png)
