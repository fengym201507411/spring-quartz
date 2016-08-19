package com.galaxy.fym.test;


import com.galaxy.fym.service.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by fengyiming on 2016/8/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:applicationContext.xml")
public class JobRunningTest {

    @Autowired
    private TaskService taskService;

    @Test
    public void test() throws Exception{
        taskService.getAllJob();
    }
}
