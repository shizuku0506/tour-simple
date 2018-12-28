package com.project.tour;

public class ProjectConstant
{
    public transient static final String REQ_ACCEPT_LANG = "ko";

    public transient static final String HEADER_ACCEPT_LANG = "accept-language";

    public transient static final String DEF_DATE_FORMAT = "yyyyMMdd";

    public enum ENCODING_STATUS
    {
        init("I", "초기값"), running("R", "진행중"), complate("C", "완료");

        private final String code;

        private final String value;

        ENCODING_STATUS(String code, String value)
        {
            this.code = code;
            this.value = value;
        }

        public String getCode()
        {
            return code;
        }

        public String getValue()
        {
            return value;
        }
    }
}
