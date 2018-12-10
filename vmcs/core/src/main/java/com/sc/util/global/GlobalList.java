package com.sc.util.global;

import com.sc.util.model.ComboboxVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 孔垂云 on 2017/9/29.
 */
public class GlobalList {
    public static final List<ComboboxVO> listCorpGrade = new ArrayList<>();//企业级别

    static {
        //企业级别
        listCorpGrade.add(new ComboboxVO("1", "普通用户"));
        listCorpGrade.add(new ComboboxVO("2", "个人版"));
        listCorpGrade.add(new ComboboxVO("3", "企业版"));
        listCorpGrade.add(new ComboboxVO("4", "经销商"));
        listCorpGrade.add(new ComboboxVO("5", "子公司"));

    }
}
