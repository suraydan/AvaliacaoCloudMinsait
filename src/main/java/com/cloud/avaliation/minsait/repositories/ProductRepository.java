package com.cloud.avaliation.minsait.repositories;

import com.cloud.avaliation.minsait.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório para a entidade Product.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
  // Se necessário, você pode adicionar métodos personalizados aqui
  // Exemplo: List<Product> findByNome(String nome);
}
