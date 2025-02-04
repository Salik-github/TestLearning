package com.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    static Logger log = LogManager.getLogger(AppTest.class);
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
        log.info("oinfo");log.warn("warn");
    }
}
