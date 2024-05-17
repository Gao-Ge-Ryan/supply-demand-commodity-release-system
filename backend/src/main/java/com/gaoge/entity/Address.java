package com.gaoge.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Data
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_address")
public class Address {
    @Id
    private Integer id;
    private String own_name;
    @NotBlank(message = "收货人不能为空")
    @Length(min = 2,max = 10,message = "收货人长度在2-10之间")
    private String consignee;
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3|4|5|7|8][0-9]{9}$",message = "请输入正确的手机号")
    private String phone;
    @NotBlank(message = "地址不能为空")
    @Length(min = 2,max = 40,message = "地址长度在2-40之间")
    private String address_detail;
    private Boolean is_default;
}
