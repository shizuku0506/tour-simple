package com.project.tour.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Date;

@ApiModel(value = "Storage - 스토레지 VO", description = "스토레지 정보 데이터")
@Data
public class Storage
{
    @ApiModelProperty(name = "seq", value = "시퀀스 고유키", example = "1")
    private int seq;

    @ApiModelProperty(name = "conSeq", value = "컨텐츠 고유키", example = "1")
    private int conSeq;

    @ApiModelProperty(name = "fileSize", value = "파일 사이즈", example = "10000 단위 바이트")
    private long fileSize;

    @ApiModelProperty(name = "filePath", value = "파일경로", example = "/201812/14")
    private String filePath;

    @ApiModelProperty(name = "fileNm", value = "파일명", example = "이쁜그림파일.jpg")
    private String fileNm;

    @ApiModelProperty(name = "realFileNm", value = "실제저장 파일명", example = "2981832.jpg")
    private String realFileNm;

    @ApiModelProperty(name = "fileExt", value = "확장자", example = "jpg, mp4")
    private String fileExt;

    @ApiModelProperty(name = "runningSec", value = "재생시간", example = "100 초단위로 저장")
    private long runningSec;

    @ApiModelProperty(name = "fileStatus", value = "상태코드", example = "E : 인코딩, F : 실패, S : 성공 , I : 초기업로드")
    private String fileStatus;

    @ApiModelProperty(name = "mimeTp", value = "마임타입", example = "image/jpeg")
    private String mimeTp;

    @ApiModelProperty(name = "createDt", value = "생성일", example = "2018-12-16")
    private Date createDt;
}
