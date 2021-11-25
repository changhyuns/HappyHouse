package com.ssafy.vue.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.vue.model.GalleryDto;
import com.ssafy.vue.model.service.GalleryService;
import com.ssafy.vue.model.service.S3Service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//http://localhost:9999/vue/swagger-ui.html
//@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/gallery")
@Api("갤러리 컨트롤러  API V1")
public class GalleryController {
	private static final Logger logger = LoggerFactory.getLogger(GalleryController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private GalleryService galleryService;
	
	@Autowired
	private S3Service s3Service;


	@ApiOperation(value = "사진 저장", notes = "새로운 사진 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	//@RequestBody @ApiParam(value = "게시글 정보.", required = true) GalleryDto galleryDto
	public ResponseEntity<String> uploadGallery(GalleryDto galleryDto, MultipartFile files) throws Exception {
		logger.info("uploadGallery - 호출");
		
		String imgPath = s3Service.upload(galleryDto.getFile_path(), files);
		galleryDto.setFile_path("https://" + "d1133kioz6zzls.cloudfront.net" + "/" + imgPath);
		if (galleryService.uploadGallery(galleryDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "공지사항 사진 가져오기", notes = "공지사항 글 번호에 해당하는 사진의 정보를 반환한다.", response = GalleryDto.class)
	@GetMapping("/notice/{noticeno}")
	public ResponseEntity<GalleryDto> getGalleryNotice(@PathVariable("noticeno") @ApiParam(value = "공지사항  글의 글번호.", required = true) int noticeno) throws Exception {
		logger.info("getGallerNotice - 호출 : " + noticeno);
		 
		return new ResponseEntity<GalleryDto>(galleryService.getGalleryNotice(noticeno), HttpStatus.OK);
	}
	
}
