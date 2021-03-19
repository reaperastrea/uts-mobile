package id.ac.umn.uts_27628;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

public class Login extends AppCompatActivity {
    private EditText isUsername, isPass;
    private Button Masuk;
    private final String id = "uasmobile";
    private final String pw = "uasmobilegenap";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        isUsername = (EditText) findViewById(R.id.isUsername);
        isPass = (EditText) findViewById(R.id.isPass);
        Masuk = findViewById(R.id.btnLogin);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isUsername.getText().toString().equals(id) && isPass.getText().toString().equals(pw)){
                    Intent intent1 = new Intent(Login.this, listLagu.class);
                    startActivity(intent1);
                } else {
                    Toast.makeText(Login.this, "ID or Pass Wrong!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}