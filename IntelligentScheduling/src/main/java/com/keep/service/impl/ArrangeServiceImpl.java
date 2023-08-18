package com.keep.service.impl;

import com.keep.entity.Shop;
import com.keep.entity.arrange;
import com.keep.entity.employee;
import com.keep.mapper.UserMapper;
import com.keep.service.ArrangeService;
import com.keep.utils.inherAlg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ArrangeServiceImpl implements ArrangeService {
    @Autowired
    private UserMapper mapper;
    @Autowired
    private inherAlg inherAlg;
    @Override
    public HashMap<String, List<List<arrange>>> queryArrange(String id, int week) {
        Shop sp = mapper.selectShop(id);
        String flag = mapper.judgeArranged(sp.getID());

        if(flag.equals("false")) {
            employee ep = new employee(id,".",".",".",".",sp.getID());
            inherAlg.algorithm(ep);
        }
        HashMap<String, List<List<arrange>>> needArrange = new HashMap<>();
        List<List<arrange>> weekArrange = new ArrayList<>();
        for(int d = 1; d <=7 ;d++) {
            List<arrange> day = mapper.selectArrange(week, d, sp.getID());
            weekArrange.add(day);
        }

        //格式渲染
        int maxOfDay = 0;
        for (List<arrange> arranges : weekArrange) {
            maxOfDay = Math.max(maxOfDay, arranges.size());
        }
        int d = 0;
        for(List<arrange> arranges : weekArrange) {
            ++d;
            int arrSize = arranges.size();
            for(int i = arrSize; i < maxOfDay; i++) {
                arranges.add(new arrange(-1, "pass", "---", "---", "下班啦", sp.getID(), 0 , 0, week, d));
            }
        }
        needArrange.put("data", weekArrange);
        return needArrange;
    }
}
