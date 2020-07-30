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
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;
import com.example.graphs.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.content.Context.MODE_PRIVATE;

public class Sales_Date extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_sales_date, container, false);
    }
    Button btn_day,btn_week,btn_custome;
    AnyChartView anyChartView;
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
        getActivity().setTitle("Sales By Date");

        btn_day = getActivity().findViewById(R.id.btn_day_intDate);
        btn_week = getActivity().findViewById(R.id.btn_week_intDate);
        btn_custome = getActivity().findViewById(R.id.btn_custom_intDate);

        anyChartView = (AnyChartView) getView().findViewById(R.id.Line_graph_today_InDate);
        Cartesian areaChart = AnyChart.line();
        List<DataEntry> seriesData = new ArrayList<>();
        seriesData.add(new CustomDataEntry("6am-11am", 100,200));
        seriesData.add(new CustomDataEntry("11-3pm", 200,600));
        seriesData.add(new CustomDataEntry("3-8pm", 180,100));
        seriesData.add(new CustomDataEntry("8-1am", 200,500));
        seriesData.add(new CustomDataEntry("1-6am", 450,50));
        draw_area_graph_today(seriesData,areaChart);

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
                String json = sharedPreferences.getString("list_obj_3", "");
                Cartesian obj = gson.fromJson(json, Cartesian.class);
                obj.dispose();
                anyChartView.clear();
                Cartesian cartesian = AnyChart.line();
                seriesData1.add(new CustomDataEntry("6am-11am", 200,100));
                seriesData1.add(new CustomDataEntry("11-3pm", 400,500));
                seriesData1.add(new CustomDataEntry("3-8pm", 680,200));
                seriesData1.add(new CustomDataEntry("8-1am", 500,100));
                seriesData1.add(new CustomDataEntry("1-6am", 50,500));
                draw_area_graph_today(seriesData1,cartesian);
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
                String json = sharedPreferences.getString("list_obj_3", "");
                Cartesian obj = gson.fromJson(json, Cartesian.class);
                obj.dispose();
                anyChartView.clear();
                Cartesian cartesian= AnyChart.line();
                List<DataEntry> seriesData3 = new ArrayList<>();
                seriesData3.add(new CustomDataEntry("Apr 1", 400,100));
                seriesData3.add(new CustomDataEntry("Apr 2", 100,50));
                seriesData3.add(new CustomDataEntry("Apr 3", 380,100));
                seriesData3.add(new CustomDataEntry("Apr 4", 200,800));
                seriesData3.add(new CustomDataEntry("Apr 5", 150,900));
                seriesData3.add(new CustomDataEntry("Apr 6", 620,150));
                draw_area_graph_today(seriesData3,cartesian);

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
                        pickerDialog[0] = new DatePickerDialog(getActivity(),R.style.DialogTheme ,new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                tv_fromdate.setText(dayOfMonth+"/"+month+"/"+year);

                                from_day = dayOfMonth;
                                from_month = month;
                                from_year = year;
                            }
                        },year ,month,day);
                        pickerDialog[0].show();
                    }
                });
                btn_todate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pickerDialog[0] = new DatePickerDialog(getActivity(),R.style.DialogTheme ,new DatePickerDialog.OnDateSetListener() {
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
                            String json = sharedPreferences.getString("list_obj_3", "");
                            Cartesian obj = gson.fromJson(json, Cartesian.class);
                            obj.dispose();
                            anyChartView.clear();
                            Cartesian cartesian = AnyChart.line();
                            List<DataEntry> seriesData2 = new ArrayList<>();
                            seriesData2.add(new CustomDataEntry("Apr 1", 300, 50));
                            seriesData2.add(new CustomDataEntry("Apr 2", 200, 900));
                            seriesData2.add(new CustomDataEntry("Apr 3", 480, 950));
                            seriesData2.add(new CustomDataEntry("Apr 4", 400, 200));
                            seriesData2.add(new CustomDataEntry("Apr 5", 350, 800));
                            seriesData2.add(new CustomDataEntry("Apr 6", 520, 20));
                            draw_area_graph_today(seriesData2, cartesian);
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
    }
    public void draw_area_graph_today(List<DataEntry> seriesData, Cartesian cartesian)
    {
        AnyChartView anyChartView = getView().findViewById(R.id.Line_graph_today_InDate);
        cartesian.animation(true);
        cartesian.padding(10d, 20d, 5d, 20d);
        cartesian.crosshair().enabled(true);
        cartesian.crosshair()
                .yLabel(true)
                // TODO ystroke
                .yStroke((Stroke) null, null, null, (String) null, (String) null);

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);

        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2Mapping = set.mapAs("{ x: 'x', value: 'value2' }");

        Line series1 = cartesian.line(series1Mapping);
        series1.hovered().markers().enabled(true);
        series1.normal().fill("#6ECDDE");
        series1.normal().stroke("#6ECDDE");
        // enable major grids
        cartesian.yGrid(0).enabled(true);



        series1.name("Previous Sale");
        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series1.tooltip()
                .position("up")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);
        Line series2 = cartesian.line(series2Mapping);
        series2.hovered().markers().enabled(true);
        series2.normal().fill("#9F3A28");
        series2.normal().stroke("#9F3A28");
        // enable major grids



        series2.name("Current Sale");
        series2.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series2.tooltip()
                .position("up")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);
        /*cartesian.yGrid(1).enabled(true);
        cartesian.yGrid(2).enabled(true);*/
        // enable minor grids
/*
        chart.xMinorGrid().enabled(true);
        chart.yMinorGrid().enabled(true);
*/


        anyChartView.setBackgroundColor("#0A1720");
        cartesian.background().fill("#0A1720");
        anyChartView.setChart(cartesian);



        sharedPreferences = getActivity().getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(cartesian);
        editor.putString("list_obj_3",json);
        editor.commit();

    }
    private class CustomDataEntry extends ValueDataEntry {
        CustomDataEntry(String x, Number value,Number value2) {
            super(x, value);
            setValue("value2", value2);
            //setValue("value3", value3);

        }
    }
 /*   public void draw_line_graph_date(List<DataEntry> seriesData, Cartesian cartesian)
    {
        AnyChartView anyChartView = getView().findViewById(R.id.Line_graph_today_InDate);
        cartesian.animation(true);
        cartesian.padding(10d, 20d, 5d, 20d);
        cartesian.crosshair().enabled(true);
        cartesian.crosshair()
                .yLabel(true)
                // TODO ystroke
                .yStroke((Stroke) null, null, null, (String) null, (String) null);

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);

        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");

        Line series1 = cartesian.line(series1Mapping);
        series1.hovered().markers().enabled(true);
        series1.normal().fill("#6ECDDE");
        series1.normal().stroke("#6ECDDE");
        // enable major grids
        cartesian.yGrid(0).enabled(true);

        *//*cartesian.yGrid(1).enabled(true);
        cartesian.yGrid(2).enabled(true);*//*
        // enable minor grids
*//*
        chart.xMinorGrid().enabled(true);
        chart.yMinorGrid().enabled(true);
*//*


        series1.name("Sale");
        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series1.tooltip()
                .position("up")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);


        anyChartView.setBackgroundColor("#0A1720");
        cartesian.background().fill("#0A1720");
        anyChartView.setChart(cartesian);



        sharedPreferences = getActivity().getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(cartesian);
        editor.putString("list_obj_3",json);
        editor.commit();

    }
*/


}
