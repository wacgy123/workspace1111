package cn.llm.rabbitmq;

import cn.llm.entity.SysLog;
import cn.llm.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Receiver {

    private CountDownLatch latch=new CountDownLatch(1);

    @Autowired
    private SysLogService sysLogService;

    public void receiveMessage(SysLog sysLog){
        sysLogService.saveLog(sysLog);
        latch.countDown();
    }
}
