package PaymentGateaway.Payment;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;

public class PaymentProcessor {
    // Definisikan variabel untuk menyimpan kunci API

    private static String apiKey = "sk_test_51OxyVBRpzjzvlUWM86Z9fCLdunutytqzsdL5pOH9vvN6eQR4Mp1RakHhEv96vscrdPQxtbkXM9AmhGOaMVcMUnGt00SjCYQ3ps";

    // Metode untuk mendapatkan kunci API
    public static String getApiKey() {
        return apiKey;
    }

    public static void main(String[] args) {
        try {
            // Setel kunci API Stripe menggunakan nilai dari getApiKey()
            Stripe.apiKey = getApiKey();

            ChargeCreateParams params
                    = ChargeCreateParams.builder()
                            .setAmount(1000L) // jumlah dalam satuan sen (misalnya, $10.00 adalah 1000 sen)
                            .setCurrency("usd")
                            .setSource("tok_mastercard") // token kartu pembayaran dari Stripe.js atau Elements
                            .build();

            Charge charge = Charge.create(params);

            System.out.println("Pembayaran berhasil: " + charge);
        } catch (StripeException e) {
            e.printStackTrace();
        }
    }
}
