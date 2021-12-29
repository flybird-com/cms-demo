package com.flybird.cms.system.api.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * description: 文件信息
 *
 * @author: flybird
 * @date: 2021-12-29 21:14:56
 */
public class SysFile {

    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件地址
     */
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("name", getName())
                .append("url", getUrl())
                .toString();
    }
}
