package com.example.graphs;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Area;
import com.anychart.core.ui.Crosshair;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.HoverMode;
import com.anychart.enums.MarkerType;
import com.anychart.enums.ScaleStackMode;
import com.anychart.enums.TooltipDisplayMode;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.ArrayList;
import java.util.List;

public class Area_Chart extends Fragment {

    TextView tv,tv2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_graph_area, container, false);
    }

    @TargetApi(Build.VERSION_CODES.O)
    //@RequiresApi(api = Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Total Sales Today");

        AnyChartView anyChartView = getView().findViewById(R.id.Area_Graph);
        tv = getView().findViewById(R.id.txt_income_Circular_bar);
        tv2 = getView().findViewById(R.id.txt_profit_Circular_bar);
        CircularProgressBar   profitprogressbar = (CircularProgressBar) getView().findViewById(R.id.Area_income_Graph_progress);
        CircularProgressBar incomeprogress = (CircularProgressBar) getView().findViewById(R.id.Area_profit_Graph_progress);
        //progressBar = (ProgressBar) getView().findViewById(R.id.Area_Graph_progress);
        Cartesian areaChart = AnyChart.area();
        areaChart.animation(true);
        Crosshair crosshair = areaChart.crosshair();
        crosshair.enabled(true);
        // TODO yStroke xStroke in crosshair
        //crosshair.yStroke((Stroke) null, null, null, (String) null, (String) null)
           //     .xStroke("#fff", 1d, null, (String) null, (String) null)
             //   .zIndex(39d);
        crosshair.yLabel(0).enabled(true);
        areaChart.yScale().stackMode(ScaleStackMode.VALUE);
        //areaChart.title("Unaudited Apple Inc. Revenue by Operating Segments");
        List<DataEntry> seriesData = new ArrayList<>();
        seriesData.add(new CustomDataEntry("Feb", 17.982, 10.941, 9.835, 4.047, 2.841));
        seriesData.add(new CustomDataEntry("Mar", 17.574, 8.659, 6.230, 2.627, 2.242));
        seriesData.add(new CustomDataEntry("Apr", 19.75, 10.35, 6.292, 3.595, 2.136));
        seriesData.add(new CustomDataEntry("May", 30.6, 17.2, 16.1, 5.4, 5.2));
        seriesData.add(new CustomDataEntry("Jun", 21.316, 12.204, 16.823, 3.457, 4.210));
        /*seriesData.add(new CustomDataEntry("July", 20.209, 10.342, 13.23, 2.872, 2.959));
        seriesData.add(new CustomDataEntry("August", 21.773, 10.577, 12.518, 3.929, 2.704));
        seriesData.add(new CustomDataEntry("April", 29.3, 17.9, 18.4, 4.8, 5.4));*/
        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping series1Data = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2Data = set.mapAs("{ x: 'x', value: 'value2' }");
        //Mapping series3Data = set.mapAs("{ x: 'x', value: 'value3' }");
        //Mapping series4Data = set.mapAs("{ x: 'x', value: 'value4' }");
        //Mapping series5Data = set.mapAs("{ x: 'x', value: 'value5' }");

        Area series1 = areaChart.area(series1Data);
        series1.name("Income");
        series1.color("#DB7093");
        series1.stroke("3 #FF0000");
        int[] color1 = {255,0,0};
        int[] color2 = {0,0,255};
        //series1.color.blend(color1, color2, 0.2);
        series1.hovered().stroke("3 #FF0000");
        series1.hovered().markers().enabled(true);
        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d)
                .stroke("1.5 #FF0000");
        series1.markers().zIndex(100d);

        Area series2 = areaChart.area(series2Data);
        series2.name("Profit");
        series2.color("#87CEEB");
        series2.stroke("3 #00BFFF");
        series2.hovered().stroke("3 #00BFFF");
        series2.hovered().markers().enabled(true);
        series2.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d)
                .stroke("1.5 #00BFFF");
        series2.markers().zIndex(100d);

        /*Area series3 = areaChart.area(series3Data);
        series3.name("Greater China");
        series3.stroke("3 #fff");
        series3.hovered().stroke("3 #fff");
        series3.hovered().markers().enabled(true);
        series3.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d)
                .stroke("1.5 #fff");
        series3.markers().zIndex(100d);*/

       /* Area series4 = areaChart.area(series4Data);
        series4.name("Japan");
        series4.stroke("3 #fff");
        series4.hovered().stroke("3 #fff");
        series4.hovered().markers().enabled(true);
        series4.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d)
                .stroke("1.5 #fff");
        series4.markers().zIndex(100d);*/

       /* Area series5 = areaChart.area(series5Data);
        series5.name("Rest of Asia Pacific");
        series5.stroke("3 #fff");
        series5.hovered().stroke("3 #fff");
        series5.hovered().markers().enabled(true);
        series5.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d)
                .stroke("1.5 #fff");
        series5.markers().zIndex(100d);*/

        areaChart.legend().enabled(true);
        areaChart.legend().fontSize(20d);
        areaChart.legend().padding(0d, 0d, 20d, 0d);

       // areaChart.xAxis(0).title(false);
        //areaChart.yAxis(0).title("Revenue (in Billons USD)");

        areaChart.interactivity().hoverMode(HoverMode.BY_X);
        areaChart.tooltip()
                .valuePrefix("$")
                .valuePostfix(" bln.")
                .displayMode(TooltipDisplayMode.UNION);

        anyChartView.setBackgroundColor("#201C2B");
        areaChart.background().fill("#201C2B");

        profitprogressbar.setProgress(30);
        tv2.setText((int) profitprogressbar.getProgress()+"%");
        tv2.setTextColor(Color.WHITE);
        tv2.setTextSize(18);
        incomeprogress.setProgress(70);
        tv.setText((int) incomeprogress.getProgress()+"%");
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(18);

        anyChartView.setChart(areaChart);
        //anyChartView.setChart(areaChart);

       // areaChart..darken("#FF0000", 0.2);
       // areaChart.normal().fill("#f1f1f1f1");
        //anyChartView.setProgressBar(getView().findViewById(R.id.Area_Progress_Bar));

          //  progressBar.setProgress(70,false);
        /*circularProgressBar.setColor(Color.BLUE);/*
        circularProgressBar.setBackgroundColor(ContextCompat.getColor(this, R.color.backgroundProgressBarColor));
        circularProgressBar.setProgressBarWidth(getResources().getDimension(R.dimen.progressBarWidth));
        circularProgressBar.setBackgroundProgressBarWidth(getResources().getDimension(R.dimen.backgroundProgressBarWidth));
        int animationDuration = 2500; // 2500ms = 2,5s
        circularProgressBar.setProgressWithAnimation(65, animationDuration);*/


    }
    private class CustomDataEntry extends ValueDataEntry {
        CustomDataEntry(String x, Number value, Number value2, Number value3, Number value4, Number value5) {
            super(x, value);
            setValue("value2", value2);
            setValue("value3", value3);
            setValue("value4", value4);
            setValue("value5", value5);
        }
    }
}