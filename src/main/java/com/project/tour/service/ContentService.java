package com.project.tour.service;

import com.project.tour.domain.Content;
import com.project.tour.domain.Content;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ContentService
{

    /**
     * 콘텐츠 생성
     *
     * @param content
     * @return
     */
    public ResponseEntity<Content> addContent(Content content);

    /**
     * 전체 콘텐츠 조회
     *
     * @param lang
     * @return
     */
    public ResponseEntity<List<Content>> getAllContent(String lang);

    /**
     * 콘텐츠 조회
     *
     * @param seq
     * @return
     */
    public ResponseEntity<Content> getContent(int seq);

    /**
     * 콘텐츠 수정 - 메타데이터
     *
     * @param content
     * @return
     */
    public ResponseEntity<Content> updatePatchContent(Content content);

    /**
     * 콘텐츠 수정 - 메타 + 파일
     *
     * @param content
     * @return
     */
    public ResponseEntity<Content> updatePutContent(Content content);

    /**
     * 콘텐츠 삭제
     *
     * @param seq
     * @return
     */
    public ResponseEntity<Content> removeContent(int seq);

}
