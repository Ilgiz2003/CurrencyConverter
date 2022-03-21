package application.duseev.valute;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;



import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;



import application.duseev.valute.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<Valute> currencies;
    ArrayAdapter<Valute> listAdapter;
    Handler mainHandler = new Handler();
    ProgressDialog progressDialog;
    String [] nameValutes = new String[34];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initializeValuteList();
        binding.updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new fetchData().start();
            }
        });
        binding.currencyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Valute item = (Valute) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, Converter.class);
                intent.putExtra("Array_item",item);
                startActivity(intent);
            }
        });

    }
    private void initializeValuteList() {
        currencies = new ArrayList<>();
        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,currencies);
        binding.currencyList.setAdapter(listAdapter);
    }

    class fetchData extends Thread{
        String data="";
        @Override
        public void run(){
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setMessage("Fetching Data");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                }
            });


            try {
                URL url = new URL("https://www.cbr-xml-daily.ru/daily_json.js");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line=bufferedReader.readLine()) != null){
                    data = data + line;
                }
                if(!data.isEmpty()){
                    JSONObject jsonObject = new JSONObject(data);
                    JSONObject valute = jsonObject.getJSONObject("Valute");
                    nameValutes[0]="AUD";
                    nameValutes[1]="AZN";
                    nameValutes[2]="GBP";
                    nameValutes[3]="AMD";
                    nameValutes[4]="BYN";
                    nameValutes[5]="BGN";
                    nameValutes[6]="BRL";
                    nameValutes[7]="HUF";
                    nameValutes[8]="HKD";
                    nameValutes[9]="DKK";
                    nameValutes[10]="USD";
                    nameValutes[11]="EUR";
                    nameValutes[12]="INR";
                    nameValutes[13]="KZT";
                    nameValutes[14]="CAD";
                    nameValutes[15]="KGS";
                    nameValutes[16]="CNY";
                    nameValutes[17]="MDL";
                    nameValutes[18]="NOK";
                    nameValutes[19]="PLN";
                    nameValutes[20]="RON";
                    nameValutes[21]="XDR";
                    nameValutes[22]="SGD";
                    nameValutes[23]="TJS";
                    nameValutes[24]="TRY";
                    nameValutes[25]="TMT";
                    nameValutes[26]="UZS";
                    nameValutes[27]="UAH";
                    nameValutes[28]="CZK";
                    nameValutes[29]="SEK";
                    nameValutes[30]="CHF";
                    nameValutes[31]="ZAR";
                    nameValutes[32]="KRW";
                    nameValutes[33]="JPY";
                    currencies.clear();
                    for(int i=0;i<34;i++){
                        JSONObject newValute= valute.getJSONObject(nameValutes[i]);
                        Valute Valute1 = new Valute();
                        Valute1.setCharCode(newValute.getString("CharCode"));
                        Valute1.setName(newValute.getString("Name"));
                        Valute1.setNominal(newValute.getInt("Nominal"));
                        Valute1.setValue(newValute.getDouble("Value"));
                        currencies.add(Valute1);
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    if(progressDialog.isShowing()){
                        progressDialog.dismiss();
                    }
                    listAdapter.notifyDataSetChanged();
                }
            });
        }
    }
}