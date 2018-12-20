package com.project.tour.service;

import com.project.tour.domain.Region;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface RegionService
{
    /**
     * 지역 생성
     *
     * @param region
     * @return
     */
    public ResponseEntity<Region> addRegion(Region region);

    /**
     * 전체 지역 조회
     *
     * @param lang
     * @return
     */
    public ResponseEntity<List<Region>> getAllRegion(String lang);

    /**
     * 지역 조회
     *
     * @param seq
     * @return
     */
    public ResponseEntity<Region> getRegion(int seq);

    /**
     * 지역 수정
     *
     * @param region
     * @return
     */
    public ResponseEntity<Region> editRegion(Region region);

    /**
     * 지역 삭제
     *
     * @param seq
     * @return
     */
    public ResponseEntity<Region> removeRegion(int seq);


}
