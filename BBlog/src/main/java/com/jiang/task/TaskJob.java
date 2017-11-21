package com.jiang.task;

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
	
    //每分钟的10秒执行  
    @Scheduled(cron = "0 30 23 * * ? ")  
    public void job() {
    	EmailUtil.sendEmailWithFile("992779752@qq.com", "C:\\log/log.log");
    }
      
}
