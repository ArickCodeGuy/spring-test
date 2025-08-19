package com.example.demo.Cashcard;

import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/public/cashcards")
public class CashcardController {  
  @Autowired
  CashcardRepository cashcardRepository;
  
  @GetMapping("/{requestedId}")
  private ResponseEntity<Cashcard> findById(@PathVariable Long requestedId) {
    Optional<Cashcard> optionalCard = cashcardRepository.findById(requestedId);

    if (optionalCard.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(optionalCard.get());
  }

  @PutMapping
  private ResponseEntity<Cashcard> create(@RequestBody Cashcard entity) {
      // db call

      return ResponseEntity.ok(entity);
  }
  
  @PostMapping("/{requestedId}")
  private ResponseEntity<Cashcard> update(@PathVariable Long requiestedId, @RequestBody Cashcard entity) {
      ResponseEntity<Cashcard> e = findById(requiestedId);

      if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
        return ResponseEntity.notFound().build();
      }

      Cashcard cashcard = e.getBody();

      // db call

      return ResponseEntity.ok(cashcard);
  }

  @DeleteMapping("/{requestedId}")
  private ResponseEntity<String> delete(@PathVariable Long requiestedId) {
      ResponseEntity<Cashcard> e = findById(requiestedId);

      if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
        return ResponseEntity.notFound().build();
      }

      return ResponseEntity.ok("ok");
  }
}
