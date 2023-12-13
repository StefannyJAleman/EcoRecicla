package com.example.reto2;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.example.retoIntermedio.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class recoestadisticas extends BaseActivityR implements RecoDataListener {

    private List<String> xValues = Arrays.asList("Aceite Industrial", "Aceite de Cocina", "Llantas", "Bombillas", "Pilas y Baterias", "Escombros", "Desechos Textiles");
    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;

    ArrayList<BarEntry> barEntries;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recoestadisticas);

        RecoCalcular recoCalcular = new RecoCalcular();
        recoCalcular.setRecoDataListener(this);

        barChart = findViewById(R.id.barchart);
        barChart.getAxisRight().setDrawLabels(false);

        setupBottomNavigation();


        barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, 45f));
        barEntries.add(new BarEntry(1, 80f));
        barEntries.add(new BarEntry(2, 65f));
        barEntries.add(new BarEntry(3, 38f));
        barEntries.add(new BarEntry(4, 90f));
        barEntries.add(new BarEntry(5, 55f));
        barEntries.add(new BarEntry(6, 36f));


        barDataSet = new BarDataSet(barEntries, "Label");
        barDataSet.setColors(ColorTemplate.createColors(ColorTemplate.MATERIAL_COLORS)); // Limpiar colores anteriores
        barDataSet.setColors(
                Color.parseColor("#FF9805"),
                Color.parseColor("#FBBC05"),
                Color.parseColor("#B1D1B4"),
                Color.parseColor("#B1E498"),
                Color.parseColor("#F63471"),
                Color.parseColor("#7DC144"),
                Color.parseColor("#30BFCE")
        );


        barData = new BarData(barDataSet);
        barChart.setData(barData);


// Personalizar propiedades del eje Y (izquierdo)
        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setAxisMaximum(100f); // Cambia el valor máximo según tus necesidades
        yAxis.setAxisMinimum(0f); // Cambia el valor mínimo según tus necesidades
        yAxis.setTextSize(10f); // Cambia el tamaño del texto del eje Y
        yAxis.setTextColor(Color.parseColor("#0F4002")); // Cambia el color del texto del eje Y
        yAxis.setGridColor(Color.parseColor("#F4F3DD")); // Cambia el color de las líneas de la cuadrícula
   // Cambia el color de la línea del eje Y
        yAxis.setAxisLineWidth(2f);
        yAxis.setLabelCount(10);


        XAxis xAxis = barChart.getXAxis();
        xAxis.setTextSize(6f);
        xAxis.setTextColor(Color.parseColor("#0F4002"));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(3f); // Espaciado entre las etiquetas del eje X
        xAxis.setGranularityEnabled(true);



        barData = new BarData(barDataSet);
        barChart.setData(barData);


        BarDataSet dataSet = new BarDataSet(barEntries, "Subjects"); // Replace "Subjects" with your desired label
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        BarData barData = new BarData(dataSet);
        barChart.setData(barData);

        barChart.getDescription().setEnabled(false);
        barChart.invalidate();

        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xValues));
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setGranularity(1f);
        barChart.getXAxis().setGranularityEnabled(true);

    }
    @Override
    public void onRecoDataAdded(String material, float valor) {
        Log.d("recoestadisticas", "Datos recibidos: Material - " + material + ", Valor - " + valor);
        agregarDatosAlGrafico(material, valor);
    }

    private void agregarDatosAlGrafico(String material, float valor) {

        BarEntry newEntry = new BarEntry(barEntries.size(), valor);
        barEntries.add(newEntry);


        barDataSet.notifyDataSetChanged();
        barData.notifyDataChanged();
        barChart.notifyDataSetChanged();
        barChart.invalidate();
    }
}




