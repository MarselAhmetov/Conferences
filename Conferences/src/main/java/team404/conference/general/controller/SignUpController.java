package team404.conference.general.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team404.conference.general.dto.SignUpDto;
import team404.conference.modules.account.dto.AccountDto;
import team404.conference.general.service.SignUpService;

@Api(value = "Registration")
@RestController
@AllArgsConstructor
public class SignUpController {

    private final SignUpService signUpService;

    @ApiOperation(value = "Register endpoint")
    @PostMapping("/api/signup")
    public void signUpRestaurateur(@ApiParam @RequestBody SignUpDto signUpDto) {
        signUpService.signUp(signUpDto);
    }
}
