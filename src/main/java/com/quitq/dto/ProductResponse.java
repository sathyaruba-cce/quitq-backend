package com.quitq.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String category;
    private String imageUrl;
    private Integer stock;
}
