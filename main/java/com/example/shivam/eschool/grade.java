package com.example.shivam.eschool;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import static com.example.sagar.eschool.BackgroundWorker.KEY_REGNO;

public class grade extends AppCompatActivity {
    private TextView m1,m2,m3,m4;
    public static String[] names;
    public static String regno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grade);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            regno = BackgroundWorker.getString(getApplicationContext(),KEY_REGNO);
            Log.d("REG",regno);
            // and get whatever type user account id is
        }
        FetchAllStudents backgroundWorker = new FetchAllStudents(getApplication());
        backgroundWorker.execute(regno.substring(1));


        //tv_username.setText(BackgroundWorker.getInstance(this).getUsername(getApplicationContext()));



    }

    class FetchAllStudents extends AsyncTask<String,Void,String> {
        public Context context;

        FetchAllStudents (Context ctx){
            context = ctx;
        }

        String all_marks_url = "http://192.168.43.127/all_marks.php/";

        @Override
        protected String doInBackground(String... params) {
            String regx = params[0];
            URL url = null;
            try {
                url = new URL(all_marks_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("regno", "UTF-8") + "=" + URLEncoder.encode(regx, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                //result=result.substring(1);

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            names = result.split(",");
            Toast.makeText(context,result,Toast.LENGTH_SHORT).show();
            m1 = (TextView) findViewById(R.id.tv_mark1);
            m2 = (TextView) findViewById(R.id.tv_mark2);
            m3 = (TextView) findViewById(R.id.tv_mark3);
            m4=(TextView)findViewById(R.id.tv_average);
            m1.setText(names[1]);
            m2.setText(names[2]);
            m3.setText(names[3]);
            int a=Integer.valueOf(names[1]);
            int b=Integer.valueOf(names[2]);
            int c=Integer.valueOf(names[3]);
            int average=0;
            average=(a+b+c)/3;
            m4.setText(String.valueOf(average));
            super.onPostExecute(result);
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
            View rowView = layoutInflater.inflate(R.layout.rowview_allstudent,null);

            TextView name =(TextView)rowView.findViewById(R.id.textView23);
            name.setText(names[position]);
            return rowView;
        }
    }

}
