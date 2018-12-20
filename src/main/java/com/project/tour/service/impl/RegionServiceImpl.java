package com.project.tour.service.impl;

import com.project.tour.domain.Region;
import com.project.tour.exception.InternalServerException;
import com.project.tour.mapper.RegionMapper;
import com.project.tour.service.RegionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
@Transactional
public class RegionServiceImpl implements RegionService
{
    @Resource
    private RegionMapper regionMapper;

    @Override
    public ResponseEntity<Region> addRegion(Region region)
    {
        if (regionMapper.insertRegion(region) == 1)
        {
            return new ResponseEntity<>(regionMapper.selectRegion(region.getSeq()), HttpStatus.CREATED);
        } else
        {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @Cacheable(value = "getAllRegion", key = "#lang")
    public ResponseEntity<List<Region>> getAllRegion(String lang)
    {
        if (lang.indexOf(',') != -1)
        {
            lang = lang.substring(0, lang.indexOf(','));
        }
        List<Region> resultList = regionMapper.selectAllRegion(lang);

        if (CollectionUtils.isEmpty(resultList))
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(resultList, HttpStatus.OK);
    }

    @Override
    @Cacheable(value = "getRegion", key = "#seq")
    public ResponseEntity<Region> getRegion(int seq)
    {
        Region region = regionMapper.selectRegion(seq);
        if (region == null)
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(region, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Region> editRegion(Region region)
    {
        Region oRegion = regionMapper.selectRegion(region.getSeq());

        if (oRegion == null)
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        int result = regionMapper.updateRegion(region);

        if (result == 1)
        {
            return new ResponseEntity<Region>(regionMapper.selectRegion(region.getSeq()), HttpStatus.OK);
        } else
        {
            throw new InternalServerException();
        }

    }

    @Override
    public ResponseEntity<Region> removeRegion(int seq)
    {
        if (regionMapper.deleteRegion(seq) == 1)
        {
            // 204
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else
        {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
