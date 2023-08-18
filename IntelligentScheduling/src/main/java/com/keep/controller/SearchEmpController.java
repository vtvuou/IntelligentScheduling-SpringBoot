
package com.keep.controller;

import com.keep.entity.employee;
import com.keep.service.SearchEmp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/study")
public class SearchEmpController {
    @Autowired
    private SearchEmp searchEmp;

    @GetMapping("/searchEmpServlet/{id}/{EnterId}")
    public Map<String, employee> queryE(@PathVariable("id") String id,@PathVariable("EnterId") String EnterId) {
        return searchEmp.queryE(id, EnterId);
    }
}
