package br.com.fiap.fast_food_kitchen.controller;

import br.com.fiap.fast_food_kitchen.dto.DemandKitchenRequest;
import br.com.fiap.fast_food_kitchen.dto.DemandKitchenResponse;
import br.com.fiap.fast_food_kitchen.usecase.IKitchenUsecase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kitchen")
public class KitchenController {

    private final IKitchenUsecase kitchenUsecase;

    public KitchenController(IKitchenUsecase kitchenUsecase) {
        this.kitchenUsecase = kitchenUsecase;
    }

    @PostMapping("/demand")
    public void sendDemand(@RequestBody DemandKitchenRequest request) {
        kitchenUsecase.saveDemand(request);
    }

    @GetMapping("/demands")
    public ResponseEntity<List<DemandKitchenResponse>> getAllDemands() {
        return ResponseEntity.ok(kitchenUsecase.getReadyDemands());
    }
}
