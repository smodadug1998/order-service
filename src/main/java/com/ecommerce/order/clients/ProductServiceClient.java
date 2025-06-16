package com.ecommerce.order.clients;

import com.ecommerce.order.response.ProductResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface ProductServiceClient {

    @GetExchange("api/products/{productId}")
    ProductResponse getProductById(@PathVariable("productId") Long productId);
}
