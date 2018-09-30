package com.gqfyanshi.mvp.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindFragment;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.gqfyanshi.adapter.DocumentListAdapter;
import com.gqfyanshi.entity.bean.DocumentListBean;
import com.gqfyanshi.mvp.activity.file.DocumentInfoActivity;
import com.gqfyanshi.mvp.activity.notice.document.NoticeReceiveOfficialDocumentActivity;
import com.gqfyanshi.mvp.databinder.ReceivinOofficialDocumentsFragmentBinder;
import com.gqfyanshi.mvp.delegate.ReceivinOofficialDocumentsFragmentDelegate;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

public class ReceivinOofficialDocumentsFragment extends BaseDataBindFragment<ReceivinOofficialDocumentsFragmentDelegate, ReceivinOofficialDocumentsFragmentBinder> {

    DocumentListAdapter adapter1;
    DocumentListAdapter adapter2;
    DocumentListAdapter adapter3;
    DocumentListAdapter adapter4;
    private String name = "";
    private String title = "";
    private String type = "";

    @Override
    protected Class<ReceivinOofficialDocumentsFragmentDelegate> getDelegateClass() {
        return ReceivinOofficialDocumentsFragmentDelegate.class;
    }

    @Override
    public ReceivinOofficialDocumentsFragmentBinder getDataBinder(ReceivinOofficialDocumentsFragmentDelegate viewDelegate) {
        return new ReceivinOofficialDocumentsFragmentBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        addRequest(binder.document_docIndex(this));
        viewDelegate.viewHolder.recycler_view1.setLayoutManager(new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        viewDelegate.viewHolder.recycler_view2.setLayoutManager(new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        viewDelegate.viewHolder.recycler_view3.setLayoutManager(new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        viewDelegate.viewHolder.recycler_view4.setLayoutManager(new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        List<String> datas = new ArrayList<>();
        datas.add("全部");
        datas.add("党委");
        datas.add("党委文件");
        datas.add("政府文件");
        datas.add("政府部门文件");
        viewDelegate.viewHolder.selectPeopleLayout1.setDatas(datas);
        viewDelegate.viewHolder.selectPeopleLayout1.setDefaultClickLinsener(new DefaultClickLinsener() {
            @Override
            public void onClick(View view, int position, Object item) {
                if (position == 0) {
                    type = "";
                } else {
                    type = "0" + position;
                }
            }
        });
        viewDelegate.viewHolder.tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoticeReceiveOfficialDocumentActivity.startAct(getActivity(),
                        viewDelegate.viewHolder.et_num.getText().toString(),
                        viewDelegate.viewHolder.et_name.getText().toString(),
                        viewDelegate.viewHolder.selectTimeLayout1.getSelectTime(),
                        viewDelegate.viewHolder.selectTimeLayout2.getSelectTime(),
                        type
                );
            }
        });


        //        2.公文发送页面四个方框更多跳转链接
        //
        //        搜索部分字段:
        //        文件字号： name
        //        文件名称：title
        //        发布时间：createtime/updatetime
        //        文件属性：type （01 党委 02 党群口 03 政府文件 04 政府部门文件）
        viewDelegate.viewHolder.lin_title1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoticeReceiveOfficialDocumentActivity.startAct(getActivity(), "01");
            }
        });
        viewDelegate.viewHolder.lin_title2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoticeReceiveOfficialDocumentActivity.startAct(getActivity(), "02");
            }
        });
        viewDelegate.viewHolder.lin_title3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoticeReceiveOfficialDocumentActivity.startAct(getActivity(), "03");
            }
        });
        viewDelegate.viewHolder.lin_title4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoticeReceiveOfficialDocumentActivity.startAct(getActivity(), "04");
            }
        });
    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                List<DocumentListBean> list1 = GsonUtil.getInstance().toList(data,
                        "listCommittee", DocumentListBean.class);
                List<DocumentListBean> list2 = GsonUtil.getInstance().toList(data,
                        "listParty", DocumentListBean.class);
                List<DocumentListBean> list3 = GsonUtil.getInstance().toList(data,
                        "listDepartment", DocumentListBean.class);
                List<DocumentListBean> list4 = GsonUtil.getInstance().toList(data,
                        "listGovernment", DocumentListBean.class);
                adapter1 = new DocumentListAdapter(getActivity(), list1);
                adapter2 = new DocumentListAdapter(getActivity(), list2);
                adapter3 = new DocumentListAdapter(getActivity(), list3);
                adapter4 = new DocumentListAdapter(getActivity(), list4);
                viewDelegate.viewHolder.recycler_view1.setAdapter(adapter1);
                viewDelegate.viewHolder.recycler_view2.setAdapter(adapter2);
                viewDelegate.viewHolder.recycler_view3.setAdapter(adapter3);
                viewDelegate.viewHolder.recycler_view4.setAdapter(adapter4);
                adapter1.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                        DocumentInfoActivity.startAct(
                                viewDelegate.getActivity(),
                                adapter1.getDatas().get(position).getId()
                        );
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                        return false;
                    }
                });
                adapter2.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                        DocumentInfoActivity.startAct(
                                viewDelegate.getActivity(),
                                adapter2.getDatas().get(position).getId()
                        );

                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                        return false;
                    }
                });
                adapter3.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                        DocumentInfoActivity.startAct(
                                viewDelegate.getActivity(),
                                adapter3.getDatas().get(position).getId()
                        );

                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                        return false;
                    }
                });
                adapter4.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                        DocumentInfoActivity.startAct(
                                viewDelegate.getActivity(),
                                adapter4.getDatas().get(position).getId()
                        );

                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                        return false;
                    }
                });

                break;
        }
    }

}
