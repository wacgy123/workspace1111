package cn.llm.rabbitmq;

import cn.llm.entity.SysLog;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Listener {

    public static final String LOG_QUEUE="log-queue";

    @Bean
    public Queue queue(){
        return new Queue(LOG_QUEUE,false);
    }
}
