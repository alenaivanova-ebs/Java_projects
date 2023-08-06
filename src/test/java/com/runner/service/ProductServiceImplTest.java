package com.runner.service;

import com.runner.dao.ProductDAO;
import com.runner.dao.ProductDAOImpl;
import com.runner.dao.model.Discount;
import com.runner.dao.model.Product;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Mock
    private ProductDAO productDAO;
    @InjectMocks
    private ProductServiceImpl productService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGet()  {
        Product product = getProduct();
        when(productDAO.get(1L)).thenReturn(product);
        Product productActual = productService.get(1L);
        assertEquals(product, productActual);
    }

    private Product getProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("test");
        product.setPrice(1.0);
        Discount discount = new Discount();
        discount.setId(1L);
        discount.setDiscountType("test");
        discount.setDiscountPercent(1.0);
        product.setDiscount(discount);
        return product;
    }
}
