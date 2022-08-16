package com.chinasofti.mall.order.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicExchangeConfig {
	@Bean
 	public TopicExchange topicExchange(){
		TopicExchange topicExchange=new TopicExchange("order.exchange");
 		return topicExchange;
 	}
	
	@Bean
    public Queue orderQueue() {
       Queue queue=new Queue("order.queue");
       return queue;
    }
 	

 	//3个binding将交换机和相应队列连起来
 	@Bean
 	public Binding bindingOrder(){
 		Binding binding=BindingBuilder.bind(orderQueue()).to(topicExchange()).with("order.pay.#");//binding key
 		return binding;
 	}
}
