package com.example.zzn.controller;
import com.example.zzn.dao.PersonRep;
import com.example.zzn.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private PersonRep personRep;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String desc() {
        return "UserController.class";
    }

    @GetMapping("/person")
    public List<Person> getPerson() {
        return personRep.findAll();
    }

    @GetMapping("/add")
    public Person addUser(@RequestParam(value = "name",defaultValue = "") String name,@RequestParam(value = "age",defaultValue = "0") Integer age) {
//        RequestParam("name") String name

        Person p = new Person();
        p.setAge(age);
        p.setName(name);

        return personRep.save(p);
    }
    @GetMapping("/remove/{id}")
    public boolean remove(@PathVariable("id") Long id) {

        personRep.deleteById(id);
        return true;
    }
}
