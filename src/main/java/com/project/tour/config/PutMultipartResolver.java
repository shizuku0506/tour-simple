package com.project.tour.config;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * Put으로 파일 전송하기 위해서 구현한 클래스
 * <p>
 * 기본적으로 파일업로드는 post 만 가능함
 */
public class PutMultipartResolver extends CommonsMultipartResolver
{
    private transient static final String MULTIPART = "multipart/";

    @Override
    public boolean isMultipart(HttpServletRequest request)
    {

        return request != null && isMultipartContent(request);
    }

    public static final boolean isMultipartContent(HttpServletRequest request)
    {
        final String method = request.getMethod().toLowerCase();
        if (!method.equals("post") && !method.equals("put"))
        {
            return false;
        }
        String contentType = request.getContentType();
        if (contentType == null)
        {
            return false;
        }
        if (contentType.toLowerCase().startsWith(MULTIPART))
        {
            return true;
        }
        return false;
    }

}
