package com.keep.controller;


import com.keep.service.EmpPerTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

//@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/study")
@Slf4j
public class EmpPerTableController {
    @Autowired
    private EmpPerTable epl;

    @GetMapping("/empPreServlet/{id}/{val}")
    public HashMap<String, List<HashMap<String, String>>> queryEmpPer(@PathVariable("id") String id,@PathVariable("val") String val) {
        val = val.substring(0, val.length() - 1);
        log.info("{} a", val);
        return epl.queryPer(id, val);
    }
}
