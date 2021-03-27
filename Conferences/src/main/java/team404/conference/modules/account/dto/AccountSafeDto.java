package team404.conference.modules.account.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import team404.conference.general.dto.LongIdDto;

@Getter
@Setter
public class AccountSafeDto extends LongIdDto {
    private String login;
    private String role;
}
