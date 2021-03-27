package team404.conference.modules.account.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto extends AccountSafeDto {
    private String password;
}
