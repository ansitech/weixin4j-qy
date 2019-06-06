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
package org.weixin4j.qy;

import java.util.HashMap;
import java.util.Map;

import org.weixin4j.qy.component.AbstractComponent;
import org.weixin4j.qy.component.BaseComponent;
import org.weixin4j.qy.component.DepartmentComponent;
import org.weixin4j.qy.component.UserComponment;
import org.weixin4j.qy.loader.DefaultTokenLoader;
import org.weixin4j.qy.loader.ITokenLoader;
import org.weixin4j.qy.model.CorpType;
import org.weixin4j.qy.model.base.Token;

/**
 * 企业微信基础支持对象
 *
 * @author yangqisheng
 * @since 1.0.0
 */
public class WeixinQy extends WeixinSupport implements java.io.Serializable {

    /**
     * 同步锁
     */
    private final static byte[] LOCK = new byte[0];
    /**
     * 企业ID
     */
    private final String corpId;
    /**
     * AccessToken加载器
     */
    protected ITokenLoader tokenLoader = new DefaultTokenLoader();
    /**
     * 新增组件
     */
    private final Map<String, AbstractComponent> components = new HashMap<String, AbstractComponent>();
    /**
     * 基础组件
     */
    private final BaseComponent baseComponent = new BaseComponent(this);

    /**
     * 单企业号，并且只支持一个企业号方式
     */
    public WeixinQy() {
        this.corpId = Configuration.getCorpId();
    }

    public String getCorpId() {
        return corpId;
    }

    /**
     * 获取Token对象
     *
     * @return Token对象
     * @throws org.weixin4j.qy.WeixinException 微信操作异常
     * @since 1.0.0
     */
    public Token getToken(CorpType corpType) throws WeixinException {
        Token token = tokenLoader.get(corpType);
        if (token == null) {
            synchronized (LOCK) {
                token = tokenLoader.get(corpType);
                if (token == null) {
                    String corpSecret = null;
                    if (corpType == CorpType.TXL) {
                        corpSecret = Configuration.getCorpTxlSecret();
                    }
                    token = base().getToken(corpSecret);
                    tokenLoader.refresh(corpType, token);
                }
            }
        }
        return token;
    }

    public BaseComponent base() {
        return baseComponent;
    }

    /**
     * 获取部门组件
     *
     * @return 部门操作组件
     * @since 1.0.0
     */
    public DepartmentComponent department() {
        String key = DepartmentComponent.class.getName();
        if (components.containsKey(key)) {
            return (DepartmentComponent) components.get(key);
        }
        DepartmentComponent component = new DepartmentComponent(this, CorpType.TXL);
        components.put(key, component);
        return component;
    }

    /**
     * 获取用户组件
     *
     * @return 用户操作组件
     * @since 1.0.0
     */
    public UserComponment user() {
        String key = UserComponment.class.getName();
        if (components.containsKey(key)) {
            return (UserComponment) components.get(key);
        }
        UserComponment component = new UserComponment(this, CorpType.TXL);
        components.put(key, component);
        return component;
    }
}
