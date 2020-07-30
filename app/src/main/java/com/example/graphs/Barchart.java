package com.example.graphs;

import android.R.layout;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.axes.Linear;
import com.anychart.core.cartesian.series.Bar;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.HoverMode;
import com.anychart.enums.LabelsOverlapMode;
import com.anychart.enums.Orientation;
import com.anychart.enums.ScaleStackMode;
import com.anychart.enums.TooltipDisplayMode;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Anchor;
import com.example.graphs.Classes.Firebase_Class;
import com.google.firebase.database.DatabaseReference;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class Barchart extends Fragment implements AdapterView.OnItemSelectedListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_graph_bar, container, false);
    }
    String[] arr = {"Last 15 Days","Last 30 Days","Last 6 months","Last year"};
    Spinner spinner;
    TextView tv_risk,tv_dev;
    SharedPreferences sharedPreferences;
    AnyChartView anyChartView;
    DatabaseReference databaseReference;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Sales by Date");
        tv_risk = getView().findViewById(R.id.txt_view_risk_price );
        tv_dev = getView().findViewById(R.id.txt_view_dev_price);
        spinner = (Spinner) getView().findViewById(R.id.spinner_time_days);
        anyChartView = getView().findViewById(R.id.Bar_Graph);
        Cartesian barChart = AnyChart.bar();
        List<DataEntry> seriesData = new ArrayList<>();
        seriesData.add(new CustomDataEntry("M", 7624, -229));
        seriesData.add(new CustomDataEntry("A", 10987, -932));
        seriesData.add(new CustomDataEntry("M", 5376, -5221));
        seriesData.add(new CustomDataEntry("J", 8814, -256));
        seriesData.add(new CustomDataEntry("J", 8998, -308));
        seriesData.add(new CustomDataEntry("A", 9321, -432));
        seriesData.add(new CustomDataEntry("S", 8342, -701));
        seriesData.add(new CustomDataEntry("S", 6998, -908));
        seriesData.add(new CustomDataEntry("O", 9261, -712));
        seriesData.add(new CustomDataEntry("N", 6998, -908));
        seriesData.add(new CustomDataEntry("D", 9261, -712));
        DrawBarGraph(seriesData,barChart);

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.spinner_item,arr);
        adapter.setDropDownViewResource(layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelected(false);
        spinner.setSelection(0,false);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);


          // must
        //must


    }

    @Override
    public void  onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        //Toast.makeText(getActivity(), "Nothing."+obj.get(0), Toast.LENGTH_SHORT).show();
        List<DataEntry> seriesData1 = new ArrayList<>();
        Gson gson = new Gson();
        String json = sharedPreferences.getString("list_obj", "");
        Cartesian obj = gson.fromJson(json, Cartesian.class);
        obj.dispose();

        anyChartView.clear();
        Cartesian barChart =AnyChart.bar();
        if (position ==0)
        {
            Firebase_Class firebase_class = new Firebase_Class();
            firebase_class.send_data("Hello World");

            //Toast.makeText(getActivity(), ""+obj.get(1), Toast.LENGTH_SHORT).show();
            seriesData1.add(new CustomDataEntry("M", 100, -129));
            seriesData1.add(new CustomDataEntry("A", 1987, -832));
            seriesData1.add(new CustomDataEntry("M", 50, -321));
            seriesData1.add(new CustomDataEntry("J", 814, -156));
            seriesData1.add(new CustomDataEntry("J", 898, -208));
            seriesData1.add(new CustomDataEntry("A", 921, -332));
            seriesData1.add(new CustomDataEntry("S", 842, -501));
            seriesData1.add(new CustomDataEntry("S", 698, -708));
            seriesData1.add(new CustomDataEntry("O", 961, -512));
            seriesData1.add(new CustomDataEntry("N", 698, -708));
            seriesData1.add(new CustomDataEntry("D", 961, -212));
            Toast.makeText(getActivity(), "Selected Zero index", Toast.LENGTH_SHORT).show();

            //barChart.addSeries((Set) seriesData1);
            //anyChartView.setChart(barChart);
            DrawBarGraph(seriesData1,barChart);
            tv_dev.setText("$124,36254");
            tv_risk.setText("$75,232651");
        }
        else if (position == 1)
        {

            /*barChart.dispose();
            barChart = null;*/
            //Cartesian barChart =AnyChart.bar();
            /*Firebase_Class firebase_class = new Firebase_Class();
            firebase_class.send_data("Hello World");*/

            seriesData1.add(new CustomDataEntry("M", 1000, -129));
            seriesData1.add(new CustomDataEntry("A", 187, -232));
            seriesData1.add(new CustomDataEntry("M", 500, -421));
            seriesData1.add(new CustomDataEntry("J", 196, -156));
            seriesData1.add(new CustomDataEntry("J", 220, -208));
            seriesData1.add(new CustomDataEntry("A", 250, -132));
            seriesData1.add(new CustomDataEntry("S", 280, -501));
            seriesData1.add(new CustomDataEntry("S", 320, -608));
            seriesData1.add(new CustomDataEntry("O", 340, -212));
            seriesData1.add(new CustomDataEntry("N", 380, -1108));
            seriesData1.add(new CustomDataEntry("D", 400, -312));
            Toast.makeText(getActivity(), "Selected First index", Toast.LENGTH_SHORT).show();
            //barChart.addSeries((Set) seriesData1);
            //anyChartView.setChart(barChart);
            DrawBarGraph(seriesData1,barChart);
            tv_dev.setText("$9,36254");
            tv_risk.setText("$2,232651");



        }
        else if (position == 2)
        {

            /*barChart.dispose();
            barChart = null;*/
            //Cartesian barChart =AnyChart.bar();
            seriesData1.add(new CustomDataEntry("M", 900, -50));
            seriesData1.add(new CustomDataEntry("A", 800, -80));
            seriesData1.add(new CustomDataEntry("M", 700, -100));
            seriesData1.add(new CustomDataEntry("J", 600, -126));
            seriesData1.add(new CustomDataEntry("J", 500, -150));
            seriesData1.add(new CustomDataEntry("A", 400, -200));
            seriesData1.add(new CustomDataEntry("S", 300, -220));
            seriesData1.add(new CustomDataEntry("S", 200, -250));
            seriesData1.add(new CustomDataEntry("O", 100, -300));
            seriesData1.add(new CustomDataEntry("N", 50, -400));
            seriesData1.add(new CustomDataEntry("D", 30, -450));
            Toast.makeText(getActivity(), "Selected Second index", Toast.LENGTH_SHORT).show();
            DrawBarGraph(seriesData1,barChart);
            tv_dev.setText("$42,625114");
            tv_risk.setText("$36,251478");

        }
        else if (position == 3)
        {
            //barChart.dispose();
            seriesData1.add(new CustomDataEntry("M", 1900, -150));
            seriesData1.add(new CustomDataEntry("A", 400, -801));
            seriesData1.add(new CustomDataEntry("M", 10, -1100));
            seriesData1.add(new CustomDataEntry("J", 800, -1126));
            seriesData1.add(new CustomDataEntry("J", 250, -1150));
            seriesData1.add(new CustomDataEntry("A", 300, -1200));
            seriesData1.add(new CustomDataEntry("S", 100, -1220));
            seriesData1.add(new CustomDataEntry("S", 3500, -1250));
            seriesData1.add(new CustomDataEntry("O", 120, -1300));
            seriesData1.add(new CustomDataEntry("N", 20, -1400));
            seriesData1.add(new CustomDataEntry("D", 350, -1450));
            Toast.makeText(getActivity(), "Selected Three index", Toast.LENGTH_SHORT).show();
            DrawBarGraph(seriesData1,barChart);
            tv_dev.setText("$15,33625");
            tv_risk.setText("$6,62541");
        }

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void DrawBarGraph(List<DataEntry> seriesData, Cartesian barChart)
    {

       Toast.makeText(getActivity(), "Function Called", Toast.LENGTH_LONG).show();
        AnyChartView anyChartView = getView().findViewById(R.id.Bar_Graph);
        barChart.animation(true);
        barChart.padding(10d, 20d, 5d, 20d);
        barChart.yScale().stackMode(ScaleStackMode.VALUE);

        barChart.yAxis(0).labels().format(
                "function() {\n" +
                        "    return Math.abs(this.value).toLocaleString();\n" +
                        "  }");
        // barChart.yAxis(0d).title("Revenue in Dollars");
        barChart.xAxis(0d).overlapMode(LabelsOverlapMode.ALLOW_OVERLAP);
        Linear xAxis1 = barChart.xAxis(1d);
        xAxis1.enabled(true);
        xAxis1.orientation(Orientation.RIGHT);
        xAxis1.overlapMode(LabelsOverlapMode.ALLOW_OVERLAP);
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
                                "      return '<span style=\"color: #D9D9D9\">$</span>' + Math.abs(this.value).toLocaleString();\n" +
                                "    }");


        Set set = Set.instantiate();
        set.data(seriesData);
       // barChart.addSeries((Set) seriesData);
        Mapping series1Data = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2Data = set.mapAs("{ x: 'x', value: 'value2' }");

        Bar series1 = barChart.bar(series1Data);
        series1.color("#DB7093");
        series1.tooltip()
                .position("right")
                .anchor(String.valueOf(Anchor.LEFT_CENTER));
        Bar series2 = barChart.bar(series2Data);
        //series2.name("Males");
        series2.color("#87CEEB");
        series2.tooltip()
                .position("left")
                .anchor(String.valueOf(Anchor.RIGHT_CENTER));
/*

        barChart.legend().enabled(true);
        barChart.legend().inverted(true);
        barChart.legend().fontSize(13d);
        barChart.legend().padding(0d, 0d, 20d, 0d);
*/
        anyChartView.setBackgroundColor("#201C2B");
        barChart.background().fill("#201C2B");
        anyChartView.setChart(barChart);
       // barChart.dispose();
        //barChart = null;

        sharedPreferences = getActivity().getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(barChart);
        editor.putString("list_obj",json);
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
