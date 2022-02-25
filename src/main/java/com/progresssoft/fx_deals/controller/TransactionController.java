package com.progresssoft.fx_deals.controller;

import com.progresssoft.fx_deals.Exception.ResourceNotFoundException;
import com.progresssoft.fx_deals.Repo.TransactionRepository;
import com.progresssoft.fx_deals.entity.Transaction;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger log = LoggerFactory.getLogger(TransactionController.class);
    //endregion

    //region GET
    //GetAllTrans
    @GetMapping
    public List<Transaction> GetAllTransactions(@RequestParam(defaultValue = "false") Boolean isDeleted){
        log.info("the method -> GetAllTransactions(Boolean isDeleted : {}) start.", isDeleted);
        var session = entityManager.unwrap(Session.class);
        var filter = session.enableFilter("deletedTransactionFilter");
        filter.setParameter("isDeleted", isDeleted);
        var transactions =  transactionRepository.findAll();
        session.disableFilter("deletedTransactionFilter");
        log.info("the method -> GetAllTransactions(Boolean isDeleted : {}) ends.", isDeleted);
        return transactions;
    }

    //GetOneTrans
    @GetMapping("/{id}")
    public Transaction GetTransaction(@PathVariable(name = "id") String id, @RequestParam(defaultValue = "false") Boolean isDeleted){
        log.info("the method -> GetTransaction(Boolean isDeleted : {}) start.", isDeleted);
        var trans = this.transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No transaction was found with the id: " + id));
        log.info("the method -> GetTransaction(Boolean isDeleted : {}) ends.", isDeleted);
        if (isDeleted == trans.getDeleted())
            return trans;
        else throw new ResourceNotFoundException("No transaction was found with the id: " + id);
    }
    //endregion

    //region POST
    //CreateTrans
    @PostMapping
    public Transaction CreateTransaction(@Valid @RequestBody Transaction transaction){
        log.info("the method -> CreateTransaction(Transaction transaction : {}) start.", transaction);
        var trans = this.transactionRepository.save(transaction);
        log.info("the method -> CreateTransaction(Transaction transaction : {}) ends.", transaction);
        return trans;
    }
    //endregion

    //region PUT
    //UpdateTrans
    @PutMapping("/{id}")
    public Transaction UpdateTransaction(@Valid @RequestBody Transaction updatedTrans, @PathVariable(name = "id") String id){
        log.info("the method -> UpdateTransaction(Transaction id : {}) start.", id);

        this.transactionRepository.findById(id) //check if transaction exist.
                .orElseThrow(() -> new ResourceNotFoundException("No transaction was found with the id: " + id));
        updatedTrans.setUniqueId(id);

        log.info("the method -> UpdateTransaction(Transaction id : {}) ends.", id);
        return this.transactionRepository.save(updatedTrans);
    }
    //endregion

    //region DELETE
    //DeleteTrans
    @DeleteMapping("/{id}")
    public ResponseEntity<Transaction> deleteUser(@PathVariable ("id") String transId){
        log.info("the method -> deleteUser(Transaction transId : {}) start.", transId);

        this.transactionRepository.findById(transId)//check if transaction exist.
                .orElseThrow(() -> new ResourceNotFoundException("No transaction was found with the id: " + transId));
        this.transactionRepository.deleteById(transId);

        log.info("the method -> deleteUser(Transaction transId : {}) ends.", transId);
        return ResponseEntity.ok().build();
    }
    //endregion

}
