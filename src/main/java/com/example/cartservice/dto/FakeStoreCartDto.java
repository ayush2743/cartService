package com.example.cartservice.dto;

import com.example.cartservice.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FakeStoreCartDto {
    private Long id;
    private Long userid;
    private String date;
    private List<Product> products;
}
