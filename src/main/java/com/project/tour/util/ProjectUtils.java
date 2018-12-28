package com.project.tour.util;

import com.project.tour.ProjectConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    public static String getAccpetLanguage(String lang)
    {
        log.debug(lang);
        if (lang.indexOf(',') != -1)
        {
            lang = lang.substring(0, lang.indexOf(','));
        }
        return lang;
    }

    public static String getDate(String pattern)
    {
        SimpleDateFormat sdf = null;
        if (StringUtils.isBlank(pattern))
        {
            sdf = new SimpleDateFormat(ProjectUtils.getDate(pattern));
        } else
        {
            sdf = new SimpleDateFormat(pattern);
        }
        return sdf.format(new Date());
    }
}
