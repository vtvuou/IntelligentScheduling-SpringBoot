package com.keep.service.impl;

import com.keep.entity.Shop;
import com.keep.mapper.UserMapper;
import com.keep.service.Init;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitImpl implements Init {
    @Autowired
    private UserMapper mapper;
    @Override
    public Shop InitData(String id) {
        return mapper.selectShop(id);
    }
}
