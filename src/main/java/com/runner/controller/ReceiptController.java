package com.runner.controller;

import com.runner.dao.model.Card;
import com.runner.service.CardService;
import com.runner.service.ProductService;
import com.runner.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ReceiptController {

    private final ProductService productService;
    private final CardService cardService;

    @Autowired
    public ReceiptController(ProductService productService, CardService cardService) {
        this.productService = productService;
        this.cardService = cardService;
    }


    @GetMapping("/receipt")
    public void getReceipt(@RequestParam Map<String, String> allQueryParams) throws ServiceException {
        Map<Long, Long> mapWithProductsFromRequest = getMapFromRequest(allQueryParams);
        String cardName = allQueryParams.get("card");
        Card card = cardService.find(cardName);
        productService.createReceipt(mapWithProductsFromRequest, card);
    }

    /**
     * Get map with Products from request
     * Example of request: //http://localhost:8080/receipt?itemId=1&1count=2&itemId=3&3count=4&card=1234
     *
     * @param allQueryParams is a map that contains request params
     * @return Map that contains productId as key and count as value
     */
    private Map<Long, Long> getMapFromRequest(Map<String, String> allQueryParams) {
        List<String> productsKeys = new ArrayList<>();
        for (Map.Entry<String, String> entry : allQueryParams.entrySet()) {
            if (!entry.getKey().contains("count") & !entry.getKey().contains("card")) {
                productsKeys.add(entry.getKey());
            }
        }
        Map<Long, Long> mapWithProductsFromRequest = new HashMap<>();
        for (String i : productsKeys) {
            mapWithProductsFromRequest.put(Long.parseLong(allQueryParams.get(i)), Long.parseLong(allQueryParams.get(i + "count")));
        }
        return mapWithProductsFromRequest;
    }
}