package com.example.cartservice.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Cart {
    private Long id;
    private Long userid;
    private String date;
    private String title;
    private List<Product> products;
}
