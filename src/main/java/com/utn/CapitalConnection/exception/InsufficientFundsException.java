package com.utn.CapitalConnection.exception;

import java.math.BigDecimal;

public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(BigDecimal balance, BigDecimal amount) {
        super("Insufficient funds: Wallet balance is " + balance + ", but tried to use " + amount);
    }
}

