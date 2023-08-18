package com.keep.controller;

import com.keep.service.Enter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/study")
@Slf4j
public class EnterController {

    @Autowired
    private Enter EnterImpl;
    @GetMapping("/enterServlet/{flag}/{username}/{password}")
    public Object getEnter(@PathVariable("flag") String flag, @PathVariable("username") String username, @PathVariable("password") String password) {
        log.info("{}, {}", flag, username);
        return EnterImpl.getInformation(flag, username, password);
    }
}
