package team404.conference.general.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team404.conference.general.dto.SignUpDto;
import team404.conference.modules.account.dto.AccountDto;
import team404.conference.modules.account.model.Account;
import team404.conference.modules.account.model.Role;
import team404.conference.modules.account.repository.AccountRepository;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public void signUp(SignUpDto signUpDto) {
        Account account = Account.builder()
                .login(signUpDto.getLogin())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .role(Role.LISTENER)
                .build();
        accountRepository.save(account);
    }
}
