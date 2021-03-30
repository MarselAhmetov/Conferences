package team404.conference.modules.account.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team404.conference.general.exception.ApiException;
import team404.conference.general.exception.StatusCode;
import team404.conference.modules.account.dto.AccountSafeDto;
import team404.conference.modules.account.model.Account;
import team404.conference.modules.account.model.Role;
import team404.conference.general.config.mapping.GlobalMapper;
import team404.conference.general.repository.SimpleDao;
import team404.conference.general.security.jwt.details.UserDetailsImpl;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final SimpleDao simpleDao;
    private final GlobalMapper mapper;

    @Override
    @Transactional
    public void submit(AccountSafeDto accountSafeDto) {
        Account account;
        if (accountSafeDto.getId() != null) {
            Optional<Account> optionalAccount = simpleDao.findById(Account.class, accountSafeDto.getId());

            account = optionalAccount.orElseThrow(() -> {
                throw new ApiException("Account with such id does not found", "account.id", StatusCode.SC_404);
            });
        } else {
            account = new Account();
            account.setRole(Role.LISTENER);
        }

        account.setLogin(accountSafeDto.getLogin());
        if (accountSafeDto.getRole() != null) {
            account.setRole(Role.valueOf(accountSafeDto.getRole()));
        }


        simpleDao.saveOrUpdate(account);
    }

    @Override
    public AccountSafeDto getCurrentAccountInformation() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getDetails();
        Optional<Account> optionalAccount = simpleDao.findById(Account.class, userDetails.getAccount().getId());

        Account account = optionalAccount.orElseThrow(() -> {
            throw new ApiException("Account with such id does not found", "presentation.id", StatusCode.SC_404);
        });

        return mapper.map(account, AccountSafeDto.class);
    }

    @Override
    public List<AccountSafeDto> getAll() {
        return mapper.mapAsList(simpleDao.findAll(Account.class), AccountSafeDto.class);
    }
}
