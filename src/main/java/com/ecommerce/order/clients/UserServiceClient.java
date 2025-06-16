package com.ecommerce.order.clients;

import com.ecommerce.order.response.UserResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface UserServiceClient {

    @GetExchange("api/users/{id}")
    UserResponse getUserDetailsById(@PathVariable("id") String id);
}
