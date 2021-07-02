package com.traudat.app.entity;

import com.traudat.app.utils.FormatUtil;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Category category;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int price;
    private boolean valid = true;
    private String remark;

    public String getPriceStr() {
        return FormatUtil.formatNumber(price);
    }

    public String getValidStr() {
        return valid ? "Yes" : "No";
    }

}
