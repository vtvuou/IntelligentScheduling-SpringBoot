package com.keep.service;

import com.keep.entity.arrange;

import java.util.HashMap;
import java.util.List;

public interface SearchArrange {
    HashMap<String, List<List<arrange>>> querySA(String value, String res, String id, int week);
}
