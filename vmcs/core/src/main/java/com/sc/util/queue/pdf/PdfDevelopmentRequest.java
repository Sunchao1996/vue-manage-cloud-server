package com.sc.util.queue.pdf;

import java.util.List;

/**
 * 业务发展请求
 * Created by 孔垂云 on 2017/8/14.
 */
public class PdfDevelopmentRequest extends BasePdfResult {
    private String xmlPath;//	varchar	业务发展Xml文件路径
    private List<Integer> answerImportList;//	arrayList<Interger>	关键工作序号list
    private List<Integer> answerList;//<Interger>	常态工作序号list
    private List<PdfDevelopmentEmp> empList;//

    public String getXmlPath() {
        return xmlPath;
    }

    public void setXmlPath(String xmlPath) {
        this.xmlPath = xmlPath;
    }

    public List<Integer> getAnswerImportList() {
        return answerImportList;
    }

    public void setAnswerImportList(List<Integer> answerImportList) {
        this.answerImportList = answerImportList;
    }

    public List<Integer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Integer> answerList) {
        this.answerList = answerList;
    }

    public List<PdfDevelopmentEmp> getEmpList() {
        return empList;
    }

    public void setEmpList(List<PdfDevelopmentEmp> empList) {
        this.empList = empList;
    }
}
