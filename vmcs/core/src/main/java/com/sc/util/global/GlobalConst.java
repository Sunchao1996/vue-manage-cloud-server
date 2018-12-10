package com.sc.util.global;

/**
 * 全局参数
 *
 * @author 孔垂云
 * @date 2017-05-23
 */
public class GlobalConst {
    /**
     * 定义全局分页每页数据记录数
     */
    public static final int PAGESIZE = 20;

    public static final String CCB_SM_QUEUE = "ccb_sm_queue";
    public static final String CCB_USER_LOCK = "ccb_user_lock";
    public static final String CCB_USER_UNLOCK_QUEUE = "ccb_user_unlock";
    public static final String CCB_USER_UNLOCK_EXCHANGE = "dead";
    public static final String CCB_JPUSH_QUEUE = "ccb_jpush_queue";//极光推送
    public static final String MINER_TYPE_KEY = "MINER_TYPE";//属性字典中矿机类型key
}
