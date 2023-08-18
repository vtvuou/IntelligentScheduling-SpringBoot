package com.keep.service.impl;

import com.keep.entity.employee;
import com.keep.mapper.UserMapper;
import com.keep.service.Enter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class EnterImpl implements Enter {

    @Autowired
    private UserMapper mapper;
    private HashMap<String, String> s;
    @Override
    public Object getInformation(String flag, String username, String password) {
        employee obj;

        if(flag.equals("0")) {
            obj = mapper.selectOne(username, password);
            if(obj != null) {
                HashMap<String, String> HashMap = new HashMap<>();
                HashMap.put("ID", obj.getID());
                HashMap.put("name",obj.getEmpName());
                HashMap.put("job", obj.getJob());
                s = HashMap;
                return "true";
            }
            else {
                return "false";
            }
        }
        else {
            return s;
        }
    }
}
