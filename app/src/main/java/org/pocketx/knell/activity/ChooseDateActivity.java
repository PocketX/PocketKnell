package org.pocketx.knell.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.Toast;

import org.pocketx.knell.R;
import org.pocketx.knell.base.BaseActivity;
import org.pocketx.knell.domain.Birthday;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseDateActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @OnClick(R.id.btn_choose_date)
    void chooseDate() {
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Log.d(TAG, "onDateSet: year = " + year + " month = " + month + " dayOfMonth = " + dayOfMonth);
                //将日期存到SP，然后跳转到时钟界面
                //如果month是一位数，前面补一个0
//                String tempMonth;
//                if ((month + "").length() == 1) {
//                    tempMonth = "0" + month;
//                } else {
//                    tempMonth = month + "";
//                }
//                SPUtils.getIn|stance().put(ConstantUtils.BIRTHDAY, year + "-" + tempMonth + "-" + dayOfMonth);
                getBirthdayManager().set(Birthday.create(year, month+1, dayOfMonth));
                Toast.makeText(ChooseDateActivity.this, R.string.save_over, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ChooseDateActivity.this, KnellActivity.class));
                finish();
            }
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