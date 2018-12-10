package com.sc.util.code;

import com.sc.util.date.DateUtil;

/**
 * 订单号生成规则
 * yyyyMMdd+corpId+6位随机数
 * Created by 孔垂云 on 2017/8/19.
 */
public class OrderCodeUtil {

    /**
     * 生成订单流水号
     * @param corpId
     * @return
     */
    public static String createOrderNo(int corpId) {
        String orderNo = DateUtil.getShortSystemDate();
        String formatCorpId = "";
        if (corpId < 10) formatCorpId = "00000" + corpId;
        else if (corpId < 100) formatCorpId = "0000" + corpId;
        else if (corpId < 1000) formatCorpId = "000" + corpId;
        else if (corpId < 10000) formatCorpId = "00" + corpId;
        else if (corpId < 100000) formatCorpId = "0" + corpId;
        else formatCorpId = corpId + "";
        return orderNo + formatCorpId + RandomCodeUtil.createRandomNum(8);
    }

    /**
     * 生成报告编号
     * @param corpId
     * @return
     */
    public static String createDocId(int corpId) {
        String docId = DateUtil.getShortSystemDate();
        String formatCorpId = "";
        if (corpId < 10) formatCorpId = "00000" + corpId;
        else if (corpId < 100) formatCorpId = "0000" + corpId;
        else if (corpId < 1000) formatCorpId = "000" + corpId;
        else if (corpId < 10000) formatCorpId = "00" + corpId;
        else if (corpId < 100000) formatCorpId = "0" + corpId;
        else formatCorpId = corpId + "";
        return docId + formatCorpId + RandomCodeUtil.createRandomNum(8);
    }

    /**
     * 生成模型编号
     * @param corpId
     * @return
     */
    public static String createModelId(int corpId) {
        String docId = DateUtil.getShortSystemDate();
        String formatCorpId = "";
        if (corpId < 10) formatCorpId = "00000" + corpId;
        else if (corpId < 100) formatCorpId = "0000" + corpId;
        else if (corpId < 1000) formatCorpId = "000" + corpId;
        else if (corpId < 10000) formatCorpId = "00" + corpId;
        else if (corpId < 100000) formatCorpId = "0" + corpId;
        else formatCorpId = corpId + "";
        return docId + formatCorpId + RandomCodeUtil.createRandomNum(8);
    }
}
