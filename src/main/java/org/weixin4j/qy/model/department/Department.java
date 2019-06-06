package org.weixin4j.qy.model.department;

/**
 * 部门
 *
 * @author yangqisheng
 */
public class Department implements java.io.Serializable {

    /**
     * 部门id，32位整型，指定时必须大于1。若不填该参数，将自动生成id
     */
    private int id;
    /**
     * 父部门id，32位整型
     */
    private int parentid;
    /**
     * 部门名称。长度限制为1~32个字符，字符不能包括\:?”<>｜
     */
    private String name;
    /***
     * 在父部门中的次序值。order值大的排序靠前。有效的值范围是[0, 2^32)
     */
    private int order;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
