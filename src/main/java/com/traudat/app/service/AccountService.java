package com.traudat.app.service;

import com.traudat.app.entity.Account;

public interface AccountService {
    public Account login(String loginId, String password);
}
