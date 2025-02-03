package com.appdev.group3.ecollect;

import java.util.HashMap;
import java.util.Map;


public class WasteData {


    private static final Map<String, String> wasteCategoryMap = new HashMap<>();

    static {
        wasteCategoryMap.put("plastic bottle", "Recyclable");
        wasteCategoryMap.put("banana peel", "Biodegradable");
        wasteCategoryMap.put("glass", "Recyclable");
        wasteCategoryMap.put("paper", "Recyclable");
        wasteCategoryMap.put("battery", "Hazardous");
        wasteCategoryMap.put("old furniture", "Bulky Waste");
        wasteCategoryMap.put("food waste", "Biodegradable");
        wasteCategoryMap.put("syringe", "Hazardous");
    }

    public static String getCategory(String item) {
        return wasteCategoryMap.getOrDefault(item.toLowerCase(), "Unknown");
    }
}
