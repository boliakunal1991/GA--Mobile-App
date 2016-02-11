package com.example.android.learn;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    TextView name_desc;
    TextView desc;
    Button button_info_back;
    Button button_back;
    Button quiz;
    Button button_quiz_back;
    Button button_submit;
    Button button_sb_back;
    Button exercise;
    Button button_exercise_back;
    int lesson_ctr = 0;
    int exer_ctr = 0;
    int quiz_ctr = 0;


    Button overall_score_button;
    int score_main=0;
    public static final String[] titles = new String[] { "Lesson 1- Bullwhip Effect",
            "Lesson 2-", "Lesson 3-", "Lesson 4-", "Lesson 5-", "Lesson 6-", "Lesson 7-","Lesson 8-" };

    //images of the lesson icons
    public static final Integer[] images = {  R.drawable.one,
            R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.six,
            R.drawable.seven, R.drawable.eight, R.drawable.nine};
    // Content of the lesson, starting from lesson one
    public static final String[] detail_titles = new String[] { "What is the Bullwhip Effect? Understanding the concept & definition",
            "Lesson 2-", "Lesson 3-", "Lesson 4-", "Lesson 5-", "Lesson 6-", "Lesson 7-","Lesson 8-" };

    //content of the lesson
    public static final String[] detail_descriptions = new String[] {
            "Through the numerous stages of a supply chain; key factors such as time and supply of order decisions, demand for the supply, lack of communication and disorganization can result in one of the most common problems in supply chain management.  This common problem is known as the bullwhip effect; also sometimes the whiplash effect. In this blog post we will explain this concept and outline some of the contributing factors to this issue." +
                    System.getProperty("line.separator") + System.getProperty("line.separator") + "What is the bullwhip effect?" + System.getProperty("line.separator") + System.getProperty("line.separator") + "The bullwhip effect can be explained as an occurrence detected by the supply chain where orders sent to the manufacturer and supplier create larger variance then the sales to the end customer.  These irregular orders in the lower part of the supply chain develop to be more distinct higher up in the supply chain.  This variance can interrupt the smoothness of the supply chain process as each link in the supply chain will over or underestimate the product demand resulting in exaggerated fluctuations.Let’s look at an example; the actual demand for a product and its materials start at the customer, however often the actual demand for a product gets distorted going down the supply chain. Let’s say that an actual demand from a customer is 8 units, the retailer may then order 10 units from the distributor; an extra 2 units are to ensure they don’t run out of floor stock.\n" +
                    "\n",
            "Fill Content",
            "Fill Content",
            "Fill Content",
            "Fill Content",
            "Fill Content",
            "Fill Content",
            "Fill Content"};

    ListView listView;
    List<RowItem> rowItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set the display screen to default and the landing screen to be displayed

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //setup the tab activity, the one we see at the landing screen
        TabHost th = (TabHost) findViewById(R.id.tabHost);
        // this setups the th activity we just defined
        th.setup();
        TabHost.TabSpec specs = th.newTabSpec("tab1");
        //set the content of tab1
        specs.setContent(R.id.tab1);
        //set the label of tab1
        specs.setIndicator("Lessons");
        //add specifications to tab1
        th.addTab(specs);

        Toast.makeText(MainActivity.this, "hello!!!", Toast.LENGTH_SHORT).show();

        //similarly for tab2
        specs = th.newTabSpec("tab2");
        specs.setContent(R.id.tab2);
        specs.setIndicator("Exam");
        th.addTab(specs);

        final  Context context = this;
        //overall score button setup
        overall_score_button = (Button)findViewById(R.id.score_button1);



        overall_score_button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Bundle dataBundle = new Bundle();
                Toast.makeText(MainActivity.this, String.valueOf(quiz_ctr) + String.valueOf(lesson_ctr), Toast.LENGTH_SHORT).show();
                dataBundle.putInt("quizcounter", quiz_ctr);
                dataBundle.putInt("lessoncounter", lesson_ctr);
                Intent l = new Intent(MainActivity.this, overallscore.class);
                l.putExtras(dataBundle);
                startActivity(l);
                //setContentView(R.layout.activity_overallscore);

            }
        });

        //list view setup goes here
        //rowItems is initialized earlier on the top of the documentation
        rowItems = new ArrayList<RowItem>();
        //here we add images and titles to the listview
        for (int i = 0; i < titles.length; i++) {
            RowItem item = new RowItem(images[i], titles[i]);
            rowItems.add(item);
        }

        //attach the listview element with the listview varriable
        listView = (ListView) findViewById(R.id.listView);
        //attach the adapter to the listview
        CustomListViewAdapter adapter = new CustomListViewAdapter(this,
                R.layout.list_item, rowItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

    }



    public void overallscore(){
       /* setContentView(R.layout.activity_overallscore);
        final Context context1= this;
        button_back = (Button) findViewById(R.id.button_back);

        button_back.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(context1, MainActivity.class);
                startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context1.startActivity(startIntent);

            }
        });

        TextView les_scr = (TextView)findViewById(R.id.textView_1);
        les_scr.setText(String.valueOf(lesson_ctr));
        TextView quiz_scr = (TextView)findViewById(R.id.textView_2);
        quiz_scr.setText( String.valueOf(quiz_ctr) ); */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final Context context;
        context=getBaseContext();
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_information) {
            //if the option information is selected, this activity is loaded
            setContentView(R.layout.information);
            TextView callnumber = (TextView) findViewById(R.id.textView_call);
            button_info_back = (Button)findViewById(R.id.button_info_back);
            button_info_back.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    Intent startIntent = new Intent(context, MainActivity.class);
                    startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(startIntent);
                }
            });


            //onclicklistener for email
            callnumber.setOnClickListener(new View.OnClickListener() {



                @Override
                public void onClick(View v) {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto","boliakunal1991@gmail.com", null));

                    startActivity(Intent.createChooser(emailIntent, "Send email..."));

                    //startActivity(intent);
                }
            });
            return true;
        }
        else{
            //this goes for uninstalling the application
            Uri packageUri = Uri.parse("package:com.example.android.zookeeper");
            //intent to uninstall the app
            Intent uninstallIntent =
                    new Intent(Intent.ACTION_UNINSTALL_PACKAGE, packageUri);
            startActivity(uninstallIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //function that handles the item selected in the listview
        //once it is clicked, it will load a new activity for lessons and the content will be loaded
        setContentView(R.layout.lesson);
        //setup textview for the running timer
        final TextView _tv = (TextView) findViewById( R.id.timer_text );
        //15mins here
        //can change the value of minutes by replacing 15 by other number
        new CountDownTimer(15*60000, 1000) {

            public void onTick(long millisUntilFinished) {
                _tv.setText("Time remaining: " +new SimpleDateFormat("mm:ss").format(new Date( millisUntilFinished)));
            }

            public void onFinish() {
                _tv.setText("done!");
            }
        }.start();

        final Context context1 = this;

        //coding for quiz button
        quiz= (Button) findViewById(R.id.button_quiz);
        //onclick listener for the quiz button
        quiz.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                quiz();
            }
        });

        //similarly for exercise
        exercise= (Button) findViewById(R.id.button_exercise);
        exercise.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                lesson_ctr++;
                Intent l = new Intent(MainActivity.this, LinearActivity.class);
                startActivity(l);
            }
        });

        //initialization and onclicklistener for back button
        button_back = (Button) findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(context1, MainActivity.class);
                startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context1.startActivity(startIntent);
            }
        });
        name_desc = (TextView) this.findViewById(R.id.textview_animal_name_detail);
        desc = (TextView) this.findViewById(R.id.textView_short_desc);
        //imageview_large = (ImageView) this.findViewById(R.id.imageView_large);

        name_desc.setText(detail_titles[position]);
        desc.setText(readTxt());
        //desc.setText(detail_descriptions[position]);
        // imageview_large.setImageResource(large_images[position]);

    }

    public void quiz(){

        //for quiz button and activity
        setContentView(R.layout.quiz);
        lesson_ctr++;
        //Toast message for timer start
        Toast.makeText(getApplicationContext(), "You Have 3 minutes",
                Toast.LENGTH_LONG).show();
        final Context context= this;
        //back button coding
        button_quiz_back = (Button)findViewById(R.id.button_quiz_back);
        button_quiz_back.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(context, MainActivity.class);
                startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(startIntent);
            }
        });

        //5min timer for quiz
        final TextView _tv = (TextView) findViewById( R.id.textView2_timer );
        new CountDownTimer(5*60000, 1000) {

            public void onTick(long millisUntilFinished) {
                _tv.setText("Time remaining: " +new SimpleDateFormat("mm:ss").format(new Date( millisUntilFinished)));
            }

            public void onFinish() {
                _tv.setText("done!");
            }
        }.start();

        button_submit = (Button)findViewById(R.id.button_submit);
        button_submit.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                //dialogue box for submit quiz button
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Your test is over ");
                //play a small sound clip
                final MediaPlayer mp = MediaPlayer.create(context, R.raw.nice);
                mp.start();
                builder1.setCancelable(true);


                builder1.setPositiveButton("OK, Take me to the score board!",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                                scoreboard();

                            }
                        });
               /* builder1.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });*/

                AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        });
    }

  /*  public void exercise1(){
        String result ;
        setContentView(R.layout.exercise_1);
        final Context context = this;
        button_exercise_back = (Button)findViewById(R.id.button_back_exercise);
        button_exercise_back.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(context, MainActivity.class);
                startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(startIntent);
            }
        });
       // result=solve("12x +  2y =32", "9x -5y=55");
    } */

   /* public String solve(String s1, String s2){    //only works if coefficients > 0

        String str;
        str=s1 + " " +s2
        str=str.replace(/[^0123456789.-]/g, ' '); //eliminate letters
        str=str.replace( /\s\s+/g, ' ' ) ;        //no double spaces


        String n[]= new String[] {str.split(" ")};            //put into an array
        int a=0,b=1,c=2,d=3,e=4,f=5 ;     //see what were doing
        float x = ( n[c]*n[e] -n[b]*n[f])/(n[a]*n[e] - n[b]*n[d])
        float y= (n[c]-n[a]*x)/n[b]

        return({x:x, y:y})
    } */

    private String readTxt(){

        InputStream inputStream = getResources().openRawResource(R.raw.hello);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int i;
        try {
            i = inputStream.read();
            while (i != -1)
            {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return byteArrayOutputStream.toString();
    }


    public void scoreboard(){
        int i=0;
        //RadioButton[] answers = new RadioButton[] {};
        //for (i=0;i<=7;i++){
        //    answer[i]= (RadioButton)findViewById(R.id.r)

       // }
        quiz_ctr++;
        int score =0;
        RadioButton q1r1 = (RadioButton)findViewById(R.id.radioButton1_q1);
        RadioButton q2r1 = (RadioButton)findViewById(R.id.radioButton1_q2);
        RadioButton q3r1 = (RadioButton)findViewById(R.id.radioButton1_q3);
        RadioButton q4r1 = (RadioButton)findViewById(R.id.radioButton1_q4);
        RadioButton q5r1 = (RadioButton)findViewById(R.id.radioButton1_q5);
        RadioButton q6r1 = (RadioButton)findViewById(R.id.radioButton1_q6);
        RadioButton q7r1 = (RadioButton)findViewById(R.id.radioButton1_q7);
        RadioButton q8r1 = (RadioButton)findViewById(R.id.radioButton1_q8);

        if (q1r1.isChecked()){
            score++;
        }
        if (q2r1.isChecked()){
            score++;
        }
        if (q3r1.isChecked()){
            score++;
        }
        if (q4r1.isChecked()){
            score++;
        }
        if (q5r1.isChecked()){
            score++;
        }
        if (q6r1.isChecked()){
            score++;
        }
        if (q7r1.isChecked()){
            score++;
        }
        if (q8r1.isChecked()){
            score++;
        }
        setContentView(R.layout.score_board);
        final Context context = this;
        TextView score_number = (TextView)findViewById(R.id.textView_number_score);
        score_number.setText(" " + String.valueOf(score)+ "/8");
        score_main= score;
        quiz_ctr++;
        //if(radio)
        button_sb_back = (Button)findViewById(R.id.button_back_scoreboard);
        button_sb_back.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(context, MainActivity.class);
                startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(startIntent);
            }
        });

    }
}
