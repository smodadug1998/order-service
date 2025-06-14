package com.ecommerce.order.serviceImpl;

import com.ecommerce.order.model.CartItem;
import com.ecommerce.order.repo.CartItemRepo;
import com.ecommerce.order.request.CartItemRequest;
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


    @Override
    public boolean addToCart(Long userId, CartItemRequest cartItemRequest) {
        /*Optional<Product> productOpt = productRepo.findById(cartItemRequest.getProductId());
        if (productOpt.isEmpty()) return false;

        Product product = productOpt.get();
        if (product.getStockQuantity() < cartItemRequest.getQuantity()) return false;

        Optional<User> userOpt = userRepo.findById(Long.valueOf(userId));
        if (userOpt.isEmpty()) return false;

        User user = userOpt.get();*/

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
    public boolean deleteItemFromCart(Long userId, Long productId) {
        CartItem cartItem =  cartItemRepo.findByUserIdAndProductId(userId,productId);
        if (cartItem != null) {
            cartItemRepo.delete(cartItem);
            return true;
        }
        return false;
    }

    @Override
    public List<CartItem> getCart(Long userId) {
        return cartItemRepo.findByUserId(userId);
    }

    @Override
    public void clearCart(Long userId) {
        cartItemRepo.deleteByUserId(userId);
    }
}
