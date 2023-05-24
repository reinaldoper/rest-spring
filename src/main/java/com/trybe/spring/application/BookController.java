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
  private List<Book> books = new ArrayList<Book>();

  @POST
  @Consumes("application/json")
  @Produces("application/json")
  public Response add(@RequestBody Book book) {
    books.add(book);
    return Response.ok(book).build();
  }

  @GET
  @Consumes("application/json")
  @Produces("application/json")
  public Response findAll() {
    return Response.ok(books).build();
  }

  /**
   * Rota /id.
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

  /**
   * Método remove.
   *
   */

  @DELETE
  @Path("/{id}")
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
   * Método remove.
   *
   */

  /* @PUT
  @Consumes("application/json")
  @Produces("application/json")
  public Response update(@PathParam("id") UUID id, @RequestBody Book book) {
    try {
      Book bookExistente = books.stream().filter(b -> b.getName().equals(book.getName()))
          .findAny().orElseThrow();
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
  } */
}