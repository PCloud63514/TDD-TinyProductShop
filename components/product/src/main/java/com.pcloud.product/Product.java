package com.pcloud.product;

import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;

@Getter
@Setter
public class Product {
    @Id @Generated
    private Long id;
    private String name;
    private String description;

    @Builder
    public Product(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
