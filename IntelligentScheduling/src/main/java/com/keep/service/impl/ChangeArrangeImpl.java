package com.keep.service.impl;

import com.keep.mapper.UserMapper;
import com.keep.service.ChangeArrange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class ChangeArrangeImpl implements ChangeArrange {
    @Autowired
    private UserMapper mapper;
    @Override
    public void updateArrange(int idx, String id, String name, String job, String task) {
        job = new String(job.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        task = new String(task.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        mapper.updateArrange(idx, id, name, job, task);
    }
}
