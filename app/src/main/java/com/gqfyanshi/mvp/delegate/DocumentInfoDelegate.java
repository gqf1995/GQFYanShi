package com.gqfyanshi.mvp.delegate;

import android.graphics.Color;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ebensz.eink.api.PennableLayout;
import com.ebensz.eink.api.StrokeRecognizer;
import com.ebensz.recognizer.latest.Result;
import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.gqfyanshi.R;

public class DocumentInfoDelegate extends BaseDelegate {
    public ViewHolder viewHolder;
    private PennableLayout mPennable;
    private StrokeRecognizer mRecognizer;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); //隐藏键盘
        mPennable = viewHolder.ink;
        mPennable.setStrokeColor(Color.BLACK);
        mPennable.setStrokeWidth(9.0f);
        mPennable.setSideKeyEnable(true);
        mRecognizer = new StrokeRecognizer(this.getActivity().getApplicationContext());
        mRecognizer.setOnResultListener(new StrokeRecognizer.OnResultListener() {
            @Override
            public void onComplete(int sessionId, Result result) {

            }

            @Override
            public void onCancel(int sessionId) {
            }
        });
        mPennable.showPenIconOnTaskBar(true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_document_info;
    }


    public static class ViewHolder {
        public View rootView;

        public TextView tv_title;
        public TextView tv_time;
        public TextView tv_file;
        public LinearLayout lin_file;
        public TextView tv_content_info;
        public LinearLayout lin_content_info;
        public TextView tv_content;
        public LinearLayout lin_content;
        public LinearLayout lin_edit;
        public PennableLayout ink;
        public FrameLayout fl_root;

        public ViewHolder(View rootView) {
            this.rootView = rootView;

            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_time = (TextView) rootView.findViewById(R.id.tv_time);
            this.tv_file = (TextView) rootView.findViewById(R.id.tv_file);
            this.lin_file = (LinearLayout) rootView.findViewById(R.id.lin_file);
            this.tv_content_info = (TextView) rootView.findViewById(R.id.tv_content_info);
            this.lin_content_info = (LinearLayout) rootView.findViewById(R.id.lin_content_info);
            this.tv_content = (TextView) rootView.findViewById(R.id.tv_content);
            this.lin_content = (LinearLayout) rootView.findViewById(R.id.lin_content);
            this.lin_edit = (LinearLayout) rootView.findViewById(R.id.lin_edit);
            this.ink = (PennableLayout) rootView.findViewById(R.id.ink);
            this.fl_root = (FrameLayout) rootView.findViewById(R.id.fl_root);
        }

    }
}