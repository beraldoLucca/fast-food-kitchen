package br.com.fiap.fast_food_kitchen.usecase;

import br.com.fiap.fast_food_kitchen.dto.DemandKitchenRequest;
import br.com.fiap.fast_food_kitchen.dto.ProductKitchenRequest;
import br.com.fiap.fast_food_kitchen.enums.DemandStatus;
import br.com.fiap.fast_food_kitchen.model.Kitchen;
import br.com.fiap.fast_food_kitchen.model.Product;
import br.com.fiap.fast_food_kitchen.repository.KitchenRepository;
import br.com.fiap.fast_food_kitchen.repository.ProductRepository;
import br.com.fiap.fast_food_kitchen.usecase.impl.KitchenUsecaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class KitchenUsecaseImplTest {

    @Mock
    private KitchenRepository kitchenRepository;

    @Mock
    private ProductRepository productRepository;

    private KitchenUsecaseImpl kitchenUsecaseImpl;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        kitchenUsecaseImpl = new KitchenUsecaseImpl(kitchenRepository, productRepository);
    }

    @Test
    void shouldSaveDemandWithNewProducts() {

        List<ProductKitchenRequest> productRequests = List.of(new ProductKitchenRequest("Cheeseburger"));
        DemandKitchenRequest request = new DemandKitchenRequest(1L, productRequests, DemandStatus.EM_PREPARACAO);

        when(productRepository.findById(anyString())).thenReturn(Optional.empty());
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> {
            Product product = invocation.getArgument(0);
            return product;
        });


        kitchenUsecaseImpl.saveDemand(request);

        verify(productRepository, times(1)).findById(anyString());
        verify(productRepository, times(1)).save(any(Product.class));
        verify(kitchenRepository).save(any(Kitchen.class));

    }

    @Test
    void shouldSaveDemandWithExistingProducts() {

        List<ProductKitchenRequest> productRequests = List.of(new ProductKitchenRequest("Cheeseburger"));
        DemandKitchenRequest request = new DemandKitchenRequest(1L, productRequests, DemandStatus.EM_PREPARACAO);

        Product existingProduct = new Product();
        existingProduct.setName("Cheeseburger");

        when(productRepository.findById("Cheeseburger")).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(existingProduct);

        kitchenUsecaseImpl.saveDemand(request);

        verify(productRepository).findById("Cheeseburger");
        verify(kitchenRepository).save(any(Kitchen.class));
    }

}