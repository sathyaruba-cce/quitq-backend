package com.quitq.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartResponse {
    private Long id;
    private String productName;
    private Double productPrice;
    private Integer quantity;
    private Double totalPrice;
}
