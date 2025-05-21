package br.com.fiap.fast_food_kitchen.dto;

import java.util.List;

public record DemandKitchenResponse(Long orderId, List<ProductKitchenResponse> products, String status) {
}
