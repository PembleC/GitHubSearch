package com.example.android.githubsearch.utils;

import android.net.Uri;

public class GitHubUtils {
    private final static String GITHUB_SEARCH_BASE_URL = "https://api.github.com/search/repositories";
    private final static String GITHUB_SEARCH_QUERY_PARAM = "q";
    private final static String GITHUB_SEARCH_SORT_PARAM = "sort";
    private final static String GITHUB_SEARCH_SORT_VALUE = "stars";

    public static String buildGitHubSearchURL(String q) {
        return Uri.parse(GITHUB_SEARCH_BASE_URL).buildUpon()
                  .appendQueryParameter(GITHUB_SEARCH_QUERY_PARAM, q)
                  .appendQueryParameter(GITHUB_SEARCH_SORT_PARAM, GITHUB_SEARCH_SORT_VALUE)
                  .build()
                  .toString();
    }
}
