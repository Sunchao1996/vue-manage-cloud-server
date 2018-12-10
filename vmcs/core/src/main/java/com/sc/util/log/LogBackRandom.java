package com.sc.util.log;

import ch.qos.logback.core.PropertyDefinerBase;
import com.sc.util.code.RandomCodeUtil;

/**定义日志路径的随机数
 * Created by 孔垂云 on 2017/9/2.
 */
public class LogBackRandom extends PropertyDefinerBase {

    @Override
    public String getPropertyValue() {
        return RandomCodeUtil.createRandomNum(4);
    }
}
