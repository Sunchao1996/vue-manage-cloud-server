package com.sc.util.queue.pdf;

import com.sc.util.json.JsonUtil;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * pdf请求的返回值
 * Created by 孔垂云 on 2017/8/14.
 */
public class BasePdfResult {
    private int status;//状态
    private boolean success;//是否成功
    private String message;//返回消息
    @JsonSerialize
    private Object data;//返回的数据值

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public static void main(String[] args) throws Exception {
        String str = " {\n" +
                "        \"empList\": [\n" +
                "            {\n" +
                "                \"reserve3\": \"development/201708/业务发展_a1_销售辅导_20170801164909.pdf\",\n" +
                "                \"pdf1\": \"development/201708/pdfContent_1_20170801164909.pdf\",\n" +
                "                \"userId\": 1\n" +
                "            },\n" +
                "            {\n" +
                "                \"reserve3\": \"development/201708/业务发展_a2_销售辅导_20170801164909.pdf\",\n" +
                "                \"pdf1\": \"development/201708/pdfContent_2_20170801164909.pdf\",\n" +
                "                \"userId\": 2\n" +
                "            },\n" +
                "            {\n" +
                "                \"reserve3\": \"development/201708/业务发展_a3_销售辅导_20170801164909.pdf\",\n" +
                "                \"pdf1\": \"development/201708/pdfContent_3_20170801164909.pdf\",\n" +
                "                \"userId\": 3\n" +
                "            }\n" +
                "        ],\n" +
                "        \"pdfId\": 4077,\n" +
                "        \"pdf2\": \"development/201708/development_pdf2_20170801164909.pdf\",\n" +
                "        \"pdf3\": \"development/201708/development_pdf3_20170801164909.pdf\",\n" +
                "        \"pdf4\": \"development/201708/development_pdf4_20170801164909.pdf\",\n" +
                "        \"reserve1\": \"development/201708/部门_development_业务发展_工作内容_20170801164909.pdf\",\n" +
                "        \"reserve2\": \"development/201708/部门_development_业务发展_20170801164909.pdf\",\n" +
                "        \"pdf1\": \"development/201708/development_pdf1_20170801164909.pdf\"\n" +
                "    }\n";
//        BasePdfResult<PdfIvalueResult> basePdfResult = JsonUtil.toObject(str, BasePdfResult.class);
//        PdfIvalueResult pdfIvalueResult = (PdfIvalueResult) basePdfResult.getData();
//        System.out.println(pdfIvalueResult.getPdf1());
//        TypeReference ref = new TypeReference<PdfIvalueResult>() {
//        };

//        JsonNode node = JsonUtil.getNode(str);
//        PdfIvalueResult pdfIvalueResult = JsonUtil.toObject(node.get("data").toString(), PdfIvalueResult.class);
//        System.out.println(pdfIvalueResult.getPdf1());
        PdfDevelopmentResult pdfDevelopmentResult= JsonUtil.toObject(str,PdfDevelopmentResult.class);
        System.out.println(pdfDevelopmentResult.getEmpList().get(0).getPdf1());

    }
}
