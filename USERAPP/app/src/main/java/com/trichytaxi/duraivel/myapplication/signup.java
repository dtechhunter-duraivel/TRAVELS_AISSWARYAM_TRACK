package com.trichytaxi.duraivel.myapplication;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class signup extends Fragment {
    private RequestQueue SQueue;
    private  Spinner spinner;
    AlertDialog alert;
    Button signu;
    ProgressDialog pd;
    String gender,fn;
    EditText fname,lname,email,phn,add1,add2,add3,pass,cpass,dob;
   Context c;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView =inflater.inflate(R.layout.activity_signup,container,false);
      c=getActivity();
        SQueue = Volley.newRequestQueue(c);
        signu=(Button)rootView.findViewById(R.id.but);
        spinner=(Spinner)rootView. findViewById(R.id.gend);
        fname=(EditText)rootView.findViewById(R.id.fn);
        email=(EditText)rootView.findViewById(R.id.email);
        phn=(EditText)rootView.findViewById(R.id.Phoneno);
        phn.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        phn.setFilters(new InputFilter[] {new InputFilter.LengthFilter(10)});

        add1=(EditText)rootView.findViewById(R.id.street);
        add2=(EditText)rootView.findViewById(R.id.doorno);
        add3=(EditText)rootView.findViewById(R.id.city);
        pass=(EditText)rootView.findViewById(R.id.pass);
        dob=(EditText)rootView.findViewById(R.id.DOB);
        cpass=(EditText)rootView.findViewById(R.id.confirmpass);
        String[] arraySpinner = new String[] {"Male","Female" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(c,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        pd = new ProgressDialog(c);
        pd.setTitle("Requesting");
        pd.setMessage("Fetching Data");
        pd.setCanceledOnTouchOutside(false);
        pd.setCancelable(false);
        signu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c,email.getText().toString(),Toast.LENGTH_SHORT).show();
                if(fname.getText().toString().trim().equals(""))
                { fname.setError("Name Cannot be Empty");}
                else if(email.getText().toString().trim().equals(""))
                {
                    email.setError("Email Cannot be Empty");

                }
                else if(add1.getText().toString().trim().equals(""))
                {
                    add1.setError("Street Cannot be Empty");
                }
                else if(add3.getText().toString().trim().equals(""))
                {
                    add3.setError("City Cannot be Empty");
                }
                else if(phn.getText().toString().trim().equals(""))
                {
                    phn.setError("Phone No Cannot be Empty");
                }
                else if(pass.getText().toString().trim().equals(""))
                {
                    pass.setError("Password Cannot be Empty");
                }
                else if(dob.getText().toString().equals(""))
                {
                    dob.setError("Date of Birth Cannot be Empty");
                }
                else
                {
                    pd.show();
                    signup(fname.getText().toString(),email.getText().toString(),phn.getText().toString().trim(),add2.getText().toString()+","+add1.getText().toString()+","+add3.getText().toString(),pass.getText().toString().trim(),dob.getText().toString(),spinner.getSelectedItem().toString());
                }
                }


        });

        return rootView;
    }
    void signup(final String fname,  final String mail, final String phonenum, final String address, final String password, final String dob, final String gender)
    {
        StringRequest request = new StringRequest(Request.Method.POST, "http://*****************/Signup.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                AlertDialog.Builder builder = new AlertDialog.Builder(c);
                builder.setMessage("You Have Registerd Successfully").setTitle("Info")
                        .setCancelable(false)
                        .setPositiveButton("GO TO LOGIN", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //do things
                                alert.dismiss();
                                Intent i = new Intent(getActivity(), TabbedView.class);
                                Bundle extras = new Bundle();
                                extras.putString("mobil", phonenum);
                                i.putExtras(extras);
                                startActivity(i);
                            }
                        });
                alert = builder.create();
                alert.show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message = null;
                String body=null;
                pd.dismiss();
                if (error instanceof NetworkError)
                {
                    message = "Cannot connect to Internet...Please check your connection!";
                }
                else if (error instanceof ServerError) {
                    try {
                        body = new String(error.networkResponse.data, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    message=body;
                }
                else if (error instanceof AuthFailureError)
                {
                    message = "Authentication Failure";
                }
                else if (error instanceof ParseError)
                {
                    message = "Parsing error! Please try again after some time!!";
                }
                else if (error instanceof NoConnectionError)
                {
                    message = "Cannot connect to Internet...Please check your connection!";
                }
                else if (error instanceof TimeoutError)
                {
                    message = "Connection TimeOut! Please check your internet connection.";
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(c);
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
                map.put("CusName", fname);
                map.put("EmailID", mail);
                map.put("Phoneno", phonenum);
                map.put("Address", address);
                map.put("Passw", password);
                map.put("DOB", dob);
                map.put("gend", gender);
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
