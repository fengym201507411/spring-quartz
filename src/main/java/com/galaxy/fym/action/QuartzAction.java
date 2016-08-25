package com.galaxy.fym.action;

import com.galaxy.fym.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fengyiming on 2016/8/18.
 */
@Controller
@RequestMapping("quartz")
public class QuartzAction {

    @Autowired
    private TaskService taskService;

    @RequestMapping("setAllJob")
    @ResponseBody
    public String setAllJob(){
        taskService.setAllJob();
        return "Ok";
    }

    @RequestMapping("print")
    @ResponseBody
    public Object print(){
        return taskService.print();
    }

    @RequestMapping("getAll")
    @ResponseBody
    public Object getAll(){
        return taskService.getAllJob();
    }
}
