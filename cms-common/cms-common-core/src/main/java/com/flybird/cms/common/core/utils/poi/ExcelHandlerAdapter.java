package com.flybird.cms.common.core.utils.poi;

/**
 * description: Excel数据格式处理适配器
 *
 * @author: flybird
 * @date: 2021-12-28 21:14:35
 */
public interface ExcelHandlerAdapter
{
    /**
     * 格式化
     * 
     * @param value 单元格数据值
     * @param args excel注解args参数组
     *
     * @return 处理后的值
     */
    Object format(Object value, String[] args);
}
