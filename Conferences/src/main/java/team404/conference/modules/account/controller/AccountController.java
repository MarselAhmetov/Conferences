package team404.conference.modules.account.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team404.conference.modules.account.dto.AccountSafeDto;
import team404.conference.modules.account.service.AccountService;

import java.util.List;

@Api(value = "Account controller")
@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @ApiOperation(value = "Submit Account information")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<String> submit(@ApiParam @RequestBody AccountSafeDto accountSafeDto) {
        accountService.submit(accountSafeDto);
        return ResponseEntity.ok("Ok");
    }

    @ApiOperation(value = "Get current Account information")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/current")
    public ResponseEntity<AccountSafeDto> getCurrent() {
        return ResponseEntity.ok(accountService.getCurrentAccountInformation());
    }

    @ApiOperation(value = "Get Accounts list")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<AccountSafeDto>> getAll() {
        return ResponseEntity.ok(accountService.getAll());
    }
}
