package com.skk.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class UploadAndDownload {

    //上传，默认路径为服务器upload目录下
    public static String upload(MultipartFile file, HttpSession session){
        ServletContext servletContext = session.getServletContext();
        String realPath = servletContext.getRealPath("/upload");
        File filedir = new File(realPath);
        if (!filedir.exists()){
            filedir.mkdirs();
        }
        String originalFilename = file.getOriginalFilename();
        String s = UUID.randomUUID().toString();
        try {
            file.transferTo(new File(realPath+ "\\" + s + originalFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s + originalFilename;
    }

    //下载
    public static void download(String fileName,String path,HttpSession session){
        ServletContext servletContext = session.getServletContext();
        String realPath = servletContext.getRealPath("/upload");
        String filePath = realPath+"\\"+fileName;
        String downloadPath = path + "\\" + fileName;
        byte[] b = new byte[1024*8];
        int len = 0;
        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            FileOutputStream outputStream = new FileOutputStream(downloadPath);
            while ((len = inputStream.read(b)) == 0){
                outputStream.write(b,0,len);
            }
            outputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
