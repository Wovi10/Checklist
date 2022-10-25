package com.wovi10.checklist.utils;

public class Debug {
    public static void fileDebug_Print(DebugHelp helper, String location){
        if (helper.name().equals("To")){
            System.out.printf("Writing to: %s", location);
        } else if (helper.name().equals("From")) {
            System.out.printf("Reading from: %s", location);
        }
    }
}
