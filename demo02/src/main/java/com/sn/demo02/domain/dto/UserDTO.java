package com.sn.demo02.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Data
@EqualsAndHashCode
@Validated
public class UserDTO {
    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空")
    @Size(min = 6,max = 18, message = "用户名长度必须在6到18之间")
    private String username;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    @Size(min = 6,max = 20, message = "密码长度必须在6到20之间")
    private String password;
}
