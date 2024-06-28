package bankingapplication.bank.controller;

import bankingapplication.bank.Model.AccountDto;
import bankingapplication.bank.entity.Account;
import bankingapplication.bank.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {



    private final AccountService accountService;


    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/add")
    public ResponseEntity<AccountDto> addAccount(@RequestBody  AccountDto accountDto)
    {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id)
    {
        AccountDto accountDto = accountService.getAccountById(id);
        return new ResponseEntity<>(accountDto , HttpStatus.OK);
    }

    @PutMapping("/{id}/{amount}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id , @PathVariable double amount)
    {
        AccountDto accountDto = accountService.AddBalance(id , amount);
        return new ResponseEntity<>(accountDto,HttpStatus.OK);
    }
    @PutMapping("/{id}/{amount}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id , @PathVariable double amount)
    {
        AccountDto accountDto = accountService.getBalance(id , amount);
        return new ResponseEntity<>(accountDto,HttpStatus.OK);
    }

    @GetMapping("/Details")
    public ResponseEntity<List<AccountDto>>getAllAccounts()
    {
       List<AccountDto> accountDtos = accountService.getAllAccounts();
       return new ResponseEntity<>(accountDtos,HttpStatus.OK);
    }

    @DeleteMapping("/{id}/deleteAccount")

    public String  deleteAccount(@PathVariable Long id)
    {
        accountService.deleteAccountById(id);
        return "account deleted successfully.";
    }

}
