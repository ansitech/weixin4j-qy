package org.weixin4j.qy.model.user;

/**
 * 网页类型的属性，url和title字段要么同时为空表示清除该属性，要么同时不为空
 *
 * @author yangqisheng
 */
public class AttrWeb implements java.io.Serializable {

    /**
     * 网页的展示标题,长度限制12个UTF8字符
     */
    private String title;
    /**
     * 网页的url,必须包含http或者https头
     */
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
