package com.fengf.blog.pojo;

public class Articles {
    private Integer articleId;

    private Integer authorId;
    
    private String title;

    private String uptime;

    private Integer reading;

    private String author;

    private String content;

    private Integer likecount;

    private Integer dislike;

    private String commentcontent;

    private String fromUid;

    private String toUid;

    private String catalog;

    private String keyword1;

    private String keyword2;

    private String keyword3;

    private String isopen;

    private String top;

    private String toptime;

   
	public Articles() {
		super();
		// TODO 自动生成的构造函数存根
	}

	

	@Override
	public String toString() {
		return "Articles [articleId=" + articleId + ", authorId=" + authorId + ", title=" + title + ", uptime=" + uptime
				+ ", reading=" + reading + ", author=" + author + ", content=" + content + ", likecount=" + likecount
				+ ", dislike=" + dislike + ", commentcontent=" + commentcontent + ", fromUid=" + fromUid + ", toUid="
				+ toUid + ", catalog=" + catalog + ", keyword1=" + keyword1 + ", keyword2=" + keyword2 + ", keyword3="
				+ keyword3 + ", isopen=" + isopen + ", top=" + top + ", toptime=" + toptime + "]";
	}



	public Integer getAuthorId() {
		return authorId;
	}



	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}



	public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime == null ? null : uptime.trim();
    }

    public Integer getReading() {
        return reading;
    }

    public void setReading(Integer reading) {
        this.reading = reading;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getLikecount() {
        return likecount;
    }

    public void setLikecount(Integer likecount) {
        this.likecount = likecount;
    }

    public Integer getDislike() {
        return dislike;
    }

    public void setDislike(Integer dislike) {
        this.dislike = dislike;
    }

    public String getCommentcontent() {
        return commentcontent;
    }

    public void setCommentcontent(String commentcontent) {
        this.commentcontent = commentcontent == null ? null : commentcontent.trim();
    }

    public String getFromUid() {
        return fromUid;
    }

    public void setFromUid(String fromUid) {
        this.fromUid = fromUid == null ? null : fromUid.trim();
    }

    public String getToUid() {
        return toUid;
    }

    public void setToUid(String toUid) {
        this.toUid = toUid == null ? null : toUid.trim();
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog == null ? null : catalog.trim();
    }

    public String getKeyword1() {
        return keyword1;
    }

    public void setKeyword1(String keyword1) {
        this.keyword1 = keyword1 == null ? null : keyword1.trim();
    }

    public String getKeyword2() {
        return keyword2;
    }

    public void setKeyword2(String keyword2) {
        this.keyword2 = keyword2 == null ? null : keyword2.trim();
    }

    public String getKeyword3() {
        return keyword3;
    }

    public void setKeyword3(String keyword3) {
        this.keyword3 = keyword3 == null ? null : keyword3.trim();
    }

    public String getIsopen() {
        return isopen;
    }

    public void setIsopen(String isopen) {
        this.isopen = isopen == null ? null : isopen.trim();
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top == null ? null : top.trim();
    }

    public String getToptime() {
        return toptime;
    }

    public void setToptime(String toptime) {
        this.toptime = toptime == null ? null : toptime.trim();
    }
}