package com.example.demo.Cashcard;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

@JsonTest
public class CashcardJsonTest {
  
  @Autowired
  private JacksonTester<Cashcard> json;
  
  @Test
  void cashCardSerializationTest() throws IOException {
    Cashcard card = new Cashcard("Test",2);

    assertThat(json.write(card)).isStrictlyEqualToJson("expected.json");
  }
}
