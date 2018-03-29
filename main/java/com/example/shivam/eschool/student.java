package com.example.shivam.eschool;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.AsyncListUtil;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class student extends AppCompatActivity{
    private TextView tv_username;
    String[] names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student);
        (new FetchAllStudents()).execute();

        //tv_username.setText(BackgroundWorker.getInstance(this).getUsername(getApplicationContext()));



    }

    class FetchAllStudents extends AsyncTask<Void,Void,Void>{

        String all_student_url="http://192.168.43.127/allStudent.php/";

        @Override
        protected Void doInBackground(Void... params) {
            URL url = null;
            try {
                url = new URL(all_student_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result = "";
                String line;
                while((line = bufferedReader.readLine()) != null){
                    result += line;
                }
                result=result.substring(1);
                names=result.split(",");
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            ListView allStudentListView = (ListView)findViewById(R.id.studentListView);
            allStudentListView.setAdapter(new allStudentAdapter(getApplicationContext(),names));
            super.onPostExecute(aVoid);
        }
    }

    class allStudentAdapter extends BaseAdapter{
Context context;
        String[] names;
        public allStudentAdapter(Context context,String[] names){
            this.context=context;
            this.names=names;
        }
        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public Object getItem(int position) {
            return names[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
            View rowView = layoutInflater.inflate(R.layout.rowview_allstudent,null);

            TextView name =(TextView)rowView.findViewById(R.id.textView23);
            name.setText(names[position]);
            return rowView;
        }
    }

}
