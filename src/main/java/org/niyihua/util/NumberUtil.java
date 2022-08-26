package org.niyihua.util;

import cn.hutool.core.util.StrUtil;

import java.math.BigDecimal;

public class NumberUtil {

    //判断反数
    public static boolean isRevertNumber(BigDecimal num){
        if(num==null){
            return false;
        }
        String[] str = num.toString().split("\\.");
        if(str.length!=2){
            return false;
        }
        String revert = new StringBuilder(str[1]).reverse().toString();
        if(StrUtil.equals(str[0],revert)&&(!StrUtil.equals(str[0],str[1]))){
            return true;
        }
        return false;
    }


    //判断阶梯数
    public static boolean isStepNumber(BigDecimal num){
        if(num==null){
            return false;
        }
        String[] array = num.toString().split("\\.");
        if(array.length==2){
            int i0 = Integer.parseInt(array[0]);
            int i1 = Integer.parseInt(array[1]);
            if(i1>9&&i0>9&&(i1-i0)==1){
                return true;
            }
        }

        String str = num.toString().replace(".", "");
        if(str.length()<3){
            return false;
        }
        char[] chars = str.toCharArray();
        if(chars.length==3){
            for (int i = 1; i < chars.length; i++) {
                if((chars[i]-chars[i-1])!=1){
                    return false;
                }
            }
            return true;
        }
        for (int i = 2; i < chars.length; i++) {
            if((chars[i]-chars[i-1])!=1){
                return false;
            }
        }
        return true;
    }

    //todo 11.11  1.1  2.2 是叠字数吗？
    public static boolean isDDNumber(BigDecimal num){
        if(num==null){
            return false;
        }
        String[] str = num.toString().split("\\.");
        if(str.length==2){
            int a = Integer.parseInt(str[0]);
            int b = Integer.parseInt(str[1]);
            if(b==0){
                return false;
            }
            if(str[0].length()!=str[1].length()){
                return false;
            }
            int result = 11;
            for(int i =2;i<str[0].length();i++){
                int sum = 1;
                for (int j = 0; j < i; j++) {
                    sum*=10;
                }
                result+=sum;
            }
            if(a%result==0&&b%result==0){
                return true;
            }
        }
        return false;
    }

    //todo 对字数  a.a是对子数吗 还是必须 ab.ab才是
    public static boolean isDuiZiNumber(BigDecimal num){
        if(num==null){
            return false;
        }
        String[] str = num.toString().split("\\.");
        if(str.length==2&&str[0].length()==2&&StrUtil.equals(str[0],str[1])){
            return true;
        }
        return false;
    }

    //todo 倍数
    public static boolean isMutiNumber(BigDecimal num){
        if(num==null){
            return false;
        }
        String[] str = num.toString().split("\\.");
        if(str.length!=2){
            return false;
        }
        int i0 = Integer.parseInt(str[0]);
        if(i0==1){
            return false;
        }
        int i1 = Integer.parseInt(str[1]);
        if(i0==0||i1==0){
            return false;
        }
        if(i1<10&&(!str[1].startsWith("0"))){
            i1*=10;
        }
        if(i1%i0==0&&(i1/i0<9)){
            return true;
        }
        return false;
    }


}
