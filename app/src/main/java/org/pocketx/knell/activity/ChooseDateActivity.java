package org.pocketx.knell.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.pocketx.knell.R;
import org.pocketx.knell.base.BaseActivity;
import org.pocketx.knell.domain.Birthday;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Shadow
 */
public class ChooseDateActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @OnClick(R.id.btn_choose_date)
    void chooseDate() {
        DatePickerDialog dialog = new DatePickerDialog(this, (view1, year, month, dayOfMonth) -> {
            Log.d(TAG, "onDateSet: year = " + year + " month = " + month + " dayOfMonth = " + dayOfMonth);
            getBirthdayManager().set(Birthday.create(year, month + 1, dayOfMonth));
            Toast.makeText(ChooseDateActivity.this, R.string.save_over, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ChooseDateActivity.this, KnellActivity.class));
            finish();
        }, 1995, 7, 19);
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosedate);
        ButterKnife.bind(this);
    }
}