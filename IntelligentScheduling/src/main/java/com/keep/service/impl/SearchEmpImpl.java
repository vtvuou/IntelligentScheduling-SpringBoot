package com.keep.service.impl;

import com.keep.entity.Shop;
import com.keep.entity.employee;
import com.keep.mapper.UserMapper;
import com.keep.service.SearchEmp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SearchEmpImpl implements SearchEmp {
    @Autowired
    private UserMapper mapper;
    @Override
    public Map<String, employee> queryE(String id, String EnterId) {
        Shop shop = mapper.selectShop(EnterId);
        employee e = mapper.selectOneEmp(id, shop.getID());
        System.out.println(e);
        Map<String, employee> ep = new HashMap<>();
        ep.put("data", e);
        return ep;
    }
}
