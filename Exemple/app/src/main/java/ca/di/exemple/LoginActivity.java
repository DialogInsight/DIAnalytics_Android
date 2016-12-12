package ca.di.exemple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import com.dianalytics.DIAnalytics;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.edittext_login_activity_email)
    EditText mEmailEditText;

    @BindView(R.id.edittext_login_activity_first_name)
    EditText mFirstNameEditText;

    @BindView(R.id.edittext_login_activity_last_name)
    EditText mLastNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_login_activity_submit)
    public void onSubmitClick() {
        if (mEmailEditText.getText().toString().equals("")) {
            Toast.makeText(this, "Email must not be empty", Toast.LENGTH_LONG).show();
            return;
        }
        if (mFirstNameEditText.getText().toString().equals("")) {
            Toast.makeText(this, "First name must not be empty", Toast.LENGTH_LONG).show();
            return;
        }
        if (mLastNameEditText.getText().toString().equals("")) {
            Toast.makeText(this, "Last name must not be empty", Toast.LENGTH_LONG).show();
            return;
        }

        HashMap contactData = new HashMap();
        contactData.put("f_EMail", mEmailEditText.getText().toString());
        contactData.put("nom", mLastNameEditText.getText().toString());
        contactData.put("prenom", mFirstNameEditText.getText().toString());

        HashMap hashMap = new HashMap();
        hashMap.put("Contact", contactData);
        DIAnalytics.identify(hashMap);

        finish();
    }
}
