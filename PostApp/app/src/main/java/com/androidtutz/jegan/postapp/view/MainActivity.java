package com.androidtutz.jegan.postapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.androidtutz.jegan.postapp.R;
import com.androidtutz.jegan.postapp.model.User;
import com.androidtutz.jegan.postapp.service.PostAppService;
import com.androidtutz.jegan.postapp.service.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText userEmail;
    private EditText passWord;
    private Button submitButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userEmail = (EditText) findViewById(R.id.et_email);
        passWord = (EditText) findViewById(R.id.et_password);
        submitButton = (Button) findViewById(R.id.btn_submit);
        resultTextView = (TextView) findViewById(R.id.tv_result);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                postData();
            }
        });
    }

    private void postData() {

        User user = new User();
        user.setUserEmail(userEmail.getText().toString());
        user.setPassWord(passWord.getText().toString());

        Log.i("responsetest", "******************* before id : " + user.getId());

        PostAppService postAppService = RetrofitInstance.getService();
        Call<User> call = postAppService.getResult(user);

        userEmail.setText("");
        passWord.setText("");

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                User returnedUser = response.body();

                resultTextView.setText("Id is : " + returnedUser.getId());

                Log.i("responsetest", "******************* after id : " + returnedUser.getId());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
