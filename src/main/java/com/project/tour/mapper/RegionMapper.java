package com.project.tour.mapper;

import com.project.tour.domain.Region;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RegionMapper
{
	@Select("select * from tb_region where seq = #{seq}") List<Region> findRegion(@Param("seq") int seq);
}
