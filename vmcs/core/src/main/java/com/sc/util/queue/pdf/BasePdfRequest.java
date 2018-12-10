package com.sc.util.queue.pdf;

/**
 * pdf请求基础字段
 * Created by 孔垂云 on 2017/8/14.
 */
public class BasePdfRequest {
    private int pdfId;//pdf报告的id
    private String userName;//报告姓名
    private String companyName;//公司名称
    private String userPost;//职务
    private String productType;//报告类型(应用类型)
    private String department;//部门名称

    public int getPdfId() {
        return pdfId;
    }

    public void setPdfId(int pdfId) {
        this.pdfId = pdfId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUserPost() {
        return userPost;
    }

    public void setUserPost(String userPost) {
        this.userPost = userPost;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
