package com.skk.utils.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skk.emp.bean.Employee;
import com.skk.emp.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans.xml"})
public class Test1 {

        @Autowired
        private EmployeeService employeeService;

    @Test
    public void test01(){
        PageHelper.startPage(2,2);

        List<Employee> list = employeeService.findAllEmp();
        System.out.println(list);
        PageInfo page = new PageInfo(list,3);
        System.out.println(list);
    }

    @Test
    public void test02() throws MessagingException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-email.xml");
        JavaMailSenderImpl bean = context.getBean(JavaMailSenderImpl.class);
        MimeMessage mimeMessage = bean.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setFrom("kuroyukihimeskk@163.com");
        helper.setTo("1456126295@qq.com");
        helper.setSubject("这是一封测试邮件");
        helper.setText("<html><head></head><body><h1>测试邮件1</h1></body></html>",true);
        System.out.println("发送中。。。");
        bean.send(mimeMessage);
        System.out.println("发送完成。");
    }
}
