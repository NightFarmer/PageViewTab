package com.nightfarmer.pageviewtab;

import android.view.View;
import android.view.ViewGroup;

/**
 * PageViewTab的标题项提供器
 * Created by zhangfan on 16-7-20.
 */
public interface PageViewTabAdapter {

    /**
     * 获取对应位置的标题项
     * @param parent 标题项目的容器
     * @param position 标题的位置
     * @return 可以为任意的View。
     */
    View getView(ViewGroup parent, int position);

}
