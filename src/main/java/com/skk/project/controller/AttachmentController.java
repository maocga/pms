package com.skk.project.controller;

import com.skk.project.bean.Attachment;
import com.skk.project.service.AttachmentService;
import com.skk.utils.UploadAndDownload;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequestMapping(value = "attachment")
@Controller
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;

    @RequestMapping(value = "saveInfo",method = RequestMethod.POST)
    public String saveInfo(Attachment attachment,MultipartFile multipartFile, HttpSession session){

        String filename = UploadAndDownload.upload(multipartFile,session);
        attachment.setPath(filename);
        attachmentService.insertById(attachment);

        return "redirect:/attachment/findAllAtta";
    }

    @RequestMapping(value = "findAllAtta",method = RequestMethod.GET)
    public ModelAndView findAllAtta(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("project-file");
        System.out.println("查询所有附件");
        List<Attachment> list = attachmentService.findAllAtta();
        System.out.println("查询完成" + list);
        mv.addObject("attas",list);
        System.out.println("返回结果");
        return mv;
    }
}
