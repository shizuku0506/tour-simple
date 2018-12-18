package com.project.tour.service;

import com.project.tour.domain.Content;
import com.project.tour.domain.Region;

import java.util.List;
import java.util.Map;

public interface ContentService
{

    /**
     * 컨텐츠 생성
     *
     * @param content
     * @return
     */
    public int addContent(Content content);

    /**
     * 전체 컨텐츠 조회
     *
     * @param header
     * @return
     */
    public List<Content> getAllContent(Map header);

    /**
     * 컨텐츠 조회
     *
     * @param seq
     * @return
     */
    public Content getContent(int seq);

    /**
     * 컨텐츠 수정
     *
     * @param content
     * @return
     */
    public int editContent(Content content);

    /**
     * 컨텐츠 삭제
     *
     * @param seq
     * @return
     */
    public int removeContent(int seq);

}
