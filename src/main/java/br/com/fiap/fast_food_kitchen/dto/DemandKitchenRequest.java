package br.com.fiap.fast_food_kitchen.dto;

import br.com.fiap.fast_food_kitchen.enums.DemandStatus;

import java.util.List;

public record DemandKitchenRequest(Long orderId, List<ProductKitchenRequest> products, DemandStatus status) {
}
