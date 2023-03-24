package Notes;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.entity.Result;
import com.example.myapplication.entity.User;
import com.example.myapplication.Post.DatabaseUtil;
import Notes.ui.ChangePwdFragment;

public class ChangePwd extends AppCompatActivity {
    private EditText username;
    private EditText mobile;
    private EditText userId;
    private EditText newPassword;
    private Button change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ChangePwdFragment.newInstance())
                    .commitNow();
        }
        setTitle("修改密码");
        userId = findViewById(R.id.editUserId);
        mobile = findViewById(R.id.editMobile);
        username = findViewById(R.id.username);
        newPassword = findViewById(R.id.editTextTextPassword2);
        change = findViewById(R.id.change_pwd);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                if (newPassword.getText().toString().equals("")){
                    Toast.makeText(ChangePwd.this,"为空",Toast.LENGTH_LONG).show();
                    return;
                }
                Result result = DatabaseUtil.updateById(new User(mobile.getText().toString(),newPassword.getText().toString(),Integer.parseInt(userId.getText().toString()),username.getText().toString()
                        ),"dev-api/test/user","update");
                System.out.println(result.toString());
                Toast.makeText(ChangePwd.this,"success",Toast.LENGTH_LONG).show();
                //sendBroadcast(new Intent("com.example.myapplication.FORCE_OFFLINE"));  //登出
            }
        });


    }
}