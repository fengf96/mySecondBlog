package com.fengf.blog.pojo;

public class UserQueryVo {
	
	private Integer userId;

	private String userName;

	//当前页
  	private Integer page;
  		
  	//每页数
  	private Integer size=5;
  		
  	//开始行
  	private Integer startRow=0;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getStartRow() {
		return startRow;
	}

	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}

	@Override
	public String toString() {
		return "UserQueryVo [userId=" + userId + ", userName=" + userName + ", page=" + page + ", size=" + size
				+ ", startRow=" + startRow + "]";
	}
  	
}
