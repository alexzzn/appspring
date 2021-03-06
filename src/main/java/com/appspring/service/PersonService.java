package com.appspring.service;

import com.appspring.dao.PersonRep;
import com.appspring.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import utils.ResultUtil;

@Service
public class PersonService {

    @Autowired
    private PersonRep personRep;

    public Object getAge(Integer id) throws Exception {

        Person p = personRep.getOne(id);
        if (p.getAge() < 18) {
           throw new Exception("age error");
        }

        return  ResultUtil.success(p);
    }
}
