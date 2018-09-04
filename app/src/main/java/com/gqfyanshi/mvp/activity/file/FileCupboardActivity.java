package com.gqfyanshi.mvp.activity.file;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.gqfyanshi.adapter.FileCupboardAdapter;
import com.gqfyanshi.mvp.databinder.FileCupboardBinder;
import com.gqfyanshi.mvp.delegate.FileCupboardDelegate;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

public class FileCupboardActivity extends BaseDataBindActivity<FileCupboardDelegate, FileCupboardBinder> {
    FileCupboardAdapter fileCupboardAdapter;

    @Override
    protected Class<FileCupboardDelegate> getDelegateClass() {
        return FileCupboardDelegate.class;
    }

    @Override
    public FileCupboardBinder getDataBinder(FileCupboardDelegate viewDelegate) {
        return new FileCupboardBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("文件柜"));
        viewDelegate.viewHolder.et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {//搜索按键action
                    String content = viewDelegate.viewHolder.et_search.getText().toString();
                    if (TextUtils.isEmpty(content)) {
                        return true;
                    }
                    return true;
                }
                return false;
            }
        });

        List<String> data = new ArrayList<>();
        data.add("123.pdf");
        data.add("123.txt");
        data.add("123.pptx");
        data.add("123.xls");
        data.add("123.xlsx");
        data.add("123.docx");
        data.add("123.doc");
        data.add("123.zip");
        data.add("123.jpg");
        data.add("123.png");
        fileCupboardAdapter = new FileCupboardAdapter(this, data);
        fileCupboardAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                TBSActivity.startAct(viewDelegate.getActivity(),
                        fileCupboardAdapter.getDatas().get(position),
                        url[position]);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        viewDelegate.viewHolder.recycler_view.setLayoutManager(new LinearLayoutManager(this));
        viewDelegate.viewHolder.recycler_view.setAdapter(fileCupboardAdapter);
    }

    String[] url = {
            "http://file3.data.weipan.cn/46186810/05f5592ba69b9efa23abe9ec548f7c72bbcf4d0f?ip=1536043319,106.46.169.103&ssig=1niW%2BrhfK9&Expires=1536043919&KID=sae,l30zoo1wmz&fn=%E6%89%98%E7%A6%8F%E6%A0%B8%E5%BF%83%E8%AF%8D%E6%B1%87.pdf&se_ip_debug=106.46.169.103&from=1221134",
            "http://file3.data.weipan.cn/47271739/b179931d6861af75ec63ff557d318253dd254345?ip=1536043577,106.46.169.103&ssig=sZGWW4R2cY&Expires=1536044177&KID=sae,l30zoo1wmz&fn=%E7%BD%91%E4%B8%8A%E7%BB%8F%E5%85%B8%E7%AC%91%E8%AF%9D%28%E5%A4%9A%E5%B9%B4%E6%9D%A5%E6%88%91%E6%94%B6%E9%9B%86%E7%9A%84%E6%88%90%E6%9E%9C%29.txt&se_ip_debug=106.46.169.103&from=1221134",
            "http://file3.data.weipan.cn/194960540/2bed711b6c954c9c6913fbe3eeac8d82ed8875be?ip=1536042687,106.46.169.103&ssig=P0u7keZOoO&Expires=1536043287&KID=sae,l30zoo1wmz&fn=%E5%88%9B%E4%B8%9A%E8%9E%8D%E8%B5%84%E7%AD%96%E5%88%92%E4%B9%A6PPT%E6%A8%A1%E6%9D%BF.pptx&se_ip_debug=106.46.169.103&from=1221134",
            "http://file3.data.weipan.cn/85109757/aa404899ed446d36c2d6e9e80ed6a895be89e28c?ip=1536042723,106.46.169.103&ssig=8U6k%2Fjl%2Fsv&Expires=1536043323&KID=sae,l30zoo1wmz&fn=%E4%BD%A0%E4%BC%9A%E7%94%A8SUM%E5%87%BD%E6%95%B0%E5%90%97.xls&se_ip_debug=106.46.169.103&from=1221134",
            "http://file3.data.weipan.cn/85109757/aa76f4ad5394ea3ecd20ec0e89e8f9108859ecfd?ip=1536042737,106.46.169.103&ssig=jVsuGGlmbu&Expires=1536043337&KID=sae,l30zoo1wmz&fn=%E9%92%BB%E5%B1%95%E6%8A%95%E6%94%BE%E8%A1%A8%E6%A0%BC.xlsx&se_ip_debug=106.46.169.103&from=1221134",
            "http://file3.data.weipan.cn/18070272/6a869b37b8fb23fec6c126922e11069b7273e4e2?ip=1536043607,106.46.169.103&ssig=yEb3MnUNYa&Expires=1536044207&KID=sae,l30zoo1wmz&fn=word%E4%B8%AA%E4%BA%BA%E7%AE%80%E5%8E%86+%284%29.docx&se_ip_debug=106.46.169.103&from=1221134",
            "http://file3.data.weipan.cn/29686685/3f4d16667ab91e15ae5ac7f5ac135b9309e92f19?ip=1536043525,106.46.169.103&ssig=SUaVAGzPWf&Expires=1536044125&KID=sae,l30zoo1wmz&fn=24%E5%BC%8F%E5%A4%AA%E6%9E%81%E6%8B%B3%E5%9B%BE%E8%A7%A3.doc&se_ip_debug=106.46.169.103&from=1221134",
            "http://file3.data.weipan.cn/157453824/1c464282d2fbbfd9dacb6d71ace2c6b15e89afa7?ip=1536043640,106.46.169.103&ssig=hZ1XsrwxlX&Expires=1536044240&KID=sae,l30zoo1wmz&fn=ICON%E5%9B%BE%E7%89%87%E6%80%80%E6%97%A7%E5%8F%A4%E5%85%B8%E6%95%88%E6%9E%9CPS%E5%8A%A8%E4%BD%9C.zip&se_ip_debug=106.46.169.103&from=1221134",
            "http://img.zcool.cn/community/0117e2571b8b246ac72538120dd8a4.jpg@1280w_1l_2o_100sh.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536050909532&di=5d3727a3cc456fcbad5f6d59a5e17c8d&imgtype=0&src=http%3A%2F%2Fpic31.nipic.com%2F20130710%2F7013570_145943198124_2.png",
            "http://fjdx.sc.chinaz.com/Files/DownLoad/moban/201809/zppt3571.rar"
    };


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
        }
    }

}
