package com.trybe.spring.application;

import com.trybe.spring.domain.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import javax.websocket.server.PathParam;
import javax.ws.rs.core.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/books")
public class BookController {

  private List<Book> books = new ArrayList<>();

  /**
   * Método do desafio.
   * 
   */

  @GetMapping
  public Response findAll() {
    return Response.ok(books).build();
  }

  /**
   * Método do desafio.
   * 
   */

  @PostMapping
  public ResponseEntity<String> add(@RequestBody Book book) {
    books.add(book);
    String author = books.get(books.size() - 1).getAuthor();
    return ResponseEntity.ok(author);
  }

  /**
   * Método do desafio.
   * 
   */

  @PutMapping
  @RequestMapping("/{id}")
  public Response update(@PathParam("id") UUID id, @RequestBody Book book) {
    try {
      Book bookExistente = books.stream().filter(b -> b.getId().equals(id)).findAny().orElseThrow();
      if (bookExistente == null) {
        return Response.status(404).build();
      }
      bookExistente.setAuthor(book.getAuthor());
      bookExistente.setName(book.getName());
      books.add(bookExistente);
      return Response.ok(books.get(0)).build();

    } catch (NoSuchElementException e) {
      return Response.status(404).build();
    }
  }


  /**
   * Método do desafio.
   * 
   */
  @DeleteMapping
  @RequestMapping("/{id}")
  public Response remove(@PathParam("id") UUID id) {
    try {
      Book book = books.stream().filter(b -> b.getId().equals(id)).findAny().orElseThrow();
      if (book == null) {
        return Response.status(404).build();
      }
      books.remove(book);
      return Response.ok(books.size()).build();
    } catch (NoSuchElementException e) {
      return Response.status(404).build();
    }
  }

  /**
   * Método do desafio.
   * 
   */

  @GetMapping
  @RequestMapping("/{id}")
  public ResponseEntity<String> findById(@PathVariable("id") UUID id) {
    try {
      Book book = books.stream().filter(b -> b.getId().equals(id)).findAny().orElse(null);
      if (book == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      }
      return ResponseEntity.status(HttpStatus.OK).body(book.getName());
    } catch (NoSuchElementException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }
}
