package com.appspring.dao;

import com.appspring.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRep extends JpaRepository<Person,Long> {

    // 通过年龄查询
    public List<Person> findByAge(Integer age);
}
