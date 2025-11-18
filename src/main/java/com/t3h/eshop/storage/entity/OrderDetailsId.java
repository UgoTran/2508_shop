package com.t3h.eshop.storage.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailsId implements Serializable {
    private String orderId;
    private Integer productId;
}
