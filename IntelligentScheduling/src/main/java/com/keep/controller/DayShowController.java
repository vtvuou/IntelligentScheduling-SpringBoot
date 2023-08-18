package com.keep.controller;


import com.keep.entity.arrange;
import com.keep.service.DayShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/study")
public class DayShowController {
    @Autowired
    private DayShow dayShow;

    @GetMapping("/daySelectServlet/{id}/{week}/{day}")
    public Map<String, List<arrange>> queryD(@PathVariable("id") String id,
                                             @PathVariable("week") int week ,
                                             @PathVariable("day") int day) {
        return dayShow.queryDay(id, week, day);
    }
}
