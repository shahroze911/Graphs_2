package com.example.graphs.Sales;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toolbar;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Area;
import com.anychart.core.cartesian.series.Bar;
import com.anychart.core.ui.Crosshair;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.HoverMode;
import com.anychart.enums.MarkerType;
import com.anychart.enums.ScaleStackMode;
import com.anychart.enums.TooltipDisplayMode;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Anchor;
import com.anychart.graphics.vector.Stroke;
import com.example.graphs.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import antonkozyriatskyi.circularprogressindicator.CircularProgressIndicator;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.content.Context.MODE_PRIVATE;

public class Sales_Brand extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_sales_brand, container, false);
    }
    Toolbar toolbar;
    CircularProgressIndicator circularProgressIndicator,circularProgressIndicator2,br1,br2,br3,br4;
    TextView t_br1,t_br2,t_br3,t_br4;
    Button btn_day,btn_week,btn_custome;
    AnyChartView anyChartView;
    SharedPreferences sharedPreferences;

    Calendar c = Calendar.getInstance();
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH);
    int day = c.get(Calendar.DAY_OF_MONTH);


    int from_day,from_month,from_year;
    int to_day,to_month,to_year;

    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Best Selling Brands");

        circularProgressIndicator = getView().findViewById(R.id.circular_progress_brand_1);
        circularProgressIndicator2 = getView().findViewById(R.id.circular_progress_brand_2);
        br1 = getView().findViewById(R.id.circular_progress_brand_MI);
        br2 = getView().findViewById(R.id.circular_progress_brand_Vivo);
        br3 = getView().findViewById(R.id.circular_progress_brand_Nokia);
        br4 = getView().findViewById(R.id.circular_progress_brand_QMobile);
        t_br1 = getView().findViewById(R.id.txt_brand_1);
        t_br2 = getView().findViewById(R.id.txt_brand_2);
        t_br3 = getView().findViewById(R.id.txt_brand_3);
        t_br4 = getView().findViewById(R.id.txt_brand_4);


        circularProgressIndicator.setProgress(800,1000);
        circularProgressIndicator2.setProgress(700,1000);
        br1.setProgress(70,100);
        br2.setProgress(60,100);
        br3.setProgress(40,100);
        br4.setProgress(20,100);

        //circularProgressIndicator2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        btn_day = getActivity().findViewById(R.id.btn_day_brand);
        btn_week = getActivity().findViewById(R.id.btn_week_brand);
        btn_custome = getActivity().findViewById(R.id.btn_custom_brand);
        anyChartView = (AnyChartView) getView().findViewById(R.id.Area_graph_brand);
        Cartesian areaChart = AnyChart.bar();
        List<DataEntry> seriesData = new ArrayList<>();
        seriesData.add(new CustomDataEntry("6am-11am", 100,200));
        seriesData.add(new CustomDataEntry("11am-3pm", 200,600));
        seriesData.add(new CustomDataEntry("3pm-8pm", 180,100));
        seriesData.add(new CustomDataEntry("8pm-1am", 200,500));
        seriesData.add(new CustomDataEntry("1am-6am", 450,50));
        draw_bar_graph_brand(seriesData,areaChart);


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
                circularProgressIndicator.setProgress(900,1000);
         //       circularProgressIndicator.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                circularProgressIndicator2.setProgress(600,1000);
       //         circularProgressIndicator2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                br1.setProgress(30,100);
                br2.setProgress(40,100);
                br3.setProgress(50,100);
                br4.setProgress(60,100);
                t_br1.setText("30% achieved");
                t_br2.setText("40% achieved");
                t_br3.setText("50% achieved");
                t_br4.setText("60% achieved");



                Gson gson = new Gson();
                String json = sharedPreferences.getString("list_obj_brand", "");
                Cartesian obj = gson.fromJson(json, Cartesian.class);
                obj.dispose();
                anyChartView.clear();
                Cartesian areaChart = AnyChart.bar();
                seriesData1.add(new CustomDataEntry("6am-11am", 900,200));
                seriesData1.add(new CustomDataEntry("11am-3pm", 100,600));
                seriesData1.add(new CustomDataEntry("3pm-8pm", 800,100));
                seriesData1.add(new CustomDataEntry("8pm-1am", 500,500));
                seriesData1.add(new CustomDataEntry("1am-6am", 250,50));
                draw_bar_graph_brand(seriesData1,areaChart);

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
                        pickerDialog[0] = new DatePickerDialog(getActivity(),R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
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
                            circularProgressIndicator.setProgress(800, 1000);
//                circularProgressIndicator.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                            circularProgressIndicator2.setProgress(500, 1000);
//                circularProgressIndicator2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                            br1.setProgress(20, 100);
                            br2.setProgress(80, 100);
                            br3.setProgress(30, 100);
                            br4.setProgress(90, 100);
                            t_br1.setText("20% achieved");
                            t_br2.setText("80% achieved");
                            t_br3.setText("30% achieved");
                            t_br4.setText("90% achieved");
                            Gson gson = new Gson();
                            String json = sharedPreferences.getString("list_obj_brand", "");
                            Cartesian obj = gson.fromJson(json, Cartesian.class);
                            obj.dispose();
                            anyChartView.clear();
                            Cartesian areaChart = AnyChart.area();
                            List<DataEntry> seriesData2 = new ArrayList<>();
                            seriesData2.add(new CustomDataEntry("Apr 1", 300, 100));
                            seriesData2.add(new CustomDataEntry("Apr 2", 200, 500));
                            seriesData2.add(new CustomDataEntry("Apr 3", 480, 50));
                            seriesData2.add(new CustomDataEntry("Apr 4", 400, 100));
                            seriesData2.add(new CustomDataEntry("Apr 5", 350, 150));
                            seriesData2.add(new CustomDataEntry("Apr 6", 520, 80));
                            draw_area_graph_today(seriesData2, areaChart);
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

                circularProgressIndicator.setProgress(700,1000);
//                circularProgressIndicator.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                circularProgressIndicator2.setProgress(850,1000);
//                circularProgressIndicator2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                br1.setProgress(90,100);
                br2.setProgress(10,100);
                br3.setProgress(60,100);
                br4.setProgress(70,100);
                t_br1.setText("90% achieved");
                t_br2.setText("10% achieved");
                t_br3.setText("60% achieved");
                t_br4.setText("70% achieved");

                Gson gson = new Gson();
                String json = sharedPreferences.getString("list_obj_brand", "");
                Cartesian obj = gson.fromJson(json, Cartesian.class);
                obj.dispose();
                anyChartView.clear();
                Cartesian areaChart = AnyChart.area();
                List<DataEntry> seriesData3 = new ArrayList<>();
                seriesData3.add(new CustomDataEntry("Apr 1", 400,400));
                seriesData3.add(new CustomDataEntry("Apr 2", 100,50));
                seriesData3.add(new CustomDataEntry("Apr 3", 380,300));
                seriesData3.add(new CustomDataEntry("Apr 4", 200,100));
                seriesData3.add(new CustomDataEntry("Apr 5", 150,100));
                seriesData3.add(new CustomDataEntry("Apr 6", 620,300));
                draw_area_graph_today(seriesData3,areaChart);

            }
        });
    }
    public void draw_area_graph_today(List<DataEntry> seriesData,Cartesian areaChart)
    {
        String[] color = new String[]{"#444F8F", "#6ECDDE"};
        String[] color2 = new String[]{"#9F3A28","#C18C53"};
        AnyChartView anyChartView = getView().findViewById(R.id.Area_graph_brand);

        /*Cartesian areaChart = AnyChart.area();*/

        areaChart.animation(true);
        Crosshair crosshair = areaChart.crosshair();
        crosshair.enabled(true);
        crosshair.displayMode("float");
        // TODO yStroke xStroke in crosshair
        crosshair.yStroke((Stroke) null, null, null, (String) null, (String) null)
                .xStroke((Stroke)null, 1d, null, (String) null, (String) null)
                .zIndex(39d);
        crosshair.yLabel(0).enabled(true);

        areaChart.yScale().stackMode(ScaleStackMode.VALUE);

        // areaChart.title("Unaudited Apple Inc. Revenue by Operating Segments");

        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping series1Data = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2Data = set.mapAs("{ x: 'x', value: 'value2' }");
        Area series1 = areaChart.area(series1Data);
        series1.name("Sale");
        series1.hovered().markers().enabled(true);
        series1.hovered().markers()
                .type(MarkerType.DIAMOND)
                .size(4d);
        series1.markers().zIndex(100d);


        series1.normal().fill(color);
        series1.normal().fallingStroke("#444F8F");
        series1.normal().risingStroke("#6ECDDE");


        Area series2 = areaChart.area(series2Data);
        series2.name("Sale");
        series2.hovered().markers().enabled(true);
        series2.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series2.markers().zIndex(100d);

        series2.normal().fill(color2);
        series2.normal().fallingStroke("#9F3A28");
        series2.normal().risingStroke("#C18C53");

        areaChart.xAxis(0).title(false);
        //areaChart.yAxis(0).title("Revenue (in Billons USD)");

        areaChart.interactivity().hoverMode(HoverMode.BY_X);
        areaChart.tooltip()
                .valuePrefix("")
                .valuePostfix(" PKR.")
                .displayMode(TooltipDisplayMode.UNION);






        /*series1.normal().hatchFill("forward-diagonal", "#6ECDDE", 1, 10);
        series1.normal().stroke("#6ECDDE");
        series1.hovered().fill("#ff6666 0.1");
        series1.hovered().hatchFill("forward-diagonal", "#6ECDDE", 1, 10);
        series1.hovered().stroke("#ff6666 2");
        series1.selected().fill("#ff6666", 0.8);
        series1.selected().hatchFill("forward-diagonal", "#6ECDDE", 1, 10);
        series1.selected().stroke("#ff6666 4");*/
        /*series1.normal().fallingFill("#00cc99", 0.3);
        series1.normal().fallingStroke("#00cc99", 1, "10 5", "round", "null");
        series1.hovered().fallingFill("#00cc99", 0.1);
        series1.hovered().fallingStroke("#00cc99", 2, "10 5", "round", "null");
        series1.selected().fallingFill("#00cc99", 0.5);
        series1.selected().fallingStroke("#00cc99", 4, "10 5", "round", "null");

        series1.normal().risingFill("#0066cc", 0.3);
        series1.normal().risingStroke("#0066cc");
        series1.hovered().risingFill("#0066cc", 0.1);
        series1.hovered().risingStroke("#0066cc 2");
        series1.selected().risingFill("#0066cc", 0.5);
        series1.selected().risingStroke("#0066cc 4");
*/

        anyChartView.setBackgroundColor("#0A1720");
        areaChart.background().fill("#0A1720");
        anyChartView.setChart(areaChart);

        sharedPreferences = getActivity().getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(areaChart);
        editor.putString("list_obj_brand",json);
        editor.commit();

    }
    private class CustomDataEntry extends ValueDataEntry {
        CustomDataEntry(String x, Number value,Number value2) {
            super(x, value);
            setValue("value2", value2);
            //setValue("value3", value3);

        }
    }
    public void draw_bar_graph_brand(List<DataEntry> seriesData,Cartesian barChart)
    {
        String[] color = new String[]{"#444F8F", "#6ECDDE"};
        String[] color2 = new String[]{"#9F3A28","#C18C53"};
        AnyChartView anyChartView = getView().findViewById(R.id.Area_graph_brand);
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

        Bar series1 = barChart.bar(series1Data);
        series1.normal().fill(color);
        /*series1.normal().fill("#5cd65c");*/

        series1.normal().stroke(null);
        series1.normal().labels().enabled(true);
        series1.tooltip()
                .position("right")
                .anchor(String.valueOf(Anchor.RIGHT_CENTER));

        Bar series2 = barChart.bar(series2Data);
        series2.normal().fill(color2);
        series2.normal().stroke(null);
        //series2.name("Males");
        series2.tooltip()
                .position("left")
                .anchor(String.valueOf(Anchor.RIGHT_CENTER));
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
        editor.putString("list_obj_brand",json);
        editor.commit();
    }
}
