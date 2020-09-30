package com.nextgentele.dmrc.lib;

class Utilities {

    static String ba2hexstring(byte[] ba) {
        return ba2hexstring(ba, true, 0, ba.length);
    }

    static String ba2hexstring(byte[] ba, boolean separate, int from,
                               int length) {
        String ret = "";
        if (ba != null) {
            for (int i = from; i < from + length; ++i) {
                String hex = "00" + Integer.toHexString(ba[i]);
                ret = ret + hex.substring(hex.length() - 2).toUpperCase();
                if (separate && i < from + length - 1) {
                }
            }
        }

        return ret;
    }

    static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
                    .digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    static String reverseStrBytes(String str) {

        byte[] baVal = Utilities.hexStringToByteArray(str);

        for (int i = 0; i < baVal.length / 2; i++) {
            byte temp = baVal[i];
            baVal[i] = baVal[baVal.length - 1 - i];
            baVal[baVal.length - 1 - i] = temp;
        }

        return ba2hexstring(baVal);
    }

    private static int fromHex(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'A' && c <= 'F') {
            return c - 'A' + 10;
        }
        if (c >= 'a' && c <= 'f') {
            return c - 'a' + 10;
        }
        throw new IllegalArgumentException();
    }

    private static char toHex(int nybble) {
        if (nybble < 0 || nybble > 15) {
            throw new IllegalArgumentException();
        }
        return "0123456789ABCDEF".charAt(nybble);
    }

    public static String invertBits(String data){
        byte[] bData = hexStringToByteArray(data);
        byte[] output = new byte[data.length()/2];
        for(int i = 0; i < data.length()/2; i++){
            output[i] = (byte)(~bData[i] & 0xFF);
        }
        return ba2hexstring(output);
    }
}
