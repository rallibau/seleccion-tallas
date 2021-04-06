package com.rallibau.tallas.domain.productSize;

import com.github.javafaker.Faker;

public class SizeSystemMother {
    private final static Faker faker = new Faker();

    public static SizeSystem create(String value) {
        return new SizeSystem(value);
    }

    public static SizeSystem random() {
        return create(faker.lorem().word());
    }
}
