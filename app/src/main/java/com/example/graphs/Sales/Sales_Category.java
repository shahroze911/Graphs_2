package com.example.graphs.Sales;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Bar;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.HoverMode;
import com.anychart.enums.ScaleStackMode;
import com.anychart.enums.TooltipDisplayMode;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Anchor;
import com.example.graphs.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.content.Context.MODE_PRIVATE;

public class Sales_Category extends Fragment {
    FirebaseDatabase rootNode;
    DatabaseReference databaseReference;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_sales_category, container, false);
    }
    Button btn_day,btn_week,btn_custome;
    AnyChartView anyChartView;
    TextView tv_brand,tv_price;
    SharedPreferences sharedPreferences;
    Calendar c = Calendar.getInstance();
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH);
    int day = c.get(Calendar.DAY_OF_MONTH);
    int from_day,from_month,from_year;
    int to_day,to_month,to_year;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Sales by Category");

        btn_day = getActivity().findViewById(R.id.btn_day_category);
        btn_week = getActivity().findViewById(R.id.btn_week_category);
        btn_custome = getActivity().findViewById(R.id.btn_custom_category);
        anyChartView = (AnyChartView) getView().findViewById(R.id.Bar_graph_Category);
        tv_price = getView().findViewById(R.id.txt_brand_price_category);
        tv_brand = getView().findViewById(R.id.txt_brand_category);


        Cartesian barChart = AnyChart.bar();
        List<DataEntry> seriesData = new ArrayList<>();
        seriesData.add(new CustomDataEntry("SmartPhones", 800,1000));
        seriesData.add(new CustomDataEntry("Laptops", 200,100));
        seriesData.add(new CustomDataEntry("Charging", 500,400));
        seriesData.add(new CustomDataEntry("Tabs", 150,250));
        seriesData.add(new CustomDataEntry("Others", 500,600));
        seriesData.add(new CustomDataEntry("Gear", 600,700));
        draw_area_graph_today(seriesData,barChart);
        tv_price.setText("800");
        tv_brand.setText("SmartPhones");


        btn_day.setOnClickListener(new View.OnClickListener() {
            List<DataEntry> seriesData1 = new ArrayList<>();
            @Override
            public void onClick(View v) {

                btn_day.setBackgroundColor(getResources().getColor(R.color.Activity_background));
                btn_day.setBackground(getResources().getDrawable(R.drawable.test));
                btn_day.setTextColor(getResources().getColor(R.color.Graph_text_color_sales_today));
                btn_week.setTextColor(getResources().getColor(R.color.MyWhite));
                btn_custome.setTextColor(getResources().getColor(R.color.MyWhite));
                btn_custome.setBackgroundColor(getResources().getColor(R.color.toolBar_background));
                btn_week.setBackgroundColor(getResources().getColor(R.color.toolBar_background));


                Gson gson = new Gson();
                String json = sharedPreferences.getString("bar_obj", "");
                Cartesian obj = gson.fromJson(json, Cartesian.class);
                obj.dispose();
                anyChartView.clear();
                Cartesian areaChart = AnyChart.bar();
                seriesData1.add(new CustomDataEntry("SmartPhones", 100,200));
                seriesData1.add(new CustomDataEntry("Laptops", 900,800));
                seriesData1.add(new CustomDataEntry("Charging", 180,250));
                seriesData1.add(new CustomDataEntry("Tabs", 200,300));
                seriesData1.add(new CustomDataEntry("Others", 450,550));
                seriesData1.add(new CustomDataEntry("Gear", 320,420));
                draw_area_graph_today(seriesData1,areaChart);
                tv_price.setText("900");
                tv_brand.setText("Laptops");


            }
        });
        btn_custome.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                btn_custome.setBackgroundColor(getResources().getColor(R.color.Activity_background));
                btn_custome.setTextColor(getResources().getColor(R.color.Graph_text_color_sales_today));
                btn_custome.setBackground(getResources().getDrawable(R.drawable.test));
                btn_day.setTextColor(getResources().getColor(R.color.MyWhite));
                btn_week.setTextColor(getResources().getColor(R.color.MyWhite));
                btn_day.setBackgroundColor(getResources().getColor(R.color.toolBar_background));
                btn_week.setBackgroundColor(getResources().getColor(R.color.toolBar_background));
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View view1 = getLayoutInflater().inflate(R.layout.custom_calender,null);
                builder.setTitle("Select Your Desire Date");
                Button btn_fromdate = view1.findViewById(R.id.btn_fromDate_today);
                Button btn_todate = view1.findViewById(R.id.btn_toDate_today);
                Button cancel = view1.findViewById(R.id.btn_cancel_today);
                Button submit = view1.findViewById(R.id.btn_submit_today);
                final TextView tv_fromdate = view1.findViewById(R.id.txt_view_from_date);
                final TextView tv_todate = view1.findViewById(R.id.txt_view_todate);
                final DatePickerDialog[] pickerDialog = new DatePickerDialog[1];
                builder.setView(view1);
                final AlertDialog dialog = builder.create();
                dialog.show();
                btn_fromdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pickerDialog[0] = new DatePickerDialog(getActivity(), R.style.DialogTheme,new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                tv_fromdate.setText(dayOfMonth+"/"+month+"/"+year);

                                from_day = dayOfMonth;
                                from_month = month;
                                from_year = year;
                            }
                        },year,month,day);
                        pickerDialog[0].show();
                    }
                });
                btn_todate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pickerDialog[0] = new DatePickerDialog(getActivity(),R.style.DialogTheme,new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                tv_todate.setText(dayOfMonth+"/"+month+"/"+year);
                                to_day = dayOfMonth;
                                to_month = month;
                                to_year = year;
                            }
                        },year,month,day);
                        pickerDialog[0].show();
                    }
                });
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (to_year> year || to_month > month || (from_month == to_month && to_day > day)) {

                            new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("Ooops ... ")
                                    .setContentText("Please Select Valid Date")
                                    .setConfirmText("OK")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            sDialog.dismissWithAnimation();
                                        }
                                    })
                                    .show();
                        } else {

                            dialog.dismiss();
                            Gson gson = new Gson();
                            String json = sharedPreferences.getString("bar_obj", "");
                            Cartesian obj = gson.fromJson(json, Cartesian.class);
                            obj.dispose();
                            anyChartView.clear();
                            Cartesian areaChart = AnyChart.bar();
                            List<DataEntry> seriesData2 = new ArrayList<>();
                            seriesData2.add(new CustomDataEntry("SmartPhones", 300, 100));
                            seriesData2.add(new CustomDataEntry("Laptops", 200, 300));
                            seriesData2.add(new CustomDataEntry("Charging", 480, 580));
                            seriesData2.add(new CustomDataEntry("Tabs", 400, 500));
                            seriesData2.add(new CustomDataEntry("Others", 350, 450));
                            seriesData2.add(new CustomDataEntry("Gear", 520, 650));
                            draw_area_graph_today(seriesData2, areaChart);
                            tv_price.setText("520");
                            tv_brand.setText("Gear");
                        }
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });




            }
        });
        btn_week.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                btn_week.setBackgroundColor(getResources().getColor(R.color.Activity_background));
                btn_week.setBackground(getResources().getDrawable(R.drawable.test));
                btn_week.setTextColor(getResources().getColor(R.color.Graph_text_color_sales_today));
                btn_day.setTextColor(getResources().getColor(R.color.MyWhite));
                btn_custome.setTextColor(getResources().getColor(R.color.MyWhite));
                btn_custome.setBackgroundColor(getResources().getColor(R.color.toolBar_background));
                btn_day.setBackgroundColor(getResources().getColor(R.color.toolBar_background));


                Gson gson = new Gson();
                String json = sharedPreferences.getString("bar_obj", "");
                Cartesian obj = gson.fromJson(json, Cartesian.class);
                obj.dispose();
                anyChartView.clear();
                Cartesian areaChart = AnyChart.bar();
                List<DataEntry> seriesData3 = new ArrayList<>();
                seriesData3.add(new CustomDataEntry("SmartPhones", 400,500));
                seriesData3.add(new CustomDataEntry("Laptops", 100,200));
                seriesData3.add(new CustomDataEntry("Charging", 900,1000));
                seriesData3.add(new CustomDataEntry("Tabs", 200,800));
                seriesData3.add(new CustomDataEntry("Others", 150,250));
                seriesData3.add(new CustomDataEntry("Gear", 620,720));

                draw_area_graph_today(seriesData3,areaChart);
                tv_price.setText("900");
                tv_brand.setText("Charging");
            }
        });




    }
    public void draw_area_graph_today(List<DataEntry> seriesData, Cartesian barChart)
    {

        //Toast.makeText(getActivity(), "Function Called", Toast.LENGTH_LONG).show();
        AnyChartView anyChartView = getView().findViewById(R.id.Bar_graph_Category);
        barChart.animation(true);
        barChart.padding(10d, 20d, 5d, 20d);
        barChart.yScale().stackMode(ScaleStackMode.VALUE);

        /*barChart.yAxis(0).labels().format(
                "function() {\n" +
                        "    return Math.abs(this.value).toLocaleString();\n" +
                        "  }");*/
        // barChart.yAxis(0d).title("Revenue in Dollars");
        /*
        barChart.xAxis(0d).overlapMode(LabelsOverlapMode.ALLOW_OVERLAP);
        Linear xAxis1 = barChart.xAxis(1d);
        xAxis1.enabled(true);
        xAxis1.orientation(Orientation.RIGHT);
        xAxis1.overlapMode(LabelsOverlapMode.ALLOW_OVERLAP);*/
        //barChart.title("Cosmetic Sales by Gender");

        barChart.interactivity().hoverMode(HoverMode.BY_X);

        barChart.tooltip()
                .title(false)
                .separator(false)
                .displayMode(TooltipDisplayMode.SEPARATED)
                .positionMode(TooltipPositionMode.POINT)
                .useHtml(true)
                .fontSize(12d)
                .offsetX(5d)
                .offsetY(0d)
                .format(
                        "function() {\n" +
                                "      return '<span style=\"color: #f1f1f1\">PKR </span>' + Math.abs(this.value).toLocaleString();\n" +
                                "    }");


        Set set = Set.instantiate();
        set.data(seriesData);
        // barChart.addSeries((Set) seriesData);
        Mapping series1Data = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2Data = set.mapAs("{ x: 'x', value: 'value2' }");

        String[] color = new String[]{"#444F8F", "#6ECDDE"};
        Bar series1 = barChart.bar(series1Data);
        series1.normal().fill(color);

        /*series1.normal().fill("#5cd65c");*/

        series1.normal().stroke(null);
        series1.normal().labels().enabled(true);
        series1.tooltip()
                .position("right")
                .anchor(String.valueOf(Anchor.RIGHT_CENTER));

        String[] color2 = new String[]{"#9F3A28","#C18C53"};
        Bar series2 = barChart.bar(series2Data);
        series2.normal().fill(color2);
        series2.normal().stroke(null);

        //series2.name("Males");
        series2.tooltip()
                .position("left")
                .anchor(String.valueOf(Anchor.RIGHT_CENTER));



        /*Bar series2 = barChart.bar(series2Data);
        //series2.name("Males");
        series2.color("#87CEEB");
        series2.tooltip()
                .position("left")
                .anchor(String.valueOf(Anchor.RIGHT_CENTER));*/
/*

        barChart.legend().enabled(true);
        barChart.legend().inverted(true);
        barChart.legend().fontSize(13d);
        barChart.legend().padding(0d, 0d, 20d, 0d);
*/
        anyChartView.setBackgroundColor("#0A1720");
        barChart.background().fill("#0A1720");
        anyChartView.setChart(barChart);
        // barChart.dispose();
        //barChart = null;

        sharedPreferences = getActivity().getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(barChart);
        editor.putString("bar_obj",json);
        editor.commit();




/*
        Iterator itr = seriesData.iterator();
        while (itr.hasNext())
        {
            int x = (Integer)itr.next();
            itr.remove();
        }
*/


    }
    private class CustomDataEntry extends ValueDataEntry {
        CustomDataEntry(String x, Number value, Number value2) {
            super(x, value);
            setValue("value2", value2);
        }
    }
}
