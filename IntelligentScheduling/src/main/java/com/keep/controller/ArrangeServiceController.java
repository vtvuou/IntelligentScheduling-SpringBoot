package com.keep.controller;

import com.keep.entity.arrange;
import com.keep.service.ArrangeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/study")
@Slf4j
public class ArrangeServiceController {
    @Autowired
    private ArrangeService as;
    @GetMapping("/arrangeServiceServlet/{id}/{week}")
    public HashMap<String, List<List<arrange>>> queryArrangeService(@PathVariable("id") String id, @PathVariable("week") int week) throws IOException {
        log.info("{}, {}", id, week);
        return as.queryArrange(id, week);
    }
}
