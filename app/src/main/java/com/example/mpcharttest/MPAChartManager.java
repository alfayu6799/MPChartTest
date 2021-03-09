package com.example.mpcharttest;

import android.content.Context;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.LineData;

import java.util.List;

public class MPAChartManager {

    private Context context;

    private CombinedChart combinedChart;
    private XAxis xAxis;                //X轴
    private YAxis leftYAxis;            //左侧Y轴
    private YAxis rightYAxis;           //右侧Y轴 自定义XY轴值

    private double degree;

    //建構子
    public MPAChartManager(Context context, CombinedChart combinedChart) {
        this.context = context;
        this.combinedChart = combinedChart;
        leftYAxis = combinedChart.getAxisLeft();
        rightYAxis = combinedChart.getAxisRight();
        xAxis = combinedChart.getXAxis();

        initChart(combinedChart);
    }

    private void initChart(CombinedChart combinedChart) {
        //顯示格線
        combinedChart.setDrawGridBackground(true);
        //不顯示邊線
        combinedChart.setDrawBorders(false);
        //雙擊不進行縮放
        combinedChart.setDoubleTapToZoomEnabled(false);
        //不用描述
        combinedChart.getDescription().setEnabled(false);
        //不用圖例
        combinedChart.getLegend().setEnabled(false);

        //是否繪製X軸網格線
        xAxis.setDrawGridLines(true);
        xAxis.setGridLineWidth(1.5f);

        //是否繪製Y軸網格線
        leftYAxis.setDrawGridLines(true);
        leftYAxis.setGridLineWidth(1.5f);

        //Y軸右側隱藏
        rightYAxis.setEnabled(false);

        //Y軸右側網格線不顯示
        rightYAxis.setDrawGridLines(false);

        //X軸設置顯示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        //最小刻度27
        leftYAxis.setAxisMinimum(25f);

        //最大刻度40
        leftYAxis.setAxisMaximum(40f);

        //X軸最小間距
        xAxis.setGranularity(1f);

        //繪製X軸線
        xAxis.setDrawAxisLine(false);

//        CombinedData data = new CombinedData();
//
//        data.setData(generateLineData());
//        data.setData(generateBarData());
//
//        combinedChart.setData(data);
//        combinedChart.invalidate();

    }

    private BarData generateBarData() {
        return null;
    }

    private LineData generateLineData() {
        return null;
    }

    public void showCombinedChart(List<CycleRecord.SuccessBean> dataList) {
        CombinedData combinedData = new CombinedData();

        for (int i = 0 ; i < dataList.size(); i++){
            degree = dataList.get(i).getTemperature(); //體溫

        }

    }
}
