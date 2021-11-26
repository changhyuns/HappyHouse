package com.ssafy.vue.model.service;

import java.util.List;

import com.ssafy.util.PageNavigation;
import com.ssafy.vue.model.GalleryDto;
import com.ssafy.vue.model.NoticeDto;
import com.ssafy.vue.model.NoticeParameterDto;

public interface NoticeService {
	public boolean writeNotice(NoticeDto noticeDto) throws Exception;
	public List<NoticeDto> listNotice(NoticeParameterDto noticeParameterDto) throws Exception;
	public PageNavigation makePageNavigation(NoticeParameterDto noticeParameterDto) throws Exception;
	
	public NoticeDto getNotice(int Noticeno) throws Exception;
	public int getPrev(int Noticeno) throws Exception;
	public int getNext(int Noticeno) throws Exception;
	public void updateHit(int Noticeno) throws Exception;
	public boolean modifyNotice(NoticeDto noticeDto) throws Exception;
	public boolean deleteNotice(int Noticeno) throws Exception;
	
	public List<GalleryDto> getFile(int noticeno) throws Exception;
}
