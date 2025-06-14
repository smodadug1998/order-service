package com.ecommerce.order.service;


import com.ecommerce.order.model.CartItem;
import com.ecommerce.order.request.CartItemRequest;

import java.util.List;

public interface CartService {
    boolean addToCart(Long userId, CartItemRequest cartItemRequest);

    boolean deleteItemFromCart(Long userId, Long productId);

    List<CartItem> getCart(Long userId);

    void clearCart(Long userId);
}
