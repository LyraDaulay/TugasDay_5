package com.example.tugasday_5;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.text.NumberFormat;
import java.util.Currency;
import android.widget.Button;
import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {
    Button share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button share= findViewById(R.id.share);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Membuat intent untuk berbagi
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");

                // Mendapatkan detail transaksi dari TextView
                TextView tvDetailTransaksi = findViewById(R.id.tvDetailTransaksi);
                String detailTransaksi = tvDetailTransaksi.getText().toString();

                // Menambahkan pesan yang ingin dibagikan
                shareIntent.putExtra(Intent.EXTRA_TEXT, detailTransaksi);

                // Memulai aktivitas berbagi
                startActivity(Intent.createChooser(shareIntent, "Bagikan ke:"));
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            // Mendapatkan data ekstra dari Intent
            String namaPelanggan = intent.getStringExtra("Nama_Pelanggan");
            String tipeMember = intent.getStringExtra("Tipe_Member");
            String kodeBarang = intent.getStringExtra("Kode_Barang");
            int jumlahBarang = intent.getIntExtra("Jumlah_Barang", 0);

            // Menampilkan pesan selamat datang dengan detail transaksi
            TextView tvWelcomeMessage = findViewById(R.id.tvWelcomeMessage);
            tvWelcomeMessage.setText("Selamat datang, " + namaPelanggan + "! Tipe member Anda: " + tipeMember);

            // Menampilkan detail transaksi
            TextView tvDetailTransaksi = findViewById(R.id.tvDetailTransaksi);
            String detailTransaksi = generateDetailTransaksi(kodeBarang, jumlahBarang);
            tvDetailTransaksi.setText(detailTransaksi);
        }
    }

    // Method untuk menghasilkan detail transaksi berdasarkan kode barang dan jumlah barang
    private String generateDetailTransaksi(String kodeBarang, int jumlahBarang) {
        // Inisialisasi variabel harga barang dan nama barang
        int hargaBarang = 0;
        String namaBarang = "";

        // Mengisi harga barang dan nama barang sesuai dengan kode barang
        switch (kodeBarang) {
            case "SGS":
                hargaBarang = 12999999;
                namaBarang = "Samsung Galaxy S";
                break;


            case "OAS":
                hargaBarang = 1989123;
                namaBarang = "Oppo a5s";
                break;

            case "AAE":
                hargaBarang = 8676981;
                namaBarang = "Acer Aspire E14";
                break;

        }


        int totalHarga = hargaBarang * jumlahBarang;

        Intent intent = getIntent();
        String tipeMember = intent.getStringExtra("Tipe_Member");


        double diskonMember = 0;
        switch (tipeMember) {
            case "Gold":
                diskonMember = 0.1;
                break;
            case "Silver":
                diskonMember = 0.05;
                break;
            case "Biasa":
                diskonMember = 0.02;
                break;
        }


        int diskon = 0;
        if (totalHarga > 10000000) {
            diskon = 100000;
        }


        double jumlahBayar = totalHarga - (totalHarga * diskonMember) - diskon;

        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        formatRupiah.setCurrency(Currency.getInstance("IDR"));
        String hargaFormatted = formatRupiah.format(hargaBarang);



        return "Detail Transaksi:\n" +
                "Nama Barang: " + namaBarang + "\n" +
                "Kode Barang: " + kodeBarang + "\n" +
                "Harga Barang: " + hargaBarang + "\n" +
                "Jumlah Barang: " + jumlahBarang + "\n" +
                "Total Harga: " + totalHarga + "\n" +
                "Diskon: " + diskon + "\n" +
                "Diskon Member: " + (diskonMember * 100) + "%\n" +
                "Jumlah Bayar: " + formatRupiah.format(jumlahBayar);
    }
}