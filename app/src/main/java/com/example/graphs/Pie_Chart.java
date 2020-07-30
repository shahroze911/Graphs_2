package com.example.graphs;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.example.graphs.Classes.Firebase_Class;
import com.example.graphs.Classes.Graph;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import static android.content.Context.MODE_PRIVATE;

public class Pie_Chart extends Fragment implements AdapterView.OnItemSelectedListener{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_graph_piechart, container, false);
    }
    AnyChartView anyChartView;
    Spinner sp_users,sp_months;
    Graph graph;
    Firebase_Class  firebase_class;
    SharedPreferences sharedPreferences;


    String[] Users = {"Khizar"};
    String[] months = {"January","Febuary","March","April","May","June"};

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Sales by Category");
        anyChartView = (AnyChartView) getView().findViewById(R.id.Pie_Graph) ;
        sp_months = (Spinner) getView().findViewById(R.id.spinners_months);
        sp_users = (Spinner) getView().findViewById(R.id.spinner_Users);

        //Toast.makeText(getActivity(), ""+dateToday, Toast.LENGTH_SHORT).show();
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.spinner_item,Users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_users.setAdapter(adapter);
        sp_users.setSelected(false);
       sp_users.setSelection(0,false);
        sp_users.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        ArrayAdapter adapter2 = new ArrayAdapter(getActivity(), R.layout.spinner_item,months);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_months.setAdapter(adapter2);
        sp_months.setSelected(false);
        sp_months.setSelection(0,false);
        sp_months.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        //final Pie pie = AnyChart.pie();

        String dateToday = getDay_Time();
        firebase_class = new Firebase_Class();
        Graph Pie1 = new Graph();
        Pie1.setIncome(10000);
        Pie1.setProfit(4000);
        Graph Pie2 = new Graph();
        Pie2.setIncome(20000);
        Pie2.setProfit(4000);
        Graph Pie3 = new Graph();
        Pie3.setIncome(30000);
        Pie3.setProfit(4000);
        Graph Pie4 = new Graph();
        Pie4.setIncome(40000);
        Pie4.setProfit(4000);
        Graph Pie5 = new Graph();
        Pie5.setIncome(50000);
        Pie5.setProfit(4000);
        Graph Pie6 = new Graph();
        Pie6.setIncome(60000);
        Pie6.setProfit(4000);
 //User1
        firebase_class.send_data(Pie1,Users[0],months[0],dateToday);
        firebase_class.send_data(Pie2,Users[0],months[1],dateToday);
        firebase_class.send_data(Pie3,Users[0],months[2],dateToday);
        firebase_class.send_data(Pie4,Users[0],months[3],dateToday);
        firebase_class.send_data(Pie5,Users[0],months[4],dateToday);
        firebase_class.send_data(Pie6,Users[0],months[5],dateToday);
/*//User2
        firebase_class.send_data(Pie1,Users[1],months[0],dateToday);
        firebase_class.send_data(Pie2,Users[1],months[1],dateToday);
        firebase_class.send_data(Pie3,Users[1],months[2],dateToday);
//User3
        firebase_class.send_data(Pie1,Users[2],months[0],dateToday);
        firebase_class.send_data(Pie2,Users[2],months[1],dateToday);
        firebase_class.send_data(Pie3,Users[2],months[2],dateToday);*/

      firebase_class.get_profit_and_income(Users[0], months[0], getDay_Time(), new OnGetDataListener() {
            @Override
            public void onStart() {

            }
            @Override
            public void onSuccess(DataSnapshot data) {
                graph = data.getValue(Graph.class);
                Toast.makeText(getActivity(), ""+graph.getIncome(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), ""+graph.getProfit(), Toast.LENGTH_SHORT).show();
                List<DataEntry> Seriedata = new ArrayList<>();
                //firebase_class.get_profit_and_income(Users[0], months[0], getDay_Time());
                Seriedata.add(new ValueDataEntry("INCOME",graph.getIncome()));
                Seriedata.add(new ValueDataEntry("PROFIT", graph.getProfit()));
                Draw_Pie_Chart(Seriedata);
            }

            @Override
            public void onFailed(DatabaseError databaseError) {

            }
        });
    }
    public interface OnGetDataListener {
        public void onStart();
        public void onSuccess(DataSnapshot data);
        public void onFailed(DatabaseError databaseError);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Spinner user = (Spinner) parent;
        Spinner time = (Spinner) parent;

        //Pie pie = AnyChart.pie();
        /*if (user.getId() == R.id.spinner_Users)
        {

            Toast.makeText(getActivity(), "User Spinner Called", Toast.LENGTH_SHORT).show();
            if (position == 0) {
                //anyChartView.clear();
                Toast.makeText(getActivity(), "Spinner zero index", Toast.LENGTH_SHORT).show();
                firebase_class.get_profit_and_income(Users[0], months[0], getDay_Time());
                data.add(new ValueDataEntry("INCOME", 50000));
                data.add(new ValueDataEntry("PROFIT", 8000));
                Draw_Pie_Chart(data);
            }

        }*/
        if(time.getId() == R.id.spinners_months)
        {
            Toast.makeText(getActivity(), "Month Spinner Called", Toast.LENGTH_SHORT).show();
            Spinner spinner = (Spinner) getView().findViewById(R.id.spinners_months);
            String text = spinner.getSelectedItem().toString();
            String Selected_month = "";

            if (text == months[0])
            {
                //Toast.makeText(getActivity(), "Month 0st index", Toast.LENGTH_SHORT).show();
                //Toast.makeText(getActivity(), ""+Firebase_Class.INCOME, Toast.LENGTH_SHORT).show();
                //Toast.makeText(getActivity(), ""+Firebase_Class.INCOME, Toast.LENGTH_SHORT).show();
                //anyChartView.clear();
                Selected_month = months[0];
            }
            else if (text == months[1])
            {
                //anyChartView.clear();
                /*Toast.makeText(getActivity(), "Month 1st index", Toast.LENGTH_SHORT).show();
                firebase_class.get_profit_and_income(Users[0],months[1],getDay_Time());
                Toast.makeText(getActivity(), ""+Firebase_Class.INCOME, Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), ""+Firebase_Class.PROFIT, Toast.LENGTH_SHORT).show();
                data.add(new ValueDataEntry("INCOME",Firebase_Class.INCOME));
                data.add(new ValueDataEntry("PROFIT",Firebase_Class.PROFIT));
                Draw_Pie_Chart(data,pie);*/
                Selected_month =  months[1];
            }
            else if (text == months[2])
            {
                /*Toast.makeText(getActivity(), "Month 2nd Index", Toast.LENGTH_SHORT).show();
                firebase_class.get_profit_and_income(Users[0],months[2],getDay_Time());
                Toast.makeText(getActivity(), ""+Firebase_Class.INCOME, Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), ""+Firebase_Class.PROFIT, Toast.LENGTH_SHORT).show();
                data.add(new ValueDataEntry("INCOME",Firebase_Class.INCOME));
                data.add(new ValueDataEntry("PROFIT",Firebase_Class.PROFIT));
                //anyChartView.clear();
                Draw_Pie_Chart(data,pie);*/
                Selected_month = months[2];

            }
            else if (text == months[3])
            {
               /* Toast.makeText(getActivity(), "Month 3rd Index", Toast.LENGTH_SHORT).show();
                firebase_class.get_profit_and_income(Users[0],months[3],getDay_Time());

                Toast.makeText(getActivity(), ""+Firebase_Class.INCOME, Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), ""+Firebase_Class.PROFIT, Toast.LENGTH_SHORT).show();
                data.add(new ValueDataEntry("INCOME",Firebase_Class.INCOME));
                data.add(new ValueDataEntry("PROFIT",Firebase_Class.PROFIT));
                //anyChartView.clear();
                Draw_Pie_Chart(data,pie);*/
               Selected_month = months[3];
            }
            else if (text == months[4])
            {
               /* Toast.makeText(getActivity(), "Month 4th Index", Toast.LENGTH_SHORT).show();
                firebase_class.get_profit_and_income(Users[0],months[4],getDay_Time());

                Toast.makeText(getActivity(), ""+Firebase_Class.INCOME, Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), ""+Firebase_Class.PROFIT, Toast.LENGTH_SHORT).show();
                data.add(new ValueDataEntry("INCOME",Firebase_Class.INCOME));
                data.add(new ValueDataEntry("PROFIT",Firebase_Class.PROFIT));
                //anyChartView.clear();
                Draw_Pie_Chart(data,pie);*/
               Selected_month = months[4];
            }
            else if (text == months[5])
            {
                /*firebase_class.get_profit_and_income(Users[0],months[5],getDay_Time());

                Toast.makeText(getActivity(), ""+Firebase_Class.INCOME, Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), ""+Firebase_Class.PROFIT, Toast.LENGTH_SHORT).show();
                data.add(new ValueDataEntry("INCOME",Firebase_Class.INCOME));
                data.add(new ValueDataEntry("PROFIT",Firebase_Class.PROFIT));
                //anyChartView.clear();
                Draw_Pie_Chart(data,pie);*/
                Selected_month = months[5];
            }
            firebase_class.get_profit_and_income(Users[0], Selected_month, getDay_Time(), new OnGetDataListener() {
                @Override
                public void onStart() {

                }

                @Override
                public void onSuccess(DataSnapshot data) {

                    Gson gson = new Gson();
                    String json = sharedPreferences.getString("pie_obj", "");
                    Pie obj = gson.fromJson(json, Pie.class);
                    obj.dispose();
                    anyChartView.clear();
                    List<DataEntry> Seriedata = new ArrayList<>();
                    graph = data.getValue(Graph.class);
                    Toast.makeText(getActivity(), ""+graph.getIncome(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(), ""+graph.getProfit(), Toast.LENGTH_SHORT).show();
                    //firebase_class.get_profit_and_income(Users[0], months[0], getDay_Time());
                    Seriedata.add(new ValueDataEntry("INCOME",graph.getIncome()));
                    Seriedata.add(new ValueDataEntry("PROFIT", graph.getProfit()));
                    Draw_Pie_Chart(Seriedata);
                }
                @Override
                public void onFailed(DatabaseError databaseError) {

                }
            });
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void Draw_Pie_Chart(List<DataEntry> data)
    {
        Pie pie = AnyChart.pie();
        pie.data(data);
        pie.animation(true);
        AnyChartView anyChartView = (AnyChartView) getView().findViewById(R.id.Pie_Graph) ;
        anyChartView.setChart(pie);
        //anyChartView.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.DARKEN);
        //anyChartView.setBackgroundColor("#170a45");
        // pie.background().stroke("5 #170a45");
        anyChartView.setBackgroundColor("#201C2B");
        pie.fill("aquastyle");
        pie.background().fill("#201C2B");


        sharedPreferences = getActivity().getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(pie);
        editor.putString("pie_obj",json);
        editor.commit();
    }
    public String getDay_Time()
    {
/*

        Date current_time = Calendar.getInstance().getTime();
        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        String Minutes = "";
        if (today.minute < 10) {
            Minutes = "0" + today.minute;
        } else {
            Minutes = String.valueOf(today.minute);
        }
        String time = today.hour + ":" + Minutes;
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date currentDate = new Date();
        String dateToday = df.format(currentDate) +" " + time;
   */
        Calendar calendar  = Calendar.getInstance(TimeZone.getDefault());
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        String date = Integer.toString(currentDay) +"-"+ Integer.toString(currentMonth) +"-"+ Integer.toString(currentYear);
        // Toast.makeText(getActivity(),"Today's Date: "+date,Toast.LENGTH_SHORT).show();


        return date;
    }

}
