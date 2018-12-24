package com.project.tour;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * <pre>
 * 테스트 케이스 추상 클래스
 *     - FixMethodOrder : method 네이밍 순서대로 실행 (prefix : test{num} )
 * </pre>
 *
 */
@Slf4j
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TourApplication.class)
@AutoConfigureMockMvc
public abstract class AbstractRestTest
{
    protected String mapToJson(Object obj) throws JsonProcessingException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> clazz) throws IOException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    protected String getLogResult(final ResultActions resultActions)
    {
        String resultString = StringUtils.EMPTY;
        try
        {
            resultString = resultActions.andReturn().getResponse().getContentAsString();
            log.info(String.format("[TEST] result - %s", resultString));
        } catch (UnsupportedEncodingException e)
        {
            log.warn("[TEST] result - contentAsString Fail !!!");
            log.warn(e.getMessage());
        }
        return resultString;
    }
}
