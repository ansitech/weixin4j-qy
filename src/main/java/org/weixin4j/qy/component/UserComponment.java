/*
 * 企业微信API(JAVA) SDK
 *
 * Copyright (c) 2014, Ansitech Network Technology Co.,Ltd All rights reserved.
 * 
 * http://www.weixin4j.org/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.weixin4j.qy.component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.weixin4j.qy.Configuration;
import org.weixin4j.qy.WeixinQy;
import org.weixin4j.qy.WeixinException;
import org.weixin4j.qy.http.HttpsClient;
import org.weixin4j.qy.http.Response;
import org.weixin4j.qy.model.CorpType;
import org.weixin4j.qy.model.user.User;

/**
 * 通讯录之成员管理
 *
 * @author yangqisheng
 */
public class UserComponment extends AbstractComponent {

    public UserComponment(WeixinQy weixinQy, CorpType corpType) {
        super(weixinQy, corpType);
    }

    /**
     * 创建成员
     *
     * @param userid 成员UserID。对应管理端的帐号，企业内必须唯一。不区分大小写，长度为1~64个字节
     * @param name 成员名称
     * @param departmentId 部门ID值
     * @throws org.weixin4j.qy.WeixinException 创建成员异常
     */
    public void create(String userid, String name, String email, String mobile, int departmentId) throws WeixinException {
        //内部业务验证
        if (departmentId <= 0) {
            throw new IllegalStateException("departmentId must be > 0");
        }
        if (userid == null || userid.equals("")) {
            throw new IllegalStateException("userid can not be null or empty");
        }
        if (name == null || name.equals("")) {
            throw new IllegalStateException("name can not be null or empty");
        }
        if ((email == null || email.equals("")) && (mobile == null || mobile.equals(""))) {
            throw new IllegalStateException("email/mobile cannot be both at the same time is empty");
        }
        //拼接参数
        JSONObject param = new JSONObject();
        param.put("userid", userid);
        param.put("name", name);
        if (email != null) {
            param.put("email", email);
        }
        if (mobile != null) {
            param.put("mobile", mobile);
        }
        //部门
        JSONArray department = new JSONArray();
        department.add(departmentId);
        param.put("department", department);
        //创建请求对象
        HttpsClient http = new HttpsClient();
        //调用获取access_token接口
        Response res = http.post("https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=" + weixinQy.getToken(corpType).getAccess_token(), param);
        //根据请求结果判定，是否验证成功
        JSONObject jsonObj = res.asJSONObject();
        if (jsonObj != null) {
            if (Configuration.isDebug()) {
                System.out.println("/user/create返回json：" + jsonObj.toString());
            }
            Object errcode = jsonObj.get("errcode");
            if (errcode != null && jsonObj.getIntValue("errcode") != 0) {
                //返回异常信息
                throw new WeixinException(getCause(jsonObj.getIntValue("errcode")));
            }
        }
    }

    /**
     * 更新成员
     *
     * @param userid 成员UserID。对应管理端的帐号，企业内必须唯一。不区分大小写，长度为1~64个字节
     * @param name 成员名称
     * @param departmentId 部门ID值
     * @throws org.weixin4j.qy.WeixinException 创建成员异常
     */
    public void update(String userid, String name, String email, String mobile, int departmentId) throws WeixinException {
        //内部业务验证
        if (departmentId <= 0) {
            throw new IllegalStateException("departmentId must be > 0");
        }
        if (userid == null || userid.equals("")) {
            throw new IllegalStateException("userid can not be null or empty");
        }
        if (name == null || name.equals("")) {
            throw new IllegalStateException("name can not be null or empty");
        }
        if ((email == null || email.equals("")) && (mobile == null || mobile.equals(""))) {
            throw new IllegalStateException("email/mobile cannot be both at the same time is empty");
        }
        //拼接参数
        JSONObject param = new JSONObject();
        param.put("userid", userid);
        param.put("name", name);
        if (email != null) {
            param.put("email", email);
        }
        if (mobile != null) {
            param.put("mobile", mobile);
        }
        //部门
        JSONArray department = new JSONArray();
        department.add(departmentId);
        param.put("department", department);
        //创建请求对象
        HttpsClient http = new HttpsClient();
        //调用获取access_token接口
        Response res = http.post("https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token=" + weixinQy.getToken(corpType).getAccess_token(), param);
        //根据请求结果判定，是否验证成功
        JSONObject jsonObj = res.asJSONObject();
        if (jsonObj != null) {
            if (Configuration.isDebug()) {
                System.out.println("/user/create返回json：" + jsonObj.toString());
            }
            Object errcode = jsonObj.get("errcode");
            if (errcode != null && jsonObj.getIntValue("errcode") != 0) {
                //返回异常信息
                throw new WeixinException(getCause(jsonObj.getIntValue("errcode")));
            }
        }
    }

    /**
     * 读取成员
     *
     * @param userid 成员UserID。对应管理端的帐号，企业内必须唯一。不区分大小写，长度为1~64个字节
     * @return 成员对象
     * @throws org.weixin4j.qy.WeixinException 微信操作异常
     */
    public User getUser(String userid) throws WeixinException {
        if (StringUtils.isEmpty(userid)) {
            throw new IllegalArgumentException("userid can't be null or empty");
        }
        //拼接参数
        String param = "?access_token=" + weixinQy.getToken(corpType).getAccess_token() + "&userid=" + userid;
        //创建请求对象
        HttpsClient http = new HttpsClient();
        //调用获取access_token接口
        Response res = http.get("https://qyapi.weixin.qq.com/cgi-bin/user/get" + param);
        //根据请求结果判定，是否验证成功
        JSONObject jsonObj = res.asJSONObject();
        if (jsonObj == null) {
            return null;
        }
        if (Configuration.isDebug()) {
            System.out.println("getUser返回json：" + jsonObj.toString());
        }
        Object errcode = jsonObj.get("errcode");
        if (errcode != null) {
            //返回异常信息
            throw new WeixinException(getCause(jsonObj.getIntValue("errcode")));
        }
        //设置公众号信息
        return JSONObject.toJavaObject(jsonObj, User.class);
    }
}
