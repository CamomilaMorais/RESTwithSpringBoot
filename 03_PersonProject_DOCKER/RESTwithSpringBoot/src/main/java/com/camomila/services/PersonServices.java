package com.camomila.services;

import com.camomila.converter.DozerConverter;
import com.camomila.data.vo.v1.PersonVO;
import com.camomila.exception.ResourceNotFoundException;
import com.camomila.data.model.Person;
import com.camomila.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonServices {

    @Autowired
    PersonRepository repository;

    public PersonVO create(PersonVO person) {
        var entity = DozerConverter.parseObject(person, Person.class);
        var vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
        return vo;
    }

    public PersonVO update(PersonVO person) {
        var entity = repository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity .setGender(person.getGender());
        var vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
        return vo;
    }

    /**
     * Notation de controlar a transação, visto que a manipulação abaixo não é padrão do Spring Data (responsável
     * automático pelas transações).
     */
    @Transactional
    public PersonVO disablePerson(Long id) {
        repository.disablePersons(id);
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));
        return DozerConverter.parseObject(entity, PersonVO.class);
    }

    public void delete(Long id) {
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));
        repository.delete(entity);
    }

    public PersonVO findById(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));
        return DozerConverter.parseObject(entity, PersonVO.class);
    }

    public Page<PersonVO> findPersonByName(String firstName, Pageable pageable) {
        var page = repository.findPersonByName(firstName, pageable);
        return page.map(this::convertToPersonVO);
    }

    public Page<PersonVO> findAll(Pageable pageable) {
        /**
         * O valor será do tipo List<Entity>
         * var entities = repository.findAll(pageable).getContent();
         */
        var page = repository.findAll(pageable);
        return page.map(this::convertToPersonVO);
    }

    private PersonVO convertToPersonVO(Person person) {
        return DozerConverter.parseObject(person, PersonVO.class);
    }
}
