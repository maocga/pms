package com.skk.emp.controller;


import com.skk.emp.bean.Employee;
import com.skk.emp.service.EmployeeService;
import com.skk.utils.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/loginToIndex",method = RequestMethod.POST)
    @ResponseBody
    public Map<Integer,String> login(String username, String password, String code, HttpServletRequest request){
        HttpSession session = request.getSession();
        Map<Integer,String> map = new HashMap<>();
        String validateCode = (String)session.getAttribute("validateCode");
        System.out.println("判断验证码");
        if (code.equals(validateCode)){
            System.out.println("验证码正确");
            boolean b = employeeService.login(username,password,session);
            System.out.println("判断用户名与密码");
            if (b) {
                map.put(200,"index.jsp");
            }else {
                System.out.println("用户名或密码错误");
                map.put(400,"用户名或密码错误");

            }

        }else {
            System.out.println("验证码错误");
            map.put(400,"验证码错误");
        }
        return map;
    }

    @RequestMapping(value="/getCode")
    public void getCode(@RequestParam(value = "time") String time, HttpServletRequest request, HttpServletResponse response) {

        ValidateCode code = new ValidateCode(75, 25, 6, 25, 20, "validateCode");
        code.getCode(request, response);
    }

}
