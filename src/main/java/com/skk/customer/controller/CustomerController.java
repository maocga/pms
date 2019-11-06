package com.skk.customer.controller;

import com.skk.customer.bean.Customer;
import com.skk.customer.bean.CustomerExample;
import com.skk.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.sound.midi.Soundbank;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "findcompnameById",method = RequestMethod.GET)
    @ResponseBody
    public Customer getComper(Integer id){
        Customer custById = customerService.findCustById(id);
        return custById;
    }

    @RequestMapping(value = "findAllComp",method = RequestMethod.GET)
    @ResponseBody
    public Map<Integer,String> findAllComp (){
        List<Customer> list = customerService.findAllComp();
        Map<Integer,String> map = new HashMap<>();
        for (Customer c: list) {
            map.put(c.getId(),c.getComname());
        }
        return map;
    }

    @RequestMapping(value = "edit",method = RequestMethod.PUT)
    public String edit(Customer customer){
        boolean b = customerService.updateById(customer);
        if (b){
            return "redirect:/customer/findAllCustomer";
        }
        return "redirect:/customer/update/{" + customer.getId() + "}";
    }

    @RequestMapping(value = "update/{id}")
    public ModelAndView update(@PathVariable("id") Integer id ){
        Customer customer = customerService.findCustById(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("customer-edit");
        mv.addObject("customer",customer);
        return mv;
    }

    @RequestMapping(value = "detil/{id}")
    public ModelAndView detil(@PathVariable("id") Integer id ){
        Customer customer = customerService.findCustById(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("customer-look");
        mv.addObject("customer",customer);
        return mv;
    }

    @RequestMapping(value = "findCustOrder",method = RequestMethod.PUT)
    public ModelAndView findCustOrder(Integer cid,String keyword,String orderby){
        ModelAndView mv = new ModelAndView();

        List<Customer> customers = customerService.findCustOrder(cid,keyword,orderby);
        mv.addObject("customerList",customers);

        mv.setViewName("/customer");
        return mv;
    }

    @RequestMapping(value = "findAllCustomer" ,method = RequestMethod.GET)
    public ModelAndView findAllCustomer(){
        ModelAndView mv = new ModelAndView();
        List<Customer> customers = customerService.findAllCustomer();
        mv.setViewName("/customer");
        mv.addObject("customerList",customers);
        return mv;
    }

    @RequestMapping(value = "addCust",method = RequestMethod.POST)
    public String addCust(Customer customer){
        System.out.println(customer);
        boolean resourt = customerService.addCust(customer);
        if (resourt){
            return "redirect:/customer/findAllCustomer";
        }
        return "customer-add";
    }

    @RequestMapping(value = "delById",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String,Object> delById(@RequestParam("ids[]") Integer[] ids){
        Map<String,Object> map = new HashMap<>();
        boolean b = customerService.delById(ids);
        if (b){
            map.put("hashCode",200);
            map.put("msg","添加成功");
        }else {
            map.put("hashCode",500);
            map.put("msg","添加失败");
        }
        return map;
    }
}
