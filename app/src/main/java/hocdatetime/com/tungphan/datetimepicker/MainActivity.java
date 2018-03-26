package hocdatetime.com.tungphan.datetimepicker;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText edtTen, edtNoiDung, edtNgay, edtThoiGian;
    Button btnNgay, btnThoiGian, btnAddCV;
    ListView lvCv;
    ArrayList<Work> listCv;
    ArrayAdapter adapter;
    Calendar cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        getDefaultInfor();
    }

    private void mapping() {
        edtTen=(EditText) findViewById(R.id.edt_congviec);
        edtNoiDung=(EditText) findViewById(R.id.edt_noidung);
        edtNgay=(EditText) findViewById(R.id.edt_ngay);
        edtThoiGian=(EditText) findViewById(R.id.edt_gio);
        btnNgay=(Button) findViewById(R.id.btnDate);

        btnThoiGian=(Button) findViewById(R.id.btnTime);

        btnAddCV=(Button) findViewById(R.id.btnAdd);

        lvCv=(ListView) findViewById(R.id.lvCv);
        listCv= new ArrayList<>();
        adapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1, listCv);
        lvCv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, listCv.get(i).getDes(), Toast.LENGTH_SHORT).show();
            }
        });
        lvCv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                listCv.remove(i);
                adapter.notifyDataSetChanged();
                return true;
            }
        });

        lvCv.setAdapter(adapter);
    }

    public void getDefaultInfor()
    {
        cal= Calendar.getInstance();
        SimpleDateFormat dft=null;
        dft=new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String strDate=dft.format(cal.getTime());
        edtNgay.setText(strDate);
        dft=new SimpleDateFormat("hh:mm a",Locale.getDefault());
        String strTime=dft.format(cal.getTime());
        edtThoiGian.setText(strTime);
        dft=new SimpleDateFormat("HH:mm",Locale.getDefault());
        edtThoiGian.setTag(dft.format(cal.getTime()));

        edtTen.requestFocus();
    }

    public void addDate(View view) {
        Calendar calendar= Calendar.getInstance();
        int ngay= calendar.get(Calendar.DAY_OF_MONTH);
        int thang= calendar.get(Calendar.MONTH);
        int nam= calendar.get(Calendar.YEAR);
        DatePickerDialog pic=new DatePickerDialog(
                MainActivity.this,
                new OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        edtNgay.setText(
                                (i2) +"/"+(i1+1)+"/"+i);
                    }
                }, nam, thang, ngay);

        pic.show();
    }

    public void addtime(View view) {
        Calendar calendar= Calendar.getInstance();
        int gio= calendar.get(Calendar.HOUR_OF_DAY);
        int phut= calendar.get(Calendar.MINUTE);
        TimePickerDialog pic=new TimePickerDialog(
                MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                int hourTam=i;
                if(hourTam>12)
                    hourTam=hourTam-12;
                edtThoiGian.setText
                        (hourTam +":"+i1 +(i>12?" PM":" AM"));
            }
        }, gio, phut, true);

        pic.show();
    }

    public void addWork(View view){
        Log.d("logging","c");
        String name= edtTen.getText().toString();
        edtTen.setText("");
        String des= edtNoiDung.getText().toString();
        edtNoiDung.setText("");
        String date= edtNgay.getText().toString();
        String time= edtThoiGian.getText().toString();

        if (!name.equals("") || !des.equals("")) {
            listCv.add(new Work(name, des, date, time));
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "Nhập đủ nội dung trước khi thêm", Toast.LENGTH_SHORT).show();
        }
    }
}
