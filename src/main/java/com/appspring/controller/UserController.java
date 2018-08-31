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


@CrossOrigin(origins = {"http://localhost:3000", "null"})
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

    // 获取所有成员
    @PostMapping("/person")
    public Object getPerson() {

        Object o = ResultUtil.success(personRep.findAll());
        return o;
    }
    // 获取单个成员
    @PostMapping("/onePerson")
    public Object getPersonById(@RequestParam("id") Integer id) throws Exception {

        Object p = personRep.findById(id);
        if (p == null) {

            return  ResultUtil.error("没有数据");
        }
        return ResultUtil.success(p);
    }

    // 通过age获取单个成员
    @PostMapping("/onePersonByAge/{age}")
    public Object searchAge(@PathVariable("age") Integer age) {

        return ResultUtil.success(personRep.findByAge(age));
    }
    // 增加成员
    @PostMapping("/add")
    public Object addUser(

            @RequestParam("age") Integer age,
            @RequestParam("name") String name,
            @RequestParam("adress") String adress) {

        Person p = new Person();
        if (age ==null || name == null || adress == null) {

            return ResultUtil.error("文本不能为空");
        }

        p.setAge(age);
        p.setName(name);
        p.setAddress(adress);

        try {

            personRep.save(p);
            return ResultUtil.success(p);
        } catch (Exception e) {

            return ResultUtil.success(e.getMessage());
        }



    }
    // 删除某个成员
    @PostMapping("/remove")
    public Object remove(@RequestParam(value = "cusId") Integer cusId) {

        try {
            personRep.deleteById(cusId);
            return ResultUtil.success("true");
        } catch (Exception e) {

            return ResultUtil.error("false");
        }
    }


    // 表单验证
    @PostMapping("/addVerify")
    public Person addObject(@Valid Person person, BindingResult result) {

        if (result.hasErrors()) {
            System.out.println(result.getFieldError());
            return null;
        }

        Person p = new Person();
        p.setAge(person.getAge());
        p.setName(person.getName());
        p.setAddress("Zhengjiang Hangzhou");

        return personRep.save(p);
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
