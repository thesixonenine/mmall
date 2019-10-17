package com.simple.mmall;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
//@SpringBootTest
public class MmallApplicationTests {

    @Test
    public void contextLoads() {
        log.info("Hello, World");
        // print internal state
//        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
//        StatusPrinter.print(lc);
    }

    @Test
    public void testLogback() {
        log.error("Hello, World");
    }

}

