package com.runner.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.runner.dao.model.Discount;
import com.runner.dao.model.Product;
import com.runner.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ProductControllerTest {
    private MockMvc mockMvc;
    @Mock
    private ProductService productService;

    @Mock
    private MessageSource messageSource;
    @InjectMocks
    private ProductController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testAllProducts() throws Exception {
        Product product = getProduct();
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        given(productService.getAllProducts()).willReturn(productList);
        this.mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(productList.size())));
    }

    @Test
    public void testGetById() throws Exception {
        final Long productId = 1L;
        Product product = getProduct();
        given(productService.get(productId)).willReturn(product);
        this.mockMvc.perform(get("/products/{id}", productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(product.getName())))
                .andExpect(jsonPath("$.price", is(product.getPrice())));
    }

    @Test
    public void testDeleteById() throws Exception {
        final Long productId = 1L;
        given(productService.delete(productId)).willReturn(Boolean.TRUE);
        this.mockMvc.perform(delete("/products/{id}", productId))
                .andExpect(status().isOk());
    }

    @Test
    public void testPostById() throws Exception {
        final Long productId = 1L;
        Product product = getProduct();
        given(productService.create(product)).willReturn(productId);
        this.mockMvc.perform(post("/products")
                .content(asJsonString(product))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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