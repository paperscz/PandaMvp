package com.pandaq.appcore.transmitter;

/**
 * Created by huxinyu on 2018/6/8.
 * Email : panda.h@foxmail.com
 * <p>
 * Description : 上传和下载请求回调
 */
public interface TransmitCallback {

    /**
     * 上传/下载成功
     */
    void done(UploadResponse.DataBean data);


    /**
     * 上传/下载失败
     *
     * @param e 异常类
     */
    void onFailed(Exception e);

    /**
     * 上传/下载进度
     *
     * @param progress 进度 % 单位
     */
    void inProgress(int progress);
}
