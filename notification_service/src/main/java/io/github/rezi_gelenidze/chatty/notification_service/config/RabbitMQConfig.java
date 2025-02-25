package io.github.rezi_gelenidze.chatty.notification_service.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.exchange}")
    private String EXCHANGE_NAME;

    @Value("${rabbitmq.queue.email}")
    private String EMAIL_QUEUE_NAME;

    @Value("${rabbitmq.queue.notification}")
    private String NOTIFICATION_QUEUE_NAME;

    @Value("${rabbitmq.routing-key.email}")
    private String EMAIL_ROUTING_KEY;

    @Value("${rabbitmq.routing-key.notification}")
    private String NOTIFICATION_ROUTING_KEY;

    @Bean
    public Queue emailQueue() {
        return new Queue(EMAIL_QUEUE_NAME, true);
    }

    @Bean
    public Queue pushQueue() {
        return new Queue(NOTIFICATION_QUEUE_NAME, true);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding emailBinding(Queue emailQueue, DirectExchange exchange) {
        return BindingBuilder.bind(emailQueue).to(exchange).with(EMAIL_ROUTING_KEY);
    }

    @Bean
    public Binding pushBinding(Queue pushQueue, DirectExchange exchange) {
        return BindingBuilder.bind(pushQueue).to(exchange).with(NOTIFICATION_ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
