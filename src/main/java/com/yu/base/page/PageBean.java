package com.yu.base.page;

import java.util.HashMap;
import java.util.Map;

public class PageBean {
	/**
	 * 当前是第几页
	 */
	private int page;
	
	/**
	 * 当前页是多少行（后台easyui用）
	 */
	private int rows;
	
	private Map<String,Object> params = new HashMap<String, Object>();

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

}
