package com.example.tugasday_5;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;



public class MainActivity extends AppCompatActivity {

    EditText etnama, etkodebarang, etjumlahbarang;
    RadioGroup radioGroup;
    RadioButton btn1,btn2,btn3;
    Button btnpro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etnama = findViewById(R.id.etnama);
        etkodebarang = findViewById(R.id.etkodebarang);
        etjumlahbarang = findViewById(R.id.etjumlahbarang);
        radioGroup = findViewById(R.id.radioGroup);
        btnpro = findViewById(R.id.btnpro);

        btnpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedId);

                String namaPelanggan = etnama.getText().toString();
                String kodeBarang = etkodebarang.getText().toString();
                int jumlahBarang = Integer.parseInt(etjumlahbarang.getText().toString());
                String tipeMember = ((RadioButton) findViewById(selectedId)).getText().toString();

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("Nama_Pelanggan", namaPelanggan);
                intent.putExtra("Tipe_Member", tipeMember);
                intent.putExtra("Kode_Barang", kodeBarang);
                intent.putExtra("Jumlah_Barang", jumlahBarang);
                startActivity(intent);
            }
        });
    }
}
