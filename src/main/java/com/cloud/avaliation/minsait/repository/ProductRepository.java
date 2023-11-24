package com.cloud.avaliation.minsait.repository;

import com.cloud.avaliation.minsait.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
  // Métodos personalizados, se necessário
}
