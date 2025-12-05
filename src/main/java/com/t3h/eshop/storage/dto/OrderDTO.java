package com.t3h.eshop.storage.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class OrderDTO {
    private String id;
    private String date;
    private Double total;
    private String status;
    private String expectedDate;
    private List<OrderItemDTO> items;

    @Data
    @Builder
    public static class OrderItemDTO {
        private Integer id;
        private String name;
        private Double price;
        private Integer qty;
        private String size;
        private String image;
        private boolean isCancelled;
        private String cancelDate;
        private String cancelReason;
    }
}