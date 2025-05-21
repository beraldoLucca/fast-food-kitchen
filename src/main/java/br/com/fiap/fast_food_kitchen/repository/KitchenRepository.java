package br.com.fiap.fast_food_kitchen.repository;

import br.com.fiap.fast_food_kitchen.model.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KitchenRepository extends JpaRepository<Kitchen, Long> {

    @Query("""
            SELECT k FROM Kitchen k
            WHERE k.status = 'EM_PREPARACAO'
            ORDER BY k.id ASC
            LIMIT 2""")
    List<Kitchen> findKitchenInPreparation();

    @Query("""
            SELECT k FROM Kitchen k
            WHERE k.status = 'FINALIZADO'
            """)
    List<Kitchen> findReadyKitchenDemands();
}
