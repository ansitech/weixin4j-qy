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
import java.util.ArrayList;
import java.util.List;
import org.weixin4j.qy.Configuration;
import org.weixin4j.qy.WeixinQy;
import org.weixin4j.qy.WeixinException;
import org.weixin4j.qy.http.HttpsClient;
import org.weixin4j.qy.http.Response;
import org.weixin4j.qy.model.CorpType;
import org.weixin4j.qy.model.department.Department;

/**
 * 通讯录之部门管理
 *
 * @author yangqisheng
 */
public class DepartmentComponent extends AbstractComponent {

    public DepartmentComponent(WeixinQy weixinQy, CorpType corpType) {
        super(weixinQy, corpType);
    }

    /**
     * 创建部门
     *
     * @param parentid 父部门ID
     * @param name 部门名字（30个字符以内）
     * @return 创建成功，返回带Id的Group对象
     * @throws org.weixin4j.qy.WeixinException 创建分组异常
     */
    public Department create(int parentid, String name) throws WeixinException {
        return create(parentid, name, 0);
    }

    /**
     * 创建部门
     *
     * @param parentid 父部门ID
     * @param name 部门名字（30个字符以内）
     * @order 在父部门中的次序
     * @return 创建成功，返回带Id的Group对象
     * @throws org.weixin4j.qy.WeixinException 创建分组异常
     */
    public Department create(int parentid, String name, int order) throws WeixinException {
        return create(parentid, name, 0, 0);
    }

    /**
     * 创建部门
     *
     * @param parentid 父部门ID
     * @param name 部门名字（30个字符以内）
     * @param order 在父部门中的次序
     * @param id 指定部门ID值
     * @return 创建成功，返回带Id的Group对象
     * @throws org.weixin4j.qy.WeixinException 创建分组异常
     */
    public Department create(int parentid, String name, int order, int id) throws WeixinException {
        //内部业务验证
        if (parentid <= 0) {
            throw new IllegalStateException("parentid must be > 0");
        }
        if (name == null || name.equals("")) {
            throw new IllegalStateException("name can not be null or empty");
        }
        //拼接参数
        JSONObject param = new JSONObject();
        param.put("parentid", parentid);
        param.put("name", name);
        if (order > 0) {
            param.put("order", order);
        }
        if (id > 0) {
            param.put("id", id);
        }
        //创建请求对象
        HttpsClient http = new HttpsClient();
        //调用获取access_token接口
        Response res = http.post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=" + weixinQy.getToken(corpType).getAccess_token(), param);
        //根据请求结果判定，是否验证成功
        JSONObject jsonObj = res.asJSONObject();
        Department department = new Department();
        department.setParentid(parentid);
        department.setName(name);
        department.setOrder(order);
        if (jsonObj != null) {
            if (Configuration.isDebug()) {
                System.out.println("/department/create返回json：" + jsonObj.toString());
            }
            Object errcode = jsonObj.get("errcode");
            if (errcode != null && jsonObj.getIntValue("errcode") != 0) {
                //返回异常信息
                throw new WeixinException(getCause(jsonObj.getIntValue("errcode")));
            }
            department.setId(jsonObj.getIntValue("id"));
        }
        return department;
    }

    /**
     * 获取所有部门列表
     *
     * @return 部门列表
     * @throws org.weixin4j.qy.WeixinException 查询所有部门异常
     */
    public List<Department> listAll() throws WeixinException {
        return list(0);
    }

    /**
     * 获取部门及子部门列表
     *
     * @param parentId 部门id，获取部门及其下的子部门。
     * @return 部门列表
     * @throws org.weixin4j.qy.WeixinException 获取部门及子部门列表异常
     */
    public List<Department> list(int parentId) throws WeixinException {
        List<Department> departmentList = new ArrayList<Department>();
        //创建请求对象
        HttpsClient http = new HttpsClient();
        //调用获取access_token接口
        Response res = http.get("https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=" + weixinQy.getToken(corpType).getAccess_token() + (parentId > 0 ? "&id=" + parentId : ""));
        //根据请求结果判定，是否验证成功
        JSONObject jsonObj = res.asJSONObject();
        if (jsonObj != null) {
            if (Configuration.isDebug()) {
                System.out.println("getGroups返回json：" + jsonObj.toString());
            }
            Object errcode = jsonObj.get("errcode");
            if (errcode != null && jsonObj.getIntValue("errcode") != 0) {
                //返回异常信息
                throw new WeixinException(getCause(jsonObj.getIntValue("errcode")));
            }
            //获取分组列表
            JSONArray departments = jsonObj.getJSONArray("department");
            for (int i = 0; i < departments.size(); i++) {
                JSONObject jsonGroup = departments.getJSONObject(i);
                Department group = (Department) JSONObject.toJavaObject(jsonGroup, Department.class);
                departmentList.add(group);
            }
        }
        return departmentList;
    }

    /**
     * 修改部门名称
     *
     * @param id 部门id
     * @param name 部门名称。长度限制为1~32个字符，字符不能包括\:?”<>｜
     * @throws org.weixin4j.qy.WeixinException 修改部门名称异常
     */
    public void update(int id, String name) throws WeixinException {
        update(id, name, 0);
    }

    /**
     * 修改部门名称
     *
     * @param id 部门id
     * @param name 部门名称。长度限制为1~32个字符，字符不能包括\:?”<>｜
     * @param sort 在父部门中的次序值。order值大的排序靠前。有效的值范围是[0, 2^32)
     * @throws org.weixin4j.qy.WeixinException 修改部门异常
     */
    public void update(int id, String name, int sort) throws WeixinException {
        update(id, name, 0, 0);
    }

    /**
     * 修改部门
     *
     * @param id 部门id
     * @param name 部门名称。长度限制为1~32个字符，字符不能包括\:?”<>｜
     * @param sort 在父部门中的次序值。order值大的排序靠前。有效的值范围是[0, 2^32)
     * @param parentid 移动到的父部门ID
     * @throws org.weixin4j.qy.WeixinException 修改部门异常
     */
    public void update(int id, String name, int sort, int parentid) throws WeixinException {
        //内部业务验证
        if (id < 0) {
            throw new IllegalStateException("id can not <= 0!");
        }
        if (name == null || name.equals("")) {
            throw new IllegalStateException("name is null!");
        }
        //拼接参数
        JSONObject postParam = new JSONObject();
        postParam.put("id", id);
        postParam.put("name", name);
        if (sort > 0) {
            postParam.put("sort", sort);
        }
        if (parentid > 0) {
            postParam.put("parentid", parentid);
        }
        //创建请求对象
        HttpsClient http = new HttpsClient();
        //调用获取access_token接口
        Response res = http.post("https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token=" + weixinQy.getToken(corpType).getAccess_token(), postParam);
        //根据请求结果判定，是否验证成功
        JSONObject jsonObj = res.asJSONObject();
        if (jsonObj != null) {
            if (Configuration.isDebug()) {
                System.out.println("/department/update返回json：" + jsonObj.toString());
            }
            //判断是否修改成功
            //正常时返回 {"errcode": 0, "errmsg": "ok"}
            //错误时返回 示例：{"errcode":40013,"errmsg":"invalid appid"}
            int errcode = jsonObj.getIntValue("errcode");
            //登录成功，设置accessToken和过期时间
            if (errcode != 0) {
                //返回异常信息
                throw new WeixinException(getCause(errcode));
            }
        }
    }

    /**
     * 删除部门
     *
     * @param id 部门Id
     * @throws org.weixin4j.qy.WeixinException 删除部门异常
     */
    public void delete(int id) throws WeixinException {
        //创建请求对象
        HttpsClient http = new HttpsClient();
        //调用获取access_token接口
        Response res = http.get("https://qyapi.weixin.qq.com/cgi-bin/department/delete?access_token=" + weixinQy.getToken(corpType).getAccess_token() + "&id=" + id);
        //根据请求结果判定，是否验证成功
        JSONObject jsonObj = res.asJSONObject();
        if (jsonObj != null) {
            if (Configuration.isDebug()) {
                System.out.println("/department/delete返回json：" + jsonObj.toString());
            }
            Object errcode = jsonObj.get("errcode");
            if (errcode != null && !errcode.toString().equals("0")) {
                //返回异常信息
                throw new WeixinException(getCause(jsonObj.getIntValue("errcode")));
            }
        }
    }
}
