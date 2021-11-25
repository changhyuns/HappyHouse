package com.ssafy.vue.model;

public class GalleryDto {
	  private int gallery_id;
	  private int noticeno;
	  private String userid;
	  private String title;
	  private String file_path;
	  private String imgFullPath;

	  public GalleryDto() {

	  }

	  public GalleryDto(int gallery_id, int noticeno, String userid, String title, String file_path, String imgFullPath) {
		super();
		this.gallery_id = gallery_id;
		this.noticeno = noticeno;
		this.userid = userid;
		this.title = title;
		this.file_path = file_path;
		this.imgFullPath = imgFullPath;
	  }
	  
	@Override
	public String toString() {
		return "GalleryDto [gallery_id=" + gallery_id + ", noticeno=" + noticeno
				+ ", userid=" + userid + ", title=" + title + ", file_path=" + file_path + ", imgFullPath=" + imgFullPath + "]";
	}


	public int getGallery_id() {
		return gallery_id;
	}

	public void setGallery_id(int gallery_id) {
		this.gallery_id = gallery_id;
	}

	public int getNoticeno() {
		return noticeno;
	}

	public void setNoticeno(int noticeno) {
		this.noticeno = noticeno;
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

	public String getImgFullPath() {
		return imgFullPath;
	}

	public void setImgFullPath(String imgFullPath) {
		this.imgFullPath = imgFullPath;
	}
	  
	  
}