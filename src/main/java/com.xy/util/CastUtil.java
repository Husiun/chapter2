package com.xy.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by issuser on 2017/9/24.
 * 类型转换工具类
 */
public class CastUtil {
    private static final Logger logger = LoggerFactory.getLogger(CastUtil.class);

    /**
     * 转为String
     * @param obj
     * @return
     */
    public static String castString(Object obj){
        return castString(obj,"");
    }

    public static String castString(Object obj,String defaultValue){
        return obj != null ? String.valueOf(obj):defaultValue;
    }

    /**
     * 转为int
     * @param obj
     * @return
     */
    public static int castInt(Object obj){
        return castInt(obj,0);
    }

    public static int castInt(Object obj,int defaultValue){
        int value = defaultValue;
        if(obj != null){
            String strValue = castString(obj);
            if(StringUtils.isNotEmpty(strValue)){
                try{
                    value = Integer.parseInt(strValue);
                }catch(Exception e){
                    value = defaultValue;
                }
            }
        }
        return value;
    }

    /**
     * 转为boolean
     * @param obj
     * @return
     */
    public static boolean castBoolean(Object obj){
        return castBoolean(obj,false);
    }

    public static boolean castBoolean(Object obj,boolean defaultValue){
        boolean value = defaultValue;
        if(obj != null){
            value = Boolean.parseBoolean(castString(obj));
        }
        return value;
    }

}
