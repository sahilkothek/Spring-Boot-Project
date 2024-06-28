package bankingapplication.bank.services;

import bankingapplication.bank.Model.AccountDto;

import java.util.List;


public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(Long id);

    AccountDto AddBalance(Long id ,double amount);

    AccountDto getBalance(Long id ,double amount);

    List<AccountDto> getAllAccounts();

     void deleteAccountById(Long id);

}
