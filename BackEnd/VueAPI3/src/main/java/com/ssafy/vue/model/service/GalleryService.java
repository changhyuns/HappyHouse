package com.ssafy.vue.model.service;

import com.ssafy.vue.model.GalleryDto;

public interface GalleryService {
	public boolean uploadGallery(GalleryDto gallerydDto) throws Exception;
	public GalleryDto getGalleryNotice(int noticeno) throws Exception;
}
