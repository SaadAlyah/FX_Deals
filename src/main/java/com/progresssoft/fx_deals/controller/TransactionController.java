package com.progresssoft.fx_deals.controller;

import com.progresssoft.fx_deals.Exception.ResourceNotFoundException;
import com.progresssoft.fx_deals.Repo.TransactionRepository;
import com.progresssoft.fx_deals.entity.Transaction;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/Transaction")
public class TransactionController {

    //region GLOBAL PARAMETERS
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private EntityManager entityManager;
    //endregion

    //region GET
    //GetAllTrans
    @GetMapping
    public List<Transaction> GetAllTransactions(@PathVariable(name = "id", required = false) Boolean isDeleted){
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedTransactionFilter");
        filter.setParameter("isDeleted", isDeleted);
        List<Transaction> transactions =  transactionRepository.findAll();
        session.disableFilter("deletedTransactionFilter");
        return transactions;
    }

    //GetOneTrans
    @GetMapping("/{id}")
    public Transaction GetTransaction(@PathVariable(name = "id") String id){
        return this.transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No transaction was found with the id: " + id));
    }
    //endregion

    //region POST
    //CreateTrans
    @PostMapping
    public Transaction CreateTransaction(@Valid @RequestBody Transaction transaction){
        return this.transactionRepository.save(transaction);
    }
    //endregion

    //region PUT
    //UpdateTrans
    @PutMapping
    public Transaction UpdateTransaction(@Valid @RequestBody Transaction updatedTrans, @PathVariable(name = "id") String id){
        this.transactionRepository.findById(id) //check if transaction exist.
                .orElseThrow(() -> new ResourceNotFoundException("No transaction was found with the id: " + id));
        updatedTrans.setUniqueId(id);
        return this.transactionRepository.save(updatedTrans);
    }
    //endregion

    //region DELETE
    //DeleteTrans
    @DeleteMapping("/{id}")
    public ResponseEntity<Transaction> deleteUser(@PathVariable ("id") String transId){
        this.transactionRepository.findById(transId)//check if transaction exist.
                .orElseThrow(() -> new ResourceNotFoundException("No transaction was found with the id: " + transId));
        this.transactionRepository.deleteById(transId);
        return ResponseEntity.ok().build();
    }
    //endregion

}
