package com.fengf.blog.pojo;

public class ArticleQueryVo {
	  	private Integer articlesId;

	    private String title;

	    private Integer authorId;
	    
	    private String keyword1;

	    private String keyword2;
	    
	    private String keyword3;

		private Integer topic;
		//当前页
		private Integer page = 1;

		//每页数
		private Integer size=5;

		//开始行
		private Integer startRow=0;
	@Override
	public String toString() {
		return "ArticleQueryVo{" +
				"articlesId=" + articlesId +
				", title='" + title + '\'' +
				", authorId=" + authorId +
				", keyword1='" + keyword1 + '\'' +
				", keyword2='" + keyword2 + '\'' +
				", keyword3='" + keyword3 + '\'' +
				", topic=" + topic +
				", page=" + page +
				", size=" + size +
				", startRow=" + startRow +
				'}';
	}

	public Integer gettopic() {
		return topic;
	}

	public void settopic(Integer topic) {
		this.topic = topic;
	}



		

		public Integer getArticlesId() {
			return articlesId;
		}

		public void setArticlesId(Integer articlesId) {
			this.articlesId = articlesId;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getKeyword1() {
			return keyword1;
		}

		public void setKeyword1(String keyword1) {
			this.keyword1 = keyword1;
		}

		public String getKeyword2() {
			return keyword2;
		}

		public void setKeyword2(String keyword2) {
			this.keyword2 = keyword2;
		}

		public String getKeyword3() {
			return keyword3;
		}

		public void setKeyword3(String keyword3) {
			this.keyword3 = keyword3;
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

		public ArticleQueryVo() {
			super();
			// TODO 自动生成的构造函数存根
		}

		public Integer getAuthorId() {
			return authorId;
		}

		public void setAuthorId(Integer authorId) {
			this.authorId = authorId;
		}

		public ArticleQueryVo(Integer articlesId, String title, Integer authorId, String keyword1, String keyword2,
				String keyword3, Integer page, Integer size, Integer startRow,Integer topic) {
			super();
			this.articlesId = articlesId;
			this.title = title;
			this.authorId = authorId;
			this.keyword1 = keyword1;
			this.keyword2 = keyword2;
			this.keyword3 = keyword3;
			this.page = page;
			this.size = size;
			this.startRow = startRow;
			this.topic = topic;
		}


}
