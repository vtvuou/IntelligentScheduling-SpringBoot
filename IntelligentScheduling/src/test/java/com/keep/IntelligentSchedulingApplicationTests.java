package com.keep;

import com.keep.entity.employee;
import com.keep.service.test;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class IntelligentSchedulingApplicationTests {

    @Autowired
    private test test;
    @Test
    void contextLoads() {
        employee employee = test.selectEmp("8001");
        log.info("{}", employee);
    }

}
