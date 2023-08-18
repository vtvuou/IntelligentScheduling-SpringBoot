package com.keep.service.impl;

import com.keep.entity.Shop;
import com.keep.entity.employee;
import com.keep.mapper.UserMapper;
import com.keep.service.EmpTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class EmpTableImpl implements EmpTable {
    @Autowired
    private UserMapper mapper;

    @Override
    public HashMap<String, List<HashMap<String, String>>> queryEmpTable(String id, String val1, String val2, String val3) {
        val3 = new String(val3.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        val2 = "%" + val2 + "%";
        val3 = "%" + val3 + "%";
        Shop shop = mapper.selectShop(id);
        List<employee> list = mapper.selectEmp(val1, val2, val3, shop.getID());
        HashMap<String,List<HashMap<String, String>>> mp = new HashMap<>();
        List<HashMap<String, String>> lt = new ArrayList<>();
        for(employee e : list) {
            HashMap<String, String> nw  = new HashMap<>();
            nw.put("id",e.getID());
            nw.put("name",e.getEmpName());
            nw.put("email", e.getEmail());
            nw.put("job",e.getJob());
            nw.put("shop", shop.getShopName());
            lt.add(nw);
        }
        mp.put("data",lt);
        return mp;
    }
}
