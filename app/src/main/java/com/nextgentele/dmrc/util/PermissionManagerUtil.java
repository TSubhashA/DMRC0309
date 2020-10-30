package com.nextgentele.dmrc.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.nextgentele.dmrc.constants.Constants;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import static android.Manifest.permission.READ_PHONE_STATE;

public class PermissionManagerUtil {


    public static final int REQUEST_PERMISSION_PHONE_STATE = 1;

    Context context;

    public PermissionManagerUtil(Context context) {
        this.context = context;
    }

    public String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        Constants.ipAddress = Formatter.formatIpAddress(inetAddress.hashCode());
                        Log.i("IP Address", "***** IP=" +  Constants.ipAddress);
                        return  Constants.ipAddress;
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e("IP Address", ex.toString());
        }
        return null;
    }


    @SuppressLint("HardwareIds")
    public String showPhoneStatePermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(
                context, READ_PHONE_STATE);
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(context, READ_PHONE_STATE)) {
            Constants.imei = telephonyManager.getDeviceId();
        }
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,
                    READ_PHONE_STATE)) {
                showExplanation("Permission Needed", "Rationale", READ_PHONE_STATE, REQUEST_PERMISSION_PHONE_STATE);
                Constants.imei = telephonyManager.getDeviceId();
            } else {
                requestPermission(READ_PHONE_STATE, REQUEST_PERMISSION_PHONE_STATE);
                Constants.imei = telephonyManager.getDeviceId();
            }
        } else {
            Constants.imei = telephonyManager.getDeviceId();
            // Toast.makeText(RegistrationActivity.this, "Permission already Granted!", Toast.LENGTH_SHORT).show();
        }
        return Constants.imei;
    }

    private void showExplanation(String title,
                                 String message,
                                 final String permission,
                                 final int permissionRequestCode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        requestPermission(permission, permissionRequestCode);
                    }
                });
        builder.create().show();
    }

    private void requestPermission(String permissionName, int permissionRequestCode) {
        ActivityCompat.requestPermissions((Activity) context,
                new String[]{permissionName}, permissionRequestCode);
    }
}
