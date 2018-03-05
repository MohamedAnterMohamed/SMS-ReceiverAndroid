package com.smsreader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;


/**
 * Created by mohamed_3nter on 3/5/2018.
 */

public class SmSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent)   {
        Bundle data  = intent.getExtras();
        Object[] pdus = (Object[]) data.get("pdus");
        for(int i=0;i<pdus.length;i++){
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);
            String sender = smsMessage.getDisplayOriginatingAddress();
            String messageBody = smsMessage.getMessageBody();
            if (sender.toLowerCase().equals("002001112688365")) {
                Intent smsIntent = new Intent("otp");
                smsIntent.putExtra("message", messageBody);
                smsIntent.putExtra("sender", sender);
                LocalBroadcastManager.getInstance(context).sendBroadcast(smsIntent);
            }
            Log.d("Test SMS",sender);
            Log.d("Test SMS",messageBody);
        }
    }
}
