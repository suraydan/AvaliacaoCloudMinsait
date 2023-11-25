package com.cloud.avaliation.minsait.controllers;

import com.cloud.avaliation.minsait.models.Product;
import com.cloud.avaliation.minsait.services.ProductService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ProductService productService;

  //   @InjectMocks
  //   private ProductController productController;

  @Test
  public void testGetAllProducts() throws Exception {
    List<Product> productList = new ArrayList<>();
    // Adicione alguns produtos à lista

    Mockito.when(productService.getAllProducts()).thenReturn(productList);

    mockMvc
      .perform(
        MockMvcRequestBuilders
          .get("/products")
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(
        MockMvcResultMatchers.jsonPath("$.size()").value(productList.size())
      );
  }

  @Test
  public void testGetProductById() throws Exception {
    Long productId = 1L;
    Product product = new Product();
    product.setId(productId);
    product.setName("TestProduct");
    product.setPrice(19.99);

    Mockito
      .when(productService.getProductById(productId))
      .thenReturn(Optional.of(product));

    mockMvc
      .perform(
        MockMvcRequestBuilders
          .get("/products/{id}", productId)
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(productId));
  }
  // Adicione testes semelhantes para outros métodos do controlador (create, update, delete)
}
