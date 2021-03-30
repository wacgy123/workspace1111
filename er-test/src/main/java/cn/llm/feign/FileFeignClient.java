package cn.llm.feign;

import cn.llm.FileListReqDTO;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@FeignClient("FILE-SERVICE")
@RestController
@RequestMapping("/files")
public interface FileFeignClient {
    @PostMapping("/download")
    Response download(@RequestBody FileListReqDTO fileListReqDTOList);
}
