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

import org.weixin4j.qy.loader.ITokenLoader;

/**
 * 微信小程序对象构建器
 *
 * @author yangqisheng
 * @since 1.0.0
 */
public final class WeixinQyBuilder {

    private WeixinQy weixinQy;
    private ITokenLoader tokenLoader;

    /**
     * 获取一个新的微信构建器
     *
     * @return 微信构造器
     */
    public static WeixinQyBuilder newInstance() {
        WeixinQyBuilder builder = new WeixinQyBuilder();
        builder.weixinQy = new WeixinQy();
        return builder;
    }

    /**
     * 配置access_token加载器
     *
     * @param tokenLoader token加载器
     * @return 微信构造器
     */
    public WeixinQyBuilder setTokenLoader(ITokenLoader tokenLoader) {
        if (tokenLoader == null) {
            throw new IllegalStateException("tokenLoader can't be null");
        }
        this.tokenLoader = tokenLoader;
        return this;
    }

    /**
     * 返回最终配置好的Weixin对象
     *
     * @return 微信对象
     */
    public WeixinQy build() {
        if (tokenLoader != null) {
            weixinQy.tokenLoader = this.tokenLoader;
        }
        return weixinQy;
    }
}
