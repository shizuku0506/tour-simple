package com.project.tour.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Date;

@ApiModel(value = "Region - 지역정보 VO" , description = "지역 정보 데이터")
@Data
public class Region {

    @ApiModelProperty(name = "seq" , value = "시퀀스 고유키" , example = "1" )
    private int seq;

    @ApiModelProperty(name = "title" , value = "지역명" , example = "한국" )
    private String title;

    @ApiModelProperty(name = "lang" , value = "언어코드(2자리, 소문자영문)" , example = "ko" )
    private String lang;

    @ApiModelProperty(name = "createDt" , value = "생성일" , example = "2018-12-16" )
    private Date createDt;

    @ApiModelProperty(name = "updateDt" , value = "수정일" , example = "2018-12-18" )
    private Date updateDt;

    @ApiModelProperty(name = "delYn" , value = "삭제여부" , example = "N" )
    private String delYn;

}
