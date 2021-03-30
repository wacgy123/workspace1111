package cn.llm.controller;

import cn.llm.FileListReqDTO;
import cn.llm.FileReqBO;
import cn.llm.feign.FileFeignClient;
import feign.Response;
import jdk.internal.util.xml.impl.Input;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/file")
@CrossOrigin
public class FileController {

    @Autowired
    private FileFeignClient fileFeignClient;

    @GetMapping("/download")
    public void download(String filename, HttpServletResponse servletResponse) throws IOException {
        List<FileReqBO> fileReqBOList=new ArrayList<>();
        FileReqBO fileReqBO1 = new FileReqBO("刘1","170591157","C:\\Users\\YCLW043\\Desktop\\学生实习协议书.doc");
        FileReqBO fileReqBO2 = new FileReqBO("刘2","170591158","C:\\Users\\YCLW043\\Desktop\\学生实习协议书.doc");
        FileReqBO fileReqBO3 = new FileReqBO("刘3","170591159","C:\\Users\\YCLW043\\Desktop\\学生实习协议书.doc");
        fileReqBOList.add(fileReqBO1);
        fileReqBOList.add(fileReqBO2);
        fileReqBOList.add(fileReqBO3);
        FileListReqDTO fileListReqDTO = new FileListReqDTO();
        fileListReqDTO.setFileReqBOList(fileReqBOList);
        fileListReqDTO.setZipFileName("学生实习协议书");

        servletResponse.setContentType("application/octet-stream");
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.setHeader("Access-Control-Expose-Headers","Content-Disposition");
        servletResponse.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("学生实习协议书.zip","utf-8"));
        Response response = fileFeignClient.download(fileListReqDTO);
        Response.Body body= response.body();
        System.out.println(body.length());
        InputStream inputStream = body.asInputStream();
        ServletOutputStream outputStream = servletResponse.getOutputStream();
        IOUtils.copy(inputStream,outputStream);
    }
}
