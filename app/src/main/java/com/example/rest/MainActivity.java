package com.example.rest;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rest.pojo.APIInterface;
import com.example.rest.pojo.Cred;
import com.example.rest.pojo.Game;
import com.example.rest.pojo.Game2;
import com.example.rest.pojo.Token;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView jsonTitle,meta;
    Button search;
    EditText game;
    ImageView img;
    String searchd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.rawg.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIInterface apiInterface=retrofit.create(APIInterface.class);


       search.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View view) {
               if(game.getText().toString()!=null){
                   searchd=game.getText().toString();
               }else {
                   searchd="The Evil Within";
               }
               Call<Game> call=apiInterface.getGame("624f5ac667cb48c6ab93a0d85f0ce400",searchd);
               call.enqueue(new Callback<Game>() {
                   @Override
                   public void onResponse(Call<Game> call, Response<Game> response) {
                       jsonTitle.setText(response.body().getResultList().get(0).getName());
                       Picasso.get().load(response.body().getResultList().get(0).getBackground_image()).into(img);
                       meta.setText(Integer.toString(response.body().getResultList().get(0).getMetacritic()));
                   }

                   @Override
                   public void onFailure(Call<Game> call, Throwable t) {
                       jsonTitle.setText(t.getMessage());
                   }
               });
           }
       });

        /*Cred cred=new Cred("8pcyw17yyafiqr2e5ia9edrtsydqwj","xuczk3e2h4xv0oge36i3rn99qkm5mn","client_credentials");
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://id.twitch.tv/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIInterface apiInterface=retrofit.create(APIInterface.class);
        Call<Token> call=apiInterface.storeCred(cred.getClient_id(),cred.getClient_secret(),cred.getGrant_type());
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                jsonTitle.setText(response.body().getAccess_token());
                Intent intent=new Intent(MainActivity.this,GameActivity.class);
                intent.putExtra("token",response.body().getAccess_token());
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                jsonTitle.setText(t.getMessage());

            }
        });*/

    }
    public void init(){
        jsonTitle=findViewById(R.id.jsonTitle);
        meta=findViewById(R.id.meta);
        search=findViewById(R.id.search);
        game=findViewById(R.id.game);
        img=findViewById(R.id.img);




    }
}