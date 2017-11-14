package com.epicodus.pdxbranch.ui;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.pdxbranch.Constants;
import com.epicodus.pdxbranch.R;
import com.epicodus.pdxbranch.adapters.MeetupGroupAdapter;
import com.epicodus.pdxbranch.models.MeetupGroup;
import com.epicodus.pdxbranch.services.MeetupService;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GroupsListFragment extends Fragment {
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.emptyView) TextView mEmptyView;

    public ArrayList<MeetupGroup> mMeetupGroups = new ArrayList<>();

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentSearch;

    public GroupsListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mEditor = mSharedPreferences.edit();

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_groups_list, container, false);
        ButterKnife.bind(this, view);
        mRecentSearch = mSharedPreferences.getString(Constants.PREFERENCES_SEARCH_KEY, null);
        findMeetupGroups(mRecentSearch);
        return view;
    }

    private void addToSharedPreferences(String search) {
        mEditor.putString(Constants.PREFERENCES_SEARCH_KEY, search).apply();
    }

    private void findMeetupGroups(String query) {
        final MeetupService meetupService = new MeetupService();
        meetupService.findGroups(query, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mMeetupGroups = meetupService.processResults(response);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mMeetupGroups.size() == 0) {
                            mEmptyView.setVisibility(View.VISIBLE);
                            mRecyclerView.setVisibility(View.GONE);
                        } else {
                            mEmptyView.setVisibility(View.GONE);
                            mRecyclerView.setVisibility(View.VISIBLE);
                            mRecyclerView.setAdapter(new MeetupGroupAdapter(getActivity(), mMeetupGroups));
                            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            mRecyclerView.setHasFixedSize(true);
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.searchbar_menu, menu);
        inflater.inflate(R.menu.toolbar_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Search Groups...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                addToSharedPreferences(query);
                findMeetupGroups(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_log_out:
                logout();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
