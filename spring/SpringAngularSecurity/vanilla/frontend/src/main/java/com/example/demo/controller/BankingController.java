package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("banking")
public class BankingController {

    // GET /banking/transfer?account=Bob&amount=200
    @GetMapping("transfer")
    public Map<String, Object> transferMoney(Principal user, @RequestParam Map<String, String> params) {
        String to = params.get("account");
        Integer amount = Integer.parseInt(params.get("amount"));
        String from = user.getName();
        System.out.printf("Sending %d from %s to %s", amount, from, to);

        return Map.of(
                "from", from,
                "to", to,
                "amount", amount
        );
    }

    /*
        POST /banking/transfer
        {
            "account": "Bob",
            "amount": 200
        }
     */
    @PostMapping("transfer")
    public Map<String, Object> transferMoneyPost(Principal user, @RequestBody Map<String, String> body) {
        String to = body.get("account");
        Integer amount = Integer.parseInt(body.get("amount"));
        String from = user.getName();
        System.out.printf("Sending %d from %s to %s", amount, from, to);

        return Map.of(
                "from", from,
                "to", to,
                "amount", amount
        );
    }
}
