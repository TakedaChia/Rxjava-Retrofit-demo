package com.lueluelue.myrxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {
    private Subscriber subscriber;
    View v;

    @Bind(R.id.btn)
    Button btn;
    @Bind(R.id.result_TV)
    TextView resultTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        getMovie();
    }

    //网络请求
    public void getMovie() {

//        Retrofit方法
//        String baseurl = "https://api.douban.com/v2/movie/";
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseurl).
//                addConverterFactory(GsonConverterFactory.create()).
//                addCallAdapterFactory(RxJavaCallAdapterFactory.create()).
//                build();
//
//        MovieService movieService = retrofit.create(MovieService.class);
//        Call<MovieEntity> call = movieService.getTopMovie(0,10);
//        call.enqueue(new Callback<MovieEntity>() {
//            @Override
//            public void onResponse(Call<MovieEntity> call, Response<MovieEntity> response) {
//                resultTV.setText(response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<MovieEntity> call, Throwable t) {
//                resultTV.setText(t.getMessage());
//            }
//        });

//        Rxjava结合Retrofit方法
//        movieService.getTopMovie(0, 10).
//                subscribeOn(Schedulers.io()).
//                observeOn(AndroidSchedulers.mainThread()).
//                subscribe(new Subscriber<MovieEntity>() {
//                    @Override
//                    public void onCompleted() {
//                        Toast.makeText(MainActivity.this, "load movie completed!", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        resultTV.setText(e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(MovieEntity movieEntity) {
//                        resultTV.setText(movieEntity.toString());
//                    }
//                });

        //封装之后的方法
        subscriber = new Subscriber<MovieEntity>() {
            @Override
            public void onCompleted() {
                Toast.makeText(MainActivity.this, "load movie completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                resultTV.setText(e.getMessage());
            }

            @Override
            public void onNext(MovieEntity movieEntity) {
                resultTV.setText(movieEntity.toString());
            }
        };
        HttpMethods.getInstance().getTopMovie(subscriber, 0, 10);
    }
}
