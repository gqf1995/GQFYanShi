package com.gqfyanshi.mvp.activity.file;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.gqfyanshi.entity.bean.DocumentBean;
import com.gqfyanshi.mvp.databinder.DocumentInfoBinder;
import com.gqfyanshi.mvp.delegate.DocumentInfoDelegate;

public class EmailInfoActivity extends BaseDataBindActivity<DocumentInfoDelegate, DocumentInfoBinder> {

    @Override
    protected Class<DocumentInfoDelegate> getDelegateClass() {
        return DocumentInfoDelegate.class;
    }

    @Override
    public DocumentInfoBinder getDataBinder(DocumentInfoDelegate viewDelegate) {
        return new DocumentInfoBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        getIntentData();
        initToolbar(new ToolbarBuilder().setTitle("文件详情"));

    }

    public static void startAct(Activity activity,
                                String id
    ) {
        Intent intent = new Intent(activity, EmailInfoActivity.class);
        intent.putExtra("id", id);
        activity.startActivity(intent);
    }

    private String id;

    private void getIntentData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        addRequest(binder.document_detailDocumnet(id, "", this));
    }

    DocumentBean documentInfoBean;

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                documentInfoBean = GsonUtil.getInstance().toObj(data, "doc", DocumentBean.class);

                String departName = GsonUtil.getInstance().getValue(data, "departName");
                viewDelegate.viewHolder.lin_content.setVisibility(TextUtils.isEmpty(departName) ? View.GONE : View.VISIBLE);
                viewDelegate.viewHolder.tv_content.setText(departName + "");

                viewDelegate.viewHolder.tv_title.setText(documentInfoBean.getTitle() + "");
                viewDelegate.viewHolder.tv_time.setText("发文时间：" + documentInfoBean.getCreatetime());

                viewDelegate.viewHolder.lin_file.setVisibility(TextUtils.isEmpty(documentInfoBean.getFile_name()) ? View.GONE : View.VISIBLE);
                viewDelegate.viewHolder.tv_file.setText(documentInfoBean.getFile_name() + "");
                viewDelegate.viewHolder.tv_file.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TBSActivity.startAct(
                                viewDelegate.getActivity(),
                                documentInfoBean.getFile_name(),
                                documentInfoBean.getFile_address()
                        );
                    }
                });
                break;
        }
    }

}
