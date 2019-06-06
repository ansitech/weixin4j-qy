package org.weixin4j.qy.model;

/**
 * 应用类型
 *
 * @author yangqisheng
 */
public enum CorpType {
    /**
     * 通讯录
     */
    TXL("txl");

    private String value = "";

    CorpType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
