package com.sc.util.code;

/**
 * 用户编号生成器
 *
 * @author 王晓安
 */
public class UserIdUtil {

    /**
     * 生成用户编号
     *
     * @return 用户编号
     */
    public static String createUserId() {
        return RandomCodeUtil.createRandomNum(10);
    }

}
