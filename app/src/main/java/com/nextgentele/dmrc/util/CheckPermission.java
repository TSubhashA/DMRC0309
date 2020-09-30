package com.nextgentele.dmrc.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class CheckPermission {

    Context context;


    public CheckPermission(Context context) {
        this.context = context;
    }

    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(context, permission)
                == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions((Activity) context,
                    new String[] { permission },
                    requestCode);
        }
        else {
            Toast.makeText(context,
                    "Permission already granted",
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }





}

