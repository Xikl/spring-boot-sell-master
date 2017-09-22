package com.ximo.springbootsellmaster;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by 朱文赵
 * 2017/9/9
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class LoggerTest {

    @Test
    public void test(){
        log.info("你好");
        log.error("错误");
    }



}
