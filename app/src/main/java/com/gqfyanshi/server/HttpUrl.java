package com.gqfyanshi.server;

import com.fivefivelike.mybaselibrary.utils.SaveUtil;
import com.gqfyanshi.base.AppConst;

/**
 * Created by 郭青枫 on 2018/1/10 0010.
 */

public class HttpUrl {

    static HttpUrl httpUrl = new HttpUrl();

    public static HttpUrl getIntance() {
        if (httpUrl == null) {
            httpUrl = new HttpUrl();
        }
        return httpUrl;
    }

    public String getUid() {
        return SaveUtil.getInstance().getString("uid");
    }


    /**
     * 登录
     */
    public String doLogin = AppConst.app2BaseUrl + "/doLogin";
    /**
     * 登录
     */
    public String doLogin2 = AppConst.app2BaseUrl + "/doLogin";
    /**
     * 获取验证码
     */
    public String pictureCheckCode = AppConst.app2BaseUrl + "/pictureCheckCode";
    /**
     * 发送手机短信验证码
     */
    public String sendPhoneCode = AppConst.app2BaseUrl + "/sendPhoneCode";
    /**
     * 登陆后获取菜单
     */
    public String getLoginedUserInfo = AppConst.app2BaseUrl + "/getLoginedUserInfo";
    /**
     * 市委约稿发送列表
     */
    public String conventional_sendList = AppConst.app2BaseUrl + "/conventional/sendList";
    /**
     * 市委约稿接收列表
     */
    public String conventional_receiveList = AppConst.app2BaseUrl + "/conventional/receiveList";
    /**
     * 公文发送
     */
    public String document_sendList = AppConst.app2BaseUrl + "/document/sendList";
    /**
     * 公文接收
     */
    public String document_receiveList = AppConst.app2BaseUrl + "/document/receiveList";
    /**
     * 会议通知信息列表
     */
    public String notice_sendList = AppConst.app2BaseUrl + "/notice/sendList";
    /**
     * 市委信息统计
     */
    public String infoNotice_sendList = AppConst.app2BaseUrl + "/infoNotice/sendList";
    /**
     * 信息发送列表
     */
    public String information_sendList = AppConst.app2BaseUrl + "/information/sendList";
    /**
     * 工作信息发布
     */
    public String jobinfo_sendList = AppConst.app2BaseUrl + "/jobinfo/sendList";
    /**
     * 要情汇报
     */
    public String threeinfo_sendList = AppConst.app2BaseUrl + "/threeinfo/sendList";
    /**
     * 获取公开信息列表
     */
    public String workInfo_getWorkInfoSendList = AppConst.app2BaseUrl + "/workInfo/getWorkInfoSendList";
    /**
     * 获取公开信息接收的列表数据
     */
    public String workInfo_getWorkInfoReceiveList = AppConst.app2BaseUrl + "/workInfo/getWorkInfoReceiveList";
    /**
     * 获取督查通知列表
     */
    public String overSeer_getOverSeerSendList = AppConst.app2BaseUrl + "/overSeer/getOverSeerSendList";
    /**
     * 获取督查通知接收的列表数据
     */
    public String overSeer_getOverSeerReceiveList = AppConst.app2BaseUrl + "/overSeer/getOverSeerReceiveList";
    /**
     * 邮件发送列表
     */
    public String email_getEmailSendList = AppConst.app2BaseUrl + "/email/getEmailSendList";
    /**
     * 邮件接收列表
     */
    public String email_getEmailReceiveList = AppConst.app2BaseUrl + "/email/getEmailReceiveList";
    /**
     * 会议室预约
     */
    public String conference_getAppointmentInfoList = AppConst.app2BaseUrl + "/conference/getAppointmentInfoList";
    /**
     * 获取电子通讯录列表数据
     */
    public String addressBook_getAddressBookList = AppConst.app2BaseUrl + "/addressBook/getAddressBookList";
    /**
     * 跳转公文发送页面
     */
    public String document_docIndex = AppConst.app2BaseUrl + "/document/docIndex/1";
    /**
     * 删除公文
     */
    public String document_delDocument = AppConst.app2BaseUrl + "/document/delDocument";
    /**
     * 公文详情
     */
    public String document_detailDocumnet = AppConst.app2BaseUrl + "/document/detailDocumnet";
    /**
     * 邮件详情
     */
    public String email_emailInfo = AppConst.app2BaseUrl + "/email/emailInfo";
    /**
     * 邮件删除
     */
    public String email_emailDel = AppConst.app2BaseUrl + "/email/emailDel";
    /**
     * 公开信息删除
     */
    public String workInfo_workInfoDel = AppConst.app2BaseUrl + "/workInfo/workInfoDel";
    /**
     * 督查通知删除
     */
    public String overSeer_overSeerDel = AppConst.app2BaseUrl + "/overSeer/overSeerDel";

    /**
     * 保存文章
     */
    public String document_saveDocument = AppConst.app2BaseUrl + "/document/saveDocument";
    /**
     * 上传文件
     */
    public String document_saveFile = AppConst.app2BaseUrl + "/document/saveFile";
    /**
     * 发送邮件
     */
    public String email_emailForm = AppConst.app2BaseUrl + "/email/emailForm";
    /**
     * 获取部门信息树型对象
     */
    public String leader_getModelTree = AppConst.app2BaseUrl + "/leader/getModelTree";
    /**
     * 获取部门人员树型对象
     */
    public String leave_getUserTree = AppConst.app2BaseUrl + "/leave/getUserTree";
    /**
     * 签批图片上传
     */
    public String document_postil = AppConst.app2BaseUrl + "/document/postil";
    /**
     * 请假
     */
    public String leave_saveLeave = AppConst.app2BaseUrl + "/leave/saveLeave";
    /**
     * 请假签批
     */
    public String leave_postil = AppConst.app2BaseUrl + "/leave/postil";
    /**
     * 请假详情
     */
    public String leave_detailLeave = AppConst.app2BaseUrl + "/leave/detailLeave";
    /**
     * 请假类型
     */
    public String leave_getLeaveType = AppConst.app2BaseUrl + "/leave/getLeaveType";
    /**
     * 请假列表
     */
    public String leave_getLeaveList = AppConst.app2BaseUrl + "/leave/getLeaveList";
    /**
     * 文件签批保存
     */
    public String fileSign_saveFileSign = AppConst.app2BaseUrl + "/fileSign/saveFileSign";
    /**
     * 文件签批详情
     */
    public String fileSign_detailFileSign = AppConst.app2BaseUrl + "/fileSign/detailFileSign";
    /**
     * 文件签批列表
     */
    public String fileSign_getFileSignList = AppConst.app2BaseUrl + "/fileSign/getFileSignList";
    /**
     * 文件签批
     */
    public String fileSign_postil = AppConst.app2BaseUrl + "/fileSign/postil";
    /**
     * 删除签批信息
     */
    public String fileSign_delPostil = AppConst.app2BaseUrl + "/fileSign/delPostil";
    /**
     * 删除签批
     */
    public String fileSign_delFileSign = AppConst.app2BaseUrl + "/fileSign/delFileSign";
    /**
     * 删除请假
     */
    public String leave_delLeave = AppConst.app2BaseUrl + "/leave/delLeave";
    /**
     * 获取文件或文件夹列表
     */
    public String fileCabinet_getFileList = AppConst.app2BaseUrl + "/fileCabinet/getFileList";
    /**
     * 保存文件夹
     */
    public String fileCabinet_createFold = AppConst.app2BaseUrl + "/fileCabinet/createFold";
    /**
     * 删除文件
     */
    public String fileCabinet_delFile = AppConst.app2BaseUrl + "/fileCabinet/delFile";
    /**
     * 上传文件柜
     */
    public String fileCabinet_upFile = AppConst.app2BaseUrl + "/fileCabinet/upFile";
    /**
     * 保存共享成员
     */
    public String fileCabinet_editFoldSeedeeId = AppConst.app2BaseUrl + "/fileCabinet/editFoldSeedeeId";
    /**
     * 版本更新
     */
    public String getAppVersion = AppConst.app2BaseUrl + "/getAppVersion";
}
