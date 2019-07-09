package com.gtaoeng.arcprogressbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main2Activity extends AppCompatActivity {


    private RecyclerView rlv_listview;
    private ItemAdapter adapter;
    private List<ItemBean> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        rlv_listview = findViewById(R.id.rlv_listview);
        dataList = new ArrayList<>();
        adapter = new ItemAdapter(this, dataList);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rlv_listview.setLayoutManager(manager);
        rlv_listview.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rlv_listview.setAdapter(adapter);


        View btn = findViewById(R.id.btn_refresh);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
    }

    private void loadData() {
        dataList.clear();
        for (int i = 0; i < 10; i++) {
            ItemBean bean = new ItemBean();
            bean.setName("名称" + (i + 1));
            Random ra = new Random();
            bean.setProgress(ra.nextInt() % 100);
            dataList.add(bean);
        }
        adapter.notifyDataSetChanged();
    }

}
