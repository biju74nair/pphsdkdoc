import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.paypal.paypalretailsdk.Invoice;
import com.paypal.paypalretailsdk.ManuallyEnteredCard;
import com.paypal.paypalretailsdk.Merchant;
import com.paypal.paypalretailsdk.RetailSDK;
import com.paypal.paypalretailsdk.RetailSDKException;
import com.paypal.paypalretailsdk.SdkCredential;
import com.paypal.paypalretailsdk.TransactionContext;
import com.paypal.paypalretailsdk.TransactionManager;
import com.paypal.paypalretailsdk.TransactionRecord;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    Button charge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        updateStatus("Click Charge To Begin");

        charge = (Button)findViewById(R.id.charge);
        charge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               try {
                   charge();
               }catch(Exception e){
                   e.printStackTrace();
               }
            }
        });
    }

    public void charge() throws Exception{
        RetailSDK.initialize(this, new RetailSDK.AppState()
        {
            /**
             * The integrating App should return an activity on which the SDK will launch it's UI elements
             * like card reader finder, payment processing alerts
             */
            @Override
            public Activity getCurrentActivity()
            {
                return MainActivity.this;
            }


            @Override
            public boolean getIsTabletMode()
            {
                return false;
            }
        });


        SdkCredential credential = new SdkCredential("sandbox", "<access_token>");

        RetailSDK.initializeMerchant(credential, new RetailSDK.MerchantInitializedCallback()
        {
            @Override
            public void merchantInitialized(RetailSDKException error, Merchant merchant)
            {

                updateStatus("merchantInitialized");
                final BigDecimal quantity = new BigDecimal(1);
                final BigDecimal unitPrice = new BigDecimal(1);
                final Integer itemId = 1;
                final String detailId = null;

                Invoice invoice = new Invoice(null);
                invoice.addItem("Item", quantity,unitPrice,itemId,detailId);


                RetailSDK.getTransactionManager().createTransaction(invoice, new TransactionManager.TransactionCallback()
                {
                    @Override
                    public void transaction(RetailSDKException error, TransactionContext transactionContext)
                    {

                        transactionContext.setCompletedHandler(new TransactionContext.TransactionCompletedCallback() {
                            public void transactionCompleted(RetailSDKException error, TransactionRecord transactionRecord){
                                if(error == null)
                                    updateStatus("success");
                                else
                                    updateStatus("failed");
                            }
                        });

                        updateStatus("createTransaction");
                        String cardNumber = "4024007163304362"; //This is sample CC took from getcreditcardnumbers.com
                        String expiration = "032020";
                        String cvv = "678";

                        ManuallyEnteredCard card = new ManuallyEnteredCard();
                        card.setCardNumber(cardNumber);
                        card.setCVV(cvv);
                        card.setExpiration(expiration);


                        transactionContext.continueWithCard(card);

                        updateStatus("Processing...");


                    }
                });


            }
        });


    }

    public void updateStatus(final String msg){
        Log.d("MainActivity", msg);
    }

}
