package com.nightfarmer.pageviewtab;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * PageViewTab控件，监听ViewPager的滑动而进行自动切换，可点击标题进行快速切换，可滑动标题。
 * Created by zhangfan on 16-7-20.
 */
public class PageViewTab extends HorizontalScrollView {
    //    private ViewPager viewPager;
    private LinearLayout tab_content;
    private RelativeLayout current_tab;

    private LayoutInflater layoutInflater;
    private int count;

    public PageViewTab(Context context) {
        this(context, null);
    }

    public PageViewTab(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PageViewTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        if (isInEditMode()) return;
        setOverScrollMode(View.OVER_SCROLL_NEVER);
        layoutInflater = LayoutInflater.from(context);
        View content = layoutInflater.inflate(R.layout.page_view_tab_init_content, this, false);
        addView(content);
        tab_content = (LinearLayout) content.findViewById(R.id.tab_content);
        current_tab = (RelativeLayout) content.findViewById(R.id.current_tab);
    }

    public void applyViewPager(final ViewPager viewPager, PageViewTabAdapter pageViewTabAdapter, @LayoutRes int highLightLayout) {
//        this.viewPager = viewPager;
//        this.pageViewTabAdapter = pageViewTabAdapter;

        if (viewPager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }

        count = viewPager.getAdapter().getCount();
        for (int i = 0; i < count; i++) {
            View tabItem = pageViewTabAdapter.getView(tab_content, i);
            tab_content.addView(tabItem);
            tabItem.setClickable(true);
            final int finalI = i;
            tabItem.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem(finalI);
                }
            });
        }

        current_tab.addView(layoutInflater.inflate(highLightLayout, current_tab, false));

        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                scrollToChild(position, positionOffset);
            }
        });
    }

    private void scrollToChild(int position, float positionOffset) {
        View childAt = tab_content.getChildAt(position);
        int left = childAt.getLeft();

        scrollTo(left + (int) (positionOffset * childAt.getWidth()) - 52, 0);

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) current_tab.getLayoutParams();
        if (position < count - 1) {
            View childNext = tab_content.getChildAt(position + 1);
            int tab_left = (int) (childNext.getLeft() * positionOffset + childAt.getLeft() * (1f - positionOffset));
            int tab_right = (int) (childNext.getRight() * positionOffset + childAt.getRight() * (1f - positionOffset));
            layoutParams.leftMargin = tab_left;
            layoutParams.width = tab_right - tab_left;
            current_tab.setLayoutParams(layoutParams);
        }
    }


}
