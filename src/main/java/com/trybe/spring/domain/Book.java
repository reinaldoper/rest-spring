package com.trybe.spring.domain;

import java.util.UUID;

public class Book {
  private UUID id;

  private String name;

  private String author;

  /**
   * Método do desafio.
   * 
   */

  public Book(String name, String author) {
    /**
     * Método do desafio.
     * 
     */
    this.setId(UUID.randomUUID()); // toda nova instância terá um novo id
    this.setName(name);
    this.setAuthor(author);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

}

