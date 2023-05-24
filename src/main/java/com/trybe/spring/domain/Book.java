package com.trybe.spring.domain;

import java.util.Objects;
import java.util.UUID;

/**
 * Class Book.
 *
 */

public class Book {
  private UUID id;
  private String name;
  private String author;

  /**
   * Construtor.
   */

  public Book() {
    this.id = UUID.randomUUID();
  }

  /**
   * Construtor.
   */

  public Book(String name, String author) {
    this.id = UUID.randomUUID(); // toda nova instância terá um novo id
    this.name = name;
    this.author = author;
  }

  /**
   * Método classe.
   */

  public UUID getId() {
    return id;
  }

  /**
   * Método classe.
   */

  public void setId(UUID id) {
    this.id = id;
  }

  /**
   * Método classe.
   */
  public String getName() {
    return name;
  }

  /**
   * Método classe.
   */

  public void setName(String name) {
    this.name = name;
  }

  /**
   * Método classe.
   */

  public String getAuthor() {
    return author;
  }

  /**
   * Método classe.
   */


  public void setAuthor(String author) {
    this.author = author;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Book other = (Book) obj;
    return Objects.equals(name, other.name);
  }

  @Override
  public String toString() {
    return "Book [name=" + name + "]";
  }

}
