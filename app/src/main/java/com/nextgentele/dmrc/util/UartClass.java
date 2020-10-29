package com.nextgentele.dmrc.util;

import android.util.Log;

import com.newcapec.b601.sys.HwGpio;
import com.newcapec.czpos.auxlib.utils.ByteUtil;
import com.newcapec.czpos.auxlib.utils.MyUtils;
import com.newcapec.jni.SerialPort;
import com.nextgentele.dmrc.response.ResponseReceived;

import static com.nextgentele.dmrc.response.ResponseReceived.pszBuf;

public class UartClass
{

    boolean ifEnd = false;
    final private int UART_PORT = 1;
    final private int BAUD_RATE = 9600;
    private boolean mOpenFlag = false;

    SerialPort serial;
    HwGpio hw;


    public void uart_send(String msg1) {

        String msg0 = msg1;
        byte[] msg = ByteUtil.toGB2312(msg0);
        Log.d("MainActivity", msg0);
        if(serial!=null) {
            int res=serial.sendMsg(msg);
            Log.i("Response", String.valueOf(res));
        }
    }

    public void uart_start() {

        int ret = serial.open(1, BAUD_RATE, 1);
        if( ret != 0){
            mOpenFlag = false;
            Log.i("uart_start: ","------open uart port fail------");
        }else {
            mOpenFlag = true;
            Log.i("uart_start: ","------open uart port succ------");
        }

        Runnable runnable = new Runnable() {
            @Override
            public void run(){
                int count = 0;
                ifEnd = false;
                while (!ifEnd) {
                    try{
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    pszBuf = serial.recvMsg();
                    if( pszBuf == null ) {
                        continue;
                    }
                    count ++;
                    try
                    {
                        String recvStr = MyUtils.bytes2HexStr1(pszBuf);
                        if( recvStr.length() > 0 ){
                            Log.i( "recv"+count," :"+recvStr );
                        } else {
                        }
                    } catch(NullPointerException e)	{
                        e.printStackTrace();
                    }
                }
            }
        };
        new Thread(runnable).start();
    }

    public void uart_open() {
        Log.i("uart start","Starting");
        hw.HW_UartMode_Set(HwGpio.UART_MODE_RS232);
        if(serial==null) {
            serial = new SerialPort();
            uart_start();
        }
    }

   public void init() {
        hw = new HwGpio();
    }
}
