package com.project.tour.mapper;

import com.project.tour.domain.Content;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContentMapper
{
    List<Content> selectAllContent(String lang);
}
