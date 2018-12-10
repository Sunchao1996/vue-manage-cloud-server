package com.sc.util.queue.pdf;

/**
 * ivalue的pdf请求
 * Created by 孔垂云 on 2017/8/14.
 */
public class PdfIvalueRequest extends BasePdfRequest {
    private Integer userTime;//填写问卷时间
    private String xmlPath;//文件路径

    public Integer getUserTime() {
        return userTime;
    }

    public void setUserTime(Integer userTime) {
        this.userTime = userTime;
    }

    public String getXmlPath() {
        return xmlPath;
    }

    public void setXmlPath(String xmlPath) {
        this.xmlPath = xmlPath;
    }
}
