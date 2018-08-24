package com.gqfyanshi.mvp.fragment;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.fivefivelike.mybaselibrary.base.BaseDataBindFragment;
import com.gqfyanshi.R;
import com.gqfyanshi.mvp.activity.notice.document.ReceivinOofficialDocumentsActivity;
import com.gqfyanshi.mvp.activity.add.AddDynamicLeadershipActivity;
import com.gqfyanshi.mvp.activity.add.AddInspectorNoticeActivity;
import com.gqfyanshi.mvp.activity.add.AddPublicInformationActivity;
import com.gqfyanshi.mvp.activity.add.AddSendMailActivity;
import com.gqfyanshi.mvp.activity.askleave.AskForLeaveAActivity;
import com.gqfyanshi.mvp.activity.file.FileCupboardActivity;
import com.gqfyanshi.mvp.activity.main.MainLinsener;
import com.gqfyanshi.mvp.activity.notice.NoticeAddressBookActivity;
import com.gqfyanshi.mvp.activity.notice.NoticeConferenceRoomReservationActivity;
import com.gqfyanshi.mvp.activity.notice.NoticeDecisionMakingActivity;
import com.gqfyanshi.mvp.activity.notice.announcement.NoticeDefaultActivity;
import com.gqfyanshi.mvp.activity.notice.mail.NoticeInboxActivity;
import com.gqfyanshi.mvp.activity.notice.NoticeLeadershipViewActivity;
import com.gqfyanshi.mvp.activity.notice.city.NoticeCityManuscriptsReceiveActivity;
import com.gqfyanshi.mvp.activity.notice.city.NoticeCityManuscriptsSendActivity;
import com.gqfyanshi.mvp.activity.notice.NoticeMayorActivity;
import com.gqfyanshi.mvp.activity.notice.announcement.NoticeMeetingActivity;
import com.gqfyanshi.mvp.activity.notice.mail.NoticeOutboxActivity;
import com.gqfyanshi.mvp.activity.notice.NoticeReportActivity;
import com.gqfyanshi.mvp.activity.notice.document.NoticeSendOfficialDocumentActivity;
import com.gqfyanshi.mvp.activity.notice.NoticeWorkMsgActivity;
import com.gqfyanshi.mvp.activity.notice.city.NoticeCityInspectorReceiveActivity;
import com.gqfyanshi.mvp.activity.notice.city.NoticeCityInspectorSendActivity;
import com.gqfyanshi.mvp.activity.notice.city.NoticeCityMgsStatisticalActivity;
import com.gqfyanshi.mvp.activity.notice.city.NoticeCityPublicMsgReceiveActivity;
import com.gqfyanshi.mvp.activity.notice.city.NoticeCitySendMsgActivity;
import com.gqfyanshi.mvp.activity.notice.city.NoticeEmergencyCityActivity;
import com.gqfyanshi.mvp.activity.notice.city.NoticePublicMsgSendActivity;
import com.gqfyanshi.mvp.activity.notice.government.NoticeEmergencyGovernmentActivity;
import com.gqfyanshi.mvp.activity.notice.government.NoticeGovernmentInspectorReceiveActivity;
import com.gqfyanshi.mvp.activity.notice.government.NoticeGovernmentInspectorSendActivity;
import com.gqfyanshi.mvp.activity.notice.government.NoticeGovernmentManuscriptsReceiveActivity;
import com.gqfyanshi.mvp.activity.notice.government.NoticeGovernmentMgsStatisticalActivity;
import com.gqfyanshi.mvp.activity.notice.government.NoticeGovernmentPublicMsgReceiveActivity;
import com.gqfyanshi.mvp.activity.notice.government.NoticeGovernmentSendActivity;
import com.gqfyanshi.mvp.activity.notice.government.NoticeGovernmentSendMsgActivity;
import com.gqfyanshi.mvp.databinder.UserDrawerBinder;
import com.gqfyanshi.mvp.delegate.UserDrawerDelegate;

public class UserDrawerFragment extends BaseDataBindFragment<UserDrawerDelegate, UserDrawerBinder> {

    @Override
    protected Class<UserDrawerDelegate> getDelegateClass() {
        return UserDrawerDelegate.class;
    }

    @Override
    public UserDrawerBinder getDataBinder(UserDrawerDelegate viewDelegate) {
        return new UserDrawerBinder(viewDelegate);
    }

    MainLinsener mainLinsener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mainLinsener = (MainLinsener) activity;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        viewDelegate.setOnClickListener(this,
                R.id.lin_module1,//通知公告
                R.id.lin_mod1_content1,//--会议通知
                R.id.lin_mod1_content2,//--一般性公告
                R.id.lin_module2,//公文流转
                R.id.lin_mod2_content1,//--公文发送
                R.id.lin_mod2_content2,//--公文接收
                R.id.lin_mod2_content3,//--请假
                R.id.lin_module3,//文件柜
                R.id.lin_module4,//市委信息工作
                R.id.lin_mod4_content1,//--约稿性发送
                R.id.lin_mod4_content2,//--约稿性接收
                R.id.lin_mod4_content3,//--信息统计
                R.id.lin_mod4_content4,//--信息发送
                R.id.lin_mod4_content5,//--工作信息发布
                R.id.lin_mod4_content6,//--要情汇报
                R.id.lin_mod4_content7,//--领导参阅
                R.id.lin_mod4_content8,//--紧急信息
                R.id.lin_module5,//政府信息工作
                R.id.lin_mod5_content1,//--约稿性发送
                R.id.lin_mod5_content2,//--约稿性接收
                R.id.lin_mod5_content3,//--信息统计
                R.id.lin_mod5_content4,//--信息发送
                R.id.lin_mod5_content5,//--市长专报
                R.id.lin_mod5_content6,//--决策参考
                R.id.lin_mod5_content7,//--紧急信息
                R.id.lin_module6,//市委督查工作
                R.id.lin_mod6_content1,//--公开信息发布
                R.id.lin_mod6_content2,//--公开信息发送
                R.id.lin_mod6_content3,//--公开信息接收
                R.id.lin_mod6_content4,//--督查通知发布
                R.id.lin_mod6_content5,//--督查通知发送
                R.id.lin_mod6_content6,//--督查通知接收
                R.id.lin_module7,//政府督查工作
                R.id.lin_mod7_content1,//--公开信息发布
                R.id.lin_mod7_content2,//--公开信息发送
                R.id.lin_mod7_content3,//--公开信息接收
                R.id.lin_mod7_content4,//--督查通知发送
                R.id.lin_mod7_content5,//--督查通知接收
                R.id.lin_module8,//领导动态
                R.id.lin_module9,//邮件发送
                R.id.lin_mod9_content1,//--邮件发送
                R.id.lin_mod9_content2,//--发件箱
                R.id.lin_mod9_content3,//--收件箱
                R.id.lin_module10,//会议室预约
                R.id.lin_module11,//电子通讯录
                R.id.lin_module12//意见箱
        );
    }

    private void showAndHide(LinearLayout lin_content, ImageView imageView) {
        if (lin_content.getVisibility() == View.VISIBLE) {
            imageView.setImageResource(R.drawable.xiangqing);
            lin_content.setVisibility(View.GONE);
        } else {
            imageView.setImageResource(R.drawable.xiala2);
            lin_content.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.lin_module1:
                showAndHide(viewDelegate.viewHolder.lin_content1,
                        viewDelegate.viewHolder.iv_module1);
                break;//通知公告
            case R.id.lin_mod1_content1:
                startActivity(new Intent(getActivity(), NoticeMeetingActivity.class));
                break;//--会议通知
            case R.id.lin_mod1_content2:
                startActivity(new Intent(getActivity(), NoticeDefaultActivity.class));
                break;//--一般性公告
            case R.id.lin_module2:
                showAndHide(viewDelegate.viewHolder.lin_content2,
                        viewDelegate.viewHolder.iv_module2);
                break;//公文流转
            case R.id.lin_mod2_content1:
                startActivity(new Intent(getActivity(), NoticeSendOfficialDocumentActivity.class));
                break;//--公文发送
            case R.id.lin_mod2_content2:
                startActivity(new Intent(getActivity(), ReceivinOofficialDocumentsActivity.class));
                break;//--公文接收
            case R.id.lin_mod2_content3:
                startActivity(new Intent(getActivity(), AskForLeaveAActivity.class));
                break;//--请假
            case R.id.lin_module3:
                startActivity(new Intent(getActivity(), FileCupboardActivity.class));
                break;//文件柜
            case R.id.lin_module4:
                showAndHide(viewDelegate.viewHolder.lin_content4,
                        viewDelegate.viewHolder.iv_module4);
                break;//市委信息工作
            case R.id.lin_mod4_content1:
                startActivity(new Intent(getActivity(), NoticeCityManuscriptsSendActivity.class));
                break;//--约稿性发送
            case R.id.lin_mod4_content2:
                startActivity(new Intent(getActivity(), NoticeCityManuscriptsReceiveActivity.class));
                break;//--约稿性接收
            case R.id.lin_mod4_content3:
                startActivity(new Intent(getActivity(), NoticeCityMgsStatisticalActivity.class));
                break;//--信息统计
            case R.id.lin_mod4_content4:
                startActivity(new Intent(getActivity(), NoticeCitySendMsgActivity.class));
                break;//--信息发送
            case R.id.lin_mod4_content5:
                startActivity(new Intent(getActivity(), NoticeWorkMsgActivity.class));
                break;//--工作信息发布
            case R.id.lin_mod4_content6:
                startActivity(new Intent(getActivity(), NoticeReportActivity.class));
                break;//--要情汇报
            case R.id.lin_mod4_content7:
                startActivity(new Intent(getActivity(), NoticeLeadershipViewActivity.class));
                break;//--领导参阅
            case R.id.lin_mod4_content8:
                startActivity(new Intent(getActivity(), NoticeEmergencyCityActivity.class));
                break;//--紧急信息
            case R.id.lin_module5:
                showAndHide(viewDelegate.viewHolder.lin_content5,
                        viewDelegate.viewHolder.iv_module5);
                break;//政府信息工作
            case R.id.lin_mod5_content1:
                startActivity(new Intent(getActivity(), NoticeGovernmentSendActivity.class));
                break;//--约稿性发送
            case R.id.lin_mod5_content2:
                startActivity(new Intent(getActivity(), NoticeGovernmentManuscriptsReceiveActivity.class));
                break;//--约稿性接收
            case R.id.lin_mod5_content3:
                startActivity(new Intent(getActivity(), NoticeGovernmentMgsStatisticalActivity.class));
                break;//--信息统计
            case R.id.lin_mod5_content4:
                startActivity(new Intent(getActivity(), NoticeGovernmentSendMsgActivity.class));
                break;//--信息发送
            case R.id.lin_mod5_content5:
                startActivity(new Intent(getActivity(), NoticeMayorActivity.class));
                break;//--市长专报
            case R.id.lin_mod5_content6:
                startActivity(new Intent(getActivity(), NoticeDecisionMakingActivity.class));
                break;//--决策参考
            case R.id.lin_mod5_content7:
                startActivity(new Intent(getActivity(), NoticeEmergencyGovernmentActivity.class));
                break;//--紧急信息
            case R.id.lin_module6:
                showAndHide(viewDelegate.viewHolder.lin_content6,
                        viewDelegate.viewHolder.iv_module6);
                break;//市委督查工作
            case R.id.lin_mod6_content1:
                startActivity(new Intent(getActivity(), NoticePublicMsgSendActivity.class));
                break;//--公开信息发布
            case R.id.lin_mod6_content2:
                startActivity(new Intent(getActivity(), AddPublicInformationActivity.class));
                break;//--公开信息发送
            case R.id.lin_mod6_content3:
                startActivity(new Intent(getActivity(), NoticeCityPublicMsgReceiveActivity.class));
                break;//--公开信息接收
            case R.id.lin_mod6_content4:
                startActivity(new Intent(getActivity(), AddInspectorNoticeActivity.class));
                break;//--督查通知发布
            case R.id.lin_mod6_content5:
                startActivity(new Intent(getActivity(), NoticeCityInspectorSendActivity.class));
                break;//--督查通知发送
            case R.id.lin_mod6_content6:
                startActivity(new Intent(getActivity(), NoticeCityInspectorReceiveActivity.class));
                break;//--督查通知接收
            case R.id.lin_module7:
                showAndHide(viewDelegate.viewHolder.lin_content7,
                        viewDelegate.viewHolder.iv_module7);
                break;//政府督查工作
            case R.id.lin_mod7_content1:
                startActivity(new Intent(getActivity(), AddPublicInformationActivity.class));
                break;//--公开信息发布
            case R.id.lin_mod7_content2:
                startActivity(new Intent(getActivity(), AddPublicInformationActivity.class));
                break;//--公开信息发送
            case R.id.lin_mod7_content3:
                startActivity(new Intent(getActivity(), NoticeGovernmentPublicMsgReceiveActivity.class));
                break;//--公开信息接收
            case R.id.lin_mod7_content4:
                startActivity(new Intent(getActivity(), NoticeGovernmentInspectorSendActivity.class));
                break;//--督查通知发送
            case R.id.lin_mod7_content5:
                startActivity(new Intent(getActivity(), NoticeGovernmentInspectorReceiveActivity.class));
                break;//--督查通知接收
            case R.id.lin_module8:
                startActivity(new Intent(getActivity(), AddDynamicLeadershipActivity.class));
                break;//领导动态
            case R.id.lin_module9:
                showAndHide(viewDelegate.viewHolder.lin_content9,
                        viewDelegate.viewHolder.iv_module9);
                break;//邮件发送
            case R.id.lin_mod9_content1:
                startActivity(new Intent(getActivity(), AddSendMailActivity.class));
                break;//--邮件发送
            case R.id.lin_mod9_content2:
                startActivity(new Intent(getActivity(), NoticeOutboxActivity.class));
                break;//--发件箱
            case R.id.lin_mod9_content3:
                startActivity(new Intent(getActivity(), NoticeInboxActivity.class));
                break;//--收件箱
            case R.id.lin_module10:
                startActivity(new Intent(getActivity(), NoticeConferenceRoomReservationActivity.class));
                break;//会议室预约
            case R.id.lin_module11:
                startActivity(new Intent(getActivity(), NoticeAddressBookActivity.class));
                break;//电子通讯录
            case R.id.lin_module12:
                break;//意见箱
        }
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
        }
    }

}
