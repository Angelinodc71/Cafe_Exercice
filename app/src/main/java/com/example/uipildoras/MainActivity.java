package com.example.uipildoras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    static int cantidad = 0;
    static double precioTotal = 0;
    static final double PRECIO_CAFE = 2;
    static final double PRECIO_NATA = 0.50;
    static final double PRECIO_CHOCO = 0.75;
    Button buttonMas;
    Button buttonMenos;
    Button buttonOrder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonMas.findViewById(R.id.btnMas);
        buttonMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aumentar(v);
            }
        });
        buttonMenos.findViewById(R.id.btnMenos);
        buttonMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disminuir(v);
            }
        });
        buttonOrder.findViewById(R.id.btnOrder);

        buttonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pedir(v);
            }
        });
    }

    /* Si la cantidad de cafes es menor a 10:
        - Aumenta en 1 la cantidad y la vista de la cantidad
        - Invoca el metodo que actualiza el precio Total
       Si no:
        - Invoca al metodo para lanzar alerta avisando de la cantidad maxima */
    public void aumentar(View view) {

        if (cantidad < 10) {

            cantidad++;
            TextView cantidadTv = findViewById(R.id.tvQuantity);
            cantidadTv.setText("" + cantidad);
            actualizaTotal(view);

        } else {
            lanzaToast("Cantidad máxima: 10");
        }
    }

    /* Si la cantidad de cafes es mayor a 1:
        - Disminuye en 1 la cantidad y la vista de la cantidad
        - Invoca el metodo que actualiza el precio Total
       Si no:
        - Invoca al metodo para lanzar alerta avisando de la cantidad minima */
    public void disminuir(View view) {

        if (cantidad > 1) {

            cantidad--;
            TextView cantidadTv = findViewById(R.id.tvQuantity);
            cantidadTv.setText("" + cantidad);
            actualizaTotal(view);

        } else {
            lanzaToast("Cantidad mínima: 1");
        }
    }

    /*  Recopila la información del pedido y añade
        un resumen en el TextView vacío de la interfaz. */
    public void pedir(View view) {

        EditText nombreET = findViewById(R.id.txtName);
        String nombre = nombreET.getText().toString();
        if( nombre.matches("")) nombre = "Cliente";

        String nata = "No";
        String choco = "No";
        CheckBox nataCB = findViewById(R.id.checkWhipped);
        CheckBox chocoCB = findViewById(R.id.checkChocolate);
        if(nataCB.isChecked()) nata = "Sí";
        if(chocoCB.isChecked()) choco = "Sí";

        String mensaje = "Resumen\n-------\nNombre: " + nombre + "\nNº de cafés: " + cantidad + "\nNata: " + nata + "\nChocolate: " + choco + "\n-------\nTotal: " + precioTotal + "€\n-------\nMuchas Gracias!";

        TextView resumen = findViewById(R.id.tvToppings);
        resumen.setText(mensaje);

    }

    /* Lanza un toast de corta duracion
       mensaje: contenido del toast */
    public void lanzaToast(String mensaje) {

        Context context = getApplicationContext();
        int tiempo = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, mensaje, tiempo);
        toast.show();
    }

    /* Calcula el precio total del pedido teniendo en cuenta
        - si se ha seleccionado nata y chocolate
        - muestra el resultado con 2 decimales */
    public void actualizaTotal(View view) {

        this.precioTotal = cantidad * PRECIO_CAFE;

        CheckBox nataCB = findViewById(R.id.checkWhipped);
        CheckBox chocoCB = findViewById(R.id.checkChocolate);

        if (nataCB.isChecked()) precioTotal += PRECIO_NATA;
        if (chocoCB.isChecked()) precioTotal += PRECIO_CHOCO;

        TextView totalTV = findViewById(R.id.tvOrder);
        DecimalFormat dosDecimales = new DecimalFormat("#.00");
        totalTV.setText(dosDecimales.format(precioTotal) + " €");
    }
}
