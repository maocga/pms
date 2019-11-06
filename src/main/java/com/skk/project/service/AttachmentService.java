package com.skk.project.service;

import com.skk.project.bean.Attachment;
import com.skk.project.bean.AttachmentExample;
import com.skk.project.mapper.AttachmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttachmentService {

    @Autowired
    private AttachmentMapper attachmentMapper;

    public void insertById(Attachment attachment) {
        attachmentMapper.insertSelective(attachment);
    }

    public List<Attachment> findAllAtta() {
        System.out.println("service方法");
        AttachmentExample example = new AttachmentExample();
        List<Attachment> attachments = attachmentMapper.selectByExample(example);
        System.out.println("service 方法结束");
        return attachments;
    }
}
