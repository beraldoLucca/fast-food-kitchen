package br.com.fiap.fast_food_kitchen.usecase;

import br.com.fiap.fast_food_kitchen.dto.DemandKitchenRequest;

public interface IKitchenUsecase {

    void saveDemand(DemandKitchenRequest demandRequest);
}
