package com.keep.controller;

import com.keep.entity.employee;
import com.keep.service.test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/get")
public class testEmp {
    @Autowired
    private test test;
    @GetMapping("/data/{id}")
    public employee getEmp(@PathVariable("id") String id) {
        return test.selectEmp(id);
    }
}
