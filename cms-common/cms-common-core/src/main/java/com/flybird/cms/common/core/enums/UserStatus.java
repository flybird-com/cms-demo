package com.flybird.cms.common.core.enums;

/**
 * description: 用户状态
 *
 * @author: flybird
 * @date: 2021-12-28 22:08:23
 */
public enum UserStatus {

    OK("0", "正常"), DISABLE("1", "停用"), DELETED("2", "删除");

    private final String code;
    private final String info;

    UserStatus(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
