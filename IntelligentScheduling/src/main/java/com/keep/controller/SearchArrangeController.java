package com.keep.controller;

import com.keep.entity.arrange;
import com.keep.service.SearchArrange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


//@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/study")
public class SearchArrangeController {
    @Autowired
    private SearchArrange searchArrange;

    @GetMapping("/searchArrangeServlet/{value}/{res}/{id}/{week}")
    public HashMap<String, List<List<arrange>>> querySearchArrange(
            @PathVariable("value") String value, @PathVariable("res") String res,
            @PathVariable("id")String id, @PathVariable int week
    ) {
        return searchArrange.querySA(value, res, id, week);
    }
}
