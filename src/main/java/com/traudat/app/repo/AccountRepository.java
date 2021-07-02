package com.traudat.app.repo;

import com.traudat.app.entity.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends BaseRepository<Account, String> {
}
