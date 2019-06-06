package org.weixin4j.qy.model.user;

import java.util.List;

/**
 * 自定义字段。
 *
 * 自定义字段需要先在WEB管理端添加，见扩展属性添加方法，否则忽略未知属性的赋值。
 *
 * 与对外属性一致，不过只支持type=0的文本和type=1的网页类型，详细描述查看对外属性
 *
 * @author yangqisheng
 */
public class ExtAttr implements java.io.Serializable {

    private List<Attr> attrs;

    public List<Attr> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<Attr> attrs) {
        this.attrs = attrs;
    }
}
