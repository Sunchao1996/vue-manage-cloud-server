package com.sc.util.queue.pdf;

import java.util.List;

/**
 * 业务发展请求结果
 * Created by 孔垂云 on 2017/8/14.
 */
public class PdfDevelopmentResult {
    private int pdfId;//	int	报告唯一标识
    private String reserve1;//varchar	全部报告下载
    private String reserve2;//varchar	全部报告下载
    private String pdf1;//	varchar
    private String pdf2;//	varchar
    private String pdf3;//	varchar
    private String pdf4;//	varchar
    private List<PdfDevelopmentEmp> empList;

    public int getPdfId() {
        return pdfId;
    }

    public void setPdfId(int pdfId) {
        this.pdfId = pdfId;
    }

    public String getReserve1() {
        return reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1;
    }

    public String getReserve2() {
        return reserve2;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2;
    }

    public String getPdf1() {
        return pdf1;
    }

    public void setPdf1(String pdf1) {
        this.pdf1 = pdf1;
    }

    public String getPdf2() {
        return pdf2;
    }

    public void setPdf2(String pdf2) {
        this.pdf2 = pdf2;
    }

    public String getPdf3() {
        return pdf3;
    }

    public void setPdf3(String pdf3) {
        this.pdf3 = pdf3;
    }

    public String getPdf4() {
        return pdf4;
    }

    public void setPdf4(String pdf4) {
        this.pdf4 = pdf4;
    }

    public List<PdfDevelopmentEmp> getEmpList() {
        return empList;
    }

    public void setEmpList(List<PdfDevelopmentEmp> empList) {
        this.empList = empList;
    }
}
