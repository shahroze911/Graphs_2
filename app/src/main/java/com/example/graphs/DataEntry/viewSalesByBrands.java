package com.example.graphs.DataEntry;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.graphs.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class viewSalesByBrands extends Fragment {
    ListView list;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    salesByBrands.Brands brands;
    Button btn,btn1;
    long maxId=0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.activity_view_sales_by_brands, container, false);
        }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast.makeText(getContext(), "View", Toast.LENGTH_SHORT).show();
        btn=getActivity().findViewById(R.id.button);
        btn1=getActivity().findViewById(R.id.buttonCancel);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().remove(viewSalesByBrands.this).commit();
                getFragmentManager().popBackStack();
                DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.START);

            }
        });
        list=getActivity().findViewById(R.id.listBrandSales);

        brands=new salesByBrands.Brands();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Sales by Brands");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<String> arrayList=new ArrayList<>();
                        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getActivity(),R.layout.brands_info,R.id.brandInfo,arrayList);

                        int s=0;
                        for (DataSnapshot ds:dataSnapshot.getChildren())
                        {
                            s+=1;
                            brands=ds.getValue(salesByBrands.Brands.class);
                            arrayList.add(String.valueOf(s) +". "+brands.getSpinnerValue()+" "+brands.getPrice()+"/- "+brands.getFrom_time()+"-"+brands.getTo_time()+" "+brands.getQuantity()+" "+brands.getFrom_date()+"");
                        }
                        list.setAdapter(arrayAdapter);
                        list.deferNotifyDataSetChanged();

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });
    }
}
