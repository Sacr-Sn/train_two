package com.sn.demo02.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author sn
 * @since 2024-09-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("s_stu")
@ApiModel(value="SStu对象", description="")
public class SStu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学生主键")
    @TableId(value = "sid", type = IdType.AUTO)
    private Integer sid;

    @ApiModelProperty(value = "学生姓名")
    private String stuName;

    @ApiModelProperty(value = "学生性别")
    private String sex;

    @ApiModelProperty(value = "学生年龄")
    private Integer age;

    @ApiModelProperty(value = "学生邮箱")
    private String email;

    @ApiModelProperty(value = "学生班级id")
    private Integer cid;

    @ApiModelProperty(value = "学生余额")
    private Integer balance;

    @ApiModelProperty(value = "手机号")
    private String phone;


}
