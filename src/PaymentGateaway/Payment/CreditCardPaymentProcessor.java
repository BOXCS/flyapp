package PaymentGateaway.Payment;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;
import java.math.BigDecimal;

public class CreditCardPaymentProcessor {

    public static void processCreditCardPayment(BraintreeGateway gateway, double amount, String nonce) {
        BigDecimal decimalAmount = BigDecimal.valueOf(amount); // Mengonversi double menjadi BigDecimal

        TransactionRequest request = new TransactionRequest()
                .amount(decimalAmount) // Menggunakan BigDecimal
                .paymentMethodNonce(nonce)
                .options()
                .submitForSettlement(true)
                .done();

        Result<Transaction> result = gateway.transaction().sale(request);

        // Handle the result of the transaction
        if (result.isSuccess()) {
            Transaction transaction = result.getTarget();
            System.out.println("Payment successful: " + transaction.getId());
        } else {
            System.out.println("Payment failed: " + result.getMessage());
        }
    }
}
