package com.rallibau.tallas.domain.productSize;

import com.github.javafaker.Faker;

public class ProductSizeIdMother {
    private final static Faker faker = new Faker();
    public static ProductSizeId create(Long value){
        return new ProductSizeId(value);
    }

    public static ProductSizeId random(){
        return create(faker.number().randomNumber());
    }
}
