package org.dromara.common.core.domain.model;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * 短信登录对象
 *
 * @author Lion Li
 */

@Data
public class SmsLoginBody {

    /**
     * 租户ID
     */
    @NotBlank(message = "{tenant.number.not.blank}")
    private String tenantId;

    /**
     * 手机号
     */
    @NotBlank(message = "{user.phoneNmber.not.blank}")
    private String phonenumber;

    /**
     * 短信code
     */
    @NotBlank(message = "{sms.code.not.blank}")
    private String smsCode;

}
