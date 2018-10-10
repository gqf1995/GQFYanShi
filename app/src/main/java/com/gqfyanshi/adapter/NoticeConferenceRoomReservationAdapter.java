package com.gqfyanshi.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.gqfyanshi.R;
import com.gqfyanshi.entity.bean.DocumentBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by 郭青枫 on 2018/1/10 0010.
 */

public class NoticeConferenceRoomReservationAdapter extends CommonAdapter<DocumentBean> {


    private LinearLayout lin_root;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private TextView tv7;
    DefaultClickLinsener defaultClickLinsener;

    public void setDefaultClickLinsener(DefaultClickLinsener defaultClickLinsener) {
        this.defaultClickLinsener = defaultClickLinsener;
    }

    public NoticeConferenceRoomReservationAdapter(Context context, List<DocumentBean> datas) {
        super(context, R.layout.adapter_notice_conference_room_reservation, datas);

    }

    public void setData(List<DocumentBean> datas) {
        this.mDatas.clear();
        this.mDatas.addAll(datas);
        this.notifyDataSetChanged();
    }

    @Override
    protected void convert(ViewHolder holder, DocumentBean s, final int position) {
        lin_root = holder.getView(R.id.lin_root);
        lin_root.setBackgroundColor(CommonUtils.getColor(position % 2 == 0 ? R.color.white : R.color.transparent));
        tv1 = holder.getView(R.id.tv1);
        tv2 = holder.getView(R.id.tv2);
        tv3 = holder.getView(R.id.tv3);
        tv4 = holder.getView(R.id.tv4);
        tv5 = holder.getView(R.id.tv5);
        tv6 = holder.getView(R.id.tv6);
        tv7 = holder.getView(R.id.tv7);

        //状态 00-待审核 01-审核通过 02-审核失败

        tv1.setText(s.getId());
        tv2.setText(s.getDepartmentName());
        tv3.setText(s.getUsername());
        tv4.setText(s.getPurpose());
        tv5.setText(s.getBegin_time() + "-" + s.getEnd_time());
        if ("00".equals(s.getStatus())) {
            tv6.setText("待审核");
        }
        if ("01".equals(s.getStatus())) {
            tv6.setText("审核通过");
        }
        if ("02".equals(s.getStatus())) {
            tv6.setText("审核失败");
        }


        tv7.setText("详情");
        tv7.setTextColor(CommonUtils.getColor(R.color.mark_color));
        tv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defaultClickLinsener.onClick(v, position, null);
            }
        });
    }

}