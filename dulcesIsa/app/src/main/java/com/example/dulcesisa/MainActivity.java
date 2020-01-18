package com.example.dulcesisa;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner sp;
    private Button btnfecha,btnhora;
    public EditText fecha,hora,nom,tel,des;
    private int dia,mes,ano,horas,minutos;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);
        ImageView log = (ImageView) findViewById(R.id.logo);
        log.setImageResource(R.drawable.disa);//mando logo de dulces Isa

        //sp=(Spinner) findViewById(R.id.spinner);

        String [] opciones = {"Norm-1lb-18.00","Norm-1.5lb-25.00","Norm-2lb-32.00","Esp-1 lb-23.00",
                "Esp-1.5lb-30.00","Esp-2lb-37.00","Volt-1lb-20.00","Volt-1.5lb-30.00","Volt-2lb-37.00",
                "I.Nor-1lb-26.00","I.Nor-1.5lb-33.00","I.Nor-2lb-40.00","I.Esp-1lb-31.00","I.Esp-1.5lb-38.00",
                "I.Esp-2lb-45.00"};

        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,opciones);
        sp.setAdapter(adapter);

        // programar botones de fecha y hora

        btnfecha =(Button) findViewById(R.id.btnfecha);
        btnhora =(Button) findViewById(R.id.btnhora);
        fecha =(EditText) findViewById(R.id.fecha);
        hora =(EditText) findViewById(R.id.hora);
        nom =(EditText) findViewById(R.id.cliente);
        tel = (EditText) findViewById(R.id.telefono);
        des = (EditText) findViewById(R.id.descripcion);

// programar el spinner


        btnfecha.setOnClickListener(this);// escuchadores de botones fecha y hora
        btnhora.setOnClickListener(this);

        inicializarFerebase();



    }

    private void inicializarFerebase() {

        FirebaseApp.initializeApp(this);

        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
       // Toast.makeText(this, "entre", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        if(v==btnfecha) {
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                   fecha.setText(dayOfMonth+"-"+(month+1)+"-"+year);
                }
            },dia,mes,ano);
            datePickerDialog.show();
        }

        if(v==btnhora){
            final Calendar c = Calendar.getInstance();
            horas = c.get(Calendar.HOUR_OF_DAY);
            minutos = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    hora.setText(hourOfDay+":"+minute);
                }
            },horas,minutos,true);
            timePickerDialog.show();
        }
    }

    public void guardar(View v){
        String Nombre = nom.getText().toString();
        String telefono = tel.getText().toString();
        String date = fecha.getText().toString();
        String hour = hora.getText().toString();
        String descripcion = des.getText().toString();
        String tipo = sp.getSelectedItem().toString();



        pedido p = new pedido();
try {
    p.setUid(UUID.randomUUID().toString());
    p.setNombre(Nombre);
    p.setTel(telefono);
    p.setFecha(date);
    p.setHora(hour);
    p.setDes(descripcion);
    p.setTip(tipo);

    databaseReference.child("pedido").child(p.getFecha()).setValue(p);
    Toast.makeText(this, "Agregado", Toast.LENGTH_LONG).show();
}catch (Exception e){
    Toast.makeText(this, "e", Toast.LENGTH_LONG).show();
}
limpiar();
    }

    private void limpiar() {
        btnfecha.setText("");

        fecha.setText("");
        hora.setText("");
        nom.setText("");
        tel.setText("");
        des.setText("");

    }

    public void siguiente(View v){

        Intent intent = new Intent(v.getContext(), Main2Activity.class);
        startActivityForResult(intent, 0);


    }

    public void salir(View v){

        Toast.makeText(this, "Gracias Por Utilizar Dulses Isa App", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
}
