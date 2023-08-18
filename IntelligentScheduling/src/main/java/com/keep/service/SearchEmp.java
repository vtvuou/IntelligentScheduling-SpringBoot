package com.keep.service;

import com.keep.entity.employee;

import java.util.Map;

public interface SearchEmp {
    Map<String, employee> queryE(String id, String EnterId);
}
