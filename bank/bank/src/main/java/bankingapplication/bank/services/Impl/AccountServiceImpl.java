package bankingapplication.bank.services.Impl;

import bankingapplication.bank.Mapper.AccountMapper;
import bankingapplication.bank.Model.AccountDto;
import bankingapplication.bank.entity.Account;
import bankingapplication.bank.repository.AccountRepository;
import bankingapplication.bank.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

   @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
       Account account = accountRepository.findById(id)
               .orElseThrow(()->new RuntimeException("Account does not exists"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto AddBalance(Long id, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Account does not exists"));

        double total = account.getBalance() + amount;
         account.setBalance(total);
         Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getBalance(Long id, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Account does not exists"));

        if(account.getBalance()<amount) {
            throw new RuntimeException("insufficient balace");
        }
        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {

       List<Account> accounts = accountRepository.findAll();
        return  accounts.stream()
                .map(account -> AccountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());


    }

    @Override
    public void deleteAccountById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Account does not exists"));

        accountRepository.deleteById(id);
    }


}
