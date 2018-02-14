import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.paypal.merchant.sdk.AuthenticationListener;
import com.paypal.merchant.sdk.MerchantManager;
import com.paypal.merchant.sdk.PayPalHereSDK;
import com.paypal.merchant.sdk.TransactionController;
import com.paypal.merchant.sdk.TransactionManager;
import com.paypal.merchant.sdk.domain.DefaultResponseHandler;
import com.paypal.merchant.sdk.domain.DomainFactory;
import com.paypal.merchant.sdk.domain.Invoice;
import com.paypal.merchant.sdk.domain.ManualEntryCardData;
import com.paypal.merchant.sdk.domain.Merchant;
import com.paypal.merchant.sdk.domain.PPError;
import com.paypal.merchant.sdk.domain.SecureCreditCard;
import com.paypal.merchant.sdk.domain.credentials.OAuthCredentials;
import com.paypal.merchant.sdk.internal.domain.InvoiceImpl;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    Button charge;
    TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);


        charge = (Button)findViewById(R.id.charge);
        status = (TextView)findViewById(R.id.status);

        charge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                charge.setEnabled(false);

                PayPalHereSDK.init(getApplicationContext(), PayPalHereSDK.Sandbox);
                PayPalHereSDK.registerAuthenticationListener(new AuthenticationListener() {
                    @Override
                    public void onInvalidToken() {
                        status.setText("Invalid Token");
                    }

                });

                PayPalHereSDK.setCredentials(new OAuthCredentials("A23AAFyVhtgi0Qfyht_0mtUwtRtGl185LeUjNjx3AYiQnvvZhnXX_PLb_rHC3nVtX_8xmtS96PE6VXakEQ1-neq3D-BiQ475w"), new DefaultResponseHandler<Merchant, PPError<MerchantManager.MerchantErrors>>() {
                    @Override
                    public void onSuccess(Merchant merchant) {

                        status.setText("Start Payment");

                        PayPalHereSDK.getTransactionManager().beginPayment(new BigDecimal(1.00),new TransactionControllerAdaptor());


                        ManualEntryCardData mManualCard =
                                DomainFactory.newManualEntryCardData("4024007163304362", "0320", "678","95678");
                        PayPalHereSDK.getTransactionManager().processPayment(mManualCard, new TransactionControllerAdaptor(), new DefaultResponseHandler<TransactionManager.PaymentResponse, PPError<TransactionManager.PaymentErrors>>() {
                            @Override
                            public void onSuccess(TransactionManager.PaymentResponse paymentResponse) {
                                status.setText("Success Payment");
                                charge.setEnabled(true);
                            }

                            @Override
                            public void onError(PPError<TransactionManager.PaymentErrors> paymentErrorsPPError) {
                                status.setText("Payment Failed");
                                charge.setEnabled(true);
                            }
                        });
                    }

                    @Override
                    public void onError(PPError<MerchantManager.MerchantErrors> merchantErrorsPPError) {
                        status.setText(merchantErrorsPPError.getDetailedMessage());
                        charge.setEnabled(true);
                    }
                });

            }
        });

    }
}
