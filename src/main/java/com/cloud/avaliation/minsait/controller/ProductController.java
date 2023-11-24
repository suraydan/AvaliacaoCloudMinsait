package com.cloud.avaliation.minsait.controller;

import com.cloud.avaliation.minsait.model.Product;
import com.cloud.avaliation.minsait.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping
  public List<Product> getAllProducts() {
    return productService.getAllProducts();
  }

  @GetMapping("/{id}")
  public Product getProductById(@PathVariable Long id) {
    return productService
      .getProductById(id)
      .orElseThrow(() -> new RuntimeException("Product not found with id " + id)
      );
  }

  @PostMapping
  public Product createProduct(@RequestBody Product product) {
    return productService.saveProduct(product);
  }

  @PutMapping("/{id}")
  public Product updateProduct(
    @PathVariable Long id,
    @RequestBody Product updatedProduct
  ) {
    Product existingProduct = productService
      .getProductById(id)
      .orElseThrow(() -> new RuntimeException("Product not found with id " + id)
      );

    existingProduct.setName(updatedProduct.getName());
    existingProduct.setPrice(updatedProduct.getPrice());

    return productService.saveProduct(existingProduct);
  }

  @DeleteMapping("/{id}")
  public void deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
  }
}
