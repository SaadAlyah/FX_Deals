package com.progresssoft.fx_deals;

import com.progresssoft.fx_deals.Repo.TransactionRepository;
import com.progresssoft.fx_deals.controller.TransactionController;
import com.progresssoft.fx_deals.entity.Transaction;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FxDealsApplicationTests {

    @Autowired
    TransactionRepository repo;

    TransactionController controller = new TransactionController();
    Transaction transaction = new Transaction("USDْ", "UAE", new Date(), 100);;


    @Test
    @Order(1)
    public void testCreate() {
        transaction = controller.CreateTransaction(transaction);

        assertNotNull(repo.findById(transaction.getUniqueId()).get());
    }

    @Test
    @Order(2)
    public void testReadAll() {
        var list = controller.GetAllTransactions(false);
        assertThat(list).size().isGreaterThan(0);
    }

    @Test
    @Order(3)
    public void testRead() {
        var trans = controller.GetTransaction(transaction.getUniqueId(), false);
        assertEquals("UAE", trans.getToCurrency());
    }

    @Test
    @Order(4)
    public void testUpdate() {
        transaction.setAmount(800);
        transaction = controller.UpdateTransaction(transaction, transaction.getUniqueId());
        assertNotEquals(100.00, repo.findById(transaction.getUniqueId()).get().getAmount());
    }

    @Test
    @Order(5)
    public void testDelete() {
        controller.deleteUser(transaction.getUniqueId());
        assertThat(repo.getById(transaction.getUniqueId()).getDeleted()).isTrue();
    }


}
