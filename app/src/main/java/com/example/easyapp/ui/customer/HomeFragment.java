package com.example.easyapp.ui.customer;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.easyapp.R;
import com.example.easyapp.adapters.HomeHorAdapter;
import com.example.easyapp.adapters.HomeVerAdapter;
import com.example.easyapp.feature.Iclickitemrecycler;
import com.example.easyapp.feature.ShippingActivity;
import com.example.easyapp.model.HomeHorModel;
import com.example.easyapp.model.HomeVerModel;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    RecyclerView homeHorizontalRec, homeVerticalRec;
    List<HomeHorModel> homeHorModelList;
    HomeHorAdapter homeHorAdapter;

    //vertical
    List<HomeVerModel>homeVerModelList;
    HomeVerAdapter homeVerAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        homeHorizontalRec = root.findViewById(R.id.home_hor_rec);
        homeVerticalRec = root.findViewById(R.id.home_ver_rec);

        //Horizontal RecyclerView

        homeHorizontalRec.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeHorModelList = new ArrayList<>();
        homeHorModelList.add(new HomeHorModel(R.drawable.ship, "Gửi Hàng"));
        homeHorModelList.add(new HomeHorModel(R.drawable.voucher, "Ưu đãi"));
        homeHorModelList.add(new HomeHorModel(R.drawable.car, "Di chuyển"));
        homeHorModelList.add(new HomeHorModel(R.drawable.gift, "Quà tặng"));

        homeHorAdapter = new HomeHorAdapter(getActivity().getApplicationContext(), homeHorModelList, new Iclickitemrecycler() {
            @Override
            public void onclickItemfeatures(HomeHorModel homeHorModel) {
                onClickGotoShipping(homeHorModel);
            }
        });
        homeHorAdapter.notifyDataSetChanged();
        homeHorizontalRec.setAdapter(homeHorAdapter);

        homeHorizontalRec.setHasFixedSize(true);
        homeHorizontalRec.setNestedScrollingEnabled(false);

        //Vertical RecyclerView
        homeVerModelList = new ArrayList<>();
        homeVerModelList.add(new HomeVerModel(R.drawable.bannersale));
        homeVerModelList.add(new HomeVerModel(R.drawable.bannersale));
        homeVerModelList.add(new HomeVerModel(R.drawable.bannersale));
        homeVerModelList.add(new HomeVerModel(R.drawable.bannersale));

        homeVerAdapter = new HomeVerAdapter(getActivity(),homeVerModelList);
        homeVerAdapter.notifyDataSetChanged();
        homeVerticalRec.setAdapter(homeVerAdapter);

        homeVerticalRec.setHasFixedSize(true);
        homeVerticalRec.setNestedScrollingEnabled(false);
        homeVerticalRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

        homeHorizontalRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        // Inflate the layout for this fragment

        return root;
    }
    private void onClickGotoShipping(HomeHorModel homeHorModel){
        Intent intent = new Intent(getActivity(), ShippingActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        startActivity(intent);
    }
}