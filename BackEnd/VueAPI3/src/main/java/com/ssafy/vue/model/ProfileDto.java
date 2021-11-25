package com.ssafy.vue.model;

public class ProfileDto {
	  private int profileno;
	  private String userid;
	  private String title;
	  private String file_path;
	public ProfileDto(int profileno, String userid, String title, String file_path) {
		super();
		this.profileno = profileno;
		this.userid = userid;
		this.title = title;
		this.file_path = file_path;
	}
	public int getProfileno() {
		return profileno;
	}
	public void setProfileno(int profileno) {
		this.profileno = profileno;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	@Override
	public String toString() {
		return "ProfileDto [profileno=" + profileno + ", userid=" + userid + ", title=" + title + ", file_path="
				+ file_path + "]";
	}
	  
}
