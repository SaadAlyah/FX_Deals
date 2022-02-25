package com.progresssoft.fx_deals.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @GetMapping("v1/Transaction/{transId}")
    public String getTransaction(@PathVariable("transId") String id) throws Exception{
        return "";
    }
}
