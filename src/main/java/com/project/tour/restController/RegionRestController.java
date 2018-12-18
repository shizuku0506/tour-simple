package com.project.tour.restController;

import com.project.tour.domain.Region;
import com.project.tour.service.RegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@Api(description = "지역 API", basePath = "/region")
@RestController
@RequestMapping(value = "/region")
public class RegionRestController
{
    @Autowired
    private RegionService regionService;

    @ApiOperation(value = "지역 조회 - 전체", notes = "<pre>지역 정보 전체를 조회한다.</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Accept-Language", value = "조회될 언어 파라메터(헤더)", required = false,
                    dataType = "string", paramType = "header", defaultValue = "ko", example = "ko, en")
    })
    @GetMapping(value = {""})
    public ResponseEntity<List> getAllRegion(@RequestHeader Map header)
    {
        // 200, 204, 206
        List<Region> resultList = regionService.getAllRegion(header);

        if (resultList == null)
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(resultList, HttpStatus.OK);
    }

    @ApiOperation(value = "지역 조회 - 단일", notes = "<pre>지역 정보 하나를 조회한다.</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "seq", value = "지역 고유키", required = true, dataType = "string", paramType = "path"
                    , defaultValue = "", example = "정수형의 숫자"),
    })
    @GetMapping(value = "/{seq}")
    public ResponseEntity<Region> getRegion(@PathVariable int seq)
    {
        Region region = regionService.getRegion(seq);

        if (region == null)
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(region, HttpStatus.OK);
    }

    @ApiOperation(value = "지역 등록", notes = "<pre>지역 정보를 저장한다.</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "지역명", required = true, dataType = "string", paramType = "string"
                    , defaultValue = "", example = "한국"),
            @ApiImplicitParam(name = "lang", value = "언어코드(2자리 소문자영문)", required = true, dataType = "string",
                    paramType = "string", defaultValue = "", example = "ko")
    })
    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces =
            {MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity addRegion(@RequestBody Region region)
    {
        if (regionService.addRegion(region) == 1)
        {
            return new ResponseEntity<String>(HttpStatus.CREATED);
        } else
        {
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "지역 삭제", notes = "<pre>지역 정보를 삭제한다.</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "seq", value = "지역 고유키", required = true, dataType = "string", paramType = "path"
                    , defaultValue = "", example = "정수형의 숫자")
    })
    @DeleteMapping(value = "/{seq}")
    public ResponseEntity removeRegion(@PathVariable int seq)
    {
        Region region = regionService.getRegion(seq);

        if (region == null)
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        if (regionService.removeRegion(seq) == 1)
        {
            // 204
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        } else
        {
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "지역 수정", notes = "<pre>지역 정보를 수정한다.</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "seq", value = "지역 고유키", required = true, dataType = "string", paramType = "path"
                    , defaultValue = "", example = "정수형의 숫자"),
            @ApiImplicitParam(name = "title", value = "지역명", required = true, dataType = "string", paramType = "string"
                    , defaultValue = "", example = "한국"),
            @ApiImplicitParam(name = "lang", value = "언어코드(2자리 소문자영문)", required = true, dataType = "string",
                    paramType = "string", defaultValue = "", example = "ko")
    })
    @PatchMapping(value = "/{seq}", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity editRegion(@PathVariable int seq, @RequestBody Region region)
    {
        Region oRegion = regionService.getRegion(seq);
        if (oRegion == null)
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        region.setSeq(seq);

        if (regionService.editRegion(region) == 1)
        {
            return new ResponseEntity<String>(HttpStatus.OK);
        } else
        {
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
