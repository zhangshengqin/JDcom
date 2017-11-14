package com.six.jdcom.wode.view;

/**
 * Created by 额头发 on 2017/11/13.
 */

public interface UserView {
    void gainFail();

    void gainSuc(String data);

    void loaginFail(String msg);

    void gainUserInfoFail();

    void gainUserSuc(String data);

    void gainaddSuc(String data);

    void unloadSuc(String msg);

    void unloadFail(String msg);

    void setNickSuc(String msg);
}
