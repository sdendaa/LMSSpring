/**
 * 
 */
package com.gcit.lms.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.gcit.lms.entity.BaseEntity;


@SuppressWarnings("hiding")
public abstract class BaseDAO<BaseEntity> {
	@Autowired
	JdbcTemplate template;

	private Integer pageNo;
	private Integer pageSize = 10;

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}	

}
