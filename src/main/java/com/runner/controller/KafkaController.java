package com.runner.controller;

import com.runner.controller.response.InfoResponse;
import com.runner.dao.model.Product;
import com.runner.kafka.KafkaConsumer;
import com.runner.kafka.KafkaProducer;
import com.runner.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping(value = "/api/product")
public class KafkaController {

    @Autowired
    private ProductService productService;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private KafkaConsumer kafkaConsumer;

    @Value("${spring.kafka.consumer.group-id}")
    private String kafkaGroupId;

    @Value("${inventories.kafka.post.product}")
    private String postProductTopic;

     @PostMapping(value = "/stream", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public InfoResponse addProduct(@RequestBody Product productEntity) {
        kafkaProducer.postBrand(postProductTopic, kafkaGroupId, productEntity);
        String message ="label.product.created";
        return new InfoResponse(
                HttpStatus.CREATED.value(),
                message,
                HttpStatus.CREATED.toString());
    }
}

