package com.cloud.avaliation.minsait.controllers;

import com.cloud.avaliation.minsait.models.Product;
import com.cloud.avaliation.minsait.services.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping
  public ResponseEntity<List<Product>> getAllProducts() {
    List<Product> products = productService.getAllProducts();
    return ResponseEntity.ok(products);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable Long id) {
    Product product = productService
      .getProductById(id)
      .orElseThrow(() -> new RuntimeException("Product not found with id " + id)
      );
    return ResponseEntity.ok(product);
  }

  @PostMapping
  public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    Product createdProduct = productService.saveProduct(product);
    return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProduct(
    @PathVariable Long id,
    @RequestBody Product updatedProduct
  ) {
    Product existingProduct = productService
      .getProductById(id)
      .orElseThrow(() -> new RuntimeException("Product not found with id " + id)
      );

    existingProduct.setName(updatedProduct.getName());
    existingProduct.setPrice(updatedProduct.getPrice());

    Product savedProduct = productService.saveProduct(existingProduct);
    return ResponseEntity.ok(savedProduct);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
    return ResponseEntity.noContent().build();
  }
}
