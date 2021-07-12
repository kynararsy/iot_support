package com.example.iot_asma_support;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class HistoryFragment extends Fragment {

    RecyclerView list;

    List<History> historyList;

    Adapter adapter;
    Context context;

    public HistoryFragment(Context context, List<History> historyList) {
        // Required empty public constructor
        this.context = context;
        this.historyList = historyList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        list = view.findViewById(R.id.list);

        adapter = new Adapter(context, historyList);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
        list.setAdapter(adapter);
        list.setLayoutManager(gridLayoutManager);

        // Inflate the layout for this fragment
        return view;
    }
}