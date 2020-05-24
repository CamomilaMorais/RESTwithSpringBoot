package com.camomila.converter.custom;

import com.camomila.data.model.Person;
import com.camomila.data.vo.PersonVO;
import com.camomila.data.vo.v2.PersonVO_v2;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonConverter {

    public PersonVO_v2 converterEntityToVO(Person person) {
        PersonVO_v2 vo = new PersonVO_v2();
        vo.setId(person.getId());
        vo.setAddress(person.getAddress());
        vo.setBirthday(new Date());
        vo.setFirstName(person.getFirstName());
        vo.setGender(person.getGender());
        vo.setLastName(person.getLastName());
        return vo;
    }

    public Person converterVOToEntity(PersonVO_v2 person) {
        Person entity = new Person();
        entity.setId(person.getId());
        entity.setAddress(person.getAddress());
        entity.setFirstName(person.getFirstName());
        entity.setGender(person.getGender());
        entity.setLastName(person.getLastName());
        return entity;
    }
}
