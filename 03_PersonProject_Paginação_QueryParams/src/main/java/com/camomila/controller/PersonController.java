package com.camomila.controller;

import com.camomila.data.vo.v1.PersonVO;
import com.camomila.services.PersonServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Swagger Custom Annotation (Removal Allowed):
 *     other option -> @Api(value = "Person Endpoint", description = "Description for Person", tags = {"PersonEndpoint"})
 */
@Api(tags = {"PersonEndpoint"})
/**
 * Habilitar Cross Domain (Controller):
 * @CrossOrigin
 */
@RestController
@RequestMapping("api/person/v1")
public class PersonController {

    @Autowired
    private PersonServices service;

    /**
     * Swagger Custom Annotation (Removal Allowed):
     */
    @ApiOperation(value = "Find a specific Person by ID")
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public PersonVO findById(@PathVariable("id") Long id) {
        PersonVO personVO = service.findById(id);
        personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personVO;
    }
    /**
     * Swagger Custom Annotation (Removal Allowed):
     */
    @ApiOperation(value = "Find all Persons")
    /**
     * Habilitar Cross Domain (Endpoint):
     * @CrossOrigin
     *     Habilitar quais domains podem acessar o método através de Cross:
     *     @CrossOrigin(origin = {"http://localhost:8080", "http://www.example.com.br"})
     *         - No Postman, usar Header com Key "Origin" e Value igual à URL do domain que desejar.
     */
    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<PagedResources<PersonVO>> findAll(
            @RequestParam(value="page", defaultValue="0") int page,
            @RequestParam(value="limit", defaultValue="12") int limit,
            @RequestParam(value="direction", defaultValue="asc") String direction,
            PagedResourcesAssembler assembler) {

        var sortDirection = "desc".equalsIgnoreCase(direction)? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));

        Page<PersonVO> persons = service.findAll(pageable);
        persons
                .stream()
                .forEach(
                        p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel())
                );
        return new ResponseEntity<>(assembler.toResource(persons), HttpStatus.OK);
    }

    /**
     * Swagger Custom Annotation (Removal Allowed):
     */
    @ApiOperation(value = "Create a new Person")
    @PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public PersonVO create(@RequestBody PersonVO person) {
        PersonVO personVO = service.create(person);
        personVO.add(linkTo(methodOn(PersonController.class).findById(person.getKey())).withSelfRel());
        return personVO;
    }

    /**
     * Swagger Custom Annotation (Removal Allowed):
     */
    @ApiOperation(value = "Update a Person by ID")
    @PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public PersonVO update(@RequestBody PersonVO person) {
        PersonVO personVO = service.update(person);
        personVO.add(linkTo(methodOn(PersonController.class).findById(person.getKey())).withSelfRel());
        return personVO;
    }

    @ApiOperation(value = "Disable a specific Person by your ID")
    @PatchMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public PersonVO disablePerson(@PathVariable("id") Long id) {
        PersonVO personVO = service.disablePerson(id);
        personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personVO;
    }

    /**
     * Swagger Custom Annotation (Removal Allowed):
     */
    @ApiOperation(value = "Remove a Person by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
