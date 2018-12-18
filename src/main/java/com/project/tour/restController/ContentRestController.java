package com.project.tour.restController;

import com.project.tour.domain.Content;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@Api(description = "컨텐츠 API", basePath = "/content")
@RestController
@RequestMapping(value = "/content")
public class ContentRestController
{
    @Autowired
    private String a;

    @ApiOperation(value = "지역 조회 - 전체", notes = "<pre>지역 정보 전체를 조회한다.</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Accept-Language", value = "조회될 언어 파라메터(헤더)", required = false,
                    dataType = "string", paramType = "header", defaultValue = "ko", example = "ko, en")
    })
    @GetMapping(value = {""})
    public ResponseEntity<List> getAllRegion(@RequestHeader Map header)
    {
        // 200, 204, 206
//        List<Region> resultList = regionService.getAllRegion(header);
        List<Content> contentList = null;

        if (contentList == null)
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(contentList, HttpStatus.OK);
    }
}
