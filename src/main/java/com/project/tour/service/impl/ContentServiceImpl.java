package com.project.tour.service.impl;

import com.project.tour.domain.Content;
import com.project.tour.mapper.ContentMapper;
import com.project.tour.service.ContentService;
import com.project.tour.util.ProjectUtils;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public List<Content> getAllContent(Map header)
    {
        String lang = ProjectUtils.getAccpetLanguage(header);
        List<Content> contentList= contentMapper.selectAllContent(lang);
        return contentList;
    }

    @Resource
    private ContentMapper contentMapper;

    @Override
    public int addContent(Content content)
    {
        return 0;
    }

    @Override
    public Content getContent(int seq)
    {
        return null;
    }

    @Override
    public int editContent(Content content)
    {
        return 0;
    }

    @Override
    public int removeContent(int seq)
    {
        return 0;
    }
}
