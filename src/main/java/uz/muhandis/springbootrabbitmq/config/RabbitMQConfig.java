package uz.muhandis.springbootrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name}")
    private String queue;

    @Value("${rabbitmq.json-queue.name}")
    private String jsonQueue;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Value("${rabbitmq.routing-json.key}")
    private String routingJsonKey;


    //spring bean for rabbitmq queue
    @Bean
    public Queue queue(){
        return new Queue(queue);
    }

    //spring bean for rabbitmq json-queue to store json messages
    @Bean
    public Queue jsonQueue(){
        return new Queue(jsonQueue);
    }

    //spring bean for rabbitmq exchange

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    //Binding between queue and exchange using routing key
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(routingKey);
    }

    //Binding between queue and exchange using routing key
    @Bean
    public Binding jsonBinding(){
        return BindingBuilder.bind(jsonQueue())
                .to(exchange())
                .with(routingJsonKey);
    }

    //ConnectionFactory
    //RabbitTemplate
    //RabbitAdmin
}
