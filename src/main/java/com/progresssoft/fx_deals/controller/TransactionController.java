package com.progresssoft.fx_deals.controller;

import com.progresssoft.fx_deals.Exception.ResourceNotFoundException;
import com.progresssoft.fx_deals.Repo.TransactionRepository;
import com.progresssoft.fx_deals.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Transaction")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    //GetAllTrans
    @GetMapping
    public List<Transaction> GetAllTransactions(){
        return this.transactionRepository.findAll();
    }

    //GetOneTrans
    @GetMapping("/{id}")
    public Transaction GetTransaction(@PathVariable(name = "id") String id){
        return this.transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No transaction was found with the id: " + id));
    }

    //CreateTrans
    @PostMapping
    public Transaction CreateTransaction(@RequestBody Transaction transaction){
        return this.transactionRepository.save(transaction);
    }

    //UpdateTrans
    @PutMapping
    public Transaction UpdateTransaction(@RequestBody Transaction updatedTrans, @PathVariable(name = "id") String id){
        this.transactionRepository.findById(id) //check if user exist.
                .orElseThrow(() -> new ResourceNotFoundException("No transaction was found with the id: " + id));
        updatedTrans.setUniqueId(id);
        return this.transactionRepository.save(updatedTrans);
    }

    //DeleteTrans
    //to be implemented...
}
