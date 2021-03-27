package team404.conference.general.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInDto {
    @ApiModelProperty(name = "Login", required = true, notes = "User login")
    private String login;
    @ApiModelProperty(name = "Password", required = true, notes = "User password")
    private String password;
}
