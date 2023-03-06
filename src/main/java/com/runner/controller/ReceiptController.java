package com.runner.controller;

import com.runner.dao.model.Card;
import com.runner.service.CardService;
import com.runner.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ReceiptController {

    private ProductService productService;
    private CardService cardService;


    @Autowired
    public ReceiptController(ProductService productService, CardService cardService) {
        this.productService = productService;
        this.cardService = cardService;
    }

    @GetMapping("/receipt")
    public void printReceipt(@RequestParam Map<String, String> allQueryParams) throws IOException {
        System.out.println(allQueryParams);
        List<String> keys = new ArrayList<>();
        for (Map.Entry<String, String> entry : allQueryParams.entrySet()) {
            String key = entry.getKey();
            String quantity = entry.getValue();
            System.out.println(key);
            System.out.println(quantity);
            if (!key.contains("count") & !key.contains("card")) {
                keys.add(key);
            }
        }
        Map<Long, Long> argsMap = new HashMap<>();
        for (String i : keys) {
            argsMap.put(Long.parseLong(allQueryParams.get(i)), Long.parseLong(allQueryParams.get(i + "count")));
        }
        String cardName = allQueryParams.get("card");
        Card card = cardService.find(cardName);
        productService.createReceipt(argsMap, card);
    }
}