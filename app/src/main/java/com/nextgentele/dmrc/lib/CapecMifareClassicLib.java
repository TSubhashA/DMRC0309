package com.nextgentele.dmrc.lib;

import android.content.Context;

import com.newcapec.czpos.auxlib.CardData;
import com.newcapec.czpos.auxlib.NcCardLib;
import com.newcapec.czpos.auxlib.utils.MyUtils;

public class CapecMifareClassicLib implements CardLibInterface {

    private static CardLibInterface capecMifareClassicLib = null;
    private int block = 5;
    int stopCardFind = 0;
    private int keyTpye = 0;    //Key A
    private byte[] key = Utilities.hexStringToByteArray("FFFFFFFFFFFF");
    private ResultInterface resultInterface;

    private CapecMifareClassicLib(ResultInterface resultInterface){
        this.resultInterface = resultInterface;
    }

    public static CardLibInterface getInstance(Context mContext, ResultInterface resultInterface) {
        NcCardLib.getInstance().registerReceiver(mContext);
        NcCardLib.getInstance().setLogLevel(2);
        if (capecMifareClassicLib == null) {
            capecMifareClassicLib = new CapecMifareClassicLib(resultInterface);
        }
        return capecMifareClassicLib;
    }

    /**
     * This function will run till it gets DESfire card with our app installed
     * @return UID of the card
     */
    @Override
    public void findCard() {
        new Thread() {
            @Override
            public void run() {
                CardData.card = -1;
                long ret = -1;
                stopCardFind = 0;
                while (stopCardFind == 0 && ret != 0) {
                    ret = NcCardLib.getInstance().findCard();
                    if (ret == 0 && CardData.card == 0) {         //0 - M1 card
                        resultInterface.onResult(ResultInterface.CMD_LIST.FIND_CARD, Utilities.ba2hexstring(CardData.SNR));
                    } else {
                        ret = -1;
                    }
                }
            }
        }.start();
    }

    /**
     * This function stops the attempt to find NFC card
     *
     * @return UID of the card: DESFire
     */
    @Override
    public void terminateCardFinder() {
        stopCardFind = 1;
    }

    @Override
    public void getCardBal() {
        new Thread() {
            @Override
            public void run() {
                long ret = NcCardLib.getInstance().findCard();
                if (ret == 0) {
                    System.out.println("Return Value: " + ret);
                    int sector = block / 4;
                    ret = NcCardLib.getInstance().m1SectorAuth(sector, keyTpye, CardData.SNR, key);
                    if (ret == 0) {
                        System.out.println("Return Value: " + ret);
                        ret = NcCardLib.getInstance().m1BlockRead(block);
                        if (ret == 0) {
                            System.out.println("Return Value: " + ret);
                            resultInterface.onResult(ResultInterface.CMD_LIST.GET_BAL, Integer.parseInt(Utilities.reverseStrBytes(MyUtils.bytes2HexStr1(CardData.sectorValue).substring(0, 8)), 16));
                        } else {
                            resultInterface.onResult(ResultInterface.CMD_LIST.GET_BAL,-1);
                        }
                    } else {
                        resultInterface.onResult(ResultInterface.CMD_LIST.GET_BAL,-1);
                    }
                } else {
                    resultInterface.onResult(ResultInterface.CMD_LIST.GET_BAL,-1);
                }
            }
        }.start();
    }

    void updateCardBal(final int value, final boolean toCredit) {

        new Thread() {
            @Override
            public void run() {
                //Prepare the data
                StringBuilder data = new StringBuilder(Utilities.reverseStrBytes(String.format("%08x", value)));
                data.append(Utilities.invertBits(data.toString()) + data.toString());
                String addressBytes = String.format("%02x", block) + Utilities.invertBits(String.format("%02x", block));
                data.append(addressBytes);
                data.append(addressBytes);

                long ret = NcCardLib.getInstance().findCard();
                if (ret == 0) {
                    System.out.println("Return Value: " + ret);
                    int sector = block / 4;
                    ret = NcCardLib.getInstance().m1SectorAuth(sector, keyTpye, CardData.SNR, key);
                    if (ret == 0) {
                        System.out.println("Return Value: " + ret);
                        ret = NcCardLib.getInstance().m1BlockValue(toCredit ? 1 : 2, block,
                                Utilities.hexStringToByteArray(data.toString()), 0);
                        if (ret == 0) {
                            System.out.println("Return Value: " + ret);
                            ret = NcCardLib.getInstance().m1BlockRead(block);
                            if (ret == 0) {
                                System.out.println("Return Value: " + ret);
                                resultInterface.onResult(toCredit? ResultInterface.CMD_LIST.CREDIT: ResultInterface.CMD_LIST.DEBIT,
                                        Integer.parseInt(Utilities.reverseStrBytes(MyUtils.bytes2HexStr1(CardData.sectorValue).substring(0, 8)), 16));
                            }
                        } else {
                            resultInterface.onResult(toCredit? ResultInterface.CMD_LIST.CREDIT: ResultInterface.CMD_LIST.DEBIT,-1);
                        }
                    } else {
                        resultInterface.onResult(toCredit? ResultInterface.CMD_LIST.CREDIT: ResultInterface.CMD_LIST.DEBIT,-1);
                    }
                } else {
                    resultInterface.onResult(toCredit? ResultInterface.CMD_LIST.CREDIT: ResultInterface.CMD_LIST.DEBIT,-1);
                }
            }
        }.start();
    }

    @Override
    public void creditCardBal(final int value)  {
        updateCardBal(value, true);
    }

    @Override
    public void debitCardBal(int value) {
        updateCardBal(value, false);
    }

    /**
     * This function should be called when all the library work is completed.
     */
    @Override
    public void deregister() {
        NcCardLib.getInstance().unregisterReceiver();
        capecMifareClassicLib = null;
    }

}
