package com.ssafy.vue.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.GalleryDto;

@Mapper
public interface GalleryMapper {
	public int uploadGallery(GalleryDto galleryDto) throws SQLException;
	public GalleryDto getGalleryNotice(int noticeno) throws SQLException;
}
