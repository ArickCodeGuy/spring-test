package com.example.demo.Cashcard;

import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/cashcards")
public class CashcardController {  
  @Autowired
  CashcardRepository cashcardRepository;
  
  @GetMapping("/{requestedId}")
  private ResponseEntity<Cashcard> findById(@PathVariable Long requestedId, @AuthenticationPrincipal OAuth2User principal) {
    Optional<Cashcard> optionalCard = cashcardRepository.findById(requestedId);

    if (optionalCard.isEmpty()) 
      return ResponseEntity.notFound().build();

    Cashcard cashcard = optionalCard.get();

    if (!isUserCashcardOwner(cashcard, principal))
      return ResponseEntity.badRequest().build();

    return ResponseEntity.ok(optionalCard.get());
  }

  @PutMapping
  private ResponseEntity<Cashcard> create(@RequestBody double amount, @AuthenticationPrincipal OAuth2User principal) {
      Cashcard cashcard = new Cashcard(principal.getAttribute("sub"), amount);
      Cashcard savedCard = cashcardRepository.save(cashcard);

      return ResponseEntity.ok(savedCard);
  }

  @PostMapping("/{requestedId}")
  private ResponseEntity<Cashcard> update(@PathVariable Long requiestedId, @RequestBody Cashcard entity, @AuthenticationPrincipal OAuth2User principal) {
      ResponseEntity<Cashcard> responseEntity = findById(requiestedId, principal);

      if (responseEntity.getStatusCode() == HttpStatus.NOT_FOUND) 
        return ResponseEntity.notFound().build();

      Cashcard cashcard = responseEntity.getBody();

      if (!isUserCashcardOwner(cashcard, principal)) 
        return ResponseEntity.badRequest().build();

      // db call

      return ResponseEntity.ok(cashcard);
  }

  @DeleteMapping("/{requestedId}")
  private ResponseEntity<Void> delete(@PathVariable Long requiestedId, @AuthenticationPrincipal OAuth2User principal) {
      ResponseEntity<Cashcard> responseEntity = findById(requiestedId, principal);

      if (responseEntity.getStatusCode() == HttpStatus.NOT_FOUND) 
        return ResponseEntity.notFound().build();

      Cashcard cashcard = responseEntity.getBody();

      if (!isUserCashcardOwner(cashcard, principal)) 
        return ResponseEntity.badRequest().build();

      // db call

      return ResponseEntity.noContent().build();
  }

  private boolean isUserCashcardOwner(Cashcard cashcard, OAuth2User principal) {
    return principal.getAttribute("sub") == cashcard.getOwner();
  }
}
