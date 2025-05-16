package br.com.fiap.fast_food_kitchen.repository;

import br.com.fiap.fast_food_kitchen.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
