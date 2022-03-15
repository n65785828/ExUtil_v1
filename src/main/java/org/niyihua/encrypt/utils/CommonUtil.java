package org.niyihua.encrypt.utils;


import java.math.BigDecimal;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.text.NumberFormat;
import java.util.Enumeration;
import java.util.Random;
import java.util.UUID;


public class CommonUtil {

	public static String UUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static String removeEnd00s(String s){
		return s.replaceAll("((00)+)$", "");
	}

	/**
	 * 将金额分，转为元
	 */
    public static String toAmount(long amount) {
        return String.format("%.2f",new BigDecimal(amount).divide(new BigDecimal(100)));
    }

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将手机号中间4位变成*号
     */
    public static String hideMobile(String s){
    	return s.substring(0, 3) + "****" + s.substring(7);
    }

    public static String hideCid(String s){
        String regex = "(\\w{6})(\\w+)(\\w{4})";
        return s.replaceAll(regex,"$1******$3");
    }

    /**
     * 判断是否是邮箱
     */
    public static boolean isEmail(String s){
    	return s!=null && s.length()>=5 && s.indexOf("@")>0;
    }

    /**
     * 将邮箱第2位到@前的字符串变成*号
     */
    public static String hideEmail(String s){
    	int index = s.lastIndexOf("@");
    	return s.charAt(0)+getStarts(index-1)+s.substring(index);
    }

    private static String getStarts(int length){
    	String s="";
    	for(int i=0;i<length;i++)s+="*";
    	return s;
    }

    /**
     * 将第2位到最后的字符串前一位变成*号
     */
    public static String hideString(String s){
    	if(s==null || s.length()<=2)return s;
    	return s.substring(0, 1)+getStarts(s.length()-2)+s.substring(s.length()-1);
    }

    /**
     * 将数字前补0
     */
    public static String prefix0s(int i, int length){
    	String s=i+"";
    	if(s.length()>=length)return s;
    	return "000000000000000000000000000000000000".substring(0,length-s.length())+s;
    }

    /**
     * 字符串末尾补0至指定长度
     * @param str 字符串
     * @param length 字符串长度
     * @return
     */
    public static String suffix0s(String str, int length) {
        StringBuilder builder = new StringBuilder(str);
        while(builder.length() < length) {
            builder.append("0");
        }
        return builder.toString();
    }

    /**
	 * 保留小数点后几位
	 */
	public static float roundPoint(double d,int scale){
		return (float)((int)((d* Math.pow(10, scale)))/ Math.pow(10, scale));
	}

    /**
     * 生成小数点后几位的百分比数字
     * @return
     */
    public static String percentage(double b, int scale){
        NumberFormat fmt = NumberFormat.getPercentInstance();
        fmt.setMaximumFractionDigits(scale);//最多两位百分小数，如25.23%
        return fmt.format(b);
    }

    /**
	 * ** ** ** *** ***
	 * 省      市    县      街道    社区
	 * @param s
	 * @return
	 */
	public static int getArea12Level(String s){
		if(s.length()!=12)return 0;
		if(s.endsWith("0000000000"))return 1;
		if(s.endsWith("00000000"))return 2;
		if(s.endsWith("000000"))return 3;
		if(s.endsWith("000"))return 4;
		return 5;
	}

    /**
     * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0
     * @param version1
     * @param version2
     * @return
     */
    public static int compareVersion(String version1, String version2) {
        String[] versionArray1 = version1.split("\\.");//注意此处为正则匹配，不能用"."；
        String[] versionArray2 = version2.split("\\.");
        int idx = 0;
        int minLength = Math.min(versionArray1.length, versionArray2.length);//取最小长度值
        int diff = 0;
        while (idx < minLength
                && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0//先比较长度
                && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {//再比较字符
            ++idx;
        }
        //如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }

    /**
     * 根据数字生成相应数量的0字符串
     * @param num
     * @return
     */
    public static String zeroString(int num){
        if (num <= 0){
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < num; i++) {
            builder.append("0");
        }
        return builder.toString();
    }



    /**
     * 获取指定长度的随机字符，大写字母、小写字母、数字等概率出现
     * @param length
     * @return
     */
    public static String getRadomStr(int length){
        // 48-57  65-90   97-122
        Random random=new Random();
        String str="";
        for(int i=0;i<length;i++){
            int num=random.nextInt(62);
            if(num<=9)str+=num;
            else if(num<=35)str+=(char)(num+55);
            else str+=(char)(num+=61);
        }
        return str;
    }

    public static boolean instanceOf(Object o, String className){
        try {
            if(o == null) return false;
            if("Array".equals(className)){
                return o.getClass().isArray();
            }
            Class clazz = Class.forName(className);
            return clazz.isInstance(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 字节转换
     *
     * @param size 字节大小
     * @return 转换后值
     */
    public static String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
        if (size >= gb) {
            return String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else {
            return String.format("%d B", size);
        }
    }

    /**
     * 获取本地真正的IP地址，即获得有线或者WiFi地址。
     * 过滤虚拟机、蓝牙等地址
     */
    public static String getRealLocalHostIP() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            boolean isWindows = System.getProperties().getProperty("os.name").toLowerCase().contains("windows");
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                if (isWindows) {
                    if (!netInterface.getDisplayName().contains("Intel") && !netInterface.getDisplayName().contains("Realtek")) {
                        continue;
                    }
                }else {
                    if(netInterface.getName().contains("lo") || netInterface.getName().contains("docker")){
                        continue;
                    }
                }
                // 去除回环接口，子接口，未运行和接口
                if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
                    continue;
                }
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress ip = addresses.nextElement();
                    if (ip != null) {
                        // ipv4
                        if (ip instanceof Inet4Address) {
                            return ip.getHostAddress();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "127.0.0.1";
    }

    public static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
        }
        return "未知";
    }
}
