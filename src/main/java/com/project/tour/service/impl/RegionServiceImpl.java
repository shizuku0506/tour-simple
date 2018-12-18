package com.project.tour.service.impl;

import com.project.tour.domain.Region;
import com.project.tour.mapper.RegionMapper;
import com.project.tour.service.RegionService;
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
public class RegionServiceImpl implements RegionService
{
    @Resource
    private RegionMapper regionMapper;

    @Override
    public int addRegion(Region region)
    {
        return regionMapper.insertRegion(region);
    }

    @Override
    public List<Region> getAllRegion(Map header)
    {
        String lang = ProjectUtils.getAccpetLanguage(header);
        return regionMapper.selectAllRegion(lang);
    }

    @Override
    public Region getRegion(int seq)
    {
        return regionMapper.selectRegion(seq);
    }

    @Override
    public int editRegion(Region region)
    {
        return regionMapper.updateRegion(region);
    }

    @Override
    public int removeRegion(int seq)
    {
        return regionMapper.deleteRegion(seq);
    }
}
