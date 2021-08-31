package com.rick.log4j.entrypoint;

import com.rick.log4j.pojo.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rick
 * E-mail:sophie_zelmani@163.com
 * @version 2021/8/26 9:09
 */
public class CustomLogEventTest {
    private static Logger log = LoggerFactory.getLogger(CustomLogEventTest.class);

    public static void main(String[] args) {
//        System.setProperty("log4j2.enable.threadlocals", "false");

        String cardNo = "4934-5522-4597-2245";
        Customer customer = new Customer();
        customer.setName("rick");
        customer.setCreditCardNo(cardNo);

        customer.setAge(30);
        customer.setCreditCardPassword(cardNo);

        Map<String, Object> map = new HashMap<>();
        map.put("phoneNo", 110);
        map.put("customer", customer);
        map.put("uid", cardNo);

        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 1; i++) {
            log.warn(cardNo);
            log.info("string :{}", cardNo);
            log.info("map :{}", map);
            log.info("object :{}", customer.toString());
            log.info("date :{}", new Date());
            log.error("Map:{}, String :{}", map, cardNo);
        }
        long end1 = System.currentTimeMillis();
        System.out.println("end1 - start1(logEventTest) = " + (end1 - start1));
    }

}
