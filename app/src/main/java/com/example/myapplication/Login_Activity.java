package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.SpinnerAdapter;
import com.example.myapplication.Post.DatabaseUtil;
import com.example.myapplication.entity.Hospital;
import com.example.myapplication.entity.Login;
import com.example.myapplication.entity.Result;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Login_Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    Gson gson = new Gson();
    private EditText editUsername, editPassword;
    private Button button;
    String code, deptId, hospital, username, password, type;
    Spinner spinner;

    private SharedPreferences pref, pref2;
    private SharedPreferences.Editor editor, editor2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).hide();

        initView();

        //保存phone
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemember = pref.getBoolean("remember_phone", false);
        if (isRemember) {
            String phone = pref.getString("phone", "");
            editUsername.setText(phone);
        }
        pref2 = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemember2 = pref2.getBoolean("remember_password", false);
        if (isRemember2) {
            String password = pref2.getString("password", "");
            editPassword.setText(password);
        }

        initEvent();
    }

    private void initEvent() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //登录验证
                username = editUsername.getText().toString();
                password = editPassword.getText().toString();
                type = "account";
//                Result result = DatabaseUtil.selectLineById( "api/cockpit/check", "?deptCode=" + code);
                Result result = DatabaseUtil.other(new Login(username, password, deptId, type), "api/user/login");
                String flag = result.getMsg().toString();
                if (flag.equals("成功")) {

                    //记住phone
                    editor = pref.edit();
                    editor.putBoolean("remember_phone", true);
                    editor.putString("phone", username);
                    editor.apply();

                    editor2 = pref2.edit();
                    editor2.putBoolean("remember_password", true);
                    editor2.putString("password", password);
                    editor2.apply();
                    //finish不加也行 啊吧
                    finish();
                    toMain();
                } else {
                    Toast.makeText(Login_Activity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
//    //保存code
//    private void Save(){
//
//    }

    private void toMain() {
        Intent intent = new Intent(Login_Activity.this, MainActivity.class);

        //传递数据
        Bundle bundle = new Bundle();
        Bundle bundle2 = new Bundle();
        bundle.putString("code", code);
        bundle2.putString("hospital", hospital);
        intent.putExtra("bun", bundle);
        intent.putExtra("bun2", bundle2);
        startActivity(intent);

        //设置跳转动画
        overridePendingTransition(R.anim.alpha, R.anim.out);
    }

    @SuppressLint("ResourceType")
    private void initView() {
        editUsername = findViewById(R.id.editTextPhone);
        editPassword = findViewById(R.id.editTextTextPassword);
        button = findViewById(R.id.button);
        editUsername.addTextChangedListener(textWatcher);
        spinner = findViewById(R.id.action_bar_spinner);
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (editUsername.getText().toString().length() == 11) {
                username = editUsername.getText().toString();
                try {
                    initSpinner();
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                Toast.makeText(Login_Activity.this, "after", Toast.LENGTH_SHORT).show();
            } else spinner.setAdapter(null);
        }
    };

//    @SuppressLint("ResourceType")
    private void initSpinner() {
        Result result_hospital = DatabaseUtil.selectLineById("api/dept/list/phone", "?mobilePhone=" + username);
        String toJson_hospital = gson.toJson(result_hospital.getResponse());
        List<Hospital> hospitalList = gson.fromJson((String.valueOf(toJson_hospital)), new TypeToken<List<Hospital>>() {
        }.getType());
        List<String> DeptNameList = hospitalList.stream().map(Hospital::getDeptName).collect(Collectors.toList());
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(DeptNameList, this);

        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                code = hospitalList.get(position).getDeptCode();
                deptId = hospitalList.get(position).getDeptId();
                hospital = hospitalList.get(position).getDeptName();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
//        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                code = hospitalList.get(position).getDeptCode();
//                deptId = hospitalList.get(position).getDeptId();
//                hospital = hospitalList.get(position).getDeptName();
//            }
//        });
        spinner.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                v.setScaleX(hasFocus ? 1.15f : 1.0f);
                v.setScaleY(hasFocus ? 1.15f : 1.0f);
            }
        });
        spinner.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);

//        SpinnerAdapterr spinnerAdapterr = new SpinnerAdapterr(DeptNameList,this);
////        LinearLayoutManager manager = new LinearLayoutManager(this);
////        spinner.setLayoutManager(manager);
////        spinner.setItemAnimator(new DefaultItemAnimator());
//        spinner.setAdapter(spinnerAdapterr);

    }
}