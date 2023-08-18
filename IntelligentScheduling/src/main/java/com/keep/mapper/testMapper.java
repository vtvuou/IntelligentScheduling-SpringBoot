package com.keep.mapper;

import com.keep.entity.employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface testMapper {
    employee selectEmp(@Param("id")String id);
}
