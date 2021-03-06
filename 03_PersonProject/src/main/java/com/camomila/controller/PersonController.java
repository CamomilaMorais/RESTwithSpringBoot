package com.camomila.controller;

import com.camomila.data.model.Person;
import com.camomila.data.vo.PersonVO;
import com.camomila.data.vo.v2.PersonVO_v2;
import com.camomila.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping("/{id}")
    public PersonVO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<PersonVO> findAll() {
        return service.findAll();
    }

    @PostMapping
    public PersonVO create(@RequestBody PersonVO person) {
        return service.create(person);
    }

    @PostMapping("/v2")
    public PersonVO_v2 create_v2(@RequestBody PersonVO_v2 person) {
        return service.create_v2(person);
    }

    @PutMapping
    public PersonVO update(@RequestBody PersonVO person) {
        return service.update(person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
