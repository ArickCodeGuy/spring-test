package com.example.demo.Cashcard;

import jakarta.persistence.Entity;

@Entity
public class Cashcard {
  long id;
  double amount;

  public Cashcard(long id, double amount) {
    this.id = id;
    this.amount = amount;
  }
  
  public long getId() {
    return this.id;
  }

  public double getAmount() {
    return this.amount;
  }
}
