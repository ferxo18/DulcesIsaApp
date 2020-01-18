package com.example.dulcesisa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

   // private Spinner sp;

    ListView lv;

    private List<pedido> listPed = new ArrayList<>();
    ArrayAdapter<pedido> arrayAdapterpedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        lv=(ListView) findViewById(R.id.lv_datospedido);

        ImageView log = (ImageView) findViewById(R.id.logo);
        log.setImageResource(R.drawable.disa);//mando logo de dulces Isa

        //sp=(Spinner) findViewById(R.id.spinner);

        inicializarFerebase();
        listadatos();
    }

    private void listadatos() {
        databaseReference.child("pedido").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               listPed.clear();

                for (DataSnapshot objSnaptshot: dataSnapshot.getChildren()
                     ) {pedido p = objSnaptshot.getValue(pedido.class);
                     listPed.add(p);

                     arrayAdapterpedido = new ArrayAdapter<pedido>(Main2Activity.this, android.R.layout.simple_list_item_1,listPed);
                    lv.setAdapter(arrayAdapterpedido);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void inicializarFerebase() {
        FirebaseApp.initializeApp(this);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void regresar(View v){

        Intent intent = new Intent(v.getContext(), MainActivity.class);
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
