package team404.conference.general.service;

import team404.conference.general.dto.SignUpDto;
import team404.conference.modules.account.dto.AccountDto;

public interface SignUpService {
    void signUp(SignUpDto signUpDto);
}
