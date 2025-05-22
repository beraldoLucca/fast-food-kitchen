package br.com.fiap.fast_food_kitchen.usecase.impl;

import br.com.fiap.fast_food_kitchen.dto.DemandKitchenRequest;
import br.com.fiap.fast_food_kitchen.dto.DemandKitchenResponse;
import br.com.fiap.fast_food_kitchen.dto.ProductKitchenResponse;
import br.com.fiap.fast_food_kitchen.enums.DemandStatus;
import br.com.fiap.fast_food_kitchen.model.Kitchen;
import br.com.fiap.fast_food_kitchen.model.Product;
import br.com.fiap.fast_food_kitchen.repository.KitchenRepository;
import br.com.fiap.fast_food_kitchen.repository.ProductRepository;
import br.com.fiap.fast_food_kitchen.usecase.IKitchenUsecase;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class KitchenUsecaseImpl implements IKitchenUsecase {

    private final KitchenRepository kitchenRepository;

    private final ProductRepository productRepository;

    public KitchenUsecaseImpl(KitchenRepository kitchenRepository, ProductRepository productRepository) {
        this.kitchenRepository = kitchenRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public void saveDemand(DemandKitchenRequest demandRequest) {
        List<Product> products = new ArrayList<>();
        var entityProducts = demandRequest.products().stream().map(productKitchenRequest ->
            {Product product = new Product();
            product.setName(productKitchenRequest.name());
            return product;
        }).toList();

        entityProducts.forEach(product -> {
            var p = productRepository.findById(product.getName()).orElse(productRepository.save(product));
            products.add(p);
        });

        Kitchen kitchen = new Kitchen();
        kitchen.setOrderId(demandRequest.orderId());
        kitchen.setProducts(products);
        kitchen.setStatus(DemandStatus.EM_PREPARACAO);
        kitchenRepository.save(kitchen);
    }

    @Override
    @Scheduled(cron = "0 */2 * * * *")
    public void updateDemandStatus() {
        var kitchenDemands = kitchenRepository.findKitchenInPreparation();
        kitchenDemands.forEach(kitchen -> {
            kitchen.setStatus(DemandStatus.FINALIZADO);
            kitchenRepository.save(kitchen);
        });
    }

    @Override
    public List<DemandKitchenResponse> getReadyDemands() {
        var demands = kitchenRepository.findReadyKitchenDemands();
        var demandKitchenResponses = new ArrayList<DemandKitchenResponse>();
        demands.forEach(kitchen -> {
            List<ProductKitchenResponse> products = new ArrayList<>();
            kitchen.getProducts().forEach(product -> {
                products.add(new ProductKitchenResponse(product.getName()));
            });
            demandKitchenResponses.add(new DemandKitchenResponse(kitchen.getOrderId(), products, kitchen.getStatus().toString()));
        });
        return demandKitchenResponses;
    }
}
