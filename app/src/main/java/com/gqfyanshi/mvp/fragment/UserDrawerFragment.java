package com.gqfyanshi.mvp.fragment;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.fivefivelike.mybaselibrary.base.BaseDataBindFragment;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ListUtils;
import com.gqfyanshi.R;
import com.gqfyanshi.adapter.MainLeftTreeItemHolder;
import com.gqfyanshi.entity.bean.MainLeftBean;
import com.gqfyanshi.mvp.activity.add.AddDocumentActivity;
import com.gqfyanshi.mvp.activity.add.AddDynamicLeadershipActivity;
import com.gqfyanshi.mvp.activity.add.AddEmailActivity;
import com.gqfyanshi.mvp.activity.add.AddInspectorNoticeActivity;
import com.gqfyanshi.mvp.activity.add.AddPublicInformationActivity;
import com.gqfyanshi.mvp.activity.add.AddSendMailActivity;
import com.gqfyanshi.mvp.activity.approval.ApprovalActivity;
import com.gqfyanshi.mvp.activity.askleave.AskForLeaveAActivity;
import com.gqfyanshi.mvp.activity.askleave.AskLeaveActivity;
import com.gqfyanshi.mvp.activity.file.FileCupboardActivity;
import com.gqfyanshi.mvp.activity.main.MainLinsener;
import com.gqfyanshi.mvp.activity.notice.NoticeAddressBookActivity;
import com.gqfyanshi.mvp.activity.notice.NoticeConferenceRoomReservationActivity;
import com.gqfyanshi.mvp.activity.notice.NoticeDecisionMakingActivity;
import com.gqfyanshi.mvp.activity.notice.NoticeLeadershipViewActivity;
import com.gqfyanshi.mvp.activity.notice.NoticeMayorActivity;
import com.gqfyanshi.mvp.activity.notice.NoticeReportActivity;
import com.gqfyanshi.mvp.activity.notice.NoticeWorkMsgActivity;
import com.gqfyanshi.mvp.activity.notice.announcement.NoticeDefaultActivity;
import com.gqfyanshi.mvp.activity.notice.announcement.NoticeMeetingActivity;
import com.gqfyanshi.mvp.activity.notice.approval.NoticeApprovalActivity;
import com.gqfyanshi.mvp.activity.notice.approval.NoticeMyApprovalActivity;
import com.gqfyanshi.mvp.activity.notice.askleave.NoticeAskLeaveActivity;
import com.gqfyanshi.mvp.activity.notice.askleave.NoticeMyAskLeaveActivity;
import com.gqfyanshi.mvp.activity.notice.city.NoticeCityInspectorReceiveActivity;
import com.gqfyanshi.mvp.activity.notice.city.NoticeCityInspectorSendActivity;
import com.gqfyanshi.mvp.activity.notice.city.NoticeCityManuscriptsReceiveActivity;
import com.gqfyanshi.mvp.activity.notice.city.NoticeCityManuscriptsSendActivity;
import com.gqfyanshi.mvp.activity.notice.city.NoticeCityMgsStatisticalActivity;
import com.gqfyanshi.mvp.activity.notice.city.NoticeCityPublicMsgReceiveActivity;
import com.gqfyanshi.mvp.activity.notice.city.NoticeCityPublicMsgSendActivity;
import com.gqfyanshi.mvp.activity.notice.city.NoticeCitySendMsgActivity;
import com.gqfyanshi.mvp.activity.notice.city.NoticeEmergencyCityActivity;
import com.gqfyanshi.mvp.activity.notice.document.NoticeReceiveOfficialDocumentActivity;
import com.gqfyanshi.mvp.activity.notice.document.NoticeSendOfficialDocumentActivity;
import com.gqfyanshi.mvp.activity.notice.document.ReceivinOofficialDocumentsActivity;
import com.gqfyanshi.mvp.activity.notice.government.NoticeEmergencyGovernmentActivity;
import com.gqfyanshi.mvp.activity.notice.government.NoticeGovernmentInspectorReceiveActivity;
import com.gqfyanshi.mvp.activity.notice.government.NoticeGovernmentInspectorSendActivity;
import com.gqfyanshi.mvp.activity.notice.government.NoticeGovernmentManuscriptsReceiveActivity;
import com.gqfyanshi.mvp.activity.notice.government.NoticeGovernmentMgsStatisticalActivity;
import com.gqfyanshi.mvp.activity.notice.government.NoticeGovernmentPublicMsgReceiveActivity;
import com.gqfyanshi.mvp.activity.notice.government.NoticeGovernmentPublicMsgSendActivity;
import com.gqfyanshi.mvp.activity.notice.government.NoticeGovernmentSendActivity;
import com.gqfyanshi.mvp.activity.notice.government.NoticeGovernmentSendMsgActivity;
import com.gqfyanshi.mvp.activity.notice.mail.NoticeInboxActivity;
import com.gqfyanshi.mvp.activity.notice.mail.NoticeOutboxActivity;
import com.gqfyanshi.mvp.databinder.UserDrawerBinder;
import com.gqfyanshi.mvp.delegate.UserDrawerDelegate;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.List;

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
        addRequest(binder.getLoginedUserInfo(this));

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
                R.id.lin_mod6_content4,//--督查通知发布  //
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


    List<MainLeftBean> leftBeans;

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                leftBeans = GsonUtil.getInstance().toList(data, "userRights", MainLeftBean.class);
                String normalMsg = GsonUtil.getInstance().getValue(data, "normalMsg");
                String docMsg = GsonUtil.getInstance().getValue(data, "docMsg");
                String meetMsg = GsonUtil.getInstance().getValue(data, "meetMsg");
                initAllView();
                break;
        }
    }

    TreeNode root;

    private void initAllView() {
        root = TreeNode.root();
        for (int i = 0; i < leftBeans.size(); i++) {
            if (leftBeans.get(i).getPid() == 0) {
                //父
                TreeNode parent1 = new TreeNode(leftBeans.get(i));
                initTree(parent1, leftBeans.get(i));
                root.addChild(parent1);
            }
        }
        AndroidTreeView treeView = new AndroidTreeView(getActivity(), root);
        treeView.setDefaultAnimation(true);
        treeView.setDefaultViewHolder(MainLeftTreeItemHolder.class);
        treeView.setDefaultNodeClickListener(new TreeNode.TreeNodeClickListener() {
            @Override
            public void onClick(TreeNode node, Object value) {
                if (value instanceof MainLeftBean) {
                    if (ListUtils.isEmpty(node.getChildren())) {
                        //通知公告
                        if ("/notice/noticeIndex/0".equals(((MainLeftBean) value).getUrl())) {
                            //会议通知
                            startActivity(new Intent(getActivity(), NoticeMeetingActivity.class));
                        } else if ("/notice/noticeIndex/1".equals(((MainLeftBean) value).getUrl())) {
                            //一般性公告
                            startActivity(new Intent(getActivity(), NoticeDefaultActivity.class));
                        }
                        //公文流转
                        else if ("/document/docIndex/0".equals(((MainLeftBean) value).getUrl())) {
                            //公文发送
                            startActivity(new Intent(getActivity(), NoticeSendOfficialDocumentActivity.class));
                        } else if ("/document/docIndex/1".equals(((MainLeftBean) value).getUrl())) {
                            //公文接收
                            startActivity(new Intent(getActivity(), NoticeReceiveOfficialDocumentActivity.class));
                        }
                        //市委信息工作
                        else if ("/conventional/conventionalIndex/0".equals(((MainLeftBean) value).getUrl())) {
                            //约稿性发送
                            startActivity(new Intent(getActivity(), NoticeCityManuscriptsSendActivity.class));
                        } else if ("/conventional/conventionalIndex/1".equals(((MainLeftBean) value).getUrl())) {
                            //约稿性接收
                            startActivity(new Intent(getActivity(), NoticeCityManuscriptsReceiveActivity.class));
                        } else if ("/information/informationlIndex/1".equals(((MainLeftBean) value).getUrl())) {
                            //信息统计
                            startActivity(new Intent(getActivity(), NoticeCityMgsStatisticalActivity.class));
                        } else if ("/information/informationlIndex/0".equals(((MainLeftBean) value).getUrl())) {
                            //信息发送
                            startActivity(new Intent(getActivity(), NoticeCitySendMsgActivity.class));
                        } else if ("/jobinfo/jobInfoIndex/0".equals(((MainLeftBean) value).getUrl())) {
                            //工作信息发布
                            startActivity(new Intent(getActivity(), NoticeWorkMsgActivity.class));
                        } else if ("/threeinfo/threeInfoIndex/0".equals(((MainLeftBean) value).getUrl())) {
                            //要情汇报
                            startActivity(new Intent(getActivity(), NoticeReportActivity.class));
                        } else if ("/threeinfo/threeInfoIndex/1".equals(((MainLeftBean) value).getUrl())) {
                            //领导参阅
                            startActivity(new Intent(getActivity(), NoticeLeadershipViewActivity.class));
                        } else if ("/threeinfo/threeInfoIndex/2".equals(((MainLeftBean) value).getUrl())) {
                            //紧急信息
                            startActivity(new Intent(getActivity(), NoticeEmergencyCityActivity.class));
                        }
                        //政府信息工作
                        else if ("/conventional/govConventionalIndex/0".equals(((MainLeftBean) value).getUrl())) {
                            //约稿性发送
                            startActivity(new Intent(getActivity(), NoticeGovernmentSendActivity.class));
                        } else if ("/conventional/govConventionalIndex/1".equals(((MainLeftBean) value).getUrl())) {
                            //约稿性接收
                            startActivity(new Intent(getActivity(), NoticeGovernmentManuscriptsReceiveActivity.class));
                        } else if ("/information/govInformationlIndex/1".equals(((MainLeftBean) value).getUrl())) {
                            //信息统计
                            startActivity(new Intent(getActivity(), NoticeGovernmentMgsStatisticalActivity.class));
                        } else if ("/information/govInformationlIndex/0".equals(((MainLeftBean) value).getUrl())) {
                            //信息发送
                            startActivity(new Intent(getActivity(), NoticeGovernmentSendMsgActivity.class));
                        } else if ("/threeinfo/govThreeInfoIndex/0".equals(((MainLeftBean) value).getUrl())) {
                            //市长专报
                            startActivity(new Intent(getActivity(), NoticeMayorActivity.class));
                        } else if ("/threeinfo/govThreeInfoIndex/1".equals(((MainLeftBean) value).getUrl())) {
                            //决策参考
                            startActivity(new Intent(getActivity(), NoticeDecisionMakingActivity.class));
                        } else if ("/threeinfo/govThreeInfoIndex/2".equals(((MainLeftBean) value).getUrl())) {
                            //紧急信息
                            startActivity(new Intent(getActivity(), NoticeEmergencyGovernmentActivity.class));
                        }
                        //市委督查工作
                        else if ("/workInfo/cityWorkInfoForm".equals(((MainLeftBean) value).getUrl())) {
                            //公开信息发布
                            startActivity(new Intent(getActivity(), NoticeCityPublicMsgSendActivity.class));
                        } else if ("/workInfo/cityWorkInfoSend".equals(((MainLeftBean) value).getUrl())) {
                            //公开信息发送
                            AddDocumentActivity.startAct(viewDelegate.getActivity(),
                                    "26", "");


                        } else if ("/workInfo/cityWorkInfoReceive".equals(((MainLeftBean) value).getUrl())) {
                            //公开信息接收
                            startActivity(new Intent(getActivity(), NoticeCityPublicMsgReceiveActivity.class));
                        } else if ("/overSeer/cityOverSeerSend".equals(((MainLeftBean) value).getUrl())) {
                            //督查通知发送
                            startActivity(new Intent(getActivity(), NoticeCityInspectorSendActivity.class));
                        } else if ("/overSeer/cityOverSeerReceive".equals(((MainLeftBean) value).getUrl())) {
                            //督查通知接收
                            startActivity(new Intent(getActivity(), NoticeCityInspectorReceiveActivity.class));
                        }
                        //政府督查工作
                        else if ("/workInfo/govWorkInfoForm".equals(((MainLeftBean) value).getUrl())) {
                            //公开信息发布
                            startActivity(new Intent(getActivity(), NoticeGovernmentPublicMsgSendActivity.class));
                        } else if ("/workInfo/govWorkInfoSend".equals(((MainLeftBean) value).getUrl())) {
                            //公开信息发送
                            AddDocumentActivity.startAct(viewDelegate.getActivity(),
                                    "32", "");
                        } else if ("/workInfo/govWorkInfoReceive".equals(((MainLeftBean) value).getUrl())) {
                            //公开信息接收
                            startActivity(new Intent(getActivity(), NoticeGovernmentPublicMsgReceiveActivity.class));
                        } else if ("/overSeer/govOverSeerSend".equals(((MainLeftBean) value).getUrl())) {
                            //督查通知发送
                            startActivity(new Intent(getActivity(), NoticeGovernmentInspectorSendActivity.class));
                        } else if ("/overSeer/govOverSeerReceive".equals(((MainLeftBean) value).getUrl())) {
                            //督查通知接收
                            startActivity(new Intent(getActivity(), NoticeGovernmentInspectorReceiveActivity.class));
                        }
                        //领导动态
                        else if ("/leader/addIndex".equals(((MainLeftBean) value).getUrl())) {
                            //领导动态
                            startActivity(new Intent(getActivity(),
                                    AddDynamicLeadershipActivity.class));
                        }
                        //邮件发送
                        else if ("/email/emailForm".equals(((MainLeftBean) value).getUrl())) {
                            //邮件发送
                            startActivity(new Intent(getActivity(), AddEmailActivity.class));
                        } else if ("/email/emailSend".equals(((MainLeftBean) value).getUrl())) {
                            //发件箱
                            startActivity(new Intent(getActivity(), NoticeOutboxActivity.class));
                        } else if ("/email/emailReceive".equals(((MainLeftBean) value).getUrl())) {
                            //收件箱
                            startActivity(new Intent(getActivity(), NoticeInboxActivity.class));
                        }
                        //会议室预约
                        else if ("/conference".equals(((MainLeftBean) value).getUrl())) {
                            //会议室预约
                            startActivity(new Intent(getActivity(), NoticeConferenceRoomReservationActivity.class));
                        }
                        //电子通讯录
                        else if ("/addressBook/list".equals(((MainLeftBean) value).getUrl())) {
                            //电子通讯录
                            startActivity(new Intent(getActivity(), NoticeAddressBookActivity.class));
                        }
                        //意见箱
                        else if ("/receipt".equals(((MainLeftBean) value).getUrl())) {
                            //反馈意见
                        }


                        //用户管理
                        else if ("/user/list".equals(((MainLeftBean) value).getUrl())) {
                            //用户管理
                        } else if ("/role/list".equals(((MainLeftBean) value).getUrl())) {
                            //角色管理
                        } else if ("/group/list".equals(((MainLeftBean) value).getUrl())) {
                            //分组管理
                        } else if ("/jobinfo/jobInfoIndex/1".equals(((MainLeftBean) value).getUrl())) {
                            //工作信息接收
                        } else if ("/overSeer/cityOverSeerForm".equals(((MainLeftBean) value).getUrl())) {
                            //督查通知发布
                            AddDocumentActivity.startAct(viewDelegate.getActivity(),
                                    "26", "");
                        } else if ("/overSeer/govOverSeerForm".equals(((MainLeftBean) value).getUrl())) {
                            //督查通知发布
                            AddDocumentActivity.startAct(viewDelegate.getActivity(),
                                    "32", "");
                        } else if ("/notice/noticeInfoIndex/0".equals(((MainLeftBean) value).getUrl())) {
                            //通知公告
                        } else if ("/jobinfo/govJobInfoIndex/0".equals(((MainLeftBean) value).getUrl())) {
                            //工作信息发布
                        } else if ("/jobinfo/govJobInfoIndex/1".equals(((MainLeftBean) value).getUrl())) {
                            //工作信息接收
                        } else if ("/notice/noticeInfoIndex/1".equals(((MainLeftBean) value).getUrl())) {
                            //通知公告
                        }
                        //信息公告
                        else if ("/infoNotice".equals(((MainLeftBean) value).getUrl())) {
                            //信息公告发送
                        }
                        //请假
                        else if (63 == (((MainLeftBean) value).getId())) {
                            //发起请假
                            startActivity(new Intent(getActivity(), AskLeaveActivity.class));
                        } else if (64 == (((MainLeftBean) value).getId())) {
                            //我的请假
                            startActivity(new Intent(getActivity(), NoticeMyAskLeaveActivity.class));
                        } else if (65 == (((MainLeftBean) value).getId())) {
                            //我的审批
                            startActivity(new Intent(getActivity(), NoticeAskLeaveActivity.class));
                        }
                        //文章签批
                        else if (67 == (((MainLeftBean) value).getId())) {
                            //发起签批
                            startActivity(new Intent(getActivity(), ApprovalActivity.class));
                        } else if (68 == (((MainLeftBean) value).getId())) {
                            //我发起的签批
                            startActivity(new Intent(getActivity(), NoticeMyApprovalActivity.class));
                        } else if (69 == (((MainLeftBean) value).getId())) {
                            //我审核的签批
                            startActivity(new Intent(getActivity(), NoticeApprovalActivity.class));
                        }
                    }
                }
            }
        });
        viewDelegate.viewHolder.contentView.addView(treeView.getView());
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
                startActivity(new Intent(getActivity(), NoticeCityPublicMsgSendActivity.class));
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

    private void initTree(TreeNode parent1, MainLeftBean mainLeftBean) {
        for (int i = 0; i < leftBeans.size(); i++) {
            if (leftBeans.get(i).getPid() == mainLeftBean.getId()) {
                TreeNode child = new TreeNode(leftBeans.get(i))
                        .setViewHolder(new MainLeftTreeItemHolder(getActivity()));
                parent1.addChild(child);
            }
        }
    }
    //    /notice/sendList
    //    一般性公告type 02 modelId 3 会议通知 model 2 type 01
    //            /document/sendList
    //    公文发送 type 02 modelId 5
    //            /document/receiveList
    //    公文接收 ModelId 6 type 01
    //            /conventional/sendList
    //    约稿性发送列表 model 8 type 01
    //            /conventional/receiveList
    //    约稿性接收 ModelId 9 type 01
    //            /information/receiveList
    //    信息统计 model 11 type 04
    //            /threeinfo/sendList
    //    信息发送列表 model 11 type 04
    //            /jobinfo/sendList
    //    工作信息发布 model 12 type 05
    //            /threeinfo/sendList
    //    要请汇报 model 7 type 07
    //            /threeinfo/sendList
    //    领导参阅 model 7 type 08
    //            /threeinfo/threeInfoIndex/2
    //    紧急信息 model 7 type 09
    //            /conventional/sendList
    //    约稿性发送 model 8 type 01
    //            /conventional/receiveList
    //    约稿性接收 model 8 type 01
    //            /document/receiveList
    //    信息统计 model 20 type 04
    //            /information/sendList
    //    信息发送 model 20 type 04
    //            /threeinfo/govThreeInfoIndex/0
    //    市长专报

}
