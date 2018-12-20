package com.project.tour.service.impl;

import com.project.tour.domain.Content;
import com.project.tour.mapper.ContentMapper;
import com.project.tour.service.ContentService;
import com.project.tour.util.ProjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
public class ContentServiceImpl implements ContentService
{
    @Resource
    private ContentMapper contentMapper;

    @Override
    public ResponseEntity<Content> addContent(Content content)
    {
        return null;
    }

    @Override
    public ResponseEntity<List<Content>> getAllContent(String lang)
    {
        lang = ProjectUtils.getAccpetLanguage(lang);
        List<Content> contentList= contentMapper.selectAllContent(lang);

        if (CollectionUtils.isEmpty(contentList))
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(contentList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Content> getContent(int seq)
    {
        return null;
    }

    @Override
    public ResponseEntity<Content> editContent(Content content)
    {
        return null;
    }

    @Override
    public ResponseEntity<Content> removeContent(int seq)
    {
        return null;
    }


}
