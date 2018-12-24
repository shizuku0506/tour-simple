package com.project.tour.rest;

import com.project.tour.domain.Content;
import com.project.tour.service.ContentService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "콘텐츠 조회 - 단일", notes = "<pre>콘텐츠 정보 하나를 조회한다.</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "seq", value = "콘텐츠 고유키(정수형의 숫자)", required = true, dataType = "string", paramType = "path", defaultValue = ""
                    , example = "1"),
    })
    @GetMapping(value = "/{seq}")
    public ResponseEntity getRegion(@PathVariable int seq)
    {
        return contentService.getContent(seq);
    }

    @ApiOperation(value = "콘텐츠 등록", notes = "<pre>콘텐츠 정보를 저장한다.</pre>")
    @PostMapping(value = "",
            consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity addRegion(@ApiParam(name = "region", value = "콘텐츠정보 데이터", required = true) @RequestBody Content content)
    {
        return contentService.addContent(content);
    }

    @ApiOperation(value = "콘텐츠 삭제", notes = "<pre>콘텐츠 정보를 삭제한다.</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "seq", value = "콘텐츠 고유키(정수형의 숫자)", required = true, dataType = "string", paramType = "path", defaultValue = "", example = "1")
    })
    @DeleteMapping(value = "/{seq}")
    public ResponseEntity removeRegion(@PathVariable int seq)
    {
        return contentService.removeContent(seq);
    }

    @ApiOperation(value = "콘텐츠 수정", notes = "<pre>콘텐츠 정보를 수정한다.</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "seq", value = "콘텐츠 고유키(정수형의 숫자)", required = true, dataType = "string", paramType = "path", defaultValue = "", example = "")
    })
    @PatchMapping(value = "/{seq}", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity editRegion(@PathVariable int seq,
                                     @ApiParam(name = "region", value = "콘텐츠정보 데이터", required = true) @RequestBody Content content)
    {
        content.setSeq(seq);
        return contentService.editContent(content);
    }
}
