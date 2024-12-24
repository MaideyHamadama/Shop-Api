package com.shop.backend.controller;


import com.shop.backend.service.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/stripe")
public class StripeController {

    private final StripeService stripeService;

    public StripeController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @PostMapping("/create-payment-intent")
    public ResponseEntity<?> createPaymentIntent(@RequestBody Map<String, Object> data) {
        try {
            Long amount = Long.parseLong(data.get("amount").toString());
            String currency = (String) data.get("currency");
            
            PaymentIntent paymentIntent = stripeService.createPaymentIntent(amount, currency);
            
            return ResponseEntity.ok(Map.of(
                "clientSecret", paymentIntent.getClientSecret()
            ));
        } catch (StripeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}