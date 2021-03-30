package cn.llm.controller;

import cn.llm.FileListReqDTO;
import cn.llm.FileReqBO;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/files")
public class FileController {
    @PostMapping("/download")
    public void download(@RequestBody FileListReqDTO fileListReqDTOList, HttpServletResponse response){
        try {
            List<FileReqBO> fileReqBOList= fileListReqDTOList.getFileReqBOList();
            String zipFileName= fileListReqDTOList.getZipFileName();
            ServletOutputStream outputStream = response.getOutputStream();
            try(ZipOutputStream zipOutputStream=new ZipOutputStream(outputStream)){
                for (FileReqBO fileReqBO : fileReqBOList) {
                    String filename=fileReqBO.getId()+fileReqBO.getName()+".doc";
                    String filePath = fileReqBO.getFilePath();
                    zipOutputStream.putNextEntry(new ZipEntry(filename));
                    InputStream inputStream = new FileInputStream(filePath);
                    IOUtils.copyLarge(inputStream,zipOutputStream);
                    zipOutputStream.closeEntry();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
