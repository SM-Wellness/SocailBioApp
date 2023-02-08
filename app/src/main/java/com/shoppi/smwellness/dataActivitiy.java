package com.shoppi.smwellness;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class dataActivitiy extends AppCompatActivity {

    Call<data_model> call;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView =findViewById(R.id.textView);

        call = ApiClient.getApiService().test_api_get("/");
        call.enqueue(new Callback<data_model>(){
            //콜백 받는 부분
            @Override
            public void onResponse(Call<data_model> call, Response<data_model> response) {
                data_model result = response.body();
                String str;
                str= result.getId() +"\n"+
                        result.getSmoking()+"\n"+
                        result.getBigThree()+"\n"+
                        result.getBmi()+"\n"+
                        result.getSugarLevel()+"\n"+
                        result.getBloodPressure()+"\n"+
                        result.getTodayCalorie()+"\n"+
                        result.getTodayWater()+"\n"+
                        result.getTodaySteps()+"\n"+
                        result.getLiverLevels()+"\n"
                ;
                textView.setText(str);
            }

            @Override
            public void onFailure(Call<data_model> call, Throwable t) {

            }
        });


    }
}