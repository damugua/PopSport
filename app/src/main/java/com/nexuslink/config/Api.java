package com.nexuslink.config;



import com.nexuslink.User;
import com.nexuslink.model.data.ChangeInfo;
import com.nexuslink.model.data.ChangeInfo1;
import com.nexuslink.model.data.ChangeInfoPassword;
import com.nexuslink.model.data.FollowInfo;
import com.nexuslink.model.data.FollowedInfo;
import com.nexuslink.model.data.FriendInfo;
import com.nexuslink.model.data.SearchInfo;
import com.nexuslink.model.data.UserInfo;
import com.nexuslink.model.data.WeatherInfo;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ASUS-NB on 2017/1/15.
 */

public interface Api {
    //获取天气信息
    @GET("onebox/weather/query")
    Observable<WeatherInfo> getWeatherInfo(@Query("cityname")String cityname, @Query("key")String key);
    //关注某人
    @GET("friend/follow")
    Observable<FollowInfo> getFollowInfo(@Query("uId") int uId,@Query("fId") int fId);

    //获取已关注的好友
    @GET("friend/mine")
    Observable<FollowedInfo> getFollowedInfo(@Query("uId") int uId);

    //登录
    @FormUrlEncoded
    @POST("user/login")
    Observable<User> logIn(@Field("uName")String uName, @Field("uPassword")String uPassword);

    //获取好友的个人信息
    @FormUrlEncoded
    @POST("friend/getInfo")
    Observable<FriendInfo> getFriendInfo(@Field("fId")int fId);

    //获取用户个人信息
    @FormUrlEncoded
    @POST("user/getInfo")
    Observable<UserInfo> getUserInfo(@Field("uId")int uId);

    //修改用户的个人信息
    @FormUrlEncoded
    @POST("user/changeInfo")
    Observable<ChangeInfo> changeUserInfo(@Field("uId") int uId,@Field("uGender")char uGender,@Field("uHeight")int
            uHeight,@Field("uWeight")int uWeight);

    //修改用户的头像
    @FormUrlEncoded
    @POST("user/cahngeName")
    Observable<ChangeInfo1> changeNickName(@Field("uId")int uId, @Field("uName")String uName);

    //搜索用户
    @FormUrlEncoded
    @POST("friend/search")
    Observable<SearchInfo> searchUser(@Field("type")int type,@Field("keyword")String keyword);

    //修改密码
    @FormUrlEncoded
    @POST("user/password")
    Observable<ChangeInfoPassword> changePassword(@Field("uId")int uId,@Field("uOldPassword")String uOldPassword,@Field("uNewPassword")String uNewPassword);
}
