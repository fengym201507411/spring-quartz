package com.galaxy.fym.service;

import com.galaxy.fym.model.ScheduleJob;
import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengyiming on 2016/8/18.
 */
@Service
public class TaskService {
    private final Logger logger = LoggerFactory.getLogger("task service");

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    private StdScheduler stdScheduler;

    public Object print(){
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();

            List<String> triggerGroups = scheduler.getTriggerGroupNames();

            return triggerGroups;
        }catch (Exception e){
            logger.error("------------------");
            return null;
        }
    }

    /**
     * 获取所有计划中的任务列表
     *
     * @return
     * @throws SchedulerException
     */
    public void getAllJob(){
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();

            //这里获取任务信息数据
            List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
            for (int i = 0; i < 10; i++) {
                ScheduleJob job = new ScheduleJob();
                job.setJobId("10001" + i);
                job.setJobName("JobName_" + i);
                job.setJobGroup("dataWork");
                job.setJobStatus("1");
                job.setCronExpression("0/1 * * * * ?");
                job.setDesc("数据导入任务");
                jobList.add(job);
            }

            for (ScheduleJob job : jobList) {

                TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());

                //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
                CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

                //不存在，创建一个
                if (null == trigger) {
                    JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactoryImpl.class)
                            .withIdentity(job.getJobName(), job.getJobGroup()).build();
                    jobDetail.getJobDataMap().put("scheduleJob", job);

                    //表达式调度构建器
                    CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
                            .getCronExpression());

                    //按新的cronExpression表达式构建一个新的trigger
                    trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(scheduleBuilder).build();
                    scheduler.scheduleJob(jobDetail, trigger);
                } else {
                    // Trigger已存在，那么更新相应的定时设置
                    //表达式调度构建器
                    CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
                            .getCronExpression());

                    //按新的cronExpression表达式重新构建trigger
                    trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                            .withSchedule(scheduleBuilder).build();

                    //按新的trigger重新设置job执行
                    scheduler.rescheduleJob(triggerKey, trigger);
                }
            }
        }catch (Exception e){

        }
    }
}
