package br.com.fiap.fast_food_kitchen.controller;

import br.com.fiap.fast_food_kitchen.dto.DemandKitchenRequest;
import br.com.fiap.fast_food_kitchen.dto.DemandKitchenResponse;
import br.com.fiap.fast_food_kitchen.dto.ProductKitchenRequest;
import br.com.fiap.fast_food_kitchen.enums.DemandStatus;
import br.com.fiap.fast_food_kitchen.usecase.IKitchenUsecase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Test
    void shouldGetAllDemandsSuccessfully() {
        // Given
        List<DemandKitchenResponse> products = new ArrayList<>();

        // When
        when(kitchenUsecase.getReadyDemands()).thenReturn(products);
        var demands = kitchenController.getAllDemands();

        // Then
        Assertions.assertNotNull(demands.getBody());
    }

}