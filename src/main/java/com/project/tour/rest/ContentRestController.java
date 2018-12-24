package com.project.tour.rest;

import com.project.tour.domain.Content;
import com.project.tour.service.ContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Api(description = "컨텐츠 API", basePath = "/content")
@RestController
@RequestMapping(value = "/content")
public class ContentRestController
{
    @Autowired
    private ContentService contentService;

    @ApiOperation(value = "콘텐츠 조회 - 전체", notes = "<pre>콘텐츠 정보 전체를 조회한다.</pre>")
    @GetMapping(value = {""})
    public ResponseEntity<List<Content>> getAllRegion(
            @ApiParam(value = "Accept-Language", required = true, defaultValue = "ko") @RequestHeader(value = "Accept-Language", defaultValue = "ko") String lang)
    {
        return contentService.getAllContent(lang);
    }
}
