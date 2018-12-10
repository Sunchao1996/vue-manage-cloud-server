package com.sc.util.code;

import java.util.regex.Pattern;

/**
 * 地址代码工具
 *
 * @author 王晓安
 */
public class AdCodeUtils {

    /**
     * 全国
     */
    private static final Pattern COUNTRY_PATTERN = Pattern.compile("^1(0{5})$");

    /**
     * 省
     */
    private static final Pattern PROVINCE_PATTERN = Pattern.compile("^\\d{2}(0{4})$");

    /**
     * 市
     */
    private static final Pattern CITY_PATTERN = Pattern.compile("^\\d{4}(0{2})$");

    /**
     * 区
     */
    private static final Pattern DISTRICT_PATTERN = Pattern.compile("\\d{6}");

    /**
     * 根据地址编码返回等级
     *
     * @param adcode 地址编码
     * @return 地址等级
     */
    public static String getLevel(String adcode) {
        String level;
        if (COUNTRY_PATTERN.matcher(adcode).matches()) {
            level = EnumAddress.COUNTRY.getCode();
        } else if (PROVINCE_PATTERN.matcher(adcode).matches()) {
            level = EnumAddress.PROVINCE.getCode();
        } else if (CITY_PATTERN.matcher(adcode).matches()) {
            level = EnumAddress.CITY.getCode();
        } else {
            level = EnumAddress.DISTRICT.getCode();
        }
        return level;
    }

}
