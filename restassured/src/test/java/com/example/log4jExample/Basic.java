package com.example.log4jExample;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class Basic {
    
    static Logger logger = LogManager.getLogger(Basic.class);
    @Test
    public void firstMethod()
    {
        logger.debug("This is debug");
        logger.info("This is info");
        logger.warn("this is warn");
        logger.error("this is error ");
        logger.fatal("this is fatal");
    }

}
