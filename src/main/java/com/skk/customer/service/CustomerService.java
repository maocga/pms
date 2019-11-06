package com.skk.customer.service;

import com.skk.customer.bean.Customer;
import com.skk.customer.bean.CustomerExample;
import com.skk.customer.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    public List<Customer> findAllCustomer() {
        List<Customer> customers = customerMapper.selectByExample(null);
        return customers;
    }

    public boolean addCust(Customer customer) {
        Date date = new Date();
        customer.setAddtime(date);
        int i = customerMapper.insert(customer);
        if (i > 0){
            return true;
        }
        return false;
    }

    public boolean delById(Integer[] ids) {
        CustomerExample example = new CustomerExample();
        CustomerExample.Criteria or = example.or();
        or.andIdIn(Arrays.asList(ids));
        int i = customerMapper.deleteByExample(example);
        if (i > 0){
            return true;
        }
        return false;
    }

    public List<Customer> findCustOrder(Integer cid, String keyword, String orderby) {

        CustomerExample example = new CustomerExample();
        CustomerExample.Criteria criteria = example.or();
        if (cid == 0){
            criteria.andCompanypersonLike("%" + keyword + "%");
            CustomerExample example2 = new CustomerExample();
            CustomerExample.Criteria or = example.or();
            or.andComnameLike("%" + keyword + "%");
            example.or(or);
        }else if (cid == 1){
            criteria.andComnameLike("%" + keyword + "%");
        }else if (cid == 2){
            criteria.andCompanypersonLike("%" + keyword + "%");
        }

        if (orderby.equals("negative")){
            example.setOrderByClause("addtime desc");
        }

        List<Customer> customers = customerMapper.selectByExample(example);
        return customers;
    }

    public Customer findCustById(Integer id) {
        Customer customer = customerMapper.selectByPrimaryKey(id);
        return customer;
    }

    public boolean updateById(Customer customer) {
        int i = customerMapper.updateByPrimaryKeySelective(customer);
        if (i > 0){
            return true;
        }
        return false;
    }

    public List<Customer> findAllComp() {
        List<Customer> customers = customerMapper.selectByExample(null);
        return customers;
    }
}
