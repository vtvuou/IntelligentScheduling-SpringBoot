package com.keep.controller;

import com.keep.service.EmpTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

//@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/study")
@Slf4j
public class EmpTableController {
    @Autowired
    EmpTable empTable;
    @GetMapping("/empTableServlet/{id}/{val1}/{val2}/{val3}")
    public HashMap<String, List<HashMap<String, String>>> queryEmpT(
            @PathVariable("id") String id,
            @PathVariable("val1") String val1,
            @PathVariable("val2") String val2,
            @PathVariable("val3") String val3
    ) {
        val1 = val1.substring(0, val1.length() - 1);
        val2 = val2.substring(0, val2.length() - 1);
        val3 = val3.substring(0, val3.length() - 1);
        log.info("{}, {}, {} a", val1, val2, val3);
        return empTable.queryEmpTable(id, val1, val2,val3);
    }
}
