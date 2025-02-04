package com.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App 
{
    static final Logger log = LogManager.getLogger(App.class);
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        log.debug("debug");
        log.info("oinfo");log.warn("warn");log.error("error");
        log.fatal("fatal");

    }
}
