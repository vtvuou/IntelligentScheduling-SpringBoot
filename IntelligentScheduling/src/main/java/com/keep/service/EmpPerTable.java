package com.keep.service;

import java.util.HashMap;
import java.util.List;

public interface EmpPerTable {
    HashMap<String, List<HashMap<String, String>>> queryPer(String id, String val);
}
