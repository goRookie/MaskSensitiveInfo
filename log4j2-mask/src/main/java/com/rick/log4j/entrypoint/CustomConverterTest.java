package com.rick.log4j.entrypoint;

import com.rick.log4j.pojo.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rick
 * E-mail:sophie_zelmani@163.com
 * @version 2021/8/30 14:04
 */
public class CustomConverterTest {
    private static final Marker SENSITIVE_DATA_MARKER = MarkerFactory.getMarker("SENSITIVE_DATA_MARKER");
    private static Logger logger = LoggerFactory.getLogger(CustomConverterTest.class);

    public static void main(String[] args) {

//        System.out.println("log4j2.enable.threadlocals = " + System.getProperty("log4j2.enable.threadlocals"));
        String cardNo = "4934-5552-4597-2245";

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
        for (int i = 0; i < 10000; i++) {
            logger.warn(SENSITIVE_DATA_MARKER, cardNo);
            logger.info(SENSITIVE_DATA_MARKER, "string :{}", cardNo);
            logger.info(SENSITIVE_DATA_MARKER, "map :{}", map);
            logger.info(SENSITIVE_DATA_MARKER, "object :{}", customer.toString());
            logger.info(SENSITIVE_DATA_MARKER, "date :{}", new Date());
            logger.error(SENSITIVE_DATA_MARKER, "Map:{}, String :{}", map, cardNo);
        }
        long end1 = System.currentTimeMillis();
        System.out.println("end1 - start1(marker) = " + (end1 - start1));
    }
}
