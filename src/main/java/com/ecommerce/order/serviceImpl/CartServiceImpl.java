package com.ecommerce.order.serviceImpl;

import com.ecommerce.order.clients.ProductServiceClient;
import com.ecommerce.order.clients.UserServiceClient;
import com.ecommerce.order.model.CartItem;
import com.ecommerce.order.repo.CartItemRepo;
import com.ecommerce.order.request.CartItemRequest;
import com.ecommerce.order.response.ProductResponse;
import com.ecommerce.order.response.UserResponse;
import com.ecommerce.order.service.CartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

    private final CartItemRepo cartItemRepo;
    private final ProductServiceClient productServiceClient;
    private final UserServiceClient userServiceClient;

    @Override
    public boolean addToCart(String userId, CartItemRequest cartItemRequest) {
        ProductResponse productResponse = productServiceClient.getProductById(cartItemRequest.getProductId());
        if (productResponse == null || productResponse.getStockQuantity() < cartItemRequest.getQuantity()) return false;

        UserResponse userResponse = userServiceClient.getUserDetailsById(userId);
        if (userResponse == null ) return false;

        CartItem existingCartItem = cartItemRepo.findByUserIdAndProductId(userId, cartItemRequest.getProductId());
        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItemRequest.getQuantity());
            existingCartItem.setPrice(BigDecimal.valueOf(10000.00));
            cartItemRepo.save(existingCartItem);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setUserId(userId);
            cartItem.setProductId(cartItemRequest.getProductId());
            cartItem.setQuantity(cartItemRequest.getQuantity());
            cartItem.setPrice(BigDecimal.valueOf(10000.00));
            cartItemRepo.save(cartItem);
        }
        return true;
    }

    @Override
    public boolean deleteItemFromCart(String userId, Long productId) {
        CartItem cartItem =  cartItemRepo.findByUserIdAndProductId(userId,productId);
        if (cartItem != null) {
            cartItemRepo.delete(cartItem);
            return true;
        }
        return false;
    }

    @Override
    public List<CartItem> getCart(String userId) {
        return cartItemRepo.findByUserId(userId);
    }

    @Override
    public void clearCart(String userId) {
        cartItemRepo.deleteByUserId(userId);
    }
}
