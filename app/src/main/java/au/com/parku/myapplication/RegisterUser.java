package au.com.parku.myapplication;



import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class RegisterUser extends AppCompatActivity {

    EditText userName,passwprd;
    Button resister,login;
    ProgressBar progressBar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        userName=(EditText) findViewById(R.id.editText1);;
        passwprd=(EditText) findViewById(R.id.editText2);
        resister=(Button) findViewById(R.id.button1);

        progressBar=(ProgressBar) findViewById(R.id.progressBar1);
        progressBar.setVisibility(View.GONE);

        resister.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);

                String s1=userName.getText().toString();
                String s2=passwprd.getText().toString();
                new ExecuteTask().execute(s1,s2);
            }
        });

    }

    class ExecuteTask extends AsyncTask<String, Integer, String>
    {

        @Override
        protected String doInBackground(String... params) {

            PostData(params);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            progressBar.setVisibility(View.GONE);
        }

    }
    public void PostData(String[] valuse) {
        try
        {
            HttpClient httpClient=new DefaultHttpClient();
            HttpPost httpPost=new HttpPost("http://10.9.1.167:8080/ParkU/api/user/register-user?username="+valuse[0]+"&password="+valuse[1]);
            httpClient.execute(httpPost);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }

}


