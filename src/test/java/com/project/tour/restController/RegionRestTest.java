package com.project.tour.restController;

import com.project.tour.TourApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@RestClientTest
@ActiveProfiles("local")
//@FixMethodOrder(MethodSorters.NAME_ASCENDING) // method 네이밍 순서대로 ASC
//@WebMvcTest
public class RegionRestTest extends TourApplicationTests
{
    @Autowired
    private MockRestServiceServer server;

//    @Autowired
//    MockMvc mockMvc;

    @Test
    public void test1getAllResion()
    {

//        ProjectConstant.TEST_LOCAL_PROT
//        server.expect(requestTo("http://localhost:8088/region")).andRespond(withSuccess("", HttpStatus.OK));
    }
}
