package com.keep.service;

import java.util.HashMap;
import java.util.List;

public interface EmpTable {
    HashMap<String, List<HashMap<String, String>>> queryEmpTable(String id, String val1, String val2, String val3);
}
