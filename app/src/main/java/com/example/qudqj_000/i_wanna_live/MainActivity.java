package com.example.qudqj_000.i_wanna_live;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    LinearLayout home;
    final int Start = 0;
    MyManageDB myDB;
    ArrayList<RankData> datas = new ArrayList<>();
    RankAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        home = (LinearLayout)findViewById(R.id.home_Menu);
        myDB = MyManageDB.getInstance(this);
        adapter = new RankAdapter(this, datas);
    }

    public void onClick(View v){
        if(v.getId() == R.id.start){
            startActivityForResult(new Intent(this, GameActivity.class), Start);
        }
        if(v.getId() == R.id.ranking){
            showRank();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Start && resultCode == RESULT_OK){
            String score = data.getStringExtra("score");

            recordSaveDialog(score);
        }
    }

    private void recordSaveDialog(String score_data){
        View view = getLayoutInflater().inflate(R.layout.new_record, null);
        final EditText name = (EditText)view.findViewById(R.id.name);
        final TextView score = (TextView)view.findViewById(R.id.score_text);

        score.setText(score_data);

        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("점수등록")
                .setView(view)
                .setPositiveButton("등록", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String n = name.getText().toString();
                        String s = score.getText().toString();

                        myDB.execINSERTrank(n, s);
                    }
                })
                .setNegativeButton("취소", null)
                .show();
    }

    private void showRank(){
        View view = getLayoutInflater().inflate(R.layout.rank_dialog, null);
        ListView lv1 = (ListView)view.findViewById(R.id.rank_list);
        lv1.setAdapter(adapter);

        String sql = "Select * from scoreboard order by score desc";
        datas.clear();
        int i = 1;
        try{
            Cursor recordset = myDB.execSELECTrank(sql);
            recordset.moveToFirst();
            do{
                datas.add(new RankData(String.valueOf(i++), recordset.getString(1), recordset.getString(2)));
                adapter.notifyDataSetChanged();
            }while(i <= 10 && recordset.moveToNext());
            recordset.close();

        }catch (SQLiteException e){
            e.printStackTrace();
        }

        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("랭킹")
                .setView(view)
                .setPositiveButton("닫기", null)
                .show();
    }
}

