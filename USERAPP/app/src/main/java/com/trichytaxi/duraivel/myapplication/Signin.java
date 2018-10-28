package com.trichytaxi.duraivel.myapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.SquareCap;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Signin extends Fragment {
    Button b1;
EditText unma,passwrd;
String usernmae,passw;
AlertDialog alert;
ProgressDialog pd;
RequestQueue SQueue;
    public static final String MyPREFERENCES = "sma" ;
    SharedPreferences sharedPreferences;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
         final View rootView =inflater.inflate(R.layout.activity_signin,container,false);
final Context c=getActivity();
unma =(EditText)rootView.findViewById(R.id.e1);

SQueue = Volley.newRequestQueue(c);
//st("Bearer 5tOZJYxU6uilDpnlB-ezYKGVKUnhGUAScD0KdrVgN8L-Eg-y1idCkqUoj8rOquxrbMmQOY4TIenXv_sm0-cbhqVjFc1MH7DfTjLMziqYw1_kN-ru-jxYhpZAiSCDTE9e7w95ysKdon3Et3p5L359_hfJ2lbK0ZCmDfxd1oKYo7NDYp-dUusDUbNqAzhCx21gaXEBfS39EDun5pvMvMp7dbRZ9HF6qvhyE9H2k-TeJAa11QwE6QlDGOcoqg_acM7ssMAojc8uIJi9L3tOre96sjyr41vFSwizYJJ-iURMHQ-0wMNgSPMUzZn_f0xwU8EHRbY0MFcLt3OIRNG7A6l1FR_Bs6Np_3kKM8vriVwjnPUJ7_YBIXZod_apROIMEmpOexhFvw78S0Pnh359N_Omsbr1J8NIf0WQmZ-_m1XC4Ycv1LB7BuYhayuA3kFRkC7-PhZHzEz05J0WQ6L0RI_gcpk5px1d-A9GDvwgdq0SP7DuWQJvl2IuEqgszu5svzdtO_bH6wRxHlE-6OffO64Fng");

        passwrd =(EditText)rootView.findViewById(R.id.e2);
        unma.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        unma.setFilters(new InputFilter[] {new InputFilter.LengthFilter(10)});
        pd = new ProgressDialog(c);
        sharedPreferences = c.getSharedPreferences(MyPREFERENCES, Context.MODE_MULTI_PROCESS);
        b1=(Button)rootView.findViewById(R.id.bt);
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    usernmae=unma.getText().toString();
                    passw =passwrd.getText().toString();
                    if(!usernmae.equals("") && !passw.equals(""))
                    {
                        pd.setMessage("Checking Credentials");
                        pd.setCanceledOnTouchOutside(false);
                        pd.setCancelable(false);
                        pd.show();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username",usernmae);
                        editor.putString("password",passw);
                        editor.putString("flag","1");
                        editor.commit();
                        login(usernmae, passw);
                    }
                    else
                    {
                        pd.dismiss();
                        AlertDialog.Builder builder = new AlertDialog.Builder(c);
                        builder.setMessage("Incorrect Username or Password").setTitle("Info")
                                .setCancelable(false)
                                .setPositiveButton("RETRY", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //do things
                                        alert.dismiss();
                                    }
                                });
                        alert = builder.create();
                        alert.show();
                    }
                }
            });
        return rootView;

    }

    public void login(final String username, final String passwrd)
    {
        StringRequest request = new StringRequest(Request.Method.POST, "http://*****************/Login.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                    pd.dismiss();

                    Intent i = new Intent(getActivity(), MakeJourney.class);
                    Bundle extras = new Bundle();
                    // extras.putString("mobil", phonenum);
                    i.putExtras(extras);
                    startActivity(i);
                    getActivity().finishAffinity();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message = null;
String body=null;
                pd.dismiss();
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof ServerError) {
                    try {
                        body = new String(error.networkResponse.data, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    message = body;
                } else if (error instanceof AuthFailureError) {
                    message = "Incorrect Username or Password!";
                } else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                } else if (error instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage(message).setTitle("Info")
                        .setCancelable(false)
                        .setPositiveButton("RETRY", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //do things
                                alert.dismiss();

                            }
                        });
                alert = builder.create();
                alert.show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {

                Map<String,String> map = new HashMap<String, String>();
                map.put("Password", passwrd);
                map.put("Phoneno", username);
                return map;
            }
        };
        SQueue.add(request);
        request.setRetryPolicy(new DefaultRetryPolicy(
                2000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }


}
