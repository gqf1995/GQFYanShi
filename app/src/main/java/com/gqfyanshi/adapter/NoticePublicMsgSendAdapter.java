package com.gqfyanshi.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.gqfyanshi.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by 郭青枫 on 2018/1/10 0010.
 */

public class NoticePublicMsgSendAdapter extends CommonAdapter<String> {


    private LinearLayout lin_root;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;

    public NoticePublicMsgSendAdapter(Context context, List<String> datas) {
        super(context, R.layout.adapter_notice_public_msg_send, datas);

    }

    public void setData(List<String> datas) {
        this.mDatas.clear();
        this.mDatas.addAll(datas);
        this.notifyDataSetChanged();
    }

    @Override
    protected void convert(ViewHolder holder, String s, final int position) {
        lin_root = holder.getView(R.id.lin_root);
        lin_root.setBackgroundColor(CommonUtils.getColor(position % 2 == 0 ? R.color.white : R.color.transparent));
        tv1 = holder.getView(R.id.tv1);
        tv2 = holder.getView(R.id.tv2);
        tv3 = holder.getView(R.id.tv3);
        tv4 = holder.getView(R.id.tv4);


        tv1.setText(s);
        lin_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show(getDatas().get(position));
            }
        });
    }

}