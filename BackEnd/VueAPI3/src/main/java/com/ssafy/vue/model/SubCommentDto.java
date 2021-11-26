package com.ssafy.vue.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SubCommentDto : 댓글정보", description = "하위 댓글의 상세 정보를 나타낸다.")
public class SubCommentDto {
	@ApiModelProperty(value = "하위 댓글 번호")
	private int sub_comment_id;
	@ApiModelProperty(value = "상위 댓글 번호")
	private int commentid;
	@ApiModelProperty(value = "게시판 글 번호")
	private int articleno;
	@ApiModelProperty(value = "하위 댓글 작성자")
	private String userid;
	@ApiModelProperty(value = "하위 댓글 내용")
	private String content;
	@ApiModelProperty(value = "작성일")
	private String regtime;
	public int getsub_comment_id() {
		return sub_comment_id;
	}
	public void setsub_comment_id(int sub_comment_id) {
		this.sub_comment_id = sub_comment_id;
	}
	public int getCommentid() {
		return commentid;
	}
	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}
	public int getArticleno() {
		return articleno;
	}
	public void setArticleno(int articleno) {
		this.articleno = articleno;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	@Override
	public String toString() {
		return "SubCommentDto [sub_comment_id=" + sub_comment_id + ", commentid=" + commentid + ", articleno=" + articleno
				+ ", userid=" + userid + ", content=" + content + ", regtime=" + regtime + "]";
	}
}
