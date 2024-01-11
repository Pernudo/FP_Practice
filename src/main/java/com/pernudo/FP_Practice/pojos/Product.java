package com.pernudo.FP_Practice.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable, Comparable<Product> {

    @Serial
    private static final long serialVersionUID = 1L;
    private String name, material, promotionCode;
    private float price;
    private LocalDate expirationDate;

    @Override
    public int compareTo(Product o) {
        return o.getPrice() == price ?
                name.compareToIgnoreCase(o.getName()) :
                Float.compare(o.getPrice(), price); //Descendente. Mayor a menor. Si coincide el precio, ordena por el nombre
    }
}
