package com.sc.util.code;

/**
 * @autho 孔垂云
 * @date 2017/7/17.
 */
public class GlobalCode {
    /**
     * 登录相关
     */
    public static final String OPERA_SUCCESS = "10003";//操作成功

    public static final String OPERA_FAILURE = "20001";//操作失败
    public static final String CODEEXIST_FAILURE = "20002";//代码已存在
    public static final String RTB_NOTENOUTH = "20301";//睿特币余额不足
    public static final String CCB_MINER = "40500";//矿机正在使用无法删除
    public static final String CCB_MINER2 = "40501";//同种矿机已经存在
    public static final String NOTICT_NO_CONTENT = "NOTICE-20001";//矿机正在使用无法删除
    public static final String CCB_DEAL_COMPLAIN_FALIE1 = "COMPLAIN-01";//申诉记录已经不存在
    public static final String CCB_DEAL_COMPLAIN_FALIE2 = "COMPLAIN-02";//申诉订单已经不存在
    public static final String CCB_DEAL_COMPLAIN_FALIE3 = "COMPLAIN-03";//记录信息不完整
    public static final String PC_USER_ERROR1 = "PC-USER-5001";//冻结用户ccb时，用户余额不足
    public static final String PC_USER_ERROR2 = "PC-USER-5002";//解冻用户ccb时，用户冻结数量不足
    public static final String PC_USER_ERROR3 = "PC-USER-5003";//修改ccb余额时，输入格式不对


    public static final String NO_AUTH = "30001";//权限失败
    public static final String PARAMETER_ERROR = "30002";//参数错误
    public static final String VERSION_LOWER = "30005";//版本过低
    public static final String NO_LOGIN = "50001";//未登录

    public static final long MAX_MOTIFY_TIME = 24 * 60 * 60 * 1000;

}
