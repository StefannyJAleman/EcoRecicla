package com.example.reto2;

import androidx.annotation.NonNull;
import java.text.DecimalFormat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.retoIntermedio.databinding.ActivityPantallapcategoriasBinding;
import com.google.android.material.textfield.TextInputLayout;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


import com.example.retoIntermedio.R;

public class RecoCalcular extends BaseActivityR {

    ImageView imagenRetroceder;
    TextView tvFecha;
    TextInputLayout etxIngreseLt, etxValorlt;
    Button btnAgregar;

    Spinner materialSpinner;
    TableLayout tabla;

    private RecoDataListener recoDataListener;
    private double valorTotal = 0.0;

    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;

    private String obtenerUnidad(String material) {
        if ("Aceite Industrial".equals(material)) {
            return "lt";
        } else if ("Aceite de Cocina".equals(material)) {
            return "lt";
        } else if ("Llantas".equals(material) || "Bombillas".equals(material) || "Pilas y Baterias".equals(material) || "Escombros".equals(material) || "Desechos Textiles".equals(material)) {
            return "kg";
        } else {
            return "";
        }
    }

    private String unidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reco_calcular);

        setupBottomNavigation();

        Log.d("RecoCalcular", "Click en el icono de eliminar");

        tabla = findViewById(R.id.talTabla);



        imagenRetroceder = findViewById(R.id.imvAtras);
        materialSpinner = findViewById(R.id.choose_item);
        tvFecha = findViewById(R.id.tvFecha);
        etxIngreseLt = findViewById(R.id.etxIngreseLt);
        btnAgregar = findViewById(R.id.btnCalcular);
        etxValorlt = findViewById(R.id.etxValorlt);
        tabla = findViewById(R.id.talTabla);

        int color = Color.parseColor("#FDBC55");
        tabla.setBackgroundColor(color);


    TableRow headerRow = new TableRow(this);
        headerRow.setLayoutParams(new TableLayout.LayoutParams(
    TableLayout.LayoutParams.MATCH_PARENT,
    TableLayout.LayoutParams.WRAP_CONTENT));

    String[] headers = {"Dia", "Mes", "Material", "Cantidad ", "Unidad", "Valor total "};

        for (String header : headers) {
        headerRow.addView(createTextView(header, true));
    }

        tabla.addView(headerRow);

        // Recorrer las filas y establecer clics
        for (int i = 1; i < tabla.getChildCount(); i++) {
            View rowView = tabla.getChildAt(i);
            if (rowView instanceof TableRow) {
                TableRow row = (TableRow) rowView;
                setTableRowClickListener(row);
            }
        }

    // SPINNER
    String[] opcionesMateriales = {"Seleccionar material", "Aceite Idustrial", "Aceite de Cocina", "Llantas", "Bombillas","Pilas y Baterias", "Escombros", "Desechos Textiles" };
    // FECHA
    String fechaActual = obtenerFechaActual();
        tvFecha.setText("Fecha: " + fechaActual);

    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcionesMateriales);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        materialSpinner.setAdapter(adapter);

    // SELECCIÓN DEL SPINNER
        materialSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            if (position == 0) {
                Toast.makeText(RecoCalcular.this, "Por favor, selecciona un material válido", Toast.LENGTH_SHORT).show();
            } else {
                String materialSeleccionado = opcionesMateriales[position];
                Log.d("RecoCalcular", "Material seleccionado: " + materialSeleccionado);  // Agregar esta línea
                unidad = obtenerUnidad(materialSeleccionado);

                Toast.makeText(RecoCalcular.this, "Material seleccionado: " + materialSeleccionado, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parentView) {

        }
    });


    loadDataFromFile();

        imagenRetroceder.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent retroceder = new Intent(getApplicationContext(), ActivityPantallapcategoriasBinding.class);
            startActivity(retroceder);
        }
    });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String material = materialSpinner.getSelectedItem().toString();
                String cantidadlt = etxIngreseLt.getEditText().getText().toString();
                String valorlt = etxValorlt.getEditText().getText().toString();

                String dia = obtenerDiaActual();
                String mes = obtenerMesActual();

                if (cantidadlt.isEmpty() || valorlt.isEmpty() || material.equals(opcionesMateriales[0])) {
                    Toast.makeText(RecoCalcular.this, "Por favor, ingresa todos los datos.", Toast.LENGTH_LONG).show();
                } else {
                    String unidad = obtenerUnidad(material);  // Obtener la unidad correspondiente

                    if (checkStoragePermission()) {
                        double valor = 0.0;
                        try {
                            valor = Double.parseDouble(valorlt);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();

                        }

                        saveDataToFile(dia, mes, material, cantidadlt, unidad, String.valueOf(valor));
                        if (recoDataListener != null) {
                            recoDataListener.onRecoDataAdded(material, (float) valor);
                        }
                    } else {
                        requestStoragePermission();
                    }

                    addRow(dia, mes, material, cantidadlt, unidad, valorlt);

                    etxIngreseLt.getEditText().getText().clear();
                    etxValorlt.getEditText().getText().clear();
                }
            }
        });



        for (int i = 1; i < tabla.getChildCount(); i++) {
        View rowView = tabla.getChildAt(i);
        if (rowView instanceof TableRow) {
            TableRow row = (TableRow) rowView;
            setTableRowClickListener(row);
        }
    }
}

    public void setRecoDataListener(RecoDataListener listener) {
        this.recoDataListener = listener;
    }

    private void loadDataFromFile() {
        File materialsFile = new File(getFilesDir(), "regismaterials.txt");

        if (materialsFile.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(materialsFile));
                String line;
                double total = 0.0;

                while ((line = reader.readLine()) != null) {
                    String[] datos = line.split(",");
                    addRow(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5]);


                    total += Double.parseDouble(datos[5]);
                }

                reader.close();


                valorTotal = 0.0;


                valorTotal += total;


                TextView tvValorTotal = findViewById(R.id.valorTGanado);
                tvValorTotal.setText("$ " + String.valueOf(valorTotal));

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error al cargar los datos", Toast.LENGTH_SHORT).show();
            }
        } else {

            valorTotal = 0.0;


            TextView tvValorTotal = findViewById(R.id.valorTGanado);
            tvValorTotal.setText("$ " + String.valueOf(valorTotal));
        }
    }
    private void agregarDatosAlGrafico(String material, float valor) {
        // Lógica para agregar datos al gráfico
        if (recoDataListener != null) {
            recoDataListener.onRecoDataAdded(material, valor);
        }
    }
    //archivo txt
    private boolean checkStoragePermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
    }

    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
    }



    private void saveDataToFile(String dia, String mes, String material, String cantidadlt, String unidad, String valorlt) {
        File regismaterialsFile = new File(getFilesDir(), "regismaterials.txt");

        if (regismaterialsFile.exists()) {
            String message = "El archivo existe en la ruta: " + regismaterialsFile.getAbsolutePath();
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "El archivo no existe", Toast.LENGTH_SHORT).show();
        }

        try {
            FileWriter writer = new FileWriter(regismaterialsFile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(
                    dia + "," +
                            mes + "," +
                            material + "," +
                            cantidadlt + "," +
                            unidad + "," +
                            valorlt
            );
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al guardar los datos", Toast.LENGTH_SHORT).show();
        }
    }

    private void addRow(String dia, String mes, String material, String cantidadlt, String unidad, String valorlt) {
        if (tabla.getChildCount() > 0 && tabla.getChildAt(0) instanceof TableRow) {
            // Si ya hay al menos una fila en la tabla, elimina la primera fila (encabezado)
            tabla.removeViewAt(0);
        }

        TableRow newRow = new TableRow(this);
        newRow.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        newRow.addView(createTextView(dia, false));
        newRow.addView(createTextView(mes, false));
        newRow.addView(createTextView(material, false));
        newRow.addView(createTextView(cantidadlt, false));
        newRow.addView(createTextView(unidad, false));  // Asegurar que la unidad se muestre en la tabla
        newRow.addView(createTextView(valorlt, false));

        ImageView deleteIcon = createDeleteIcon();
        newRow.addView(deleteIcon);

        setTableRowClickListener(newRow);

        tabla.addView(newRow);

        double valor = Double.parseDouble(valorlt);

        valorTotal += valor;

        valorTotal = Math.round(valorTotal * 100.0) / 100.0;

        // Formatear el valorTotal con dos decimales
        DecimalFormat df = new DecimalFormat("#.##");
        String formattedValorTotal = df.format(valorTotal);

        TextView tvValorTotal = findViewById(R.id.valorTGanado);
        tvValorTotal.setText("$ " + formattedValorTotal);
    }


    private ImageView createDeleteIcon() {
        ImageView deleteIcon = new ImageView(this);
        deleteIcon.setImageResource(R.drawable.ic_delete);
        deleteIcon.setClickable(true);

        deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    TableRow parentRow = (TableRow) v.getParent();
                    if (parentRow != null) {
                        tabla.removeView(parentRow);
                        Toast.makeText(RecoCalcular.this, "Fila eliminada", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.e("RecoCalcular", "ParentRow is null");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("RecoCalcular", "Error al eliminar fila: " + e.getMessage());
                }
            }
        });

        return deleteIcon;
    }


    private void setTableRowClickListener(TableRow row) {
        for (int i = 0; i < row.getChildCount(); i++) {
            View childView = row.getChildAt(i);
            childView.setClickable(true);
            childView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if (v instanceof ImageView) {
                            TableRow parentRow = (TableRow) v.getParent();


                            int valorTotalIndex = 5;
                            if (parentRow.getChildCount() > valorTotalIndex) {
                                TextView valorTotalTextView = (TextView) parentRow.getChildAt(valorTotalIndex);
                                String valorTotalText = valorTotalTextView.getText().toString();
                                double valorEliminado = Double.parseDouble(valorTotalText);


                                valorTotal -= valorEliminado;
                                valorTotal = Math.round(valorTotal * 100.0) / 100.0;
                            }


                            tabla.removeView(parentRow);
                            removeFromFile(parentRow);

                            Toast.makeText(RecoCalcular.this, "Fila eliminada", Toast.LENGTH_SHORT).show();


                            TextView tvValorTotal = findViewById(R.id.valorTGanado);
                            tvValorTotal.setText("$ " + String.valueOf(valorTotal));


                            if (tabla.getChildCount() == 1) {

                                valorTotal = 0.0;

                                // Actualizar el TextView del valor total
                                tvValorTotal.setText("$ " + String.valueOf(valorTotal));
                            }
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        Toast.makeText(RecoCalcular.this, "Error al eliminar la fila: Valor no válido", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(RecoCalcular.this, "Error al eliminar la fila", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    private void removeFromFile(TableRow row) {
        File regismaterialsFile = new File(getFilesDir(), "regismaterials.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(regismaterialsFile));
            List<String> lines = new ArrayList<>();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(",");
                String rowDia = ((TextView) row.getChildAt(0)).getText().toString();
                String rowMes = ((TextView) row.getChildAt(1)).getText().toString();
                String rowMaterial = ((TextView) row.getChildAt(2)).getText().toString();
                String rowCantidad = ((TextView) row.getChildAt(3)).getText().toString();
                String rowUnidad = ((TextView) row.getChildAt(4)).getText().toString();

                if (!datos[0].equals(rowDia)
                        || !datos[1].equals(rowMes)
                        || !datos[2].equals(rowMaterial)
                        || !datos[3].equals(rowCantidad)
                        || !datos[4].equals(rowUnidad)) {
                    lines.add(line);
                }
            }

            reader.close();

            FileWriter writer = new FileWriter(regismaterialsFile, false);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            for (String newLine : lines) {
                bufferedWriter.write(newLine);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();

            // Verificar si no hay más filas en el archivo
            if (lines.isEmpty()) {
                // Si no hay más filas, establecer valorTotal en cero
                valorTotal = 0.0;

                // Actualizar el TextView del valor total
                TextView tvValorTotal = findViewById(R.id.valorTGanado);
                tvValorTotal.setText("$ " + String.valueOf(valorTotal));
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al actualizar el archivo", Toast.LENGTH_SHORT).show();
        }
    }



    private TextView createTextView(String text, boolean isHeader) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(8, 8, 8, 8);

        if (isHeader) {
            textView.setBackgroundColor(Color.TRANSPARENT);
            textView.setTextColor(Color.BLACK);
        } else {

            int color = Color.parseColor("#F9F8EE");
            textView.setBackgroundColor(color);
        }

        return textView;
    }

    private String obtenerFechaActual() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Bogota"));
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return formatoFecha.format(calendar.getTime());
    }

    private String obtenerDiaActual() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Bogota"));
        SimpleDateFormat formatoDia = new SimpleDateFormat("dd", Locale.getDefault());
        return formatoDia.format(calendar.getTime());
    }

    private String obtenerMesActual() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Bogota"));
        SimpleDateFormat formatoMes = new SimpleDateFormat("MM", Locale.getDefault());
        return formatoMes.format(calendar.getTime());
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {

                Toast.makeText(this, "Permiso de escritura en almacenamiento externo denegado.", Toast.LENGTH_SHORT).show();



            }
        }
    }
}

