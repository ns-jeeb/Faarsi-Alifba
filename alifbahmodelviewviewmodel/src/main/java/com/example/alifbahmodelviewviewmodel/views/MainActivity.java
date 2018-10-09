package com.example.alifbahmodelviewviewmodel.views;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.example.alifbahmodelviewviewmodel.MyAbstrackClass;
import com.example.alifbahmodelviewviewmodel.R;
import com.example.alifbahmodelviewviewmodel.databinding.ActivityMainBinding;
import com.example.alifbahmodelviewviewmodel.viewmodels.DataViewModel;

public class MainActivity extends AppCompatActivity implements MyAbstrackClass.MyInterface {
    private DataViewModel dataViewModel;
    public TextView mTxtName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = bind();
        initRecyclerView(view);
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerViewDataBinding = view.findViewById(R.id.data_recycler_view);
        recyclerViewDataBinding.setLayoutManager(new LinearLayoutManager(recyclerViewDataBinding.getContext()));
        recyclerViewDataBinding.addItemDecoration(new DividerItemDecoration(recyclerViewDataBinding.getContext(), LinearLayoutManager.VERTICAL));
    }
    ActivityMainBinding binding;
    private View bind() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        dataViewModel = new DataViewModel();
        binding.setViewModel(dataViewModel);
        return binding.getRoot();
    }

    @Override
    protected void onResume() {
        super.onResume();
        dataViewModel.setUp();
    }

    @Override
    protected void onPause() {
        super.onPause();
        dataViewModel.tearDown();
    }

    @Override
    public void showName(String name) {
        binding.name.setText(name);
    }
}
