package team404.conference.general.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team404.conference.modules.account.model.Account;
import team404.conference.general.dto.SignInDto;
import team404.conference.general.dto.TokenDto;
import team404.conference.modules.account.repository.AccountRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignInServiceImpl implements SignInService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public TokenDto signIn(SignInDto signInDto) {
        Optional<Account> userOptional = accountRepository.findAccountByLogin(signInDto.getLogin());

        if (userOptional.isPresent()) {
            Account account = userOptional.get();
            if (passwordEncoder.matches(signInDto.getPassword(), account.getPassword())) {
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, 3);
                String token = Jwts.builder()
                        .setSubject(account.getId().toString())
                        .setExpiration(calendar.getTime())
                        .signWith(SignatureAlgorithm.HS256, secret)
                        .compact();
                return new TokenDto(token);
            } else throw new AccessDeniedException("Wrong login/password");
        } else throw new AccessDeniedException("User not found");
    }

}
