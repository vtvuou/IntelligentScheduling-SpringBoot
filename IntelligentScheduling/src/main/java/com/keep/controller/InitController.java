package com.keep.controller;

import com.keep.entity.Shop;
import com.keep.service.Init;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/study")
public class InitController {
    @Autowired
    private Init initImpl;

    @GetMapping("/initServlet/{id}")
    public Shop getInit(@PathVariable String id) {
        return initImpl.InitData(id);
    }
}
