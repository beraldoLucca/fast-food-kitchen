package br.com.fiap.fast_food_kitchen.usecase.impl;

import br.com.fiap.fast_food_kitchen.dto.DemandKitchenRequest;
import br.com.fiap.fast_food_kitchen.model.Kitchen;
import br.com.fiap.fast_food_kitchen.model.Product;
import br.com.fiap.fast_food_kitchen.repository.KitchenRepository;
import br.com.fiap.fast_food_kitchen.repository.ProductRepository;
import br.com.fiap.fast_food_kitchen.usecase.IKitchenUsecase;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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
        kitchen.setStatus(demandRequest.status());
        kitchenRepository.save(kitchen);
    }
}
