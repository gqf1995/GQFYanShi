package com.gqfyanshi.mvp.fragment;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.circledialog.CircleDialogHelper;
import com.fivefivelike.mybaselibrary.base.BaseDataBindFragment;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ListUtils;
import com.fivefivelike.mybaselibrary.utils.SaveUtil;
import com.gqfyanshi.R;
import com.gqfyanshi.adapter.MainLeftTreeItemHolder;
import com.gqfyanshi.entity.bean.MainLeftBean;
import com.gqfyanshi.entity.bean.UserLoginBean;
import com.gqfyanshi.mvp.activity.add.AddDocumentActivity;
import com.gqfyanshi.mvp.activity.add.AddDynamicLeadershipActivity;
import com.gqfyanshi.mvp.activity.add.AddEmailActivity;
import com.gqfyanshi.mvp.activity.approval.ApprovalActivity;
import com.gqfyanshi.mvp.activity.askleave.AskLeaveActivity;
import com.gqfyanshi.mvp.activity.file.FileCupboardActivity;
import com.gqfyanshi.mvp.activity.main.LoginActivity;
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
    UserLoginBean loginInfo;
    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        addRequest(binder.getLoginedUserInfo(this));
         loginInfo = UserLoginBean.getLoginInfo();
        viewDelegate.viewHolder.tv_name.setText("您好 "+loginInfo.getName());
        viewDelegate.viewHolder.tv_division.setText("部门："+loginInfo.getUser_division());
        viewDelegate.viewHolder.tv_position.setText("职位："+loginInfo.getUser_position());
        viewDelegate.viewHolder.tv_phone.setText("手机："+loginInfo.getPhone_num());
        viewDelegate.viewHolder.lin_logout.setOnClickListener(this);
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
                        //文件柜
                        else if (72 == (((MainLeftBean) value).getId())) {
                            startActivity(new Intent(getActivity(), FileCupboardActivity.class));
                        }
                    }
                }
            }
        });
        viewDelegate.viewHolder.contentView.addView(treeView.getView());
        treeView.expandAll();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
             case R.id.lin_logout:
                 CircleDialogHelper.initDefaultDialog(getActivity(), "是否退出登录？", new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         SaveUtil.getInstance().saveString("uid","");
                         startActivity(new Intent(getActivity(), LoginActivity.class));
                         getActivity().finish();
                     }
                 }).show();
                break;//退出登录
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
