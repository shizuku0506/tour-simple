package com.project.tour.restController;

import com.project.tour.domain.Region;
import com.project.tour.service.RegionService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(basePath = "/region", description = "지역 데이터에 대한 CRUD")
@RestController
@RequestMapping(value = "/region")
public class RegionRestController
{
    @Autowired
    private RegionService regionService;

    @ApiOperation(value = "지역 조회 - 전체", notes = "<pre>지역 정보 전체를 조회한다.</pre>")
    @GetMapping(value = {""})
    public ResponseEntity getAllRegion(@ApiParam(value = "Accept-Language", required = true, defaultValue = "ko") @RequestHeader(value = "Accept-Language",
            defaultValue = "ko") String lang)
    {
        return regionService.getAllRegion(lang);
    }

    @ApiOperation(value = "지역 조회 - 단일", notes = "<pre>지역 정보 하나를 조회한다.</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "seq", value = "지역 고유키(정수형의 숫자)", required = true, dataType = "string", paramType = "path", defaultValue = ""
                    , example = "1"),
    })
    @GetMapping(value = "/{seq}")
    public ResponseEntity getRegion(@PathVariable int seq)
    {
        return regionService.getRegion(seq);
    }

    @ApiOperation(value = "지역 등록", notes = "<pre>지역 정보를 저장한다.</pre>")
    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            produces = {MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity addRegion(@ApiParam(name = "region", value = "지역정보 데이터", required = true) @RequestBody Region region)
    {
        return regionService.addRegion(region);
    }

    @ApiOperation(value = "지역 삭제", notes = "<pre>지역 정보를 삭제한다.</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "seq", value = "지역 고유키(정수형의 숫자)", required = true, dataType = "string", paramType = "path", defaultValue = "", example = "1")
    })
    @DeleteMapping(value = "/{seq}")
    public ResponseEntity removeRegion(@PathVariable int seq)
    {
        return regionService.removeRegion(seq);
    }

    @ApiOperation(value = "지역 수정", notes = "<pre>지역 정보를 수정한다.</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "seq", value = "지역 고유키(정수형의 숫자)", required = true, dataType = "string", paramType = "path", defaultValue = "", example = "")
    })
    @PatchMapping(value = "/{seq}", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<Region> editRegion(@PathVariable int seq,
                                             @ApiParam(name = "region", value = "지역정보 데이터", required = true) @RequestBody Region region)
    {
        region.setSeq(seq);
        return regionService.editRegion(region);
    }
}
