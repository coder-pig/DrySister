package com.coderpig.drysisters.bean.entity;

import java.util.ArrayList;

/**
 * 描述：
 *
 * @author jay on 2018/1/12 16:22
 */

public class SisterResult {
    private boolean error;
    private ArrayList<Sister> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public ArrayList<Sister> getResults() {
        return results;
    }

    public void setResults(ArrayList<Sister> results) {
        this.results = results;
    }
}
