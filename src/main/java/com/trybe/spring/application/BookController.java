package com.trybe.spring.application;

import com.trybe.spring.domain.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Path("/api/books")

public class BookController {

  private List<Book> books = new ArrayList<>();

  /**
   * Método do desafio.
   * 
   */

  @POST
  @Consumes("application/json") // tipo de dado que é consumido
  @Produces("application/json") // tipo de dado enviado como resposta
  public Response add(Book book) {
    books.add(book);
    return Response.ok(book).build();
  }

  /**
   * Método do desafio.
   * 
   */

  @PUT
  @Path("/{id}")
  @Consumes("application/json") // tipo de dado que é consumido
  @Produces("application/json") // tipo de dado enviado como resposta
  public Response update(@PathParam("id") UUID id, @RequestBody Book book) {
    try {
      Book bookExistente = books.stream().filter(b -> b.getId().equals(id)).findAny().orElseThrow();
      if (bookExistente == null) {
        return Response.status(404).build();
      }
      bookExistente.setAuthor(book.getAuthor());
      bookExistente.setName(book.getName());
      books.add(bookExistente);
      return Response.status(200).build();

    } catch (NoSuchElementException e) {
      return Response.status(404).build();
    }
  }


  /**
   * Método do desafio.
   * 
   */
  @DELETE
  @Path("/{id}")
  @Consumes("application/json")
  @Produces("application/json")
  public Response remove(@PathParam("id") UUID id) {
    try {
      Book book = books.stream().filter(b -> b.getId().equals(id)).findAny().orElseThrow();
      books.remove(book);
      return Response.status(200).build();
    } catch (NoSuchElementException e) {
      return Response.status(404).build();
    }
  }

  /**
   * Método do desafio.
   * 
   */

  @GET
  @Consumes("application/json")
  @Produces("application/json")
  public Response findAll() {
    return Response.ok(books).build();
  }

  /**
   * Método do desafio.
   * 
   */

  @GET
  @Path("/{id}")
  @Consumes("application/json")
  @Produces("application/json")
  public Response findById(@PathParam("id") UUID id) {
    try {
      Book book = books.stream().filter(b -> b.getId().equals(id)).findAny().orElseThrow();
      return Response.ok(book).build();
    } catch (NoSuchElementException e) {
      return Response.status(404).build();
    }
  }
}
