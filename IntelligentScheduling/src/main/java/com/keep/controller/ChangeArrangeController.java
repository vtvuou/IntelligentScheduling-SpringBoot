package com.keep.controller;

import com.keep.service.ChangeArrange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/study")
public class ChangeArrangeController {
    @Autowired
    private ChangeArrange changeArrange;

    @GetMapping("/changeArrangeServlet/{idx}/{id}/{name}/{job}/{task}")
    public void updateA(@PathVariable("idx") int idx,
                        @PathVariable("id") String id,
                        @PathVariable("name") String name ,
                        @PathVariable("job") String job,
                        @PathVariable("task") String task) {
        changeArrange.updateArrange(idx, id, name, task , job);
    }
}
