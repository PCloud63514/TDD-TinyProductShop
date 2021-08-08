package com.pcloud.product;

import javassist.NotFoundException;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
@Getter
@Setter
public class Product {
    @Id @Generated
    @Column(name="product_id")
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy="products", fetch = FetchType.LAZY)
    private List<Category> categories = new ArrayList<>();

    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        int resultStock = this.stockQuantity - quantity;
        if (0 > resultStock) {
            throw new NotEnoughStockException("need nore stock");
        }
        this.stockQuantity = resultStock;
    }
}
