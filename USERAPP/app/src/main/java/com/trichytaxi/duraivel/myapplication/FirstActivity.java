package com.trichytaxi.duraivel.myapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity {
       String name;
    String[] carid;
    String[] catname;
    String[] seat;
    String[] aci;
    String[] nonac;
    String[] minchrge;
    String[] minkm;
    String[] nonacrg;
    AlertDialog alert;
    String[] accrg;
       String picloc,droploc,pos,categ,distan;
    String[] ss = { "India ", "Arica", "India ", "Arica", "India ", "Arica",
            "India ", "Arica", "India ", "Arica" };
        String id;
        ArrayAdapter adapter;
        RecyclerView recyclerView;
        ArrayList<ModelProduct> productList;
        String s;
        String ac="0";
    String catg;
    FirebaseDatabase database;
    DatabaseReference myRef;
        TextView t1;
        void adapt(String cid,String name,String seat,String cata,String cost,String minkm,String acharge,String type)
        {
            if (cid.equals("1")) {
                productList.add(new ModelProduct(R.drawable.indica, name, seat, "Tata", "Min. Rs." + cost + "/" + minkm + " KMs", "Additional Charges " + acharge + " Rs/km", type));
                //productList.add(new ModelProduct(R.drawable.indica,"Indica","140 kMph","Tata","Min. Rs.100/4 kms","Additional Charges 15 Rs/km","A/C"));
            } else if (cid.equals("2")) {
                productList.add(new ModelProduct(R.drawable.omni, name, seat, "Maruti Suzuki", "Min. Rs." + cost + "/" + minkm + "KMs", "Additional Charges " + acharge + " Rs/km", type));
                //productList.add(new ModelProduct(R.drawable.omni,"OMNI","136 kMph","Maruti Suzuki","Rs.100/4 kms","Additional Charges 15 Rs/km","A/C"));
            } else if (cid.equals("3")) {
                productList.add(new ModelProduct(R.drawable.tourister, name, seat, "Maruti Suzuki", "Min. Rs." + cost + "/" + minkm + " KMs", "Additional Charges " + acharge + " Rs/km", type));
                //productList.add(new ModelProduct(R.drawable.eco,"Eeco","125 kMph","Maruti Suzuki","Rs.100/3 kms","Additional Charges 17 Rs/km","A/C"));
            } else if (cid.equals("4")) {
                productList.add(new ModelProduct(R.drawable.hitech, name, seat, "Tata", "Min. Rs." + cost + "/" + minkm + " KMs", "Additional Charges " + acharge + " Rs/km", type));
                // productList.add(new ModelProduct(R.drawable.indigo,"INDIGO","150 kMph","Tata","Min. Rs.100/3 kms","Additional Charges 17 Rs/km","A/C"));
            } else if (cid.equals("5")) {
                productList.add(new ModelProduct(R.drawable.innov, name, seat, "Toyota", "Min. Rs." + cost + "/" + minkm + " KMs", "Additional Charges " + acharge + " Rs/km", type));
                //productList.add(new ModelProduct(R.drawable.xcent,"Xcend","140 kMph","Hyundai","Min. Rs.100/3 kms","Additional Charges 17 Rs/km","A/C"));
            } else if (cid.equals("6")) {
                productList.add(new ModelProduct(R.drawable.tavera, name, seat, "Chevrolet", "Min. Rs." + cost + "/" + minkm + " KMs", "Additional Charges " + acharge + " Rs/km", type));
            } else if (cid.equals("7")) {
                productList.add(new ModelProduct(R.drawable.etios, name, seat, "Tata", "Min. Rs." + cost + "/" + minkm + " KMs", "Additional Charges " + acharge + " Rs/km", type));
                //productList.add(new ModelProduct(R.drawable.venture,"VENTURE","120 kMph","Tata","Min. Rs.100/3 kms","Additional Charges 17 Rs/km","A/C"));
            } else if (cid.equals("8")) {
                productList.add(new ModelProduct(R.drawable.indigo, name, seat, "Tata", "Min. Rs." + cost + "/" + minkm + " KMs", "Additional Charges " + acharge + " Rs/km", type));
                // productList.add(new ModelProduct(R.drawable.indigo,"INDIGO","150 kMph","Tata","Min. Rs.100/3 kms","Additional Charges 17 Rs/km","A/C"));
            } else if (cid.equals("9")) {
                productList.add(new ModelProduct(R.drawable.tempo, name, seat, "Suzuki", "Min. Rs." + cost + "/" + minkm + " KMs", "Additional Charges " + acharge + " Rs/km", type));
                //productList.add(new ModelProduct(R.drawable.swift,"SWIFT","195 kMph","Suzuki","Min. Rs.100/3 kms","Additional Charges 17 Rs/km","A/C"));
            } else if (cid.equals("10")) {
                productList.add(new ModelProduct(R.drawable.xylo, name, seat, "Mahindra", "Min. Rs." + cost + "/" + minkm + " KMs", "Additional Charges " + acharge + " Rs/km", type));
                //productList.add(new ModelProduct(R.drawable.xcent,"Xcend","140 kMph","Hyundai","Min. Rs.100/3 kms","Additional Charges 17 Rs/km","A/C"));
            } else if (cid.equals("11")) {
                productList.add(new ModelProduct(R.drawable.fiesta, name, seat, "Ford", "Min. Rs." + cost + "/" + minkm + " KMs", "Additional Charges " + acharge + " Rs/km", type));
                //productList.add(new ModelProduct(R.drawable.lodgy,"Lodgy","195 kMph","Renault","Min. Rs.200/4 kms","Additional Charges 22 Rs/km","A/C"));
            } else if (cid.equals("12")) {
                productList.add(new ModelProduct(R.drawable.dezire, name, seat, "Suzuki", "Min. Rs." + cost + "/" + minkm + " KMs", "Additional Charges " + acharge + " Rs/km", type));
                //productList.add(new ModelProduct(R.drawable.swift,"SWIFT","195 kMph","Suzuki","Min. Rs.100/3 kms","Additional Charges 17 Rs/km","A/C"));
            }


        }
        void setList()
        {
            ProductAdapter adapter =new ProductAdapter(this,productList);
            recyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
                @Override
                public void OnItemClick(int position) {
                    if(position==0||position==3||position==5||position==7||position==8||position==9||position==11)
                        customDialog(position);
                    else if(position==4||position==6||position==10)
                    {
                        Intent i = new Intent(FirstActivity.this,Billing.class);
                        Bundle extras = new Bundle();
                        extras.putString("pos",String.valueOf(position));
                        extras.putString("cate","Non - A/C");
                        extras.putString("distance",distan);
                        extras.putString("picklocation",picloc);
                        extras.putString("droplocation",droploc);
                        i.putExtras(extras);
                        startActivity(i);
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);


                    }
                    else
                    {
                        Intent i = new Intent(FirstActivity.this,Billing.class);
                        Bundle extras = new Bundle();
                        extras.putString("pos",String.valueOf(position));
                        extras.putString("cate","A/C");
                        extras.putString("distance",distan);
                        extras.putString("picklocation",picloc);
                        extras.putString("droplocation",droploc);
                        i.putExtras(extras);
                        startActivity(i);
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);


                    }

                }
                @Override
                public void OnBookClick(int position) {
                    if(position==0||position==3||position==5||position==7||position==8||position==9||position==11)
                        customDialog(position);
                    else if(position==4||position==6||position==10)
                    {
                        Intent i = new Intent(FirstActivity.this,Billing.class);
                        Bundle extras = new Bundle();
                        extras.putString("pos",String.valueOf(position));
                        extras.putString("cate","Non - A/C");
                        extras.putString("distance",distan);
                        extras.putString("picklocation",picloc);
                        extras.putString("droplocation",droploc);
                        i.putExtras(extras);
                        startActivity(i);
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);


                    }
                    else
                    {
                        Intent i = new Intent(FirstActivity.this,Billing.class);
                        Bundle extras = new Bundle();
                        extras.putString("pos",String.valueOf(position));
                        extras.putString("cate","A/C");
                        extras.putString("distance",distan);
                        extras.putString("picklocation",picloc);
                        extras.putString("droplocation",droploc);
                        i.putExtras(extras);
                        startActivity(i);
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);


                    }
                }
            });

        }


        //productList.add(new ModelProduct(R.drawable.tavera,"TAVERA","150 kMph","Chevrolet","Min. Rs.200/4 kms","Additional Charges 22 Rs/km","A/C"));



void adaptoffline()
    {
        recyclerView =findViewById(R.id.rv);
        productList =new ArrayList<>();
        LinearLayoutManager layoutManager =new LinearLayoutManager(this);
        RecyclerView.LayoutManager rvLayoutManager = layoutManager;
        recyclerView.setLayoutManager(rvLayoutManager);

        productList.add(new ModelProduct(R.drawable.indica,"INDICA","4 + 1","Tata","Min. Rs.100/4 kms","Additional Charges 13 Rs/km","A/C & Non-A/C"));
        //productList.add(new ModelProduct(R.drawable.indica,"Indica","140 kMph","Tata","Min. Rs.100/4 kms","Additional Charges 15 Rs/km","A/C"));

        //productList.add(new ModelProduct(R.drawable.omni,"Maruthi","7 + 1","Maruti Suzuki","Min. Rs.100/4 kms","Additional Charges 6.5 Rs/km","Non-A/C"));
        productList.add(new ModelProduct(R.drawable.omni,"OMNI","4 + 1 ","Maruti Suzuki","Rs.100/4 kms","Additional Charges 14 Rs/km","NON - A/C"));

        //productList.add(new ModelProduct(R.drawable.tourister,"Tourister","12 + 1","Maruti Suzuki","Min. Rs.300/4 kms","Additional Charges 9 Rs/km","A/C & Non-A/C"));
       productList.add(new ModelProduct(R.drawable.eco,"EECO","4 + 1","Maruti Suzuki","Rs.130/4 kms","Additional Charges 14 Rs/km","A/C"));

      //  productList.add(new ModelProduct(R.drawable.hitech,"Hi-Tech Van","12 + 1","Tata","Min. Rs.300/4 kms","Additional Charges 15 Rs/km","A/C & Non-A/C"));
        // productList.add(new ModelProduct(R.drawable.indigo,"INDIGO","150 kMph","Tata","Min. Rs.100/3 kms","Additional Charges 17 Rs/km","A/C"));


        productList.add(new ModelProduct(R.drawable.innov,"INNOVA","7 + 1","Toyota","Min. Rs.200/4 kms","Additional Charges 18 Rs/km","A/C "));
        //productList.add(new ModelProduct(R.drawable.xcent,"Xcend","140 kMph","Hyundai","Min. Rs.100/3 kms","Additional Charges 17 Rs/km","A/C"));


        productList.add(new ModelProduct(R.drawable.tavera,"XYLO","7 + 1","Chevrolet","Min. Rs.175/4 kms","Additional Charges 18 Rs/km","A/C & Non-A/C"));


        productList.add(new ModelProduct(R.drawable.etios,"ETIOS","4 + 1","Tata","Min. Rs.120/4 kms","Additional Charges 14 Rs/km","A/C & Non-A/C"));
        //productList.add(new ModelProduct(R.drawable.venture,"VENTURE","120 kMph","Tata","Min. Rs.100/3 kms","Additional Charges 17 Rs/km","A/C"));


     //   productList.add(new ModelProduct(R.drawable.indigo,"Indigo","4 + 1","Tata","Min. Rs.120/4 kms","Additional Charges 7.50 Rs/km","A/C & Non-A/C"));
        // productList.add(new ModelProduct(R.drawable.indigo,"INDIGO","150 kMph","Tata","Min. Rs.100/3 kms","Additional Charges 17 Rs/km","A/C"));

       // productList.add(new ModelProduct(R.drawable.tempo,"TEMPO","14 + 1","Suzuki","Min. Rs.200/4 kms","Additional Charges 8.50 Rs/km","A/C & Non-A/C"));
        //productList.add(new ModelProduct(R.drawable.swift,"SWIFT","195 kMph","Suzuki","Min. Rs.100/3 kms","Additional Charges 17 Rs/km","A/C"));

        productList.add(new ModelProduct(R.drawable.xylo,"XYLO","7 + 1","Mahindra","Min. Rs.200/4 kms","Additional Charges 18 Rs/km","A/C & Non-A/C"));
        //productList.add(new ModelProduct(R.drawable.xcent,"Xcend","140 kMph","Hyundai","Min. Rs.100/3 kms","Additional Charges 17 Rs/km","A/C"));

     //   productList.add(new ModelProduct(R.drawable.fiesta,"Fiesta","4 + 1","Ford","Min. Rs.120/4 kms","Additional Charges 8.50 Rs/km","A/C & Non-A/C"));
        //productList.add(new ModelProduct(R.drawable.lodgy,"Lodgy","195 kMph","Renault","Min. Rs.200/4 kms","Additional Charges 22 Rs/km","A/C"));

       // productList.add(new ModelProduct(R.drawable.dezire,"Dezire","4 + 1","Suzuki","Min. Rs.120/4 kms","Additional Charges 8.50 Rs/km","A/C & Non-A/C"));
        productList.add(new ModelProduct(R.drawable.swift,"SWIFT","4 + 1","Suzuki","Min. Rs.120/4 kms","Additional Charges 14 Rs/km","A/C"));



        //productList.add(new ModelProduct(R.drawable.tavera,"TAVERA","150 kMph","Chevrolet","Min. Rs.200/4 kms","Additional Charges 22 Rs/km","A/C"));





        ProductAdapter adapter =new ProductAdapter(this,productList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                if(position==0||position==3||position==5||position==7||position==8||position==9||position==11)
                    customDialog(position);
                else if(position==4||position==6||position==10)
                {
                    Intent i = new Intent(FirstActivity.this,Billing.class);
                    Bundle extras = new Bundle();
                    extras.putString("pos",String.valueOf(position));
                    extras.putString("cate","Non - A/C");
                    extras.putString("distance",distan);
                    extras.putString("picklocation",picloc);
                    extras.putString("droplocation",droploc);
                    i.putExtras(extras);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);


                }
                else
                {
                    Intent i = new Intent(FirstActivity.this,Billing.class);
                    Bundle extras = new Bundle();
                    extras.putString("pos",String.valueOf(position));
                    extras.putString("cate","A/C");
                    extras.putString("distance",distan);
                    extras.putString("picklocation",picloc);
                    extras.putString("droplocation",droploc);
                    i.putExtras(extras);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);


                }

            }
            @Override
            public void OnBookClick(int position) {
                if(position==0||position==3||position==5||position==7||position==8||position==9||position==11)
                    customDialog(position);
                else if(position==4||position==6||position==10)
                {
                    Intent i = new Intent(FirstActivity.this,Billing.class);
                    Bundle extras = new Bundle();
                    extras.putString("pos",String.valueOf(position));
                    extras.putString("cate","Non - A/C");
                    extras.putString("distance",distan);
                    extras.putString("picklocation",picloc);
                    extras.putString("droplocation",droploc);
                    i.putExtras(extras);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);


                }
                else
                {
                    Intent i = new Intent(FirstActivity.this,Billing.class);
                    Bundle extras = new Bundle();
                    extras.putString("pos",String.valueOf(position));
                    extras.putString("cate","A/C");
                    extras.putString("distance",distan);
                    extras.putString("picklocation",picloc);
                    extras.putString("droplocation",droploc);
                    i.putExtras(extras);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);


                }
            }
        });
    }
    Animation animFadeIn,animcon;
TextView txtFadeIn,con;
RequestQueue SQueue;
ProgressDialog prog;
@Override
protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_first);
    carid=new String[50];
    catname=new String[50];
    seat=new String[50];
    aci =new String[50];
    nonac=new String[50];
    minchrge=new String[50];
    minkm=new String[50];
    nonacrg=new String[50];
    accrg=new String[50];
         SQueue = Volley.newRequestQueue(this);
         prog=new ProgressDialog(this);
         prog.setMessage("Searching For Car");
         prog.setTitle("Please Wait");
        // prog.show();
    recyclerView = findViewById(R.id.rv);
    productList = new ArrayList<>();
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    RecyclerView.LayoutManager rvLayoutManager = layoutManager;
    recyclerView.setLayoutManager(rvLayoutManager);
     //    getTarrif();
       adaptoffline();
         getSupportActionBar().setTitle("Choose Your Car");
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         Intent intent = getIntent();
         Bundle extras = intent.getExtras();
         picloc = extras.getString("picklocation");
         droploc = extras.getString("droplocation");
         pos =extras.getString("pos");
         categ=extras.getString("cate");
         distan=extras.getString("distance");
}

void customDialog(int  position)
{
final int pos2=position;
    final Context context = this;
    AlertDialog.Builder builder =new AlertDialog.Builder(FirstActivity.this);
    View mView =getLayoutInflater().inflate(R.layout.alert,null);
    builder.setTitle("Choose Category");
    final Spinner spinner=(Spinner)mView.findViewById(R.id.sp1);
    ArrayAdapter<String> adapter=new ArrayAdapter<String>(FirstActivity.this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.type));
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(adapter);
    builder.setPositiveButton("CONTINUE", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {

            if(!spinner.getSelectedItem().toString().equalsIgnoreCase("Choose Category"))
            {
             catg= spinner.getSelectedItem().toString();
             // Toast.makeText(getApplicationContext(),spinner.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();



            //    Toast.makeText(getApplicationContext(),String.valueOf(pos2),Toast.LENGTH_SHORT).show();
                Intent i = new Intent(FirstActivity.this,Billing.class);
                Bundle extras = new Bundle();
                extras.putString("pos",String.valueOf(pos2));
                extras.putString("cate",catg);
                extras.putString("distance",distan);
                extras.putString("picklocation",picloc);
                extras.putString("droplocation",droploc);
                i.putExtras(extras);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);



            }
        }
    });
    builder.setNegativeButton("DISMISS", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    });
    builder.setView(mView);
    AlertDialog d=builder.create();
    d.show();



}


@Override
public void onBackPressed()
        {
            Intent i = new Intent(FirstActivity.this,MakeJourney.class);
startActivity(i);
overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }




    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();  return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
