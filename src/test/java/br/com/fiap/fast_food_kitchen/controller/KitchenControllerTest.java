package br.com.fiap.fast_food_kitchen.controller;

import br.com.fiap.fast_food_kitchen.dto.DemandKitchenRequest;
import br.com.fiap.fast_food_kitchen.dto.ProductKitchenRequest;
import br.com.fiap.fast_food_kitchen.enums.DemandStatus;
import br.com.fiap.fast_food_kitchen.usecase.IKitchenUsecase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.verify;

class KitchenControllerTest {

    @Mock
    private IKitchenUsecase kitchenUsecase;

    private KitchenController kitchenController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        kitchenController = new KitchenController(kitchenUsecase);
    }

    @Test
    void shouldSendDemandSuccessfully() {
        // Given
        List<ProductKitchenRequest> products = List.of(
                new ProductKitchenRequest("Cheeseburger")
        );
        DemandKitchenRequest request = new DemandKitchenRequest(1L, products, DemandStatus.EM_PREPARACAO);

        // When
        kitchenController.sendDemand(request);

        // Then
        verify(kitchenUsecase).saveDemand(request);
    }

}