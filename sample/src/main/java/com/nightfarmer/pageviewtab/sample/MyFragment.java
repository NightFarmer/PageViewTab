package com.nightfarmer.pageviewtab.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 测试用Fragment
 * Created by zhangfan on 16-7-20.
 */
public class MyFragment extends Fragment {

    int mark;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.my_fragment, container, false);
        TextView tv_title = (TextView) inflate.findViewById(R.id.tv_title);
        tv_title.setText("" + mark);
        return inflate;
    }
}
