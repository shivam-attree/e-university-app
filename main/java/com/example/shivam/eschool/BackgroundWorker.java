package com.example.shivam.eschool;

        import android.app.AlertDialog;
        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.AsyncTask;
        import android.util.Log;
        import android.widget.Toast;

        import java.io.BufferedReader;
        import java.io.BufferedWriter;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.OutputStream;
        import java.io.OutputStreamWriter;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.net.URLEncoder;


public class BackgroundWorker extends AsyncTask<String, Void, String> {
    AlertDialog alertDialog;
    private static Context context;
    public static BackgroundWorker mInstance;
    public static final String SHARED_PREF_NAME="mysharedpref12";
    public static final String KEY_USERNAME="username";
    public static final String KEY_PASSWORD="password";
    public static final String KEY_REGNO="regno";
    public static final String KEY_NAME="name";
    public static final String KEY_SURNAME="surname";
    public static final String KEY_SEM="sem";
    public static final String KEY_BRANCH="branch";
    public String reg;
    public static final String KEY_A="marks1";
    public static final String KEY_B="marks2";
    public static final String KEY_C="marks3";

    BackgroundWorker (Context ctx){
        context = ctx;
    }
    public static synchronized  BackgroundWorker getInstance(Context ctx){
        if(mInstance==null){
            mInstance=new BackgroundWorker(ctx);
        }
        return mInstance;
    }
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];

        String login_url = "http://192.168.43.127/login.php/";
        String reg_url = "http://192.168.43.127/register.php/";
        String reg1_url = "http://192.168.43.127/register1.php/";
        String res_url ="http://192.168.43.127/result.php/";
        String all_student_url="http://192.168.43.127/allStudent.php/";
        String login_marks_url="http://192.168.43.127/login_marks.php";
        String notification_url="http://192.168.43.127/notificatios.php";
        String all_notification_url="http://192.168.43.127/all_notifications.php";
                if(type.equals("login")){
            try {
                String user_name = params[1];
                String password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"+ URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result = "l";
                String line;
                while((line = bufferedReader.readLine()) != null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                String[] results = result.split(" ");
                if(!result.equals("lfailure")){
                    putString(context,KEY_USERNAME,user_name);
                    putString(context,KEY_PASSWORD,password);
                    putString(context,KEY_BRANCH,results[3]);
                    putString(context,KEY_NAME,results[1]);
                    putString(context,KEY_SEM,results[4]);
                    putString(context,KEY_REGNO,results[0]);
                    putString(context,KEY_SURNAME,results[2]);
                    /*putString(context,KEY_A,results[5]);
                    putString(context,KEY_B,results[6]);
                    putString(context,KEY_C,results[7]);*/

                }
                else{
                    putString(context,KEY_USERNAME,null);
                    putString(context,KEY_PASSWORD,null);
                }
                Log.d("Result",result);
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if(type.equals("login1")){
            try {
                String Registration = params[1];
                URL url = new URL(login_marks_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("regno","UTF-8")+"="+URLEncoder.encode(Registration,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result = "cd";
                String line;
                while((line = bufferedReader.readLine()) != null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                String[] results = result.split(" ");
                if(!result.equals("cdfailure")){
                    /*putString(context,KEY_USERNAME,user_name);
                    putString(context,KEY_PASSWORD,password);
                    putString(context,KEY_BRANCH,results[3]);
                    putString(context,KEY_NAME,results[1]);
                    putString(context,KEY_SEM,results[4]);
                    putString(context,KEY_REGNO,results[3]);
                    putString(context,KEY_SURNAME,results[2]);*/
                  //putString(context,KEY_REGNO,results[3]);
                    putString(context,KEY_A,results[0]);
                    putString(context,KEY_B,results[1]);
                    putString(context,KEY_C,results[2]);

                }
                else{
                    putString(context,KEY_REGNO,null);
                   // putString(context,KEY_PASSWORD,null);
                }
                //Log.d("Result",result);
                Log.d("REsult",result);
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if(type.equals("register")) {
            try {
                String regno = params[1];
                String name = params[2];
                String surname = params[3];
                String username = params[4];
                String password = params[5];
                String branch = params[6];
                String sem = params[7];

                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("regno","UTF-8")+"="+URLEncoder.encode(regno,"UTF-8")+"&"+ URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8") + "&" +
                        URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+ URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8") + "&" +
                        URLEncoder.encode("surname","UTF-8")+"="+URLEncoder.encode(surname,"UTF-8")+"&"+ URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8") + "&" +
                        URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"+ URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8") + "&" +
                        URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"+ URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8") + "&" +
                        URLEncoder.encode("branch","UTF-8")+"="+URLEncoder.encode(branch,"UTF-8")+"&"+ URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8") + "&" +

                        URLEncoder.encode("sem","UTF-8")+"="+URLEncoder.encode(sem,"UTF-8")+"&"+ URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result = "r";
                String line;
                while((line = bufferedReader.readLine()) != null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("register1")) {
            try {
                String mark1 = params[1];
                String mark2 = params[2];
                String mark3 = params[3];
                String registration=  params[4];

                URL url = new URL(reg1_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("Marks1","UTF-8")+"="+URLEncoder.encode(mark1,"UTF-8")+"&"+ URLEncoder.encode("Marks2","UTF-8")+"="+URLEncoder.encode(mark2,"UTF-8") + "&" + URLEncoder.encode("Marks3","UTF-8")+"="+URLEncoder.encode(mark3,"UTF-8")
                        +"&"+URLEncoder.encode("registration","UTF-8")+"="+URLEncoder.encode(registration,"UTF-8") ;
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result = "m";
                String line;
                while((line = bufferedReader.readLine()) != null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("register2")) {
            try {
                //Log.e("abc","asdfgh");
                String notification = params[1];
                String registration=params[2];
                URL url = new URL(notification_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("notification","UTF-8")+"="+URLEncoder.encode(notification,"UTF-8")+"&"+URLEncoder.encode("registration","UTF-8")+"="+URLEncoder.encode(registration,"UTF-8") ;
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result = "n";
               // Log.e("bcd","asdfghj");
                String line;
                while((line = bufferedReader.readLine()) != null){
                    result += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public boolean isLoggedIn(){
        if(getString(context,KEY_USERNAME)!=null){
            return true;
        }
        return false;
    }
    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Status");
    }

    @Override
    protected void onPostExecute(String result) {

        if(result.equals("failure")){
            //Intent intent=new Intent(context,Main2Activity.class);
            //context.startActivity(intent);
            alertDialog.setMessage(result);
            alertDialog.show();
        }
        else
            {

            if(result.substring(0,1).equals("l")){
                if(result.equals("lfailure")){
                    alertDialog.setMessage(result.substring(1,result.length()));
                    alertDialog.show();}
                /*else if (result.substring(0,2).equals("cd")){
                    alertDialog.setMessage(result.substring(1,result.length()));
                    alertDialog.show();
                }*/
                else{
            Intent intent=new Intent(context,Main2Activity.class);
                    //Intent intent3=new Intent(context,grade.class);
                    //intent.putExtra("regno",reg);
                    intent.putExtra("marks1",KEY_A);
                    intent.putExtra("marks2",KEY_B);
                    intent.putExtra("marks",KEY_C);
                    //putString(context,"reg",reg);
            context.startActivity(intent);
                    //context.startActivity(intent3);
                }
            }
            else if(result.substring(0,1).equals("m") || result.substring(0,1).equals("r") || result.substring(0,1).equals("n")){
            alertDialog.setMessage(result.substring(1,result.length()));
            alertDialog.show();}
        }}



    public static String getUsername(Context context){
        return getString(context,KEY_USERNAME);
    }
    public static String getUserEmail(Context context){
        return getString(context,KEY_PASSWORD);
    }

    public static SharedPreferences getSharedPreference(Context context){
        return context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
    }
    public static SharedPreferences.Editor getPreferenceEditor(Context context){
        return getSharedPreference(context).edit();
    }
    public static void putString(Context context,String KEY,String VALUE){
        getPreferenceEditor(context).putString(KEY,VALUE)
                .commit();

    }
    public static String getString(Context context,String KEY){
        return getSharedPreference(context).getString(KEY,null);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }



}
