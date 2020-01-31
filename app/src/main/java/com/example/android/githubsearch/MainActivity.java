package com.example.android.githubsearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.githubsearch.utils.GitHubUtils;
import com.example.android.githubsearch.utils.NetworkUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mSearchResultsRV;
    private EditText mSearchBoxET;
    private GitHubSearchAdapter mGitHubSearchAdapter;

    private String[] dummySearchResults = {
            "Dummy search results",
            "Dummy search results",
            "Dummy search results",
            "Dummy search results",
            "Dummy search results",
            "Dummy search results",
            "Dummy search results",
            "Dummy search results",
            "Dummy search results",
            "Dummy search results"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchBoxET = findViewById(R.id.et_search_box);
        mSearchResultsRV = findViewById(R.id.rv_search_results);

        mSearchResultsRV.setLayoutManager(new LinearLayoutManager(this));
        mSearchResultsRV.setHasFixedSize(true);

        mGitHubSearchAdapter = new GitHubSearchAdapter();
        mSearchResultsRV.setAdapter(mGitHubSearchAdapter);

        Button searchButton = findViewById(R.id.btn_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = mSearchBoxET.getText().toString();
                if (!TextUtils.isEmpty(searchQuery)) {
                    doGitHubSearch(searchQuery);
                    //mGitHubSearchAdapter.updateSearchResults(new ArrayList<String>(Arrays.asList(dummySearchResults)));
                }
            }
        });
    }

    private void doGitHubSearch (String searchQueries){
        String url = GitHubUtils.buildGitHubSearchURL(searchQueries);       //Builds the URL in Github Utils
        Log.d(TAG, "querying url: " + url);
        new GitHubSearchTask().execute(url);


    }


    // Pass in a String, Progress Bar=void, Returns JSON String
    public class GitHubSearchTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {    // Variable number of arguments;
            String url = strings[0];                            // Arguments are stored as array
            String searchResults = null;
            try {
                searchResults = NetworkUtils.doHttpGet(url);    // Do HTTP Call
            } catch (IOException e) {
                e.printStackTrace();
            }
            return searchResults;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null){
                ArrayList<String> searchResultsList = new ArrayList<>();        // Create a new Array
                searchResultsList.add(s);                                       // Add output to the array
                mGitHubSearchAdapter.updateSearchResults(searchResultsList);    // Update the results
                mSearchBoxET.setText("");                                       // Clear text box


            }

        }


    }


}
