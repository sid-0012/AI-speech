package com.jiawa.nls.business.resp;

import lombok.Data;

import java.util.List;

@Data
public class PageResp<T> {

    /**
     * 总条数
     */
    protected long total;

    /**
     * 列表内容
     */
    protected List<T> list;
}
