package com.skk.emp.service;

import com.skk.emp.bean.Employee;
import com.skk.emp.bean.EmployeeExample;
import com.skk.emp.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private PositionService positionService;

    //通过职位查询
    public List<Employee> findEmplByPid(Integer pid) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        List<Integer> list = new ArrayList<>();
        list.add(pid);
        criteria.andPFkIn(list);
        return employeeMapper.selectByExample(example);
    }

    public Employee findEmplById(Integer empFk) {
        return employeeMapper.selectByPrimaryKey(empFk);
    }

    public boolean login(String username, String password, HttpSession session) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(password);
        List<Employee> employees = employeeMapper.selectByExample(example);
        if (employees.size() == 0){
            return false;
        }else {
            Employee employee = employees.get(0);
            session.setAttribute("user",employee);
            return true;
        }
    }

    public List<Employee> findAllEmp() {
        EmployeeExample example = new EmployeeExample();
        List<Employee> list = employeeMapper.selectByExample(example);
        for (Employee employee : list) {
            Integer id = employee.getpFk();
            employee.setPosition(positionService.findPosById(id));
        }
        return list;

    }
}
