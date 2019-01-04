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

    /**
     * 전체 리스트 조회
     *
     * @param lang
     * @return
     */
    @ApiOperation(value = "콘텐츠 조회 - 전체", notes = "<pre>콘텐츠 정보 전체를 조회한다.</pre>")
    @GetMapping(value = {""})
    public ResponseEntity<List<Content>> getAllContent(
            @ApiParam(value = "Accept-Language", required = true, defaultValue = "ko") @RequestHeader(value = "Accept-Language", defaultValue = "ko") String lang)
    {
        return contentService.getAllContent(lang);
    }

    /**
     * 단일 항목 조회
     *
     * @param seq
     * @return
     */
    @ApiOperation(value = "콘텐츠 조회 - 단일", notes = "<pre>콘텐츠 정보 하나를 조회한다.</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "seq", value = "콘텐츠 고유키(정수형의 숫자)", required = true, dataType = "string", paramType = "path", defaultValue = ""
                    , example = "1"),
    })
    @GetMapping(value = "/{seq}")
    public ResponseEntity getContent(@PathVariable int seq)
    {
        return contentService.getContent(seq);
    }

    /**
     * 등록
     *
     * @param content
     * @return
     */
    @ApiOperation(value = "콘텐츠 등록", notes = "<pre>콘텐츠 정보를 저장한다.</pre>")
    @PostMapping(value = "",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity addContent(@ApiParam(name = "content", value = "콘텐츠정보 데이터", required = true) @ModelAttribute Content content)
    {
        return contentService.addContent(content);
    }

    /**
     * patch 는 메타 데이터만 수정하도록 처리
     *
     * @param seq
     * @param content
     * @return
     */
    @ApiOperation(value = "콘텐츠 수정", notes = "<pre>콘텐츠 수정한다.(메타)</pre>")
    @PatchMapping(value = "/{seq}"
            , consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}
            , produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.TEXT_PLAIN_VALUE}
    )
    public ResponseEntity updatePatchContent(@PathVariable int seq,
                                             @ApiParam(name = "content", value = "콘텐츠정보 데이터", required = true) @RequestBody Content content)
    {
        content.setSeq(seq);
        return contentService.updatePatchContent(content);
    }

    /**
     * put 전체 데이터 수정
     *
     * @param seq
     * @param content
     * @return
     */
    @ApiOperation(value = "콘텐츠 수정", notes = "<pre>콘텐츠 정보를 수정한다.</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "seq", value = "콘텐츠 고유키(정수형의 숫자)", required = true, dataType = "string",
                    paramType = "path", defaultValue = "", example = "")
    })
    @PutMapping(value = "/{seq}",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity updatePutContent(@PathVariable int seq
                                          , @ApiParam(name = "content", value = "콘텐츠정보 데이터", required = true) @ModelAttribute Content
 content
//                                           , @RequestParam(value = "title2" , name = "title2") String title2
    )
    {
        log.info("@@#@#@#@@#");
//        content.setSeq(seq);
//        log.info("#############" + content.toString());
//        return contentService.updatePutContent(content);
        return null;
    }

    @ApiOperation(value = "콘텐츠 삭제", notes = "<pre>콘텐츠 정보를 삭제한다.</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "seq", value = "콘텐츠 고유키(정수형의 숫자)", required = true, dataType = "string", paramType = "path", defaultValue = "", example = "1")
    })
    @DeleteMapping(value = "/{seq}")
    public ResponseEntity removeContent(@PathVariable int seq)
    {
        return contentService.removeContent(seq);
    }

}
