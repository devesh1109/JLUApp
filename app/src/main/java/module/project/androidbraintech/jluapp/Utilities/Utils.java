package module.project.androidbraintech.jluapp.Utilities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

/**
 * Created by Tushar-PC on 14-08-2016.
 */
public class Utils {



    public static int ALPHA_Value=100;

   public static Context pContext;
    public static  ProgressDialog dialog;



    public static Boolean checkIfNetworkIsAvailable(Context context) {

        NetworkDetector cd = new NetworkDetector(context);
        Boolean isInternetPresent = cd.isConnectingToInternet();
        if (isInternetPresent == true) {
            return  true;
        } else {
            Log.d("connection", "no connection found");
            AlertDialog dailog = new AlertDialog.Builder(context).create();
            dailog.setTitle("Error");
            dailog.setMessage("Please turn on your internet connection to proceed");
            dailog.setButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) { }
            });
            dailog.show();
            return false;
        }
    }

    public static void ShowAlertDialog(Context context,String title,String message){
        AlertDialog dailog = new AlertDialog.Builder(context).create();
        dailog.setTitle(title);
        dailog.setMessage(message);
        dailog.setButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { }
        });
        dailog.show();


    }


}
