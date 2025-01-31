package com.bharath.copilot;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Product {
    private int id;
    private String name;
    private int quantity;
}