package team404.conference.general.service;

import team404.conference.general.dto.SignInDto;
import team404.conference.general.dto.TokenDto;

public interface SignInService {
    TokenDto signIn(SignInDto signInDto);
}
