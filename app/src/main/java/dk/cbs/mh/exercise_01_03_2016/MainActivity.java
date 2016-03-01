package dk.cbs.mh.exercise_01_03_2016;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    private Button btnRead, btnWrite;
    private TextView txtOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initListeners();
    }

    public void initViews(){
        btnRead = (Button) findViewById(R.id.btnRead);
        btnWrite = (Button) findViewById(R.id.btnWrite);
        txtOutput = (TextView) findViewById(R.id.txtOutput);
    }

    private void initListeners(){

        btnWrite.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                writeFile();
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                txtOutput.setText(readFile());
            }
        });


    }

    public String readFile(){
        String FILENAME = "debug_file";
        String contentsAsString = "";

        try{
            FileInputStream ios = openFileInput(FILENAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(ios));

            StringBuilder builder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            contentsAsString = builder.toString();

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

            return contentsAsString;
    }

    public void writeFile(){
        String FILENAME = "debug_file";
        String contents = "Debug message line 1";

        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(contents.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Logger.getAnonymousLogger().info("Info written to file");

    }

}
