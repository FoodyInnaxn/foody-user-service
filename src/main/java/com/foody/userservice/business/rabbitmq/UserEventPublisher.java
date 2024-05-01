package com.foody.userservice.business.rabbitmq;

import com.foody.userservice.configuration.RabbitMQConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserEventPublisher {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void publishDelete(Long authId) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE, "", authId);
    }

}
