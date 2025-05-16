package br.com.fiap.fast_food_kitchen.model;

import br.com.fiap.fast_food_kitchen.enums.DemandStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Kitchen {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private Long orderId;

    @ManyToMany
    @JoinTable(
            name = "kitchen_product",
            joinColumns = @JoinColumn(name = "kitchen_id"),
            inverseJoinColumns = @JoinColumn(name = "product_name")
    )
    List<Product> products;

    @Enumerated(EnumType.STRING)
    DemandStatus status;

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setStatus(DemandStatus status) {
        this.status = status;
    }
}
