package br.com.fiap.fast_food_kitchen.repository;

import br.com.fiap.fast_food_kitchen.model.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KitchenRepository extends JpaRepository<Kitchen, Long> {
}
