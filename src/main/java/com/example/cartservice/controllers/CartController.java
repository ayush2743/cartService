package com.example.cartservice.controllers;

import com.example.cartservice.models.Cart;
import com.example.cartservice.models.Product;
import com.example.cartservice.services.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class CartController {

        private CartService cartService;
        public CartController(CartService cartService) {
                this.cartService = cartService;
        }


        @GetMapping("/carts")
        public List<Cart> getAllCarts() {
            return cartService.getAllCarts();
        }

        @GetMapping("/carts/{id}")
        public Cart getSingleCart(@PathVariable("id") Long id) {

                return cartService.getSingleCart(id);
        }

        //GET IN DATE RANGE

        @GetMapping("/carts/user/{userid}")
        List<Cart> getUserCarts(@PathVariable("userid") Long userid) {
            return cartService.getUserCarts(userid);
        }

        @PostMapping("/carts")
        public Cart createCart(@RequestBody Cart cart) {

                return cartService.createCart(cart);
        }


        @PutMapping("/carts/{id}")
        public void updateCart(@RequestBody Cart cart, @PathVariable("id") Long id) {
                cartService.updateCart(cart, id);
        }


        @DeleteMapping("/carts/{id}")
        public void deleteCart(@PathVariable("id") Long id) {
                cartService.deleteCart(id);
        }


}





