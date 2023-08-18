package com.keep.service;

import com.keep.entity.arrange;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface ArrangeService {
    HashMap<String, List<List<arrange>>> queryArrange(String id, int week) throws IOException;
}
