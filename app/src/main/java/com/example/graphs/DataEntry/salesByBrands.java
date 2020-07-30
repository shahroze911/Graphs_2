package com.example.graphs.DataEntry;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.Message;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.ui.Label;
import com.example.graphs.R;
import com.example.graphs.Sales.Sales_Category;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class salesByBrands extends Fragment {
    Spinner spinner;
    EditText from,to;
    Button btn_fromdate;
    Button btn_todate;
    Button cancel;
    Button submit;
    EditText priceInput,quantityInput;
    long maxId=0;
    String saveFromDate,saveToDate;
    String[] stringArray;
    TextView getFrom,getTo;
    private int mYear, mMonth, mDay;
    String selectedItemText;
    FirebaseDatabase rootNode;
    DatabaseReference databaseReference;
    String fromTime,toTime;
    androidx.appcompat.app.ActionBarDrawerToggle toggle;
    DrawerLayout drawer;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_sales_by_brands, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //spinner
        Toast.makeText(getContext(), "Brand", Toast.LENGTH_SHORT).show();
        stringArray = getResources().getStringArray(R.array.brand_names);
        spinner=getActivity().findViewById(R.id.spinnerBrands);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, stringArray);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long id) {
                if(spinner!= null && spinner.getSelectedItem() !=null ) {
                    selectedItemText= (String) arg0.getItemAtPosition(position);
                    Toast.makeText(getContext(), selectedItemText, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                Toast.makeText(getContext(), "selectedItemText", Toast.LENGTH_SHORT).show();
            }
        });

        priceInput=getActivity().findViewById(R.id.inputPrice);
        quantityInput=getActivity().findViewById(R.id.inputQuantity);
//date and time
        getFrom=getActivity().findViewById(R.id.fromDate);
        from=getActivity().findViewById(R.id.fromTime);
        from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        from.setText( selectedHour+":"+selectedMinute);
                        fromTime=selectedHour+":"+selectedMinute;

                    }
                }, hour, minute,true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        to=getActivity().findViewById(R.id.toTime);
        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        to.setText( selectedHour+":"+selectedMinute);
                        toTime=selectedHour+":"+selectedMinute;
                    }
                }, hour, minute,true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
        btn_fromdate=getActivity().findViewById(R.id.btn_fromDate_today);
        submit=getActivity().findViewById(R.id.btn_submit_today);
        cancel=getActivity().findViewById(R.id.btn_cancel_today);
        btn_fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                    getFrom.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                saveFromDate=dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                Toast.makeText(getActivity(), saveFromDate, Toast.LENGTH_SHORT).show();
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        //rootNode= FirebaseDatabase.getInstance();
        databaseReference=FirebaseDatabase.getInstance().getReference().child("Sales by Brands");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    maxId=(dataSnapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String spinnerValue=selectedItemText;
                    String from_time=fromTime;
                    String to_time=toTime;
                    String from_date=saveFromDate;
                    String price=priceInput.getText().toString();
                    String quantity=quantityInput.getText().toString();
                    int price1=Integer.parseInt(price);
                    int quantity1=Integer.parseInt(quantity);
                    price1=price1*quantity1;
                    Brands brands=new Brands(spinnerValue,from_date,from_time,to_time,price1,quantity1);
                    databaseReference.child(String.valueOf(maxId+1)).setValue(brands);
                    //     databaseReference.child("Brands").child(spinnerValue).setValue(brands);
                    //databaseReference.child(spinnerValue).setValue(brands);
                    Toast.makeText(getContext(), "Data Inserted SuccessFully", Toast.LENGTH_SHORT).show();
                }catch (Exception e)
                {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().remove(salesByBrands.this).commit();
                getFragmentManager().popBackStack();
                DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.START);
                //drawer.closeDrawer(GravityCompat.START);
             //   return true;
            }
        });
    }


    public static class Brands
    {
        String spinnerValue,from_date,from_time,to_time;
        int price,quantity;

        public Brands() {

        }

        public Brands(String spinnerValue, String from_date, String from_time, String to_time, int price, int quantity) {
            this.spinnerValue = spinnerValue;
            this.from_date = from_date;
            this.from_time = from_time;
            this.to_time = to_time;
            this.price = price;
            this.quantity = quantity;
        }

        public String getSpinnerValue() {
            return spinnerValue;
        }

        public void setSpinnerValue(String spinnerValue) {
            this.spinnerValue = spinnerValue;
        }

        public String getFrom_date() {
            return from_date;
        }

        public void setFrom_date(String from_date) {
            this.from_date = from_date;
        }

        public String getFrom_time() {
            return from_time;
        }

        public void setFrom_time(String from_time) {
            this.from_time = from_time;
        }

        public String getTo_time() {
            return to_time;
        }

        public void setTo_time(String to_time) {
            this.to_time = to_time;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
    }

