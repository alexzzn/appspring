package com.example.zzn.dao;

import com.example.zzn.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRep extends JpaRepository<Person,Long> {
}
