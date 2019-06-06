package org.weixin4j.qy.model.user;

/**
 * 扩展属性
 *
 * @author yangqisheng
 */
public class Attr implements java.io.Serializable {

    /**
     * 属性类型: 0-本文 1-网页 2-小程序
     */
    private int type;
    /**
     * 属性名称： 需要先确保在管理端有创建改属性，否则会忽略
     */
    private String name;
    private AttrText text;
    private AttrWeb web;
    private AttrMiniprogram miniprogram;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AttrText getText() {
        return text;
    }

    public void setText(AttrText text) {
        this.text = text;
    }

    public AttrWeb getWeb() {
        return web;
    }

    public void setWeb(AttrWeb web) {
        this.web = web;
    }

    public AttrMiniprogram getMiniprogram() {
        return miniprogram;
    }

    public void setMiniprogram(AttrMiniprogram miniprogram) {
        this.miniprogram = miniprogram;
    }
}
