package com.nextgentele.dmrc.util;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.nextgentele.dmrc.activity.MainActivity;
import com.nextgentele.dmrc.activity.SecondActivity;
import com.nextgentele.dmrc.activity.ThirdActivity;
import com.nextgentele.dmrc.db.MyDb;
import com.nextgentele.dmrc.db.Users;
import com.nextgentele.dmrc.lib.CapecMifareClassicLib;
import com.nextgentele.dmrc.lib.CardLibInterface;
import com.nextgentele.dmrc.lib.ResultInterface;

public class CardLibInterfaceClass {

    CardLibInterface cardLibObj;

    String userName;
    long dateTimeIn;
    long dateTimeOut;
    String latIn = "37.42166666";
    String LongIn = "122.04578899";
    String latOut = "37.42166666";
    String LongIOut = "122.04578899";
    float amount;

    Users users;
    MyDb myDb;

    Context context;
    Intent i;

    public CardLibInterfaceClass(Context context) {
        this.context = context;
        resultShow();
        myDb=new MyDb(context);
    }

    public void findCard(){
        cardLibObj.findCard();
    }

    public void creditCard(int i){
        cardLibObj.creditCardBal(i);
    }
    public void debitCard(int i){
        cardLibObj.debitCardBal(i);
    }
    public void getCardBal(){
        cardLibObj.getCardBal();
    }


    private void resultShow(){

        cardLibObj = CapecMifareClassicLib.getInstance(context, new ResultInterface() {

            @Override
            public void onResult(CMD_LIST currCmd, Object result) {
                switch (currCmd){
                    case FIND_CARD:
                        System.out.println("UID: " + (String) result);
                        //cardLibObj.creditCardBal(100);
                        userName=(String) result;
                        Log.i("username",userName);
                        Users value = myDb.getUser(userName);

                        if(value.getUserBal()==0 || value.getUserName()==null || value.getUserBal()<10){
                            cardLibObj.getCardBal();
                        }else if (value.getUserBal()>=10)
                        {
                            i=new Intent(context, ThirdActivity.class);
                            i.putExtra("user",value);
                            cardLibObj.debitCardBal(25);
                        }
                        break;
                    case CREDIT:
                        System.out.println("Current Card Value after Credit: " + (int)result);
                        break;
                    case DEBIT:
                        System.out.println("Current Card Value after Debit: " + (int)result);
                        context.startActivity(i);
                        break;
                    case GET_BAL:
                        System.out.println("Current Card Value: " + (int)result);
                        amount= (int) result;
                        java.util.Date date=new java.util.Date();
                        dateTimeIn = date.getTime();
                        if (amount!=-1){
                            users=new Users(userName,amount,dateTimeIn,0,latIn,LongIn,null,null,0);
                            Intent i=new Intent(context, SecondActivity.class);
                            i.putExtra("user",users);
                            context.startActivity(i);}
                        //cardLibObj.creditCardBal(10000);
                        //finish();
                        break;
                }
            }
        });
    }




}
