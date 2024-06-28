package bankingapplication.bank.Model;

import lombok.*;


@Data
@AllArgsConstructor
public class AccountDto {

    private Long id;
    private String accountHolderName;
    private double balance;


}
