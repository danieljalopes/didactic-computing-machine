package com.kafka101.producer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;


/**
 * Configure Topics
 * 
 */
@Configuration
public class KafkaTopicsConfiguration {
	
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;

	@Bean
	public KafkaAdmin  kafkaAdmin() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		return new KafkaAdmin(props) ;
	}

	@Bean
	public NewTopic adasTopic() {
		return TopicBuilder.name("ADAS").build();
	}

	@Bean
	public NewTopic lidarTopic() {
		return TopicBuilder.name("LIDAR").build();
	}

}
