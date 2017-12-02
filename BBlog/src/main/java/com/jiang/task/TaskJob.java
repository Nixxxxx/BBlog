package com.jiang.task;

import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jiang.util.EmailUtil;

/**
 * 定时任务
 * @author JH
 *
 */
@Component("taskJob")
public class TaskJob {
	
	private static Logger logger = Logger.getLogger(TaskJob.class);
	
    //每分钟的10秒执行  
    @Scheduled(cron = "0 0 2 * * ? ")  
    public void job() {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	logger.info("邮箱发送log文件" + format.format(System.currentTimeMillis() - 24*60*60*1000));
    	EmailUtil.sendEmailWithFile("123456789@qq.com", 
    			"/root/BBlog/log/log.log." + format.format(System.currentTimeMillis() - 24*60*60*1000));
    }
      
}
