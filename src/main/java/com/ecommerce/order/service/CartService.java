package com.ecommerce.order.service;


import com.ecommerce.order.model.CartItem;
import com.ecommerce.order.request.CartItemRequest;

import java.util.List;

public interface CartService {
    boolean addToCart(String userId, CartItemRequest cartItemRequest);

    boolean deleteItemFromCart(String userId, Long productId);

    List<CartItem> getCart(String userId);

    void clearCart(String userId);
}
