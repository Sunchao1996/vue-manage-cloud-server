package com.sc.util.string;

import com.sc.util.date.DateUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用户字符串操作，这里面包括字符串的decode、encode、substrac等等操作
 *
 * @author 孔垂云
 * @date 2017-05-23
 */
public class StringUtil {
    private final static String[] agent = {"Android", "iPhone", "iPod", "iPad", "Windows Phone", "MQQBrowser"}; // 定义移动端请求的所有可能类型

    /**
     * 把前台传过来的含中文的url字符串转换成标准中文，比如%25E5%258C%2597%25E4%25BA%25AC转换成北京
     *
     * @param url url字符串
     * @return string
     */
    public static String decodeUrl(String url) {
        if (url == null)
            return "";
        String str = "";
        try {
            str = URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 把比如北京转换成%25E5%258C%2597%25E4%25BA%25AC
     *
     * @param url url字符串
     * @return string
     */
    public static String encodeUrl(String url) {
        if (url == null)
            return "";
        String str = "";
        try {
            str = URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 取字符除最后一位的子串，比如 aaa,bbb, 返回aaa,bbb，一般用在多个字段进行拼接，要去除最后一位
     *
     * @param str 字符串
     * @return string
     */
    public static String subTract(String str) {
        if (str.length() == 0)
            return "";
        else
            return str.substring(0, str.length() - 1);
    }

    /**
     * 判断字符串是null或空，null或""都返回true
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean isNullOrEmpty(String str) {
        if (str == null || str.equals(""))
            return true;
        else
            return false;
    }

    /**
     * 判断字符串不是null或空
     *
     * @param str 字符串
     * @return
     */
    public static boolean isNotNullOrEmpty(String str) {
        if (str != null && !str.equals(""))
            return true;
        else
            return false;
    }

    /**
     * 判断是否是ajax请求，用于进行权限控制或异常处理时，得判断是否是ajax请求
     *
     * @param request
     * @return
     */
    public static boolean checkAjaxRequest(HttpServletRequest request) {
        String requestType = request.getHeader("X-Requested-With");
        if (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest")) {
            return true;
        } else
            return false;
    }

    /**
     * 获取客户端请求的ip地址，可以跳过代理等直接获取
     *
     * @param request
     * @return
     */
    public static String getIp(HttpServletRequest request) {
        String szClientIP = request.getHeader("x-forwarded-for");
        if (szClientIP == null || szClientIP.length() == 0 || "unknown".equalsIgnoreCase(szClientIP))
            szClientIP = request.getHeader("Proxy-Client-IP");
        else if (szClientIP == null || szClientIP.length() == 0 || "unknown".equalsIgnoreCase(szClientIP))
            szClientIP = request.getHeader("WL-Proxy-Client-IP");
        else if (szClientIP == null || szClientIP.length() == 0 || "unknown".equalsIgnoreCase(szClientIP))
            szClientIP = request.getRemoteAddr();
        if (szClientIP == null || szClientIP.equals("0:0:0:0:0:0:0:1"))
            szClientIP = "127.0.0.1";
        return szClientIP;
    }

    /**
     * 过滤表情，在移动开发中，有些字符是表情等特殊字符，数据库不识别，需要过滤掉， 替换为*
     *
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {
        if (StringUtil.isNotNullOrEmpty(source)) {
            return source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "*");
        } else {
            return source;
        }
    }

    /**
     * 获取url地址，整个请求地址，不包含?后面的参数信息，如果最后一位后缀为#，去掉
     *
     * @param request 请求
     * @return url地址
     */
    public static String getUrlPath(HttpServletRequest request) {
        String url;
        if (request.getServerPort() == 80) {
            url = request.getScheme() + "://" + request.getServerName() + request.getRequestURI();
        } else {
            url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + request.getRequestURI();
        }
        if (url.contains("#")) {
            url = url.substring(0, url.indexOf("#"));
        }
        return url;
    }

    /**
     * 判断是否是手机号
     *
     * @param mobile
     * @return
     */
    public static boolean isMobile(String mobile) {
        Pattern p = Pattern.compile("^1[3-9]\\d{9}$");
        Matcher m = p.matcher(mobile);
        boolean b = m.matches();
        return b;
    }

    /**
     * 获取Request的参数，并将其"Key=Value&Key=Value"的格式返回
     *
     * @param request 请求
     * @return "Key=Value&Key=Value"格式的字符串
     */
    public static String getOperaParams(HttpServletRequest request) {
        String parameters = "";// 定义所有参数值
        Map<String, String[]> map = request.getParameterMap();
        // /取得所有参数值，用&号组装起来
        Object[] obj = null;
        obj = map.keySet().toArray();
        for (int i = 0; i < obj.length; i++) {
            parameters += obj[i].toString() + "=" + request.getParameter(obj[i].toString()) + "&";
        }
        return parameters;
    }

    /**
     * 元转换成分
     *
     * @param amount
     * @return
     */
    public static int getMoney(float amount) {
        Float f = new Float(Math.round(amount * 100));
        int fen = f.intValue();
        return fen;
    }

    /**
     * 元转换成分
     *
     * @param amount
     * @return
     */
    public static float getActualMoney(int amount) {
        Float f = new Float(amount * 1.0 / 100);
        return f;
    }


    /**
     * 去掉小数点后面的0，导入数据的时候用到
     *
     * @param val
     * @return
     */
    public static String formatNumber(String val) {
        val = val.replaceAll("0+?$", "");// 去掉后面无用的零
        val = val.replaceAll("[.]$", "");// 如小数点后面全是零则去掉小数点
        return val;
    }

    /**
     * 判断是否为yyyy-MM-DD格式的日期字符串
     *
     * @param dateStr
     * @return
     */
    public static boolean checkDateFormat(String dateStr) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-DD");
        try {
            @SuppressWarnings("unused")
            Date date = simpleDateFormat.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * 将Map中的数据转换成key1=value1&key2=value2的形式 不包含签名域signature
     *
     * @param data 待拼接的Map数据
     * @return 拼接好后的字符串
     */
    public static String coverMap2String(Map<String, String> data) {
        TreeMap<String, String> tree = new TreeMap<String, String>();
        Iterator<Map.Entry<String, String>> it = data.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> en = it.next();
            if ("signature".equals(en.getKey().trim())) {
                continue;
            }
            tree.put(en.getKey(), en.getValue());
        }
        it = tree.entrySet().iterator();
        StringBuffer sf = new StringBuffer();
        while (it.hasNext()) {
            Map.Entry<String, String> en = it.next();
            sf.append(en.getKey() + "=" + en.getValue() + "&");
        }
        return sf.substring(0, sf.length() - 1);
    }

    /**
     * 功能：前台交易构造HTTP POST自动提交表单<br>
     *
     * @param 、        表单提交地址<br>
     * @param hiddens  以MAP形式存储的表单键值<br>
     * @param encoding 上送请求报文域encoding字段的值<br>
     * @return 构造好的HTTP POST交易表单<br>
     */
    public static String createAutoFormHtml(String reqUrl, Map<String, String> hiddens, String encoding) {
        StringBuffer sf = new StringBuffer();
        sf.append("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=" + encoding
                + "\"/></head><body>");
        sf.append("<form id = \"pay_form\" action=\"" + reqUrl + "\" method=\"post\">");
        if (null != hiddens && 0 != hiddens.size()) {
            Set<Map.Entry<String, String>> set = hiddens.entrySet();
            Iterator<Map.Entry<String, String>> it = set.iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> ey = it.next();
                String key = ey.getKey();
                String value = ey.getValue();
                sf.append("<input type=\"hidden\" name=\"" + key + "\" id=\"" + key + "\" value=\"" + value + "\"/>");
            }
        }
        sf.append("</form>");
        sf.append("</body>");
        sf.append("<script type=\"text/javascript\">");
        sf.append("document.all.pay_form.submit();");
        sf.append("</script>");
        sf.append("</html>");
        return sf.toString();
    }

    /**
     * 计算百分比
     *
     * @param num
     * @param count
     * @return
     */
    public static int calPercent(int num, int count) {
        if (count == 0)
            return 0;
        BigDecimal b = new BigDecimal(num * 1.0 / count * 100);
        if (b.intValue() > 100)
            b = new BigDecimal(100);
        return b.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
    }

    /**
     * 计算商
     *
     * @param numerator   分子
     * @param denominator 分母
     * @return
     */
    public static int calQuotient(int numerator, int denominator) {
        if (denominator == 0)
            return 0;
        BigDecimal b = new BigDecimal(numerator * 1.0 / denominator);
        if (b.intValue() > 100)
            b = new BigDecimal(100);
        return b.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
    }


    /*
     * 判断字符串是否为空
     */

    public static boolean isNull(Object strData) {
        if (strData == null || String.valueOf(strData).trim().equals("")) {
            return true;
        }
        return false;
    }

    /**
     * 去掉字符串包含的所有空格
     *
     * @param value
     * @return
     */
    public static String string2AllTrim(String value) {
        if (isNull(value)) {
            return "";
        }
        return value.trim().replace(" ", "");
    }

    /**
     * 移除特殊字符（□）
     *
     * @param value
     * @return
     */
    public static String removeSpecialCharacter(String value) {
        return value.replaceAll("□", "");
    }

    /**
     * 计算平均值
     *
     * @param args
     * @return
     */
    public static int calcAvgValue(int... args) {
        if (args == null || args.length == 0)
            return 0;
        else {
            int sum = 0;
            for (int i = 0; i < args.length; i++) {
                sum += args[i];
            }
            return sum / args.length;
        }
    }

    /**
     * 计算平均值,四舍五入取整
     *
     * @param a
     * @return
     */
    public static int divide(int a, int b) {
        if (b == 0)
            return 0;
        else {
            BigDecimal ret = new BigDecimal(a * 1.0 / b).setScale(0, BigDecimal.ROUND_HALF_UP);
            return ret.intValue();
        }
    }

    /**
     * 根据从request中获取body的参数值
     *
     * @param br
     * @return
     */
    public static String getBodyString(BufferedReader br) {
        String inputLine;
        String str = "";
        try {
            while ((inputLine = br.readLine()) != null) {
                str += inputLine;
            }
            br.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        return str;
    }

    /**
     * 数组降序排列
     *
     * @param a
     */
    public static int[] sortDesc(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] > a[i]) {
                    int t = a[j];
                    a[j] = a[i];
                    a[i] = t;
                }
            }
        }
        return a;
    }

    /**
     * 取字符串固定长度的值，多余的显示...
     *
     * @param length 长度
     */
    public static String subTract(String str, int length) {
        if (str.length() > 10)
            return str.substring(0, 10) + "...";
        else
            return str;
    }

    /**
     * (80,100]为A,(60，80]为B,(40,60]为C,(20,40]为D,[0,20]为E
     */
    public static String scoreToRank(int score) {
        if (score > 80)
            return "A";
        else if (score > 60)
            return "B";
        else if (score > 40)
            return "C";
        else if (score > 20)
            return "D";
        else if (score >= 0)
            return "E";
        else
            return "";
    }

    /**
     * 将报告中代表换行符的符号转换为HTML的<br>
     */
    public static String transLinefeed(String source, String truncate) {
        if (source == null)
            return null;
        return source.replaceAll(truncate, "<br/>");
    }

    /**
     * 将报告中代表换行符的符号转换为HTML的<br>
     */
    public static String transLinefeed(String source) {
        return transLinefeed(source, "#NL#");
    }

    /**
     * 将报告中数字小项前加一个<br>
     * 比如：2.文字,转换为<br>2.文字
     */
    public static String transLinefeedNumber(String source) {
        //以前返回数据两项之间没有换行所以用正则匹配换行，现在返回直接带换行了所以暂时先不作处理，直接返回
        return source;


        /*if (isNullOrEmpty(source)) return "";
        String result = source;
        for (int i = 1, j = 1; i < source.length() - 2; i++, j++) {
            String s1 = source.substring(i, i + 2);
            if (s1.matches("\\d+\\.")) {
                String addStr1 = "<br>";
                result = result.substring(0, j) + addStr1 + result.substring(j, result.length());
                j += addStr1.length();
            }
            String s2 = source.substring(i, i + 3);
            if (s2.matches("\\(\\d+\\)")) {
                String addStr2 = "<br>&nbsp;&nbsp;&nbsp;&nbsp;";
                result = result.substring(0, j) + addStr2 + result.substring(j, result.length());
                j += addStr2.length();
            }
        }
        return result;*/
    }

    /**
     * 判断User-Agent 是不是来自于手机
     *
     * @param ua
     * @return
     */
    public static boolean checkAgentIsMobile(String ua) {
        boolean flag = false;
        if (!ua.contains("Windows NT") || (ua.contains("Windows NT") && ua.contains("compatible; MSIE 9.0;"))) {
            // 排除 苹果桌面系统
            if (!ua.contains("Windows NT") && !ua.contains("Macintosh")) {
                for (String item : agent) {
                    if (ua.contains(item)) {
                        flag = true;
                        break;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * iValue目标度、移动度。。。计算百分比
     *
     * @param val
     * @return
     */
    public static String calIvaluePercent(String val) {
        if (val.matches("\\d+")) {
            return String.valueOf(calPercent(Integer.parseInt(val), 1000));
        } else
            return "0";
    }

    /**
     * 字符串转数字，是数字形式的则原样返回，非数字形式返回"0".
     *
     * @param s
     */
    public static String parseIntString(String s) {
        if (s.matches("\\d+")) {
            return s;
        } else
            return "0";
    }

    /**
     * 字符串转数字，是数字形式的则转换为数字，非数字形式返回0.
     *
     * @param s
     */
    public static int getSafeInt(String s) {
        return Integer.parseInt(parseIntString(s));
    }

    /**
     * 格式化金额
     *
     * @param s
     */
    public static String formatMoney(float s) {
        DecimalFormat df = new DecimalFormat("#.0");
        return df.format(s);
    }

    /**
     * 格式化金额，中间用,隔开
     *
     * @param s
     * @return
     */
    public static String formatAmount(float s) {
        NumberFormat nf = new DecimalFormat("#,###");
        String str = nf.format(s);
        return str;
    }

    /**
     * 格式化金额，中间用,隔开，保留两位小数
     *
     * @param s
     * @return
     */
    public static String formatAmount(float s, String pattern) {
        NumberFormat nf = new DecimalFormat(pattern);
        String str = nf.format(s);
        return str;
    }

    /**
     * 获取随机字符串,微信支付用到
     *
     * @return
     */
    public static String getNonceStr() {
        // 随机数
        String currTime = DateUtil.dateToString(new Date(), "yyyyMMddHHmmss");
        // 8位日期
        String strTime = currTime.substring(8, currTime.length());
        // 四位随机数
        String strRandom = buildRandom(4) + "";
        // 10位序列号,可以自行调整。
        return strTime + strRandom;
    }

    /**
     * 取出一个指定长度大小的随机正整数.
     *
     * @param length int 设定所取出随机数的长度。length小于11
     * @return int 返回生成的随机数。
     */
    public static int buildRandom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }

    /**
     * 生成MD5字符串
     *
     * @param s
     * @return
     */
    public static String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes("UTF-8");
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 判断是否为数字型字符串
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(URLDecoder.decode("http://connect.qq.com/widget/shareqq/index.html?url=http%3A%2F%2Fwww.jiathis.com%2Fhelp%2Fhtml%2Fjiathis-diy%23jtss-cqq&showcount=0&desc=JiaThis%E5%AE%A2%E6%88%B7%E7%AB%AFJS%E8%87%AA%E5%AE%9A%E4%B9%89API+-+JiaThis%E9%80%9A%E8%BF%87JiaThis%E8%8E%B7%E5%8F%96%E4%BB%A3%E7%A0%81%E7%9A%84%E5%8A%9F%E8%83%BD%EF%BC%8C%E7%BD%91%E7%AB%99%E4%B8%BB%E5%8F%AF%E4%BB%A5%E4%BE%BF%E6%8D%B7%E7%9A%84%E8%8E%B7%E5%8F%96%E5%88%B0%E7%AC%A6%E5%90%88%E8%87%AA%E5%B7%B1%E7%BD%91%E7%AB%99%E9%A3%8E%E6%A0%BC%E7%9A%84%E5%88%86%E4%BA%AB%E6%8C%89%E9%92%AE%E4%BB%A3%E7%A0%81%E3%80%82%E5%AF%B9%E4%BA%8E%E5%A4%A7%E5%A4%9A%E6%95%B0%E7%9A%84%E7%BD%91%E7%AB%99%E8%80%8C%E8%A8%80%EF%BC%8C%E6%88%96%E8%AE%B8%E5%B7%B2%E7%BB%8F%E8%B6%B3%E5%A4%9F%E3%80%82%E4%BD%86%E6%98%AF%EF%BC%8C%E4%B8%80%E5%AE%9A%E8%BF%98%E6%9C%89%E4%B8%8D%E5%B0%91%E7%9A%84%E7%BD%91%E7%AB%99%E4%B8%BB%E6%83%B3%E6%8B%A5%E6%9C%89%E6%9B%B4%E5%8A%A0%E4%B8%AA%E6%80%A7%E5%8C%96%E7%9A%84%E8%87%AA%E5%AE%9A%E4%B9%89%E5%8A%9F%E8%83%BD%E3%80%82%E5%85%B6%E5%AE%9E%EF%BC%8CJiaThis%E4%B8%80%E7%9B%B4%E9%83%BD%E6%94%AF%E6%8C%81%E9%9D%9E%E5%B8%B8%E5%A4%9A%E7%9A%84%E4%B8%AA%E6%80%A7%E5%8C%96%E8%87%AA%E5%AE%9A%E4%B9%89%E5%8A%9F%E8%83%BD%EF%BC%8C%E4%B8%8D%E7%9F%A5%E9%81%93%E7%BB%86%E5%BF%83%E7%9A%84%E6%82%A8%E6%9C%89%E6%B2%A1%E6%9C%89%E5%8F%91%E7%8E%B0%EF%BC%9A%E9%99%A4%E4%BA%86%E5%A4%A7%E5%AE%B6%E7%BB%8F%E5%B8%B8%E4%BC%9A%E4%BD%BF%E7%94%A8%E5%88%B0%E7%9A%84%E8%87%AA%E5%AE%9A%E4%B9%89URL%E3%80%81TITLE%EF%BC%8C%E8%87%AA%E5%AE%9A%E4%B9%89%E5%88%86%E4%BA%AB%E5%B0%8F%E5%9B%BE&summary=JiaThis%E5%AE%A2%E6%88%B7%E7%AB%AFJS%E8%87%AA%E5%AE%9A%E4%B9%89API+-+JiaThis%E9%80%9A%E8%BF%87JiaThis%E8%8E%B7%E5%8F%96%E4%BB%A3%E7%A0%81%E7%9A%84%E5%8A%9F%E8%83%BD%EF%BC%8C%E7%BD%91%E7%AB%99%E4%B8%BB%E5%8F%AF%E4%BB%A5%E4%BE%BF%E6%8D%B7%E7%9A%84%E8%8E%B7%E5%8F%96%E5%88%B0%E7%AC%A6%E5%90%88%E8%87%AA%E5%B7%B1%E7%BD%91%E7%AB%99%E9%A3%8E%E6%A0%BC%E7%9A%84%E5%88%86%E4%BA%AB%E6%8C%89%E9%92%AE%E4%BB%A3%E7%A0%81%E3%80%82%E5%AF%B9%E4%BA%8E%E5%A4%A7%E5%A4%9A%E6%95%B0%E7%9A%84%E7%BD%91%E7%AB%99%E8%80%8C%E8%A8%80%EF%BC%8C%E6%88%96%E8%AE%B8%E5%B7%B2%E7%BB%8F%E8%B6%B3%E5%A4%9F%E3%80%82%E4%BD%86%E6%98%AF%EF%BC%8C%E4%B8%80%E5%AE%9A%E8%BF%98%E6%9C%89%E4%B8%8D%E5%B0%91%E7%9A%84%E7%BD%91%E7%AB%99%E4%B8%BB%E6%83%B3%E6%8B%A5%E6%9C%89%E6%9B%B4%E5%8A%A0%E4%B8%AA%E6%80%A7%E5%8C%96%E7%9A%84%E8%87%AA%E5%AE%9A%E4%B9%89%E5%8A%9F%E8%83%BD%E3%80%82%E5%85%B6%E5%AE%9E%EF%BC%8CJiaThis%E4%B8%80%E7%9B%B4%E9%83%BD%E6%94%AF%E6%8C%81%E9%9D%9E%E5%B8%B8%E5%A4%9A%E7%9A%84%E4%B8%AA%E6%80%A7%E5%8C%96%E8%87%AA%E5%AE%9A%E4%B9%89%E5%8A%9F%E8%83%BD%EF%BC%8C%E4%B8%8D%E7%9F%A5%E9%81%93%E7%BB%86%E5%BF%83%E7%9A%84%E6%82%A8%E6%9C%89%E6%B2%A1%E6%9C%89%E5%8F%91%E7%8E%B0%EF%BC%9A%E9%99%A4%E4%BA%86%E5%A4%A7%E5%AE%B6%E7%BB%8F%E5%B8%B8%E4%BC%9A%E4%BD%BF%E7%94%A8%E5%88%B0%E7%9A%84%E8%87%AA%E5%AE%9A%E4%B9%89URL%E3%80%81TITLE%EF%BC%8C%E8%87%AA%E5%AE%9A%E4%B9%89%E5%88%86%E4%BA%AB%E5%B0%8F%E5%9B%BE&title=JiaThis%E5%AE%A2%E6%88%B7%E7%AB%AFJS%E8%87%AA%E5%AE%9A%E4%B9%89API+-+JiaThis&site=jiathis&pics="));
        System.out.println(calIvaluePercent("mdf"));
        System.out.println(calPercent(2, 3));
        System.out.println(transLinefeedNumber("1.d2.e(3)h"));
        System.out.println("sdf2.".matches("\\d+"));

    }

}
