package com.keep.service;

import com.keep.entity.arrange;

import java.util.List;
import java.util.Map;

public interface DayShow {
    Map<String, List<arrange>> queryDay(String id, int week, int day) ;
}
