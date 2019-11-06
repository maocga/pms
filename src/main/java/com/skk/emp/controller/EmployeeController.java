package com.skk.emp.controller;

import com.skk.emp.bean.Employee;
import com.skk.emp.service.EmployeeService;
import com.skk.project.controller.ProjectController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "empl")
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "findEmplByPid",method = RequestMethod.POST)
    @ResponseBody
    public Map<Integer,String> findEmplByPid(Integer pid){
        List<Employee> empl = employeeService.findEmplByPid(pid);
        Map<Integer,String> map = new HashMap<>();
        for (Employee employee: empl) {
            map.put(employee.getEid(),employee.getEname());
        }
        return map;
    }

    @RequestMapping(value = "findAllEmp",method = RequestMethod.GET)
    public ModelAndView findAllEmp(){
        ModelAndView modelAndView = new ModelAndView();
        List<Employee> allEmp = employeeService.findAllEmp();
        modelAndView.setViewName("user");
        modelAndView.addObject("user",allEmp);
        return modelAndView;
    }

}
