package com.example.cartservice.services;

import com.example.cartservice.dto.FakeStoreCartDto;
import com.example.cartservice.models.Cart;
import com.example.cartservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreCartService implements CartService {


    RestTemplate restTemplate = new RestTemplate();



    @Override
    public List<Cart> getAllCarts() {
        FakeStoreCartDto[] fakeStoreCartDtos = restTemplate.getForObject("https://fakestoreapi.com/carts", FakeStoreCartDto[].class);

        List<Cart> carts = new ArrayList<>();

        if(fakeStoreCartDtos != null) {
            for(int i=0; i< fakeStoreCartDtos.length; i++) {
                FakeStoreCartDto fakeStoreCartDto =fakeStoreCartDtos[i];
                Cart cart = convertJSONToCart(fakeStoreCartDto);
                carts.add(cart);
            }
            return carts;
        }
        return null;
    }

    @Override
    public Cart getSingleCart(long id) {

        FakeStoreCartDto fakeStoreCartDto = restTemplate.getForObject("https://fakestoreapi.com/carts/" + id, FakeStoreCartDto.class);

        return convertJSONToCart(fakeStoreCartDto);
    }

    @Override
    public List<Cart> getUserCarts(Long userid) {

        FakeStoreCartDto[] fakeStoreCartDtos = restTemplate.getForObject("'https://fakestoreapi.com/carts/user/" + userid, FakeStoreCartDto[].class);



        if(fakeStoreCartDtos != null) {
            List<Cart> carts = new ArrayList<>();
            for(int i=0; i<fakeStoreCartDtos.length; i++) {
                FakeStoreCartDto fakeStoreCartDto = fakeStoreCartDtos[i];
                Cart cart = convertJSONToCart(fakeStoreCartDto);
                carts.add(cart);
            }
            return carts;
        }
        return null;
    }

    @Override
    public Cart createCart(Cart cart) {
        FakeStoreCartDto fakeStoreCartDto = convertCartToJSON(cart);

        FakeStoreCartDto fakeStoreCartDtoOutput = restTemplate.postForObject("'https://fakestoreapi.com/carts", fakeStoreCartDto, FakeStoreCartDto.class);

        return convertJSONToCart(fakeStoreCartDtoOutput);
    }

    @Override
    public void updateCart(Cart cart, long id) {
        FakeStoreCartDto fakeStoreCartDto = convertCartToJSON(cart);
        restTemplate.put("https://fakestoreapi.com/carts/" + id, fakeStoreCartDto, FakeStoreCartDto.class);
    }

    @Override
    public void deleteCart(long id) {
        restTemplate.delete("https://fakestoreapi.com/carts/" + id);
    }

    private Cart convertJSONToCart(FakeStoreCartDto fakeStoreCartDto) {
        if(fakeStoreCartDto == null) return null;
        Cart cart = new Cart();
        cart.setId(fakeStoreCartDto.getId());
        cart.setUserid(fakeStoreCartDto.getUserid());
        cart.setDate(fakeStoreCartDto.getDate());
        cart.setProducts(fakeStoreCartDto.getProducts());
        return cart;
    }

    private FakeStoreCartDto convertCartToJSON(Cart cart) {

        if(cart == null) return null;

        FakeStoreCartDto fakeStoreCartDto  = new FakeStoreCartDto();
        fakeStoreCartDto.setId(cart.getId());
        fakeStoreCartDto.setUserid(cart.getUserid());
        fakeStoreCartDto.setDate(cart.getDate());
        fakeStoreCartDto.setProducts(cart.getProducts());
        return fakeStoreCartDto;
    }

}
