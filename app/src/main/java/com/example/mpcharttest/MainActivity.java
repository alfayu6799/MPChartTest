package com.example.mpcharttest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private CombinedChart combinedChart;

    private CycleRecord cycleRecord;
    private List<CycleRecord.SuccessBean> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();

    }

    private void initData() {
        String myJsonStr = loadJSONFromAsset("menstruation_03.json");
        cycleRecord = CycleRecord.newInstance(myJsonStr);
        dataList = cycleRecord.getSuccess();

        MPAChartManager mpaChartManager = new MPAChartManager(this,combinedChart);
        mpaChartManager.showCombinedChart(dataList);
    }

    private void initView() {
        combinedChart = findViewById(R.id.chart);
    }

//    //折線圖
//    private LineData generateLineData(List<CycleRecord.SuccessBean> dataList){
//        LineData lineData = new LineData();
//
//        ArrayList<String> label = new ArrayList<>(); //日期
//
//        ArrayList<Entry> entries = new ArrayList<>();
//
//        String myJsonStr = loadJSONFromAsset("menstruation_03.json");
//        cycleRecord = CycleRecord.newInstance(myJsonStr);
//
//        this.dataList = cycleRecord.getSuccess();
//
//        for (int i = 0; i < this.dataList.size(); i++){
//
//            CycleRecord.SuccessBean data = this.dataList.get(i);
//
//            String[] str = data.getTestDate().split("-");
//            String testDay = str[2];
//
//            if (data.getTemperature() > 0 ){
//                entries.add(new Entry(i, (float) data.getTemperature()));
//            }
//
//            label.add(testDay);
//        }
//
//        xAxis.setValueFormatter(new IndexAxisValueFormatter(label));
//        xAxis.setLabelCount(label.size());
//        xAxis.setLabelCount(10,true);
//
//        LineDataSet set = new LineDataSet(entries, "");
//        set.setAxisDependency(YAxis.AxisDependency.LEFT);
//        set.setColor(Color.BLACK);
//        set.setLineWidth(0.9f);
//        set.setCircleColor(Color.BLUE);
//        set.setCircleRadius(2f);
//        set.setMode(LineDataSet.Mode.LINEAR);
//        set.setDrawValues(false); //是否顯示圓點的數值
//        lineData.addDataSet(set);
//
//        return lineData;
//    }
//
//    //長條圖
//    private BarData generateBarData(List<CycleRecord.SuccessBean> dataList){
//        BarData barData = new BarData();
//
//        List<BarEntry> entries1 = new ArrayList<BarEntry>();
//        List<BarEntry> entries2 = new ArrayList<BarEntry>();
//
//        String myJsonStr = loadJSONFromAsset("menstruation.json");
//        cycleRecord = CycleRecord.newInstance(myJsonStr);
//
//        this.dataList = cycleRecord.getSuccess();
//
//        for (int i = 0; i < this.dataList.size(); i++) {
//
//            CycleRecord.SuccessBean data = this.dataList.get(i);
//            String[] str = data.getTestDate().split("-");
//            String testDay = str[2];
//
//            if (data.getCycleStatus().contains(4)) {
//                data.setTemperature(40);
//                entries1.add(new BarEntry(i, (float) data.getTemperature()));
//            } else if (data.getCycleStatus().contains(6)) {
//                data.setTemperature(40);
//                entries2.add(new BarEntry(i, (float) data.getTemperature()));
//            }
//
//        }
//
//        BarDataSet barDataSet = new BarDataSet(entries1, "經期"); // add entries to dataset
//        barDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
//
//        BarDataSet barDataSet2 = new BarDataSet(entries2, "排卵期"); // add entries to dataset
//        barDataSet2.setAxisDependency(YAxis.AxisDependency.LEFT);
//
//        //设置数据显示颜色：柱子颜色
//        barDataSet.setColor(Color.rgb(225,63,174));
//        barDataSet2.setColor(Color.rgb(225,186,63));
//
//        //不顯示數據點數值
//        barDataSet.setDrawValues(false);
//        barDataSet2.setDrawValues(false);
//
//        barData.addDataSet(barDataSet);
//        barData.addDataSet(barDataSet2);
//
//        barData.setBarWidth(1.2f);
//
//        return barData;
//    }

    //讀取local json file
    public String loadJSONFromAsset(String fileName)
    {
        String json;
        try
        {
            InputStream is = getApplicationContext().getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex)
        {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}