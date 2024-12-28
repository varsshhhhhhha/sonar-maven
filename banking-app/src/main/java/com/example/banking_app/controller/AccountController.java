package com.example.banking_app.controller;

import com.example.banking_app.dto.Accountdto;
import com.example.banking_app.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //Add Account REST API

    @PostMapping

    public ResponseEntity<Accountdto> addAccount(@RequestBody Accountdto accountdto) {
        return new ResponseEntity<>(accountService.createAccount(accountdto), HttpStatus.CREATED);
    }
    //Get Account REST API
    @GetMapping("/{id}")
    public ResponseEntity<Accountdto> getAccountById(@PathVariable Long id) {
        Accountdto accountdto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountdto);
    }

    //Deposit REST API
    @PutMapping("/{id}/deposit")
    public ResponseEntity<Accountdto> deposit(@PathVariable Long id,
                                              @RequestBody Map<String, Double> request){
    Double amount = request.get("amount");

    Accountdto accountdto = accountService.deposit(id, amount);
    return ResponseEntity.ok(accountdto);

    }

    //Withdraw REST API
    @PutMapping("{id}/withdraw")
    public  ResponseEntity<Accountdto> withdraw(@PathVariable Long id,
                                                @RequestBody Map<String, Double> request){
        double amount = request.get("amount");
        Accountdto accountdto = accountService.withdraw(id, amount);
        return ResponseEntity.ok(accountdto);
    }

    //Get All Accounts REST API
    @GetMapping
    public ResponseEntity<List<Accountdto>> getAllAccounts(){
        List<Accountdto> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    //Delete Account REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted");
    }



}
