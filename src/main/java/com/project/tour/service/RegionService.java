package com.project.tour.service;

import com.project.tour.domain.Region;

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
    public int addRegion(Region region);

    /**
     * 전체 지역 조회
     *
     * @param header
     * @return
     */
    public List<Region> getAllRegion(Map header);

    /**
     * 지역 조회
     *
     * @param seq
     * @return
     */
    public Region getRegion(int seq);

    /**
     * 지역 수정
     *
     * @param region
     * @return
     */
    public int editRegion(Region region);

    /**
     * 지역 삭제
     *
     * @param seq
     * @return
     */
    public int removeRegion(int seq);


}
