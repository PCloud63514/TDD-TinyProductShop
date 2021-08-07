package com.pcloud.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void product_fields() {
        Product givenProduct = Product.builder()
                .name("name")
                .description("description")
                .build();

        assertThat("name").isEqualTo(givenProduct.getName());
        assertThat("description").isEqualTo(givenProduct.getDescription());
    }
}