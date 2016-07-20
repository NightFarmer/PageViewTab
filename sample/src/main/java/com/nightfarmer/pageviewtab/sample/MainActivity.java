package com.nightfarmer.pageviewtab.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nightfarmer.pageviewtab.PageViewTab;
import com.nightfarmer.pageviewtab.PageViewTabAdapter;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> titleList = Arrays.asList(
            "标题的",
            "字符串长度",
            "是",
            "不确定的",
            "高亮的部分",
            "可以",
            "随标题的宽度",
            "而改变"
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);


        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                MyFragment myFragment = new MyFragment();
                myFragment.mark = position;
                return myFragment;
            }

            @Override
            public int getCount() {
                return titleList.size();
            }
        });

        PageViewTab pageViewTab = (PageViewTab) findViewById(R.id.pageViewTab);
        pageViewTab.applyViewPager(viewPager, new PageViewTabAdapter() {

            @Override
            public View getView(ViewGroup parent, int position) {
                View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_item, parent, false);
                TextView tv_title = (TextView) inflate.findViewById(R.id.tv_title);
                tv_title.setText(titleList.get(position));
                return inflate;
            }
        }, R.layout.high_light_item);

    }


}
