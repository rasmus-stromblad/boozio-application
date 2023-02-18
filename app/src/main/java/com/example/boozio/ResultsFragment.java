package com.example.boozio;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultsFragment extends Fragment implements ResultsRecyclerAdapter.OnRecyclerListener{

    private RecyclerView resultsRecyclerView;
    private ResultsRecyclerAdapter recyclerAdapter;

    private DividerItemDecorator itemDecorator;


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ResultsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResultsFragment.
     */
    public static ResultsFragment newInstance(String param1, String param2) {
        ResultsFragment fragment = new ResultsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_results, container, false);

        resultsRecyclerView = rootView.findViewById(R.id.resultsRecyclerView);

        recyclerAdapter = new ResultsRecyclerAdapter(Controller.getInstance().getRecipesFound(), this);
        RecyclerView.LayoutManager recipesFoundLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        resultsRecyclerView.setLayoutManager(recipesFoundLayoutManager);
        resultsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        itemDecorator = new DividerItemDecorator(10, 60);
        resultsRecyclerView.addItemDecoration(itemDecorator);
        resultsRecyclerView.setAdapter(recyclerAdapter);

        // Inflate the layout for this fragment
        return rootView;
    }

    public void onResume() {
        super.onResume();

        recyclerAdapter.setItems(Controller.getInstance().getRecipesFound());
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRecyclerClick(int position) {
        Intent intent = new Intent(getContext(), RecipeInformationActivity.class);
        intent.putExtra("recipe_index", position);
        startActivity(intent);
    }
}