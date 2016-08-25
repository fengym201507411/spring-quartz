网上关于spring自带的定时任务是怎么运行的文档很少，goole 百度了很久都没找到这块的文档，而集成quartz的文档很多，而spring如果单纯的只是运行定时任务是不需要quartz包的。下面是我自己研究出来的，有错见谅。

经过长时间的研究spirng的源码，发现如果仅仅只是在xml里配置定时任务或者通过注解来实现配置定时任务的话，无需依赖spring-context-support，这个包里所含的scheduling包都是为了集成quartz的，而spirng其实只用了spring-context的scheduling包。下面解读一下spring是如何解析我们的配置并且运行的

简单的配置一下定时任务

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:task="http://www.springframework.org/schema/task"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.0.xsd http://www.springframework.org/schema/task http://www.springframework.org/schema/task/spring-task.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
    <!-- job 的包 加载定时任务实现类-->
    <context:component-scan base-package="com.galaxy.fym.job"></context:component-scan>
    <!-- 任务调度器配置 配置任务线性池 pool-size
    指配的一个scheduled-tasks中所有的运行方法的线程总数
    不同的scheduled-tasks配置了相同的task:scheduler会有相同的总数限制，而不是和的限制-->
    <task:scheduler id="scheduler" pool-size="10" />
    <!-- 指定运行的方法和运行时间规则时间
    task:scheduler/@pool-size：调度线程池的大小，调度线程在被调度任务完成前不会空闲
    task:scheduled/@cron：cron表达式，注意，若上次任务未完成，即使到了下一次调度时间，任务也不会重复调度 -->
    <task:scheduled-tasks scheduler="scheduler">
        <!-- 一秒一次 -->
        <task:scheduled ref="jobTest" method="jobPrint" cron="*/1 * * * * ?"/>
        <task:scheduled ref="jobTest" method="jobPrint2" cron="*/1 * * * * ?"/>
    </task:scheduled-tasks>
</beans>

这里因为我在applicationContext中import了这个文件，所以spring在加载的时候也会来解析这个文件，在spring-context中我发现了spring.handlers这个文件说明了那个类是用来解析schedule配置的

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.springframework.scheduling.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import org.springframework.scheduling.config.AnnotationDrivenBeanDefinitionParser;
import org.springframework.scheduling.config.ExecutorBeanDefinitionParser;
import org.springframework.scheduling.config.ScheduledTasksBeanDefinitionParser;
import org.springframework.scheduling.config.SchedulerBeanDefinitionParser;

public class TaskNamespaceHandler extends NamespaceHandlerSupport {
    public TaskNamespaceHandler() {
    }

    public void init() {
        this.registerBeanDefinitionParser("annotation-driven", new AnnotationDrivenBeanDefinitionParser());
        this.registerBeanDefinitionParser("executor", new ExecutorBeanDefinitionParser());
        this.registerBeanDefinitionParser("scheduled-tasks", new ScheduledTasksBeanDefinitionParser());
        this.registerBeanDefinitionParser("scheduler", new SchedulerBeanDefinitionParser());
    }
}

上面已经清楚的说到了每个xml属性配置是由哪个类来解析的。

spring加载的时候会实例化ContextLifecycleScheduledTaskRegistrar这个类，而这个类有继承了ScheduledTaskRegistrar类，ScheduledTaskRegistrar类就是整个spring配置里的定时任务的注册中心， 
下面是这个类的几个成员属性。

public class ScheduledTaskRegistrar implements InitializingBean, DisposableBean {
    private TaskScheduler taskScheduler;
    private ScheduledExecutorService localExecutor;
    private List<TriggerTask> triggerTasks;
    private List<CronTask> cronTasks;
    private List<IntervalTask> fixedRateTasks;
    private List<IntervalTask> fixedDelayTasks;
    private final Set<ScheduledFuture<?>> scheduledFutures = new LinkedHashSet();

像开始的配置，最后加载到cronTasks这个属性里，至于是怎么存的，自己看一下源码就知道了。 
接下来，spring就不停用线程来跑定时任务的方法了。

如果你想在代码运行的时候查看哪些你自己配置了哪些定时任务，很简单，加上这段代码

package com.galaxy.fym.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.ContextLifecycleScheduledTaskRegistrar;
import org.springframework.scheduling.config.CronTask;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fengyiming on 2016/8/18.
 */
@Service
public class TaskService {
    private final Logger logger = LoggerFactory.getLogger("task service");

    @Autowired
    private ContextLifecycleScheduledTaskRegistrar contextLifecycleScheduledTaskRegistrar;

    public Object print(){
        try {
            List<CronTask> cronTasks = contextLifecycleScheduledTaskRegistrar.getCronTaskList();
            return cronTasks;
        }catch (Exception e){
            logger.error("------------------");
            return null;
        }
    }
}
无需再spring里实例化ContextLifecycleScheduledTaskRegistrar类，这个类已经被实例化好了。按需取值就行了。

