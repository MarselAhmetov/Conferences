package team404.conference.modules.account.service;

import team404.conference.modules.account.dto.AccountDto;
import team404.conference.modules.account.dto.AccountSafeDto;

import java.util.List;

public interface AccountService {
    void submit(AccountSafeDto accountSafeDto);
    AccountSafeDto getCurrentAccountInformation();
    List<AccountSafeDto> getAll();
}
