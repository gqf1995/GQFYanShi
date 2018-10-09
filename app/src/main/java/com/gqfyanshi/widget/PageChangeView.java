package com.gqfyanshi.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.gqfyanshi.R;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 郭青枫 on 2018/8/13 0013.
 */

public class PageChangeView extends FrameLayout {
    public ImageView iv_max_left;
    public ImageView iv_left;
    public TagFlowLayout tag_pages;
    public ImageView iv_right;
    public ImageView iv_max_right;
    public TextView tv_page;

    DefaultClickLinsener defaultClickLinsener;
    int nowPage = 1;
    int maxPage = 1;
    List<String> titles;

    public void setDefaultClickLinsener(DefaultClickLinsener defaultClickLinsener) {
        this.defaultClickLinsener = defaultClickLinsener;
    }

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
        if (tag_pages != null) {
            initPageTags();
        }
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        maxPage = maxPage / 10 + (maxPage % 10 > 0 ? 1 : 0);
        this.maxPage = maxPage;
        if (tag_pages != null) {
            initPageTags();
        }
    }

    private void initPageTags() {
        titles = new ArrayList<>();
        for (int i = 1; i <= maxPage; i++) {
            if (maxPage <= 7) {
                titles.add(i + "");
            } else {
                if (titles.size() < 7) {
                    if (nowPage + i - 4 > 0) {
                        if (nowPage + i - 4 > maxPage) {
                            titles.add(0, Integer.parseInt(titles.get(0)) - 1 + "");
                        } else {
                            titles.add(nowPage + i - 4 + "");
                        }
                    }
                }
            }
        }
        tag_pages.setAdapter(new TagAdapter<String>(titles) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv_title = (TextView) ((LayoutInflater) tag_pages.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_page_item,
                        parent, false);
                tv_title.setText(s);
                if (Integer.parseInt(s) == nowPage) {
                    tv_title.setTextColor(CommonUtils.getColor(R.color.mark_color));
                } else {
                    tv_title.setTextColor(CommonUtils.getColor(R.color.color_font2));
                }
                return tv_title;
            }

            @Override
            public void onSelected(int position, View view) {
                super.onSelected(position, view);
                nowPage = Integer.parseInt(titles.get(position));
                initPageTags();
                defaultClickLinsener.onClick(view, nowPage, null);
            }
        });
        tv_page.setText("当前" + nowPage + "页,共" + maxPage + "页");
    }


    public PageChangeView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public PageChangeView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public PageChangeView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = layoutInflater.inflate(R.layout.layout_page, null);
        this.iv_max_left = (ImageView) rootView.findViewById(R.id.iv_max_left);
        this.iv_left = (ImageView) rootView.findViewById(R.id.iv_left);
        this.tag_pages = (TagFlowLayout) rootView.findViewById(R.id.tag_pages);
        this.iv_right = (ImageView) rootView.findViewById(R.id.iv_right);
        this.iv_max_right = (ImageView) rootView.findViewById(R.id.iv_max_right);
        this.tv_page = (TextView) rootView.findViewById(R.id.tv_page);

        iv_max_left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                nowPage = 1;
                defaultClickLinsener.onClick(v, nowPage, null);
                initPageTags();
            }
        });
        iv_left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nowPage > 1) {
                    nowPage = nowPage - 1;
                }
                defaultClickLinsener.onClick(v, nowPage, null);
                initPageTags();
            }
        });
        iv_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nowPage < maxPage) {
                    nowPage = nowPage + 1;
                }
                defaultClickLinsener.onClick(v, nowPage, null);
                initPageTags();
            }
        });
        iv_max_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                nowPage = maxPage;
                defaultClickLinsener.onClick(v, nowPage, null);
                initPageTags();
            }
        });
        this.addView(rootView);

    }

}
