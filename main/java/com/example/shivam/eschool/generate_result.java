package com.example.shivam.eschool;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class generate_result extends AppCompatActivity{
    private TextView tv_username;
    String[] names;
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.generate_result);
        (new generate_result.FetchAllStudents()).execute();

        //tv_username.setText(BackgroundWorker.getInstance(this).getUsername(getApplicationContext()));

        lv=(ListView)findViewById(R.id.studentListView2);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),reg_teacher_marks.class);
                startActivity(intent);
            }
        });
    }
    class FetchAllStudents extends AsyncTask<Void,Void,Void> {

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
            ListView allStudentListView = (ListView)findViewById(R.id.studentListView2);
            allStudentListView.setAdapter(new generate_result.allStudentAdapter(getApplicationContext(),names));
            super.onPostExecute(aVoid);
        }
    }

    class allStudentAdapter extends BaseAdapter {
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
            View rowView = layoutInflater.inflate(R.layout.rowview_allstudent2,null);

            TextView name =(TextView)rowView.findViewById(R.id.textView25);
            name.setText(names[position]);
            return rowView;
        }
    }
}
