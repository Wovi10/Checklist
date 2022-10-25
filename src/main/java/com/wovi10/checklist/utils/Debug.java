package com.wovi10.checklist.utils;

import java.io.FileNotFoundException;

public class Debug {
    public static void fileDebug_Print(DebugHelp helper, String location){
        if (helper.name().equals("To")){
            System.out.printf("Writing to: %s", location);
        } else if (helper.name().equals("From")) {
            System.out.printf("Reading from: %s", location);
        }
    }

    public static void fileNotFound_error_Print(FileNotFoundException error){
        System.out.println(error.getMessage());
        System.out.println(error.getCause());
        System.out.println(error.getStackTrace());
    }
}
