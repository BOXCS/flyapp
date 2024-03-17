package PaymentGateaway.Payment;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Environment;

public class PaymentConfig {

    private static final String MERCHANT_ID = "qf572m2t9zccwd9g";
    private static final String PUBLIC_KEY = "jdrkjf3bb2byqqmb";
    private static final String PRIVATE_KEY = "e63da81a9a89075bc7208ca75e099c1f";

    public static BraintreeGateway createBraintreeGateway() {
        return new BraintreeGateway(
                Environment.SANDBOX,
                MERCHANT_ID,
                PUBLIC_KEY,
                PRIVATE_KEY
        );
    }
}
