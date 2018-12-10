package com.sc.util.json;


import com.sc.util.code.EnumReturnCode;

/**
 * 接口调用json返回类
 *
 * @author 王晓安
 */
public class JsonResult {

    /**
     * 调用结果
     */
    private boolean success;

    /**
     * 接口返回码
     */
    private String returnCode;

    /**
     * 接口返回消息
     */
    private String msg;

    /**
     * 接口返回数据
     */
    private Object data;

    public JsonResult() {
    }

    public JsonResult(boolean success) {
        this.success = success;
    }

    public JsonResult(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public JsonResult(boolean success, String msg, Object data) {
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    public JsonResult(String returnCode, String msg) {
        this.returnCode = returnCode;
        this.msg = msg;
    }

    public JsonResult(String returnCode) {
        this.returnCode = returnCode;
        this.msg = EnumReturnCode.getValue(returnCode);
    }


    public JsonResult(EnumReturnCode enumReturnCode) {
        this.returnCode = enumReturnCode.getCode();
        this.msg = enumReturnCode.getValue();
        this.success = enumReturnCode.isSuccess();
    }


    public JsonResult(EnumReturnCode enumReturnCode, Object data) {
        this.success = enumReturnCode.isSuccess();
        this.returnCode = enumReturnCode.getCode();
        this.msg = enumReturnCode.getValue();
        this.data = data;
    }

    public JsonResult(String returnCode, String msg, Object data) {
        this.returnCode = returnCode;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "success=" + success +
                ", returnCode='" + returnCode + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
