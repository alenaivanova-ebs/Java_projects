package com.runner.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.runner.dao.model.Product;
import com.runner.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;

@Component
@Transactional
public class KafkaConsumer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    ProductService productService;

    @KafkaListener(topics = "4igc0qsg-inventories.kafka.post.product", groupId = "inventories")
    public void processPostProduct(String brandJSON){
        logger.info("received content = '{}'", brandJSON);
        try{
            ObjectMapper mapper = new ObjectMapper();
            Product productEntity = mapper.readValue(brandJSON, Product.class);
            Long productId = productService.create(productEntity);
            logger.info("Success process brand '{}' with topic '{}'", productId, "inventories.kafka.post.brand");
        } catch (Exception e){
            logger.error("An error occurred! '{}'", e.getMessage());
        }
    }

}
