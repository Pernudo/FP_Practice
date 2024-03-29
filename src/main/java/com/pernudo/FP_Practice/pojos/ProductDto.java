package com.pernudo.FP_Practice.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private String name, material;
    private float price;

    public ProductDto(Product product) {
        name = product.getName();
        material = product.getMaterial();
        price = product.getPrice();
    }
}
