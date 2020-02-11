package com.water.neptune.common.dao.plus.ditc;


import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;

/**
 * Created by admin on 2017/11/23.
 */
public class DictionaryMap {
    public static boolean isInited = false;
    private static Object lock = new Object();
    private static Map<String, Map> dictMap = new HashMap();

    private static final Log log = LogFactory.getLog(DictionaryMap.class);
    static {
        init();
    }
    public static void init() {
        if (!isInited){
            synchronized (lock) {
                if (!isInited) {
                    System.out.println("开始将字典缓冲至内存");
                    DictInitalzation.XML2Memory();
                    System.out.println("字典已缓冲至内存");
                    DictInitalzation.LOCALXML2CACHE(dictMap);
                }
            }
        }
    }

    public static Map<String, Map> getDictMap() {
        return dictMap;
    }

    public static Map<String, String> getDictItemMap(String code) {
        Map<String, Map> dictMap = DictionaryMap.getDictMap();
        return dictMap.get(code);
    }

    public static String getDictItemName(String code, String itemcode) {
        return getDictItemMap(code).get(itemcode);
    }
}
