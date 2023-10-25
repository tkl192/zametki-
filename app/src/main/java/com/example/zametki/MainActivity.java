package com.example.zametki;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.internal.MultiViewUpdateListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OpenNote(View view){
        setContentView(R.layout.note);
    }

    public class notes{
        public String name;
        public String text;
        public String date;
    }

    public List<notes> list_nites = new ArrayList<>();

    public void AddNote(View view) {
        notes new_notes = new notes();

        EditText e_name = findViewById(R.id.editTextTextPersonName);
        new_notes.name = e_name.getText().toString();

        MultiAutoCompleteTextView e_text = findViewById(R.id.multiAutoCompleteTextView);
        new_notes.text = e_text.getText().toString();

        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd");
        new_notes.date = formatForDateNow.format(dateNow);

        list_nites.add(new_notes);
        setContentView(R.layout.activity_main);
        onLoad();
    }

    private void onLoad() {
        LinearLayout parent = findViewById(R.id.parent);

        System.out.print(list_nites.size());
        for(int i = 0; i < list_nites.size(); i++){
            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.HORIZONTAL);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT
            );
            ll.setLayoutParams(params);
            ll.setTag(i);
            ll.setOnClickListener(this::OpenNotes);
            ImageView iv = new ImageView(this);
            iv.setImageResource(R.drawable.notelogo);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150, 150);
            iv.setLayoutParams(layoutParams);
            iv.setPadding(20,20,20,20);

            LinearLayout ll_ver = new LinearLayout(this);
            ll_ver.setOrientation(LinearLayout.VERTICAL);
            ll_ver.setLayoutParams(params);

            TextView tv_name = new TextView(this);
            tv_name.setText(list_nites.get(i).name);
            RelativeLayout.LayoutParams params_tv = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            tv_name.setLayoutParams(params_tv);
            tv_name.setTextColor(Color.BLACK);
            tv_name.setTextSize(18);

            TextView tv_data = new TextView(this);
            tv_data.setText(list_nites.get(i).date);
            tv_data.setLayoutParams(params_tv);
            tv_data.setTextColor(Color.GRAY);

            parent.addView(ll);
            ll.addView(iv);
            ll.addView(ll_ver);
            ll_ver.addView(tv_name);
            ll_ver.addView(tv_data);
        }
    }

    public void OpenNotes(View view){
        int id = (int) view.getTag();
        setContentView(R.layout.note);

        EditText e_name = findViewById(R.id.editTextTextPersonName);
        e_name.setText(list_nites.get(id).name);

        MultiAutoCompleteTextView e_text = findViewById(R.id.multiAutoCompleteTextView);
        e_text.setText(list_nites.get(id).text);
    }
}
