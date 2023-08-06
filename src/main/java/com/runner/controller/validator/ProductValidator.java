package com.runner.controller.validator;
import com.runner.controller.exception.EntityNotFoundException;
import com.runner.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import java.util.Locale;

@Component
public class ProductValidator {
    private static ProductService productService;
    private static MessageSource messageSource;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public static void validateIfExists(Long productId, Locale locale)  {
        if (!productService.doExistsById(productId)) {
            String errorMessage = messageSource.getMessage("label.not.found.product", null, locale);
            throw new EntityNotFoundException(errorMessage + ": " + productId);
        }
    }

}
