package cn.llm.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

@Configuration
@Slf4j
public class FeignExceptionErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        BaseException baseException = null;
        try {
            String errorContent = Util.toString(response.body().asReader());
            ObjectMapper objectMapper=new ObjectMapper();
            Result result = objectMapper.readValue(errorContent, Result.class);
            baseException = new BaseException(result.getCode(), result.getMsg());
        } catch (Exception e) {
            log.error("处理FeignClient 异常错误");
            e.printStackTrace();
            return new BaseException("500","系统出现异常");
        }
        return baseException;
    }
}
