package org.weixin4j.qy.model.user;

import java.util.List;

/**
 * 成员对外属性
 *
 * @author yangqisheng
 */
public class ExternalProfile implements java.io.Serializable {

    private String external_corp_name;
    private List<Attr> external_attr;

    public String getExternal_corp_name() {
        return external_corp_name;
    }

    public void setExternal_corp_name(String external_corp_name) {
        this.external_corp_name = external_corp_name;
    }

    public List<Attr> getExternal_attr() {
        return external_attr;
    }

    public void setExternal_attr(List<Attr> external_attr) {
        this.external_attr = external_attr;
    }
}
