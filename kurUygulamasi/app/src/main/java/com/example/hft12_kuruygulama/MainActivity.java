package com.example.hft12_kuruygulama;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;

import com.example.hft12_kuruygulama.databinding.ActivityMainBinding;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Collections;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    HashMap<String, Double> ratesMap = new HashMap<>();
    // YENİ: Para birimi kodlarını tam adlarıyla eşleştirmek için HashMap
    HashMap<String, String> currencyNamesMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // YENİ: Para birimi adlarını doldur
        populateCurrencyNames();

        new DataIndir().execute();

        binding.calculateButton.setOnClickListener(v -> calculateResult());
    }

    // YENİ: Para birimi adlarını HashMap'e ekleyen metod
    private void populateCurrencyNames() {
        currencyNamesMap.put("TRY", "Türk Lirası");
        currencyNamesMap.put("USD", "Amerikan Doları");
        currencyNamesMap.put("EUR", "Euro");
        currencyNamesMap.put("GBP", "İngiliz Sterlini");
        currencyNamesMap.put("JPY", "Japon Yeni");
        currencyNamesMap.put("CHF", "İsviçre Frangı");
        currencyNamesMap.put("CAD", "Kanada Doları");
        currencyNamesMap.put("AUD", "Avustralya Doları");
        currencyNamesMap.put("CNY", "Çin Yuanı");
        currencyNamesMap.put("RUB", "Rus Rublesi");
        currencyNamesMap.put("AZN", "Azerbaycan Manatı");
        currencyNamesMap.put("BTC", "Bitcoin");
        // Diğer para birimleri eklenebilir...
    }

    private void calculateResult() {
        String amountStr = binding.amountEditText.getText().toString();
        // GÜNCELLENDİ: Spinner'dan gelen tam metni al
        String fromCurrencySelectedItem = (String) binding.fromCurrencySpinner.getSelectedItem();
        String toCurrencySelectedItem = (String) binding.toCurrencySpinner.getSelectedItem();

        if (amountStr.isEmpty()) {
            Toast.makeText(this, "Lütfen bir miktar girin.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (fromCurrencySelectedItem == null || toCurrencySelectedItem == null || ratesMap.isEmpty()) {
            Toast.makeText(this, "Kur verileri henüz yüklenmedi veya seçilemedi.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            // GÜNCELLENDİ: Metnin sadece ilk 3 karakterini (kodu) al
            String fromCurrencyCode = fromCurrencySelectedItem.substring(0, 3);
            String toCurrencyCode = toCurrencySelectedItem.substring(0, 3);

            double amount = Double.parseDouble(amountStr);
            double fromRate = ratesMap.get(fromCurrencyCode);
            double toRate = ratesMap.get(toCurrencyCode);

            double result = (amount / fromRate) * toRate;

            String resultText = String.format("%.2f %s", result, toCurrencyCode);
            binding.resultTextView.setText(resultText);

        } catch (Exception e) {
            Toast.makeText(this, "Hesaplama sırasında bir hata oluştu.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    class DataIndir extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder gelen = new StringBuilder();
            try {
                URL url = new URL("https://data.fixer.io/api/latest?access_key=11e045a9e629d609ccab2fb6e5bc223b&format=1");
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                InputStream inputStream = httpsURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                int data = inputStreamReader.read();
                while (data != -1) {
                    gelen.append((char) data);
                    data = inputStreamReader.read();
                }
                return gelen.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (s == null) {
                Toast.makeText(MainActivity.this, "Kur verileri alınamadı. İnterneti kontrol edin.", Toast.LENGTH_LONG).show();
                return;
            }

            try {
                JSONObject jsonObject = new JSONObject(s);
                String baseCurrency = jsonObject.getString("base");
                JSONObject ratesObject = jsonObject.getJSONObject("rates");

                ratesMap.put(baseCurrency, 1.0);

                Iterator<String> keys = ratesObject.keys();
                while (keys.hasNext()) {
                    String key = keys.next();
                    ratesMap.put(key, ratesObject.getDouble(key));
                }

                // GÜNCELLENDİ: Spinner için "KOD - İsim" formatında liste oluştur
                ArrayList<String> currencyCodeList = new ArrayList<>(ratesMap.keySet());
                Collections.sort(currencyCodeList);

                ArrayList<String> displayList = new ArrayList<>();
                for (String code : currencyCodeList) {
                    String name = currencyNamesMap.get(code);
                    if (name == null) {
                        name = "Bilinmeyen Para Birimi"; // Eşleşme bulunamazsa
                    }
                    displayList.add(code + " - " + name);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, displayList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                binding.fromCurrencySpinner.setAdapter(adapter);
                binding.toCurrencySpinner.setAdapter(adapter);

                // Varsayılan seçimleri ayarla (örneğin EUR -> TRY)
                binding.fromCurrencySpinner.setSelection(adapter.getPosition("EUR - Euro"));
                binding.toCurrencySpinner.setSelection(adapter.getPosition("TRY - Türk Lirası"));

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "Veri işlenirken hata oluştu.", Toast.LENGTH_LONG).show();
            }
        }
    }
}