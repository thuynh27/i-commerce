package com.nab.product.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Min;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductPredicate {

    private String key;
    
    @Min(value = 0)
    private int page = 0;

    @Min(value = 1)
    private int size = 20;
    
    private BigDecimal price;

    private String[] sortable;
    
}
