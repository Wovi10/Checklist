package com.wovi10.checklist.utils;

public class Debug {
    public static void fileDebug_Print(DebugHelp helper, String location){
        if (helper.toString().equals("To")){
            System.out.printf("Writing to: %s", location);
        }
    }
}
