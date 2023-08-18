package com.keep.service.impl;

import com.keep.entity.Shop;
import com.keep.entity.arrange;
import com.keep.mapper.UserMapper;
import com.keep.service.SearchArrange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SearchArrangeImpl implements SearchArrange {

    @Autowired
    private UserMapper mapper;
    @Override
    public HashMap<String, List<List<arrange>>> querySA(String value, String res, String id, int week) {

        Shop sp = mapper.selectShop(id);
        HashMap<String, List<List<arrange>>> searchRes = new HashMap<>();
        List<List<arrange>> weekSearch = new ArrayList<>();
        if(res.equals("1")) {
            for(int i = 1; i <= 7; i++) {
                List<arrange> dayS = mapper.selectArrangeById(sp.getID(), value, week, i);
                weekSearch.add(dayS);
            }
        }
        else if(res.equals("2")) {
            for(int i = 1 ;i <=7 ; i++) {
                value = "%" + value + "%";
                List<arrange> dayS = mapper.selectArrangeByJob(sp.getID(), value, week, i);
                weekSearch.add(dayS);
            }
        }
        int max = 0;
        for(List<arrange> arr: weekSearch) {
            max = Math.max(arr.size(), max);
        }
        for(List<arrange> arr : weekSearch) {
            int len = arr.size();
            for(int i = len; i < max ; i++) {
                arr.add(new arrange(-1, "pass", ".", ".", ".", ".", 0, 0, 0, 0));
            }
        }
        searchRes.put("data", weekSearch);
        return searchRes;
    }
}
