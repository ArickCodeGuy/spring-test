package com.example.demo.Cashcard;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class CashcardController {  
  @GetMapping("/api/public/cashcards/{requestedId}")
  private ResponseEntity<Cashcard>  findById(@PathVariable Long requestedId) {
    Cashcard cashcard = new Cashcard(0,0);

    return ResponseEntity.ok(cashcard);
  }
}
