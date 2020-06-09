package jvd.ir.digiknew.Chart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

import jvd.ir.digiknew.R;

public class ChartActivity extends AppCompatActivity {

    LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        setupViews();
    }

    private void setupViews() {

        lineChart=findViewById(R.id.chart_chart_chart);
        ImageView imgBack=findViewById(R.id.img_chart_back);

        List<Entry> values=new ArrayList();
        values.add(new Entry(0,10));
        values.add(new Entry(1,3));
        values.add(new Entry(2,5));
        values.add(new Entry(3,20));
        values.add(new Entry(4,12));
        LineDataSet lineDataSet=new LineDataSet(values,"نمودار قیمتی");

        lineDataSet.setColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));
        lineDataSet.setLineWidth(3f);
        lineDataSet.setValueTextSize(10f);
        lineDataSet.setValueTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorBlack));
        lineDataSet.setCircleColor(ContextCompat.getColor(this,R.color.colorGreen));
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFillDrawable(ContextCompat.getDrawable(this,R.drawable.chart_gradient));

        List<ILineDataSet> dataSets=new ArrayList();
        dataSets.add(lineDataSet);
        lineChart.animateXY(1000,1000);

        LineData lineData=new LineData(dataSets);
        lineChart.setData(lineData);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
