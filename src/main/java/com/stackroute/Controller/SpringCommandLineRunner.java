package com.stackroute.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
public class SpringCommandLineRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(SpringCommandLineRunner.class);
    @Override
    public void run(String...args) throws Exception {
        logger.info("Application started with commandline runner", Arrays.toString(args));
    }
}