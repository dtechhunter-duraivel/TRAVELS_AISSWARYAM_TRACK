package com.trichytaxi.duraivel.myapplication;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
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
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Billing extends AppCompatActivity implements OnMapReadyCallback, LocationListener {
GoogleMap map;
    String distanc;
    double mincost;
    double km;
    double costkm;
    String cost;
    AlertDialog alert;
    String pos;
    String picklocation;
    String droplocation;
    final Context context=this;
    String categ;
    RequestQueue SQueue;
    String ctype;
    Button dialogButton;
    SharedPreferences pref;
    String a;
String un;
String ps;
    String at;
    ProgressDialog pd;
    int approxkm;
TextView tv3,tv4,con;
    private int mYear, mMonth, mDay;
String f;
Button b1,b2,b3;
    LocationManager locationManager;
    String[] carid;
    String[] catname;
    String[] seat;
    String[] ac;
    String[] nonac;
    String[] minchrge;
    String[] minkm;
    String[] nonacrg;
    String[] accrg;
    String fdis;
    String fne;
    String unum;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);
        carid=new String[50];
        catname=new String[50];
        seat=new String[50];
        ac =new String[50];
        nonac=new String[50];
        minchrge=new String[50];
        minkm=new String[50];
        nonacrg=new String[50];
        accrg=new String[50];
        pd = new ProgressDialog(Billing.this);
        pd.setTitle("Processing");
        pd.setMessage("Booking Your Car..Please Wait");
        SQueue = Volley.newRequestQueue(this);


        pref = getApplicationContext().getSharedPreferences("sma", Context.MODE_MULTI_PROCESS); // 0 - for private mode
        //SharedPreferences.Editor editor = pref.edit();

        if(pref.contains("username") && pref.contains("password"))
        {
            un = pref.getString("username", null).toString();
            ps = pref.getString("password", null).toString();
           // Toast.makeText(getApplicationContext(),un+ps,Toast.LENGTH_SHORT).show();

            }


        if(pref.contains("fne") && pref.contains("unum"))
        {
            fne = pref.getString("fne", null).toString();
            unum = pref.getString("unum", null).toString();
            // Toast.makeText(getApplicationContext(),un+ps,Toast.LENGTH_SHORT).show();

        }
        if(pref.contains("auth"))
        {
            f= pref.getString("auth", null).toString();
        // Toast.makeText(getApplicationContext(),f,Toast.LENGTH_SHORT).show();
            // a="1";
        }

        AlertDialog.Builder builder =new AlertDialog.Builder(Billing.this);
        View mView =getLayoutInflater().inflate(R.layout.layout,null);
        final Button conboo =(Button)mView.findViewById(R.id.conb);
        final EditText datepick=(EditText)mView.findViewById(R.id.pd);
        final EditText timepicker=(EditText)mView.findViewById(R.id.pt);
        datepick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(Billing.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                datepick.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        timepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Billing.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timepicker.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Pickup Time");
                mTimePicker.show();
            }
        });
        builder.setView(mView);
        final AlertDialog d=builder.create();
        AlertDialog.Builder buildern = new AlertDialog.Builder(Billing.this);
        builder.setMessage("Please Select Your Pickup Date & Time").setTitle("Info")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                        alert.dismiss();
                    }
                });
        alert = buildern.create();
        conboo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date=datepick.getText().toString();
                Toast.makeText(getApplicationContext(),date,Toast.LENGTH_SHORT).show();
                String time=timepicker.getText().toString();
                if(date.equals("")||time.equals(""))
                {

                    alert.show();
                }
                else {
                    //  Toast.makeText(getApplicationContext(), "Hello" + date, Toast.LENGTH_SHORT).show();
                    pd.show();
                    getUserID(un, ps,date+" "+time);
                }
            }
        });

        //d.getWindow().getAttributes().windowAnimations = R.style.MyAnim;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        View view = getLayoutInflater().inflate(R.layout.billdetails, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);
        TextView Title = (TextView) view.findViewById(R.id.actionbar_title);
        Title.setText("Journey Details");
        getSupportActionBar().setCustomView(view,params);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tv3=(TextView)findViewById(R.id.t8);
        final Intent intent = getIntent();
        b1=(Button) findViewById(R.id.booknow);
        b2=(Button)findViewById(R.id.callnow);
        Bundle extras = intent.getExtras();
        if (extras != null) {
             distanc = extras.getString("distance");
pos=extras.getString("pos");
categ=extras.getString("cate");
String seat = null;
  if(pos.equals("0"))
            {
                ctype="Indica";
                categ="A/C & Non-A/C";
                seat="4+1";
            }
            else if(pos.equals("1")) {
      ctype = "Maruthi";
      categ="Non - A/C";
      seat="7+1";
  }         
  else if(pos.equals("2"))
  {
      ctype = "Tourister" ;
      categ="Non - A/C";
      seat="12+1";
  }         
  else if(pos.equals("3"))
  {
      ctype = "Hi-Tech Van";
      categ="A/C & Non - A/C";
      seat="12+1";
  }       
  else if(pos.equals("4"))
  {
      ctype = "Innovo";
      categ="A/C";
      seat="7+1";
  }
  else if(pos.equals("5"))
  {
      ctype = "Tavera";
      categ="A/C & Non - A/C";
      seat="7+1";
  }
  else if(pos.equals("6"))
  {
      ctype = "Etios";
      categ="A/C";
      seat="4+1";
  }
  else if (pos.equals("7"))
  {
      ctype = "Indigo" ;
      categ="A/C & Non - A/C";
      seat="4+1";
  }
  else if(pos.equals("8"))
  {
      ctype = "Tempo";
      categ="A/C & Non - A/C";
      seat="14+1";
  }
  else if(pos.equals("9"))
  {
      ctype = "Xylo";
      categ="A/C & Non - A/C";
      seat="7+1";
  }
  else if(pos.equals("10"))
  {
      ctype = "Fiesta" ;
      categ="A/C";
      seat="4+1";
  }
  else if(pos.equals("11"))
  {
      ctype = "Dezire";
      categ="A/C & Non - A/C";
      seat="4+1";
  }

            picklocation=extras.getString("picklocation");
           droplocation=extras.getString("droplocation");
           String dm=distanc;
           TextView sp=(TextView)findViewById(R.id.spoint);
           TextView ep=(TextView)findViewById(R.id.epoint);
           TextView ty=(TextView)findViewById(R.id.category);
            TextView car=(TextView)findViewById(R.id.carname);
            TextView seating=(TextView)findViewById(R.id.seat);

            sp.setText(picklocation);
           ep.setText(droplocation);
           ty.setText(categ);
           car.setText(ctype);
           seating.setText(seat);
           String digits = dm.replaceAll("km", "");
           double fdist=Double.parseDouble(digits);
           final double distr=fdist;
          approxkm=(int)fdist;
            fdis=String.valueOf(approxkm);

            // Toast.makeText(getApplicationContext(),String.valueOf(distr),Toast.LENGTH_LONG).show();
           if(pos.equals("0"))
            {
                mincost=100;
                km=4;
                    if (categ.equals("Non - A/C")) {
                        costkm = 6;
                        if (distr <= km) {
                            costkm = mincost;
                        } else if (distr > km) {
                            costkm = (distr - km) * costkm + mincost;
                        }
                    } else {
                        costkm = 7;
                        if (distr <= km)
                            costkm = mincost;
                        else
                            costkm = (distr - km) * costkm + mincost;
                    }


            }


           else if(pos.equals("1"))
            {
                mincost=100;
                km=4;

                    if (categ.equals("Non - A/C")) {
                        costkm = 6;
                        if (distr <= km) {
                            costkm = mincost;
                        } else if (distr > km) {
                            costkm = (distr - km) * costkm + mincost;
                        }
                    } else {
                        costkm = 6;
                        if (distr <= km)
                            costkm = mincost;
                        else
                            costkm = (distr - km) * costkm + mincost;
                    }


            }

            else if(pos.equals("2"))
            {

                mincost=300;
                km=4;

                    if (categ.equals("Non - A/C")) {
                        costkm = 9;
                        if (distr <= km)
                            costkm = mincost;
                        else
                            costkm = (distr - km) * costkm + mincost;
                    } else {
                        costkm = 9;
                        if (distr <= km)
                            costkm = mincost;
                        else
                            costkm = (distr - km) * costkm + mincost;
                    }


            }
           else if(pos.equals("3"))
           {

               mincost=300;
               km=4;

               if (categ.equals("Non - A/C")) {
                   costkm = 12;
                   if (distr <= km)
                       costkm = mincost;
                   else
                       costkm = (distr - km) * costkm + mincost;
               } else {
                   costkm = 15;
                   if (distr <= km)
                       costkm = mincost;
                   else
                       costkm = (distr - km) * costkm + mincost;
               }


           }
           else if(pos.equals("4"))
           {

               mincost=200;
               km=4;

               if (categ.equals("Non - A/C")) {
                   costkm = 9.5;
                   if (distr <= km)
                       costkm = mincost;
                   else
                       costkm = (distr - km) * costkm + mincost;
               } else {
                   costkm = 9.5;
                   if (distr <= km)
                       costkm = mincost;
                   else
                       costkm = (distr - km) * costkm + mincost;
               }


           }



           else if(pos.equals("5"))
           {

               mincost=200;
               km=4;

               if (categ.equals("Non - A/C")) {
                   costkm = 7.5;
                   if (distr <= km)
                       costkm = mincost;
                   else
                       costkm = (distr - km) * costkm + mincost;
               } else {
                   costkm = 8.5;
                   if (distr <= km)
                       costkm = mincost;
                   else
                       costkm = (distr - km) * costkm + mincost;
               }


           }


           else if(pos.equals("6"))
           {

               mincost=120;
               km=4;

               if (categ.equals("Non - A/C")) {
                   costkm = 8.5;
                   if (distr <= km)
                       costkm = mincost;
                   else
                       costkm = (distr - km) * costkm + mincost;
               } else {
                   costkm = 8.5;
                   if (distr <= km)
                       costkm = mincost;
                   else
                       costkm = (distr - km) * costkm + mincost;
               }


           }



           else if(pos.equals("7"))
           {

               mincost=120;
               km=4;

               if (categ.equals("Non - A/C")) {
                   costkm = 7.5;
                   if (distr <= km)
                       costkm = mincost;
                   else
                       costkm = (distr - km) * costkm + mincost;
               } else {
                   costkm = 8.5;
                   if (distr <= km)
                       costkm = mincost;
                   else
                       costkm = (distr - km) * costkm + mincost;
               }


           }


           else if(pos.equals("8"))
           {

               mincost=200;
               km=4;

               if (categ.equals("Non - A/C")) {
                   costkm = 9;
                   if (distr <= km)
                       costkm = mincost;
                   else
                       costkm = (distr - km) * costkm + mincost;
               } else {
                   costkm = 10;
                   if (distr <= km)
                       costkm = mincost;
                   else
                       costkm = (distr - km) * costkm + mincost;
               }


           }

           else if(pos.equals("9"))
           {

               mincost=200;
               km=4;

               if (categ.equals("Non - A/C")) {
                   costkm = 7.5;
                   if (distr <= km)
                       costkm = mincost;
                   else
                       costkm = (distr - km) * costkm + mincost;
               } else {
                   costkm = 8.5;
                   if (distr <= km)
                       costkm = mincost;
                   else
                       costkm = (distr - km) * costkm + mincost;
               }


           }

           else if(pos.equals("10"))
           {

               mincost=120;
               km=4;

               if (categ.equals("Non - A/C")) {
                   costkm = 0;
                   if (distr <= km)
                       costkm = mincost;
                   else
                       costkm = (distr - km) * costkm + mincost;
               } else {
                   costkm = 8.5;
                   if (distr <= km)
                       costkm = mincost;
                   else
                       costkm = (distr - km) * costkm + mincost;
               }


           }
           else if(pos.equals("11"))
           {

               mincost=120;
               km=4;

               if (categ.equals("Non - A/C")) {
                   costkm = 7.5;
                   if (distr <= km)
                       costkm = mincost;
                   else
                       costkm = (distr - km) * costkm + mincost;
               } else {
                   costkm = 8.5;
                   if (distr <= km)
                       costkm = mincost;
                   else
                       costkm = (distr - km) * costkm + mincost;
               }


           }

//The key argument here must match that used in the other activity
        }

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           // Toast.makeText(getApplicationContext(),"Opening Dialpad",Toast.LENGTH_SHORT).show();
                Intent dial = new Intent(Intent.ACTION_DIAL);
                // Send phone number to intent as data
                dial.setData(Uri.parse("tel:" + "7373522300"));
                // Start the dialer app activity with number
                startActivity(dial);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

d.show();
// Toast.makeText(getApplicationContext(),"Your Car is Booked",Toast.LENGTH_SHORT).show();
            }
        });



        //int dis = Integer.parseInt(distanc);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
         //   Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1,1, this);

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
map =googleMap;
    }



    void getUserID(final String uname, final String password,final String bookdate)
    {
        //  Toast.makeText(getApplicationContext(),tok,Toast.LENGTH_SHORT).show();
        StringRequest request = new StringRequest(Request.Method.POST, "http://*****************/userinfo.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                 // Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
String sp[]=response.split("/");
           booking(sp[0],uname,sp[1],bookdate);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                String message = null;
                String body = null;
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof ServerError) {
                    message = "User details does not meets minimum the requirements of Megala Travels";
                } else if (error instanceof AuthFailureError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                } else if (error instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                }
             Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
            }
        })
        {



            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {

                Map<String,String> map = new HashMap<String, String>();
                map.put("User_Name", uname);
                map.put("Password", password);
                return map;
            }

        };
        SQueue.add(request);
        request.setRetryPolicy(new DefaultRetryPolicy(
                2000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }



    void booking(final String uid, final String phoneno, final String unm,final String bookdate)
    {
      //  Toast.makeText(getApplicationContext(),tok,Toast.LENGTH_SHORT).show();
        StringRequest request = new StringRequest(Request.Method.POST, "http://*****************/insert.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
             pd.dismiss();
            //  Toast.makeText(getApplicationContext(),response+"Your Car ha",Toast.LENGTH_LONG).show();

         //   gp(unum,fne);

                AlertDialog.Builder builder = new AlertDialog.Builder(Billing.this);
                builder.setMessage("THANK YOU FOR BOOKING AISSWARYAM TRACK . SOON OUR DRIVER WILL CONTACT YOU!").setTitle("Info")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //do things
                                alert.dismiss();
                                Intent s =new Intent(Billing.this,MakeJourney.class);
                                startActivity(s);
                                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                            }
                        });
                alert = builder.create();
                alert.show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                String message = null;
                String body = null;
          if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof ServerError) {
                    message = "User details does not meets minimum the requirements of Megala Travels";
                } else if (error instanceof AuthFailureError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                } else if (error instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(Billing.this);
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
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                Map<String,String> map = new HashMap<String, String>();
                map.put("uid", uid);
                map.put("cusname", unm);
                map.put("from", picklocation);
                map.put("to", droplocation);
                map.put("bdate", dateFormat.format(date));
                map.put("pdate", bookdate);
                map.put("phone",phoneno);
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
