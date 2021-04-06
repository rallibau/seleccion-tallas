package com.rallibau.tallas.domain.productSize;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductSizeShould {

    @Test
    void product_with_same_sizeSystem_are_equals(){
        SizeSystem sizeSystem = SizeSystemMother.random();
        ProductSize productSize1 = ProductSizeMother.create(ProductSizeIdMother.random(),sizeSystem);
        ProductSize productSize2 = ProductSizeMother.create(ProductSizeIdMother.random(),sizeSystem);

        assertEquals(productSize1,productSize1,"Los productos deben ser iguales");

    }
    @Test
    void product_with_disctint_sizeSystem_are_disctint(){
        SizeSystem sizeSystem = SizeSystemMother.create("1234");
        SizeSystem sizeSystem2 = SizeSystemMother.create("5678");
        ProductSize productSize1 = ProductSizeMother.create(ProductSizeIdMother.random(),sizeSystem);
        ProductSize productSize2 = ProductSizeMother.create(ProductSizeIdMother.random(),sizeSystem2);

        assertNotEquals(productSize2, productSize1, "Los productos deben ser distintos");

    }

    @Test
    void product_with_sizeSystem_with_same_characters_in_disctint_order_are_equals(){
        SizeSystem sizeSystem = SizeSystemMother.create("1234");
        SizeSystem sizeSystem2 = SizeSystemMother.create("4321");
        ProductSize productSize1 = ProductSizeMother.create(ProductSizeIdMother.random(),sizeSystem);
        ProductSize productSize2 = ProductSizeMother.create(ProductSizeIdMother.random(),sizeSystem2);

        assertEquals(productSize1,productSize2,"Los productos deben ser iguales");

    }
}
