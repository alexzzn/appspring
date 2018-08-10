package com.appspring.controller;
import com.appspring.dao.PersonRep;
import com.appspring.model.Person;
import com.appspring.model.Result;
import com.appspring.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import utils.ResultUtil;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {


    @Value("${version}")
    private String version;
    @Autowired
    private PersonRep personRep;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String desc() {
        return "UserController.class " + version;
    }

    @GetMapping("/person")
    public Object getPerson() {

        Object o = ResultUtil.success(personRep.findAll());
        return o;
    }

    @GetMapping("/person/{id}")
    public Object getPersonById(@RequestParam("id") Long id) throws Exception {

        PersonService p = new PersonService();
        return p.getAge(id);
    }

    @GetMapping("/add")
    public Person addUser(@RequestParam(value = "name",defaultValue = "") String name,@RequestParam(value = "age",defaultValue = "0") Integer age) {
        //        RequestParam("name") String name

        Person p = new Person();
        p.setAge(age);
        p.setName(name);

        return personRep.save(p);
    }

    // 表单验证
    @GetMapping("/addobj")
    public Person addObject(@Valid Person person, BindingResult result) {

        if (result.hasErrors()) {
            System.out.println(result.getFieldError());
            return null;
        }
        Person p = new Person();
        p.setAge(person.getAge());
        p.setName(person.getName());

        return personRep.save(p);
    }

    @GetMapping("/remove/{id}")
    public boolean remove(@PathVariable("id") Long id) {

        personRep.deleteById(id);
        return true;
    }

    @GetMapping("/age/{id}")
    public boolean searchAge(@PathVariable("age") Integer age) {

        personRep.findByAge(age);
        return true;
    }

    // 事务
    @Transactional
    @GetMapping("/test/transaction")
    public void transAction() {

        Person p1 = new Person();
        Person p2 = new Person();

        p1.setName("xiaoA");
        p1.setAge(99);

        p2.setName("xiaoN");
        p2.setAge(89);

        personRep.save(p1);
        personRep.save(p2);
    }
}
