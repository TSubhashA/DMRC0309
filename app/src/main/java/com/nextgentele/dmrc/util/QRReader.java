package com.nextgentele.dmrc.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.newcapec.b601.sys.HwGpio;
import com.newcapec.b601.sys.HwScanner;
import com.nextgentele.dmrc.R;
import com.nextgentele.dmrc.activity.MainActivity;
import com.nextgentele.dmrc.activity.SecondActivity;
import com.nextgentele.dmrc.activity.ThirdActivity;

import java.util.Objects;

public class QRReader extends MainActivity
{
    String TAG ;
    private int TestType = 0;
    boolean ifEnd = false;
    final private int UART_PORT  = 1;
    final private int BAUD_RATE = 115200;
    private boolean mOpenFlag = false;
    HwScanner serial;
    HwGpio hw;

    Context context;

    public QRReader(Context context) {
        this.context = context;
    }

    public void scanner_start() {

        int ret = serial.open(BAUD_RATE, 1);
        // displayInfo("------open scanner port fail------");
        // displayInfo("------open scanner port succ------");
        mOpenFlag = ret == 0;
        Runnable runnable = new Runnable() {
            @Override
            public void run(){
                byte[] pszBuf1 = new byte[1000];
                int count = 0;
                ifEnd = false;
                while (!ifEnd) {
                    try{
                        Thread.sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    pszBuf1 = serial.recvMsg();
                    if( pszBuf1 == null ) {
                        continue;
                    }
                    count ++;
                    try
                    {
                        String recvStr = new String(pszBuf1).trim();

                        if (recvStr!=null){
                        if( recvStr.length() > 0 ){
                            Log.i("received",recvStr);
                            //displayInfo( "recv "+count+": "+recvStr );
                            //Toast.makeText(context,  "recv "+count+": "+recvStr , Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(context, ThirdActivity.class);
                            intent.putExtra("qr1123",recvStr);
                            context.startActivity(intent);
                        }}
                    } catch(NullPointerException e)	{
                        e.printStackTrace();
                    }
                }
            }
        };
        new Thread(runnable).start();
    }

    private void displayInfo(String s) {

        //Toast.makeText(context, ""+s, Toast.LENGTH_SHORT).show();
        if (s.length()>0){
        Intent intent=new Intent(context, ThirdActivity.class);
        intent.putExtra(s,"qr");
        context.startActivity(intent);}
   }

    public void scanner_pwron() {
        //displayInfo("scanner power on");
        hw.HW_QrdCom_PwrEn(1);
        hw.HW_UartMode_Set(HwGpio.UART_MODE_QRCODE);
    }

    public void scanner_stop() {
        ifEnd = true;
        //displayInfo("scanner close");
        serial.close();
        //displayInfo("scanner power off");
        hw.HW_QrdCom_PwrEn(0);
    }

    public void init(){
        hw = new HwGpio();
        serial = new HwScanner();
        scanner_pwron();
        scanner_start();
    }

    public void onDestroy() {
        super.onDestroy();
        ifEnd = true;
        serial.close();
        hw.HW_QrdCom_PwrEn(0);
    }

}
