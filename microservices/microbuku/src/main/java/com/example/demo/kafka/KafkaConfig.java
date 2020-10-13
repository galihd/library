//package com.example.demo.kafka;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.ByteArrayDeserializer;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//import org.springframework.kafka.support.serializer.JsonSerializer;
//
//import com.example.demo.model.Buku;
//
//
//@Configuration
//public class KafkaConfig {
//	@Bean
//	public ProducerFactory<String,byte[]> filebukufactory(){
//		
//		Map<String, Object> config = new HashMap<String,Object>();
//		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class);
//		
//		
//		return new DefaultKafkaProducerFactory<>(config);
//	}
//	@Bean
//	public ProducerFactory<String, Buku> objbukufactory(){
//		Map<String,Object> config = new HashMap<>();
//		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,JsonSerializer.class);
//		
//		return new DefaultKafkaProducerFactory<>(config);
//	}
//	
//	@Bean
//	public KafkaTemplate<String, byte[]> fileKafkatemplate(){
//		return new KafkaTemplate<>(filebukufactory());
//	}
//	@Bean
//	public KafkaTemplate<String, Buku> objKafkaTemplate(){
//		return new KafkaTemplate<>(objbukufactory());
//	}
//	
//	
//	@Bean
//	public ConsumerFactory<String, String> judulfactory(){
//		Map<String, Object> config = new HashMap<>();
//		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//		return new DefaultKafkaConsumerFactory<>(config,new StringDeserializer(),new StringDeserializer());
//	}
//	
//	@Bean
//	public ConcurrentKafkaListenerContainerFactory<String, String> listenerjudul(){
//		ConcurrentKafkaListenerContainerFactory<String, String> factory =
//				new ConcurrentKafkaListenerContainerFactory<>();
//		factory.setConsumerFactory(judulfactory());
//		return factory;
//	}
//}
