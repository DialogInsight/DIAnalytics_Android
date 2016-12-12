package ca.di.exemple;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.dianalytics.DIAnalytics;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.edittext_main_activity_push_id)
    EditText pushIdEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("DIAnalytics demo");
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_main_activity_set_token)
    public void onSetTokenClick() {
        DIAnalytics.registerForRemoteNotification();
    }

    @OnClick(R.id.button_main_activity_identify)
    public void onIdentifyClick() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.button_main_activity_simulate_push_reception)
    public void onSimulatePushReceptionClick() {
        DIAnalytics.sendPushReception(pushIdEditText.getText().toString());
    }

}
