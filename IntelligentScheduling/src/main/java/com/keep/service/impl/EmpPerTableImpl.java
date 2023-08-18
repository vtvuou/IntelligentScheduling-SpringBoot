package com.keep.service.impl;

import com.alibaba.fastjson.JSON;
import com.keep.entity.Shop;
import com.keep.entity.empPre;
import com.keep.mapper.UserMapper;
import com.keep.service.EmpPerTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmpPerTableImpl implements EmpPerTable {

    @Autowired
    private UserMapper mapper;
    @Override
    public HashMap<String, List<HashMap<String, String>>> queryPer(String id, String val1) {
        Shop shop = mapper.selectShop(id);
        HashMap<String,List<HashMap<String, String>>> mp = new HashMap<>();
        List<HashMap<String, String>> lt = new ArrayList<>();
        List<empPre> list = mapper.selectEPre(val1, shop.getID());
        for(empPre pre : list) {
            HashMap<String, String>temp = new HashMap<>();
            temp.put("id", pre.getEmpId());
            Map dayPre = JSON.parseObject(pre.getDayPre());
            StringBuilder strTemp = new StringBuilder();
            boolean []vis = new boolean[10];
            for(int i = 0 ; i < 10 ; i ++) {
                vis[i] = true;
            }
            for(Object d : dayPre.keySet()) {
                Integer d1 = (Integer) dayPre.get(d);
                vis[d1] = false;
            }
            for(int i=1 ; i <= 7 ; i++) {
                if(vis[i]) {
                    strTemp.append(i).append(" ");
                }
            }
            temp.put("dayPre", strTemp.toString());
            Map TimePre = JSON.parseObject(pre.getTimePre());
            if (!TimePre.isEmpty()) {
                String sTime = TimePre.get("s1").toString();
                String eTime = TimePre.get("e1").toString();
                temp.put("startTime", sTime);
                temp.put("endTime", eTime);
            }
            else {
                temp.put("startTime", "-1");
                temp.put("endTime", "-1");
            }
            temp.put("groupPre", pre.getGroupPre().toString());
            temp.put("shop", shop.getShopName());
            lt.add(temp);
        }
        mp.put("data", lt);
        return mp;
    }
}
