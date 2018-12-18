package com.project.tour.util;

import com.project.tour.ProjectConstant;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class ProjectUtils
{
    public static String getAccpetLanguage(Map header)
    {
        log.debug(header.toString());
        if (header == null)
        {
            return ProjectConstant.REQ_ACCEPT_LANG;
        }
        if (header.get(ProjectConstant.HEADER_ACCEPT_LANG) == null)
        {
            return ProjectConstant.REQ_ACCEPT_LANG;
        }
        return (String) header.get(ProjectConstant.HEADER_ACCEPT_LANG);
    }
}
