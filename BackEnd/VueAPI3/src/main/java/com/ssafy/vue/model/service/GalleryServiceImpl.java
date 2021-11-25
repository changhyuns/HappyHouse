package com.ssafy.vue.model.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.vue.model.GalleryDto;
import com.ssafy.vue.model.mapper.GalleryMapper;

@Service
@Transactional
public class GalleryServiceImpl implements GalleryService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public boolean uploadGallery(GalleryDto galleryDto) throws Exception {
		if(galleryDto.getTitle() == null || galleryDto.getFile_path() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(GalleryMapper.class).uploadGallery(galleryDto) == 1;
	}

	@Override
	public GalleryDto getGalleryNotice(int noticeno) throws Exception {
		return sqlSession.getMapper(GalleryMapper.class).getGalleryNotice(noticeno);
	}

}
