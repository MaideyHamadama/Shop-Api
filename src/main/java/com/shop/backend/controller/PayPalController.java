package com.shop.backend.controller;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.shop.backend.service.PayPalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/paypal")
public class PayPalController {

    @Autowired
    private PayPalService payPalService;

    private static final String SUCCESS_URL = "http://localhost:8000/api/paypal/success";
    private static final String CANCEL_URL = "http://localhost:8000/api/paypal/cancel";

    @PostMapping("/pay")
    public String pay(@RequestParam double price) {
        try {
            Payment payment = payPalService.createPayment(
                price, "USD", "paypal",
                "sale", "Payment description",
                CANCEL_URL, SUCCESS_URL);

            for (var link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    return "redirect:" + link.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping("/success")
    public String successPay(@RequestParam("paymentId") String paymentId,
                             @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = payPalService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping("/cancel")
    public String cancelPay() {
        return "Payment canceled";
    }
}
