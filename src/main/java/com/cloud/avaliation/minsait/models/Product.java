package com.cloud.avaliation.minsait.models;

import jakarta.persistence.*;

@Entity
@Table(name = "product") // Nome da tabela no banco de dados
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "price", nullable = false)
  private double price;

  // Construtor padrão vazio
  public Product() {}

  // Construtor com parâmetros, útil para a criação de instâncias
  public Product(String name, double price) {
    this.name = name;
    this.price = price;
  }

  // Getters e Setters

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }
}
