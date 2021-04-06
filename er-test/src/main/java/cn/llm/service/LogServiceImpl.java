package cn.llm.service;

import cn.llm.entity.SysLog;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService{

    public static final String LOG_QUEUE="log-queue";

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void addLog(SysLog sysLog) {
        amqpTemplate.convertAndSend(LOG_QUEUE,sysLog);
    }
}
