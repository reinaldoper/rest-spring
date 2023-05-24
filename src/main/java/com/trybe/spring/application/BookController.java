package com.trybe.spring.application;

import com.trybe.spring.domain.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@Path("/api/books")
public class BookController {
  private List<Book> books = new ArrayList<Book>();

  /**
   * Método classe.
   */

  @POST
  @Consumes("application/json")
  @Produces("application/json")
  public Response add(@RequestBody Book book) {
    books.add(book);
    return Response.ok(book).build();
  }

  /**
   * Método classe.
   */

  @GET
  @Consumes("application/json")
  @Produces("application/json")
  public Response findAll(@QueryParam("filter") String filter) {
    if (filter != null) {
      return Response.ok(books.stream().filter(b -> b.getName().contains(filter))).build();
    }
    return Response.ok(books).build();
  }

  /**
   * Método classe.
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
   * Método classe.
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
      return Response.ok(books.size()).build();
    } catch (NoSuchElementException e) {
      return Response.status(404).build();
    }
  }

  /**
   * Método remove.
   *
   */


  @PUT
  @Path("/{id}")
  @Consumes("application/json")
  @Produces("application/json")
  public Response update(@PathParam("id") UUID id, @RequestBody Book updatedBook) {
    try {
      Book book = books.stream().filter(b -> b.getId().equals(id)).findAny().orElseThrow();
      book.setName(updatedBook.getName());
      book.setAuthor(updatedBook.getAuthor());


      return Response.ok(book).build();
    } catch (NoSuchElementException e) {
      return Response.status(404).build();
    }
  }

}
