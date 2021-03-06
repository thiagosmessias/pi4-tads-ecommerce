package com.gruposet.ecommerce.helpers;

public class Messages {
    
    public static void writeError(String error) {
        System.out.println("[ERROR] " + error);
    }
    
    public static void writeWarning(String warning) {
        System.out.println("[WARNING] " + warning);
    }
    
    public static void writeInfo(String info) {
        System.out.println("[INFO] " + info);
    }
    
    public static void writeSuccess(String success) {
        System.out.println("[SUCCESS] " + success);
    }
    
    public static void customMessage(String message) {
        System.out.println(message);
    }
}
