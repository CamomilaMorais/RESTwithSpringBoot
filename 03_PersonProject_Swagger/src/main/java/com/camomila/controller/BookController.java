package com.camomila.controller;

import com.camomila.data.vo.v1.BookVO;
import com.camomila.services.BookServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Swagger Custom Annotation (Removal Allowed):
 *     other option -> @Api(value = "Book Endpoint", description = "Description for Book", tags = {"BookEndpoint"})
 */
@Api(tags = {"BookEndpoint"})
@RestController
@RequestMapping("api/book/v1")
public class BookController {

    @Autowired
    private BookServices service;

    /**
     * Swagger Custom Annotation (Removal Allowed):
     */
    @ApiOperation(value = "Find a specific Book by ID")
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public BookVO findById(@PathVariable("id") Long id) {
        BookVO bookVO = service.findById(id);
        bookVO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return bookVO;
    }

    /**
     * Swagger Custom Annotation (Removal Allowed):
     */
    @ApiOperation(value = "Find all Books")
    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public List<BookVO> findAll() {
        List<BookVO> booksVO = service.findAll();
        booksVO
                .stream()
                .forEach(
                        p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel())
                );
        return booksVO;
    }

    /**
     * Swagger Custom Annotation (Removal Allowed):
     */
    @ApiOperation(value = "Create a new Book")
    @PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public BookVO create(@RequestBody BookVO book) {
        BookVO bookVO = service.create(book);
        bookVO.add(linkTo(methodOn(BookController.class).findById(book.getKey())).withSelfRel());
        return bookVO;
    }

    /**
     * Swagger Custom Annotation (Removal Allowed):
     */
    @ApiOperation(value = "Update a Book by ID")
    @PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public BookVO update(@RequestBody BookVO book) {
        BookVO bookVO = service.update(book);
        bookVO.add(linkTo(methodOn(BookController.class).findById(book.getKey())).withSelfRel());
        return bookVO;
    }

    /**
     * Swagger Custom Annotation (Removal Allowed):
     */
    @ApiOperation(value = "Remove a Book by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
