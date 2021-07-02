package com.traudat.app.service.impl;

import com.traudat.app.entity.Account;
import com.traudat.app.model.ElicException;
import com.traudat.app.repo.AccountRepository;
import com.traudat.app.service.AccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account login(String loginId, String password) {

        if (StringUtils.isEmpty(loginId)) {
            throw new ElicException("Please enter Login Id.");
        }

        if (StringUtils.isEmpty(password)) {
            throw new ElicException("Please enter password.");
        }

        Account account = accountRepository.findById(loginId)
                            .orElseThrow(() -> new ElicException("Please check your login ID again"));

        if (!password.equals(account.getPassword())) {
            throw new ElicException("Your password is incorrect.");
        }

        return account;
    }
}
