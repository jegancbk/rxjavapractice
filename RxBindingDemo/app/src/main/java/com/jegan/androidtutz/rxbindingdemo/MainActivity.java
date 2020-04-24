package com.jegan.androidtutz.rxbindingdemo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import kotlin.Unit;


public class MainActivity extends AppCompatActivity {

    private EditText inputText;
    private TextView viewText;
    private Button clearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.etInputField);
        viewText = findViewById(R.id.tvInput);
        clearButton = findViewById(R.id.btnClear);

        /*inputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewText.setText(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputText.setText("");
                viewText.setText("");
            }
        });*/

        Disposable disposable = RxTextView.textChanges(inputText)
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        viewText.setText(charSequence);
                    }
                });

        Disposable disposable2 = RxView.clicks(clearButton)
                .subscribe(new Consumer<Unit>() {
                    @Override
                    public void accept(Unit unit) throws Exception {
                        inputText.setText("");
                        viewText.setText("");
                    }
                });

    }
}
