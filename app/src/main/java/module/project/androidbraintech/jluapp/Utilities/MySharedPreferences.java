package module.project.androidbraintech.jluapp.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import module.project.androidbraintech.jluapp.containers.ContentFaculty;
import module.project.androidbraintech.jluapp.containers.ContentRegisteredStudent;

/**
 * Created by Tushar-PC on 19-08-2016.
 */
public class MySharedPreferences {


    public static void SaveLoginGuest(Context context,String name,String emailid,String city){

        SharedPreferences sp=context.getSharedPreferences("JLU_LOGIN_GUEST",Context.MODE_APPEND);
        SharedPreferences.Editor editor=sp.edit();
        editor.clear();
        editor.putString("name",name);
        editor.putString("emailid",emailid);
        editor.putString("city",city);
        editor.apply();

    }

    public static void SaveProspective(Context context,String formno){

        SharedPreferences sp=context.getSharedPreferences("JLU_REG_PROS",Context.MODE_APPEND);
        SharedPreferences.Editor editor=sp.edit();
        editor.clear();
        editor.putString("formno",formno);
        editor.apply();

    }

    public static String GetFormNo(Context context)
    {
        SharedPreferences sp=context.getSharedPreferences("JLU_REG_PROS",Context.MODE_APPEND);
        return sp.getString("formno",null);

    }
    public static void SaveCurrentStudent(Context context,ContentRegisteredStudent info){

        SharedPreferences sp=context.getSharedPreferences("JLU_REG_STUD",Context.MODE_APPEND);
        SharedPreferences.Editor editor=sp.edit();
        editor.clear();

        Gson gson=new Gson();
        String sinfo=gson.toJson(info);

        editor.putString("info",sinfo);
        
        editor.apply();

    }
    
    public static ContentRegisteredStudent GetStudentInfo(Context context){
        Gson gson=new Gson();
        SharedPreferences sp=context.getSharedPreferences("JLU_REG_STUD",Context.MODE_APPEND);

        Type type=new TypeToken<ContentRegisteredStudent>(){}.getType();

        return gson.fromJson(sp.getString("info",null),type);


        
    }

    public static void SaveFaculty(Context context,ContentFaculty info){

        SharedPreferences sp=context.getSharedPreferences("JLU_REG_FAC",Context.MODE_APPEND);
        SharedPreferences.Editor editor=sp.edit();
        editor.clear();
        Gson gson=new Gson();
        String sinfo=gson.toJson(info);
        editor.putString("info",sinfo);
        editor.apply();
    }

    public static ContentFaculty getFacultyInfo(Context context){
        Gson gson=new Gson();
        SharedPreferences sp=context.getSharedPreferences("JLU_REG_FAC",Context.MODE_APPEND);

        Type type=new TypeToken<ContentFaculty>(){}.getType();

        return gson.fromJson(sp.getString("info",null),type);


    }

    public static void saveAdapterType(Context context,int t){

        SharedPreferences sp=context.getSharedPreferences("JLU_ADAPTER_TYPE",Context.MODE_APPEND);
        SharedPreferences.Editor editor=sp.edit();
        editor.clear();
        editor.putInt("type",t);
        editor.apply();

    }

    public static int getAdapterType(Context context){

        SharedPreferences sp=context.getSharedPreferences("JLU_ADAPTER_TYPE",Context.MODE_APPEND);
        return sp.getInt("type",0);

    }




}
