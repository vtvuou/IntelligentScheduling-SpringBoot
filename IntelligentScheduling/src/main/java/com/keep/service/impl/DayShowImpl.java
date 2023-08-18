package com.keep.service.impl;

import com.keep.entity.Shop;
import com.keep.entity.arrange;
import com.keep.mapper.UserMapper;
import com.keep.service.DayShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class DayShowImpl implements DayShow {
    @Autowired
    private UserMapper mapper;

    @Override
    public Map<String, List<arrange>> queryDay(String s, int week, int day) {
        Shop sp = mapper.selectShop(s);
        Map<String, List<arrange>> needArrange = new HashMap<>();
        List<arrange> dayArr = mapper.selectDayArrange(sp.getID(), week, day);
        needArrange.put("data", dayArr);
        return needArrange ;
    }
}
