package com.skk.project.service;

import com.skk.customer.service.CustomerService;
import com.skk.emp.service.EmployeeService;
import com.skk.project.bean.Project;
import com.skk.project.bean.ProjectExample;
import com.skk.project.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectMapper  projectMapper;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmployeeService employeeService;


    public boolean insertProject(Project project) {
        int insert = projectMapper.insert(project);
        if (insert > 0){
            return true;
        }
        return false;
    }

    public List<Project> findAllProj() {
        List<Project> projects = projectMapper.selectByExample(null);
        for (Project p: projects
             ) {
            p.setCustomer(customerService.findCustById(p.getComname()));
            p.setEmp(employeeService.findEmplById(p.getEmpFk()));
        }
        return projects;
    }

    public boolean delById(Integer[] ids) {
        ProjectExample example = new ProjectExample();
        ProjectExample.Criteria criteria = example.createCriteria();
        criteria.andPidIn(Arrays.asList(ids));
        int i = projectMapper.deleteByExample(example);
        if (i > 0){
            return true;
        }
        return false;
    }

    public Project findProjectById(Integer pid) {
        Project project = projectMapper.selectByPrimaryKey(pid);
        project.setEmp(employeeService.findEmplById(project.getEmpFk()));
        project.setCustomer(customerService.findCustById(project.getComname()));

        return project;
    }

    public boolean update(Project project) {
        int i = projectMapper.updateByPrimaryKeySelective(project);
        if (i > 0){
            return true;
        }
        return false;
    }
}
