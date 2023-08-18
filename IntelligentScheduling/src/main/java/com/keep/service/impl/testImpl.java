package com.keep.service.impl;

import com.keep.entity.employee;
import com.keep.mapper.testMapper;
import com.keep.service.test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class testImpl implements test {

    @Autowired
    private testMapper tm;
    @Override
    public employee selectEmp(String id) {
        return tm.selectEmp(id);
    }
}
