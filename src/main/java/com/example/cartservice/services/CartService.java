package com.example.cartservice.services;

import com.example.cartservice.models.Cart;

import java.util.List;

public interface CartService {

    List<Cart> getAllCarts();

    Cart getSingleCart(long id);


    List<Cart> getUserCarts(Long userid);
    Cart createCart(Cart cart);

    void updateCart(Cart cart, long id);

    void deleteCart(long id);

}
