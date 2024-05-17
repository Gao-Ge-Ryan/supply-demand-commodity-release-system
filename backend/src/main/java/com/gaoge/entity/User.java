package com.gaoge.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Table(name = "tb_user")
@ApiModel(description = "用户", value = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
//    @Column(name = "userName")
//    @NotBlank(message = "账号不能为空")
    @Pattern(regexp = "^[a-zA-Z][A-Za-z0-9]{2,9}+$",message = "账号必须以字母开头，长度在3-10之间，只能包含英文字符、数字")
    private String userName;
//    @NotBlank(message = "密码不能为空")
    @Pattern(  regexp = "^[a-zA-Z][a-zA-Z0-9_]{5,17}$",message = "密码必须以字母开头，长度在6-18之间，只能包含英文字符、数字和下划线")
    private String password;
    @NotBlank(message = "昵称不能为空")
    private String nickName;
    @Pattern(regexp = "^1[3|4|5|7|8][0-9]{9}$",message = "请输入正确的手机号")
    private String phone;
    @Pattern(regexp = "^\\d{15}|\\d{18}$",message = "请输入15或18位有效身份证号")
    private String identityNum;
    @Length(min = 2,max = 30,message = "请输入的地址长度在2-30之间")
    private String address;
    @Pattern(regexp = "^(user|user,expert|user,expert,admin)$",message = "设置角色请输入 user 或 user,expert 或 user,expert,admin 等角色")
    private String role;
    private Date createTime;
    private Date updateTime;
    private int integral;
    private int credit;
    private String avatar;
}
