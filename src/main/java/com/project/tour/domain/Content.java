package com.project.tour.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.List;

@ApiModel(value = "Content - 컨텐츠 VO", description = "컨텐츠 정보 데이터")
@Data
public class Content
{
    @ApiModelProperty(name = "seq", value = "시퀀스 고유키", example = "1")
    private int seq;

    @ApiModelProperty(name = "title", value = "제목", example = "경복궁")
    private String title;

    @ApiModelProperty(name = "description", value = "내용", example = "본문내용입니다.")
    private String description;

    @ApiModelProperty(name = "lang", value = "언어코드(2자리, 소문자영문)", example = "ko")
    private String lang;

    @ApiModelProperty(name = "createDt", value = "생성일", example = "2018-12-16")
    private Date createDt;

    @ApiModelProperty(name = "updateDt", value = "수정일", example = "2018-12-18")
    private Date updateDt;

    @ApiModelProperty(name = "delYn", value = "삭제여부", example = "N")
    private String delYn;

    private List<Storage> storageList;

    private List<MultipartFile> fileList;
}
