package com.keep.mapper;


import com.keep.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface UserMapper {

    employee selectOne(@Param("number") String number, @Param("pw") String pw);

    List<arrange> selectArrange(@Param("w")int w, @Param("d")int d, @Param("id")String id);

    Shop selectShop(@Param("id") String id);

    String selectShopId(@Param("id") String id);

    List<employee> selectEmp(@Param("val1")String val1,@Param("val2")String val2, @Param("val3")String val3, @Param("id")String id);

    void deleteArrange();

    void insertTbUser(List<HashMap<String,String>> list);

    void insertArrange(List<List<List<arrange>>> list);

    List<employee> selectEmpShop(@Param("id")String id);

    List<shopFlow> selectSF(@Param("id") String id, @Param("w") int w, @Param("d") int d);

    List<employee> selectEmployeeData(@Param("id") String id);

    Rule selectRule(@Param("id") String id);

    List<empPre> selectEmpPre(@Param("id") String id);

    String judgeArranged(@Param("id") String id);

    void updateArranged(@Param("id") String id, @Param("flag") String flag);

    List<arrange> selectDayArrange(@Param("id") String id, @Param("w") int w, @Param("d") int d);

    List<arrange> selectArrangeById(@Param("shopId")String shopId, @Param("id") String id, @Param("w") int w, @Param("d") int d);

    List<arrange> selectArrangeByJob(@Param("shopId") String shopId, @Param("job") String job, @Param("w") int w,@Param("d") int d);

    employee selectOneEmp(@Param("id")String id, @Param("enterId")String enterId);

    void updateArrange(@Param("index") int index, @Param("id")String id, @Param("name")String name, @Param("job")String job, @Param("task")String task);

    List<empPre> selectEPre(@Param("val1")String val1, @Param("shopId")String shopId);
}















