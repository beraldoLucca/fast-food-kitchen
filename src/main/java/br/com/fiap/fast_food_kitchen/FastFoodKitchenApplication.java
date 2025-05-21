package br.com.fiap.fast_food_kitchen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FastFoodKitchenApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastFoodKitchenApplication.class, args);
	}

}
