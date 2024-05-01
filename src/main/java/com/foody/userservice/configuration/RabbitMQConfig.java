package com.foody.userservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

@Configuration
public class RabbitMQConfig {
    public static final String AUTH_DELETE_QUEUE = "user_auth_delete_queue";
    public static final String AUTH_QUEUE = "auth_queue";
    public static final String FANOUT_EXCHANGE = "fanout_user_exchange";

    @Bean
    public Queue auth_queue() {
        return new Queue(AUTH_QUEUE);
    }
    @Bean
    public Queue auth_delete_queue() {
        return new Queue(AUTH_DELETE_QUEUE);
    }

    @Bean
    public FanoutExchange fanout_exchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public Binding binding(Queue auth_delete_queue, FanoutExchange exchange) {
        return BindingBuilder.bind(auth_delete_queue)
                .to(exchange);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
