package br.com.fiap.fast_food_kitchen.usecase;

import br.com.fiap.fast_food_kitchen.dto.DemandKitchenRequest;
import br.com.fiap.fast_food_kitchen.dto.DemandKitchenResponse;

import java.util.List;

public interface IKitchenUsecase {

    void saveDemand(DemandKitchenRequest demandRequest);

    void updateDemandStatus();

    List<DemandKitchenResponse> getReadyDemands();
}
