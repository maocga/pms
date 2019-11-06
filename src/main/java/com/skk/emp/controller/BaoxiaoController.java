package com.skk.emp.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skk.emp.bean.BaoXiao;
import com.skk.emp.bean.Employee;
import com.skk.emp.service.BaoxiaoService;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/baoxiao")
public class BaoxiaoController {

    @Autowired
    private BaoxiaoService baoxiaoService;

    @RequestMapping(value = "/mybaoxiaoList/{num}",method = RequestMethod.GET)
    public ModelAndView findBaoxiaoListByEid(@PathVariable("num") Integer num, @RequestParam("pages") Integer pages,
                                             @RequestParam("searchCriteria") Integer sc, @RequestParam("keyword") String keyword,
                                             HttpServletRequest request){
        HttpSession session = request.getSession();
        Employee user = (Employee)session.getAttribute("user");
        ModelAndView mv = new ModelAndView();
        if (user == null || "".equals(user)){
            mv.setViewName("login");
            return mv;
        }
        System.out.println(pages);
        System.out.println(num);
        System.out.println(sc + "-----" + keyword);
/*        Integer Ipages = null;
        if (pages != null){
            Ipages = Integer.valueOf(pages);
        }*/
        //做首页尾页的判断
        if (num < 1){
            num = 1;
        }
        if (pages != 0 && pages != -1){
            if (num > pages){
                num = pages;
            }
        }
        //刚进入展示页面时pages为0
        if (pages == 0){
            //清空搜索条件
            session.setAttribute("searchCriteria",0);
            //清空关键字
            session.setAttribute("keyword","");

            //设置条件，查询所有
            sc = 0;
            keyword = "";

            //从搜索按钮进入这个方法时，pages = -1
        } else if (pages == -1){
            System.out.println(sc + "-----" + keyword);
            session.setAttribute("searchCriteria",sc);
            session.setAttribute("keyword",keyword);

            //从分页按钮进入这个方法时，pages为其他值
        }else {
            sc =  (Integer) session.getAttribute("searchCriteria");
            keyword = (String) session.getAttribute("keyword");
        }

        //设置每页数据量
        int pageSize = 10;
        //启动分页
        PageHelper.startPage(num,pageSize);



        //获取分页查询的结果,条件查询在service中
        List<BaoXiao> list = baoxiaoService.findBaoxiaoListByEid(user.getEid(),sc,keyword);
        PageInfo pageInfo = new PageInfo(list,5);
        //将索引数组写入到mv
        mv.addObject("navigatePages",pageInfo.getNavigatepageNums());
        //将总页数写入mv
        mv.addObject("pages",pageInfo.getPages());
        //当前页数写入到mv
        mv.addObject("pageNum",pageInfo.getPageNum());

        long count = pageInfo.getTotal();
        System.out.println(list);
        mv.setViewName("mybaoxiao-base");
        mv.addObject("baoxiao",list);

        return mv;
    }

    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(BaoXiao baoXiao,HttpServletRequest request){
        HttpSession session = request.getSession();
        Employee user = (Employee)session.getAttribute("user");
        //设置id
        String s = UUID.randomUUID().toString();
        baoXiao.setBxid(s);
        //设置外键
        baoXiao.setEmpFk(user.getEid());
        //插入到数据库
        boolean b = baoxiaoService.addBaoxiao(baoXiao);
        if (b){
            return "redirect:/baoxiao/mybaoxiaoList/1?pages=1&searchCriteria=0&keyword=";
        }
        return "redirect:mybaoxiao-add.jsp";
    }
}
