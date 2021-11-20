package com.ssafy.vue.model;

public class CommentPlusDto {
	private int articleno;
	private int comments;
	public int getArticleno() {
		return articleno;
	}
	public void setArticleno(int articleno) {
		this.articleno = articleno;
	}
	public int getComments() {
		return comments;
	}
	public void setComments(int comments) {
		this.comments = comments;
	}
	@Override
	public String toString() {
		return "CommentPlusDto [articleno=" + articleno + ", comments=" + comments + "]";
	}
}
