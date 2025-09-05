package com.example.demo.Cashcard;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Cashcard")
public class Cashcard {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")  
  long id;

  @NotNull
  @Column(name = "owner")
  String owner;
  
  @NotNull
  @Column(name = "amount")
  double amount;

  public Cashcard(String owner, double amount) {
    this.owner = owner;
    this.amount = amount;
  }

  public long getId() { return this.id; }
  public String getOwner() { return this.owner; }
  public double getAmount() { return this.amount; }
}
