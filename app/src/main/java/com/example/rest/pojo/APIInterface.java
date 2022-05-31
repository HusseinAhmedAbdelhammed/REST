package com.example.rest.pojo;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {
   //@GET("comments")
    //public Call<List<Post>> getPost(@Query("postId") String postId);

   //@POST("posts")
    //public Call<Post> storePost(@Body Post post);
   /*@POST("oauth2/token")
   @FormUrlEncoded
   public Call<Token> storeCred(@Field("client_id") String client_id,@Field("client_secret") String client_secret
     , @Field("grant_type") String grant_type);


   @POST("games")
   @FormUrlEncoded
   public Call<Game> getGame(@Field("Client-ID") String Client_ID, @Field("Authorization") String Authorization,
                             @Query("id") int id);*/

@GET("games")

    public Call<Game> getGame(@Query("key") String key,@Query("search") String search);



}
