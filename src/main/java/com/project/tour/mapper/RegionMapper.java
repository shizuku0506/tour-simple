package com.project.tour.mapper;

import com.project.tour.domain.Region;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RegionMapper
{
    Region selectRegion(int seq);

    List<Region> selectAllRegion(String lang);

    int insertRegion(Region region);

    int deleteRegion(int seq);

    int updateRegion(Region region);
}
