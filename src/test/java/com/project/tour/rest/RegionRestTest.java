package com.project.tour.rest;

import com.project.tour.AbstractRestTest;
import com.project.tour.domain.Region;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegionRestTest extends AbstractRestTest
{
    @Autowired
    private MockMvc mvc;

    private static int regionSeq;


    /**
     * 등록 테스트
     *
     * @throws Exception
     */
    @Test
    public void test1_addRegion() throws Exception
    {
        // init parameter
        String title = "test code title";
        String lang = "ko";
        Region region = new Region();
        region.setTitle(title);
        region.setLang(lang);
        String jsonStr = super.mapToJson(region);

        // excute
        ResultActions resultActions = mvc.perform(post("/region")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonStr)
        );

        // Http status code 검증
        log.info(String.format("Http Status Code %d", resultActions.andReturn().getResponse().getStatus()));
        resultActions
                .andExpect(status().isCreated());

        String resultStr = super.getLogResult(resultActions);

        // data 검증
        Region nRegion = super.mapFromJson(resultStr, Region.class);
        Assert.assertEquals(title, nRegion.getTitle());
        Assert.assertEquals(lang, nRegion.getLang());
        regionSeq = nRegion.getSeq();
    }

    /**
     * 전체조회 테스트
     *
     * @throws Exception
     */
    @Test
    public void test2_getAllRegion() throws Exception
    {
        // excute
        ResultActions resultActions = mvc.perform(get("/region")
                .contentType(MediaType.APPLICATION_JSON));

        // Http status code 검증
        log.info(String.format("Http Status Code %d", resultActions.andReturn().getResponse().getStatus()));
        resultActions
                .andExpect(status().isOk());

        super.getLogResult(resultActions);
    }

    /**
     * 단일 조회 테스트
     *
     * @throws Exception
     */
    @Test
    public void test3_getRegion() throws Exception
    {
        // excute
        ResultActions resultActions = mvc.perform(get("/region" + "/" + String.valueOf(regionSeq)));

        // Http status code 검증
        log.info(String.format("Http Status Code %d", resultActions.andReturn().getResponse().getStatus()));
        resultActions
                .andExpect(status().isOk());

        super.getLogResult(resultActions);
    }

    /**
     * 수정 테스트
     *
     * @throws Exception
     */
    @Test
    public void test4_editRegion() throws Exception
    {
        // init parameter
        String title = "테스트 코드 타이틀";
        String lang = "ko";
        Region region = new Region();
        region.setTitle(title);
        region.setLang(lang);
        String jsonStr = super.mapToJson(region);

        // excute
        ResultActions resultActions = mvc.perform(patch("/region" + "/" + String.valueOf(regionSeq))
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonStr)
        );
        // Http status code 검증
        log.info(String.format("Http Status Code %d", resultActions.andReturn().getResponse().getStatus()));
        resultActions
                .andExpect(status().isOk());

        super.getLogResult(resultActions);
    }

    /**
     * 삭제 테스트
     *
     * @throws Exception
     */
    @Test
    public void test5_removeRegion() throws Exception
    {
        // excute
        ResultActions resultActions = mvc.perform(delete("/region" + "/" + String.valueOf(regionSeq)));

        // Http status code 검증
        log.info(String.format("Http Status Code %d", resultActions.andReturn().getResponse().getStatus()));
        resultActions
                .andExpect(status().isNoContent());

        super.getLogResult(resultActions);
    }


}
