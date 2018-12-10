package com.sc.util.page;

import java.util.List;

/**
 * Created by 孔垂云 on 2017/8/18.
 */
@SuppressWarnings("rawtypes")
public class PageVO {
	private int count;
	private List list;// 记录

	public PageVO() {
	}

	public PageVO(int count, List list) {
		this.count = count;
		this.list = list;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}
}
