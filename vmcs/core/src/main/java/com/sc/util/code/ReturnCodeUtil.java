package com.sc.util.code;

import java.util.HashMap;

/**
 * 设置公共返回码
 * 100*成功系列
 * 200*失败系列
 * 300*系统部分
 *
 * @author 王晓安
 */
public class ReturnCodeUtil {
    private static HashMap<String, ReturnMsg> hashMap = new HashMap<>();

    /*
     * 初始化该对象
     */
    static {
        /*
         * 成功类
         */
        //新增、修改返回该值
        hashMap.put("10001", new ReturnMsg("10001", "保存成功"));
        //删除成功返回该值
        hashMap.put("10002", new ReturnMsg("10002", "删除成功"));
        //操作成功返回该值，比如重置密码、锁定解锁用户
        hashMap.put("10003", new ReturnMsg("10003", "操作成功"));
        //审核成功返回该值，比如审核是否有问题等
        hashMap.put("10004", new ReturnMsg("10004", "审核成功"));

        //系统管理-重置密码成功
        hashMap.put("10101", new ReturnMsg("10101", "用户重置密码成功，重置后密码变为123456！"));
        hashMap.put("10105", new ReturnMsg("10105", "登录成功！"));



        /*
         * 成功类
         */
        hashMap.put("100401", new ReturnMsg("100401", "新增成功"));
        hashMap.put("100402", new ReturnMsg("100402", "编辑成功"));


        /*
         * 失败类
         */
        //操作失败返回该值，比如新增、修改、删除失败
        hashMap.put("20001", new ReturnMsg("20001", "操作失败！"));
        hashMap.put("20002", new ReturnMsg("20002", "代码已存在！"));



        /*
         * 分大类错误码,系统管理类
         */
        hashMap.put("20101", new ReturnMsg("20101", "不能和上级节点一样！"));
        hashMap.put("20102", new ReturnMsg("20102", "上级节点不存在！"));
        hashMap.put("20103", new ReturnMsg("20103", "还有下级节点，不能删除！"));
        hashMap.put("20104", new ReturnMsg("20104", "用户账号已存在！"));
        hashMap.put("20105", new ReturnMsg("20105", "原密码输入错误！"));
        hashMap.put("20106", new ReturnMsg("20106", "账号或密码输入错误！"));
        hashMap.put("20107", new ReturnMsg("20107", "账号已锁定！"));
        hashMap.put("20108", new ReturnMsg("20108", "用户不存在！"));

        /*
         * 订单相关操作
         */
        hashMap.put("20303", new ReturnMsg("20303", "支付成功！"));
        hashMap.put("20304", new ReturnMsg("20304", "验签失败！"));
        hashMap.put("20305", new ReturnMsg("20305", "支付失败！"));
        hashMap.put("20306", new ReturnMsg("20306", "电子发票已经发送您的邮箱，请注意查收！"));
        hashMap.put("20307", new ReturnMsg("20307", "电子发票生成失败，请联系客服！"));
        hashMap.put("20308", new ReturnMsg("20308", "电子发票已经生成！"));
        hashMap.put("20309", new ReturnMsg("20309", "客户没有提交发票信息！"));

        //验证码失效提示
        hashMap.put("20404", new ReturnMsg("20404", "原管理员手机验证码失效！"));
        hashMap.put("20405", new ReturnMsg("20405", "新管理员手机验证码失效！"));
        hashMap.put("20406", new ReturnMsg("20406", "原管理员手机验证码错误！"));
        hashMap.put("20407", new ReturnMsg("20407", "新管理员手机验证码错误！"));
        hashMap.put("20408", new ReturnMsg("20408", "密码错误！"));
        hashMap.put("20409", new ReturnMsg("20409", "手机验证码错误！"));
        hashMap.put("20416", new ReturnMsg("20416", "手机格式不正确！"));

        //注册
        hashMap.put("20417", new ReturnMsg("20417", "企业名称已被注册！"));
        hashMap.put("20418", new ReturnMsg("20418", "手机号已被注册！"));
        hashMap.put("20419", new ReturnMsg("20419", "手机验证码发送成功！"));
        hashMap.put("20420", new ReturnMsg("20420", "部门不存在！"));
        hashMap.put("20421", new ReturnMsg("20421", "企业不存在！"));
        hashMap.put("20422", new ReturnMsg("20422", "请先获取短信验证码！"));


        hashMap.put("20423", new ReturnMsg("20423", "QQ已经被其他账号绑定！"));
        hashMap.put("20424", new ReturnMsg("20424", "微信已经被其他账号绑定！"));


        //消息类
        hashMap.put("20501", new ReturnMsg("20501", "消息不存在！"));
        hashMap.put("20502", new ReturnMsg("20502", "删除失败"));

        hashMap.put("20601", new ReturnMsg("20601", "新增菜单失败,只能有三个主菜单"));
        hashMap.put("20602", new ReturnMsg("20602", "新增菜单失败,二级菜单不能超过五个"));
        hashMap.put("20603", new ReturnMsg("20603", "删除菜单失败,该菜单下有子菜单"));

        /*
         * 系统相关类
         */
        //操作失败返回该值，比如新增、修改、删除失败
        hashMap.put("30001", new ReturnMsg("30001", "登录失效，请重新登录！"));
        hashMap.put("30002", new ReturnMsg("30002", "参数错误！"));
        //301*注册、登录、找回密码相关
        hashMap.put("30003", new ReturnMsg("30003", "路径不存在！"));
        hashMap.put("30004", new ReturnMsg("30004", "系统错误！"));
        hashMap.put("30005", new ReturnMsg("30005", "版本过低，请您尽快升级！"));
        //ccb提示
        hashMap.put("40500", new ReturnMsg("40500", "矿机正在使用无法删除！"));
        hashMap.put("40501", new ReturnMsg("40501", "同种矿机已经存在！"));
        hashMap.put("NOTICE-20001", new ReturnMsg("NOTICE-20001", "公告内容为空！"));
        hashMap.put("COMPLAIN-01", new ReturnMsg("COMPLAIN-01", "申诉记录已经不存在！"));
        hashMap.put("COMPLAIN-02", new ReturnMsg("COMPLAIN-02", "申诉订单已经不存在！"));
        hashMap.put("COMPLAIN-03", new ReturnMsg("COMPLAIN-03", "记录信息不完整！"));
        hashMap.put("PC-USER-5001", new ReturnMsg("PC-USER-5001", "用户CC币余额不足！"));
        hashMap.put("PC-USER-5002", new ReturnMsg("PC-USER-5002", "用户冻结CC币不足！"));
        hashMap.put("PC-USER-5003", new ReturnMsg("PC-USER-5003", "余额格式不正确！"));

    }

    /**
     * 获取返回的中文说明
     * 如果没有对应的码，返回该码
     *
     * @param resultCode 返回码
     * @return 返回码对应中文
     */
    public static String getMsg(String resultCode) {
        if (hashMap.containsKey(resultCode)) {
            return hashMap.get(resultCode).getCnMsg();
        } else {
            return resultCode;
        }
    }

}
