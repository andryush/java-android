package com.arakelyan.guessperson;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;

    private ImageView personImage;

    private ArrayList<String> images;
    private ArrayList<String> names;
    private ArrayList<Button> buttons;

    private int numberOfQuestion;
    private int numberOfRightAnswer;

    String url = "http://www.posh24.se/kandisar";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0 = findViewById(R.id.btn_0);
        button1 = findViewById(R.id.btn_1);
        button2 = findViewById(R.id.btn_2);
        button3 = findViewById(R.id.btn_3);

        buttons = new ArrayList<>();
        buttons.add(button0);
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);

        personImage = findViewById(R.id.iv_person_image);

        images = new ArrayList<>();
        names = new ArrayList<>();

        getContent();
        playGame();

    }

    private void getContent() {

        DownloadContentTask contentTask = new DownloadContentTask();

        try {
            String content = contentTask.execute(url).get();

            String start = "<p class=\"link\">Topp 100 kändisar</p>";
            String finish = "<div class=\"col-xs-12 col-sm-6 col-md-4\">";

            Pattern pattern = Pattern.compile(start + "(.*?)" + finish);
            Matcher matcher = pattern.matcher(content);

            String splitContent = "";

            while (matcher.find()) {
                splitContent = matcher.group(1);
            }

            Pattern patternImages = Pattern.compile("<img src=\"(.*?)\"");
            Pattern patternNames = Pattern.compile("alt=\"(.*?)\"/>");

            Matcher matcherImages = patternImages.matcher(splitContent);
            Matcher matcherNames = patternNames.matcher(splitContent);

            while (matcherImages.find()) {
                images.add(matcherImages.group(1));
            }

            for (String img : images) {
                Log.i("MatcherImages", img);
            }

            while (matcherNames.find()) {
                names.add(matcherNames.group(1));
            }

            for (String nms : names) {
                Log.i("MatcherNames", nms);
            }



        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void playGame() {
        generateQuestion();
        DownloadImageTask imageTask = new DownloadImageTask();
        try {
            Bitmap bitmap = imageTask.execute(images.get(numberOfQuestion)).get();

            if (bitmap != null) {
                personImage.setImageBitmap(bitmap);

                for (int i = 0; i < buttons.size(); i++) {
                    if (i == numberOfRightAnswer) {
                        buttons.get(i).setText(names.get(numberOfQuestion));
                    }
                    else {
                        int wrongAnswer = generateWrongAnswer();
                        buttons.get(i).setText(names.get(wrongAnswer));
                    }
                }
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void generateQuestion() {

        numberOfQuestion = (int) (Math.random() * names.size());
        numberOfRightAnswer = (int) (Math.random() * buttons.size());

    }

    private int generateWrongAnswer() {
        return (int) (Math.random() * names.size());
    }

    public void onClickAnswer(View view) {
        Button button = (Button) view;
        String tag = button.getTag().toString();
        if (Integer.parseInt(tag) == numberOfRightAnswer) {
            Toast.makeText(this, "RIGHT :)", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "WRONG :( The right answer is " + names.get(numberOfQuestion), Toast.LENGTH_SHORT).show();
        }
        playGame();

    }


    private static class DownloadContentTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {

            URL url;
            HttpURLConnection urlConnection = null;
            StringBuilder stringBuilder = new StringBuilder();

            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line = bufferedReader.readLine();

                while (line != null) {
                    stringBuilder.append(line);
                    line = bufferedReader.readLine();
                }

                return stringBuilder.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }

            return null;
        }
    }

    private static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... strings) {

            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                return bitmap;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }

            return null;
        }
    }
}
