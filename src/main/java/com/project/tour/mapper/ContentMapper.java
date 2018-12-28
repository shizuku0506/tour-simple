package com.project.tour.mapper;

import com.project.tour.domain.Content;
import com.project.tour.domain.Storage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContentMapper
{
    List<Content> selectContent(Content content);

    int insertContent(Content content);

    int insertStorage(List<Storage> storageList);
}
