package org.weixin4j.qy.model.user;

/**
 * 成员
 *
 * @author yangqisheng
 */
public class User implements java.io.Serializable {

    /**
     * 成员UserID。对应管理端的帐号，企业内必须唯一。不区分大小写，长度为1~64个字节
     */
    private String userid;
    /**
     * 成员名称。长度为1~64个utf8字符
     */
    private String name;
    /**
     * 成员别名。长度1~32个utf8字符
     */
    private String alias;
    /**
     * 成员所属部门id列表,不超过20个
     */
    private int[] department;
    /**
     * 部门内的排序值，默认为0，成员次序以创建时间从小到大排列。数量必须和department一致，数值越大排序越前面。有效的值范围是[0, 2^32)
     */
    private int[] order;
    /**
     * 个数必须和department一致，表示在所在的部门内是否为上级。1表示为上级，0表示非上级。在审批等应用里可以用来标识上级审批人
     */
    private int[] is_leader_in_dept;
    /**
     * 职务信息。长度为0~128个字符
     */
    private String position;
    /**
     * 性别。1表示男性，2表示女性
     */
    private String gender;
    /**
     * 手机号码。企业内必须唯一，mobile/email二者不能同时为空
     */
    private String mobile;
    /**
     * 邮箱。长度6~64个字节，且为有效的email格式。企业内必须唯一，mobile/email二者不能同时为空
     */
    private String email;
    /**
     * 成员启用状态。1表示启用的成员，0表示被禁用。注意，服务商调用接口不会返回此字段
     */
    private Integer enable;
    /**
     * 成员头像的mediaid，通过素材管理接口上传图片获得的mediaid
     */
    private String avatar_mediaid;
    /**
     * 座机。32字节以内，由纯数字或’-‘号组成。
     */
    private String telephone;
    /**
     * 自定义字段。
     */
    private ExtAttr extattr;
    /**
     * 是否邀请该成员使用企业微信（将通过微信服务通知或短信或邮件下发邀请，每天自动下发一次，最多持续3个工作日），默认值为true。
     */
    private boolean to_invite;
    /**
     * 对外职务，如果设置了该值，则以此作为对外展示的职务，否则以position来展示。长度12个汉字内
     */
    private String external_position;
    /**
     * 成员对外属性
     */
    private ExternalProfile external_profile;
    /**
     * 读取可获得
     *
     * 激活状态: 1=已激活，2=已禁用，4=未激活。
     *
     * 已激活代表已激活企业微信或已关注微工作台（原企业号）。未激活代表既未激活企业微信又未关注微工作台（原企业号）。
     */
    private int status;
    /**
     * 员工个人二维码，扫描可添加为外部联系人；
     *
     * 第三方仅通讯录应用可获取
     */
    private String qr_code;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int[] getDepartment() {
        return department;
    }

    public void setDepartment(int[] department) {
        this.department = department;
    }

    public int[] getOrder() {
        return order;
    }

    public void setOrder(int[] order) {
        this.order = order;
    }

    public int[] getIs_leader_in_dept() {
        return is_leader_in_dept;
    }

    public void setIs_leader_in_dept(int[] is_leader_in_dept) {
        this.is_leader_in_dept = is_leader_in_dept;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getAvatar_mediaid() {
        return avatar_mediaid;
    }

    public void setAvatar_mediaid(String avatar_mediaid) {
        this.avatar_mediaid = avatar_mediaid;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public ExtAttr getExtattr() {
        return extattr;
    }

    public void setExtattr(ExtAttr extattr) {
        this.extattr = extattr;
    }

    public boolean isTo_invite() {
        return to_invite;
    }

    public void setTo_invite(boolean to_invite) {
        this.to_invite = to_invite;
    }

    public String getExternal_position() {
        return external_position;
    }

    public void setExternal_position(String external_position) {
        this.external_position = external_position;
    }

    public ExternalProfile getExternal_profile() {
        return external_profile;
    }

    public void setExternal_profile(ExternalProfile external_profile) {
        this.external_profile = external_profile;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getQr_code() {
        return qr_code;
    }

    public void setQr_code(String qr_code) {
        this.qr_code = qr_code;
    }

}
