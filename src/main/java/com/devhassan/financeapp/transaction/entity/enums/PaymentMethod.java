package com.devhassan.financeapp.transaction.entity.enums;

import java.util.ArrayList;
import java.util.List;

public enum PaymentMethod {
    CASH,
    CARD,
    BANK_TRANSFER;

    public static List<PaymentMethod> getPaymentMethods() {
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        paymentMethods.add(CASH);
        paymentMethods.add(CARD);
        paymentMethods.add(BANK_TRANSFER);

        return paymentMethods;
    }
}
