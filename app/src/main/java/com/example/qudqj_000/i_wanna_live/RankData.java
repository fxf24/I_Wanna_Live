package com.example.qudqj_000.i_wanna_live;

/**
 * Created by qudqj_000 on 2017-06-12.
 */

public class RankData {
    private String rank_num;
    private String name;
    private String score;

    public RankData(String rank_num, String name, String score){
        this.rank_num = rank_num;
        this.name = name;
        this.score = score;
    }

    public String getRank_num() {
        return rank_num;
    }

    public String getName(){
        return name;
    }

    public String getScore(){
        return score;
    }
}
