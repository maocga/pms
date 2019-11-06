package com.skk.project.controller;

import com.skk.customer.bean.Customer;
import com.skk.customer.service.CustomerService;
import com.skk.emp.bean.Employee;
import com.skk.emp.service.EmployeeService;
import com.skk.project.bean.Project;
import com.skk.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "findProNameAndId",method = RequestMethod.GET)
    @ResponseBody
    public Map<Integer,String> findProNameAndId(){
        List<Project> allProj = projectService.findAllProj();
        Map<Integer,String> map = new HashMap<>();
        for (Project project : allProj) {
            map.put(project.getPid(),project.getPname());
        }
        return map;
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    public String update(Project project){
        System.out.println(project);
        boolean b = projectService.update(project);
        if (b){
            return "redirect:/project/findAllProj";
        }
        return "redirect:/project/toEditPage/" + project.getPid();
    }

    @RequestMapping(value = "toEditPage/{pid}",method = RequestMethod.GET)
    public ModelAndView toEditPage(@PathVariable("pid") Integer pid){
        ModelAndView mv = new ModelAndView();
        Project project = projectService.findProjectById(pid);

        //获取所有项目经理信息
        List<Employee> emplByPid = employeeService.findEmplByPid(4);
        //获取所有客户信息
        List<Customer> allCustomer = customerService.findAllCustomer();
        mv.addObject("project",project);
        mv.addObject("emp",emplByPid);
        mv.addObject("customer",allCustomer);
        mv.setViewName("project-base-edit");
        return mv;
    }

    @RequestMapping(value = "delById",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String,Object> delById(@RequestParam("ids[]") Integer[] ids){
        //System.out.println(Arrays.toString(ids));
        Map<String,Object> map = new HashMap<>();
        boolean b = projectService.delById(ids);
        if (b){
            map.put("hashCode",200);
            map.put("msg","添加成功");
        }else {
            map.put("hashCode",500);
            map.put("msg","添加失败");
        }
        return map;
    }

    @RequestMapping(value = "findAllProj",method = RequestMethod.GET)
    public ModelAndView findAllComp(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("project-base");
        List<Project> list = projectService.findAllProj();
        mv.addObject("projects",list);
        return mv;
    }

    @RequestMapping(value = "addProj",method = RequestMethod.POST)
    public String addProj(Project project){
        System.out.println(project.getEmpFk());
        boolean b = projectService.insertProject(project);
        if (b){
            return "redirect:/project/findAllProj";
        }
        return "project-base-add";
    }
}
