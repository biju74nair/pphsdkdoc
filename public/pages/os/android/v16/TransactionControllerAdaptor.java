//place this file in respective package

import android.app.Activity;
import android.graphics.Bitmap;

import com.paypal.merchant.sdk.TransactionController;
import com.paypal.merchant.sdk.domain.Invoice;
import com.paypal.merchant.sdk.domain.SDKReceiptScreenOptions;
import com.paypal.merchant.sdk.domain.SDKSignatureScreenOptions;

public class TransactionControllerAdaptor implements TransactionController {
    @Override
    public TransactionControlAction onPreAuthorize(Invoice invoice, String s) {
        return null;
    }

    @Override
    public void onPostAuthorize(boolean b) {

    }

    @Override
    public void onPrintRequested(Activity activity, Invoice invoice) {

    }

    @Override
    public Activity getCurrentActivity() {
        return null;
    }

    @Override
    public SDKSignatureScreenOptions getSignatureScreenOpts() {
        return null;
    }

    @Override
    public SDKReceiptScreenOptions getReceiptScreenOptions() {
        return null;
    }

    @Override
    public void onUserPaymentOptionSelected(PaymentOption paymentOption) {

    }

    @Override
    public void onUserRefundOptionSelected(PaymentOption paymentOption) {

    }

    @Override
    public void onTokenExpired(Activity activity, TokenExpirationHandler tokenExpirationHandler) {

    }

    @Override
    public void onContactlessReaderTimeout(Activity activity, ContactlessReaderTimeoutOptionsHandler contactlessReaderTimeoutOptionsHandler) {

    }

    @Override
    public void onReadyToCancelTransaction(CancelTransactionReason cancelTransactionReason) {

    }

    @Override
    public TipPromptOptions shouldPromptForTips() {
        return null;
    }

    @Override
    public Bitmap provideSignatureBitmap() {
        return null;
    }

    @Override
    public void onReaderDisplayUpdated(PresentedReaderDisplay presentedReaderDisplay) {

    }
}
