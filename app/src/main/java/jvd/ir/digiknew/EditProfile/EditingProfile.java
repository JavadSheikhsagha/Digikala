package jvd.ir.digiknew.EditProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatSpinner;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jvd.ir.digiknew.Model.EditProfile;
import jvd.ir.digiknew.R;

public class EditingProfile extends AppCompatActivity {

    EditText edtName,edtCode,edtHome,edtPhone,edtEmail;

    AppCompatCheckBox checkBox;

    RadioButton radioMan,radioWoman;

    RadioGroup radioGroup;

    Button btnSubmit;

    AppCompatSpinner spinnerDay,spinnerMonth,spinnerYear;

    String email;

    String[] days,years,months;

    String birthday;

    String sex="1";

    String khabarname="1";

    String daySelected,monthSelected,yearSelected;

    EditingProfileViewModel editingProfileViewModel=new EditingProfileViewModel();

    CompositeDisposable compositeDisposable=new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing);

        setupViews();

        setProfile();

    }

    private void setProfile() {

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedItem=radioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton=findViewById(selectedItem);

                editingProfileViewModel.editProfile(edtEmail.getText().toString(),edtName.getText().toString(),edtCode.getText().toString(),edtHome.getText().toString(),edtPhone.getText().toString(),birthday,selectedRadioButton.getText().toString().equals("مرد")?"1":"0",checkBox.isChecked()?"1":"0")
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<List<EditProfile>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                compositeDisposable.add(d);
                            }

                            @Override
                            public void onSuccess(List<EditProfile> editProfiles) {
                                edtCode.setText(editProfiles.get(0).getCode());
                                edtName.setText(editProfiles.get(0).getName());
                                edtHome.setText(editProfiles.get(0).getHome());
                                edtPhone.setText(editProfiles.get(0).getMobile());

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("LOG14", "onError: "+e.toString());
                            }
                        });

            }
        });


    }

    private void setupViews() {

        years=getResources().getStringArray(R.array.year);
        months = getResources().getStringArray(R.array.month);
        days = getResources().getStringArray(R.array.days);

        edtCode = findViewById(R.id.edt_editing_codeMelli);
        edtEmail = findViewById(R.id.edt_editing_email);
        edtHome = findViewById(R.id.edt_editing_phoneHome);
        edtPhone = findViewById(R.id.edt_editing_phoneCell);
        edtName = findViewById(R.id.edt_editing_name);

        checkBox = findViewById(R.id.chk_editing_checkBox);

        radioGroup = findViewById(R.id.rg_editing_sex);
        radioMan = findViewById(R.id.rb_editing_man);
        radioWoman = findViewById(R.id.rb_editing_lady);

        spinnerDay = findViewById(R.id.spinner_editing_day);
        spinnerMonth = findViewById(R.id.spinner_editing_month);
        spinnerYear = findViewById(R.id.spinner_editing_year);

        btnSubmit = findViewById(R.id.btn_editing_submit);


        email = getIntent().getExtras().getString("email");
        edtEmail.setText(email);

        ArrayAdapter dayAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,days);
        ArrayAdapter monthAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,months);
        ArrayAdapter yearAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,years);

        //Toast.makeText(EditingProfile.this, birthday, Toast.LENGTH_SHORT).show();

        spinnerDay.setAdapter(dayAdapter);
        spinnerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                daySelected=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerMonth.setAdapter(monthAdapter);
        spinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                monthSelected=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerYear.setAdapter(yearAdapter);
        spinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                yearSelected=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





    }

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }
}
