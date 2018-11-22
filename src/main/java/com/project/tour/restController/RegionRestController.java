package com.project.tour.restController;

import com.project.tour.domain.Region;
import com.project.tour.mapper.RegionMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class RegionRestController
{
	private static final transient org.slf4j.Logger logger = LoggerFactory.getLogger(RegionRestController.class);

	@Resource private RegionMapper regionMapper;

	@GetMapping(value = { "", "/" }) public Region mainCall()
	{

		logger.info("@@@@@@@@@@@@@@@@@@@@");

		Region region = new Region();

		region.setSeq(0);
		region.setTitle("test");
		logger.info(region.toString());

		List<Region> list = regionMapper.findRegion(1);

		for (Region r : list)
		{
			logger.warn(r.toString());
		}

		return region;
	}
}
