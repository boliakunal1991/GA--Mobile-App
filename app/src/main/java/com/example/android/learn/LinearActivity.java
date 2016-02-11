package com.example.android.learn;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.test.suitebuilder.annotation.LargeTest;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class LinearActivity extends Activity {

    String Str_X;
    String Str_Y;
    TextView Text_Eqn, Text_X, Text_Y;
    EditText Edit_a1, Edit_b1, Edit_c1, Edit_a2, Edit_b2, Edit_c2;
    String Str_Eqn;
    Button Butt_Get;
    TextView num;

   // float a1, a2, b1, b2, c1, c2;
    float xsol, ysol;
    float x, y;
    int i = 1;
    //arrays for storing different value of coefficient of x, y, c
    int[] a1 = {4,2,6,3,7};
    int[] b1 = {2,4,8,4,6};
    int[] c1 = {7,3,5,7,3};
    int[] a2 = {2,4,8,4,6};
    int[] b2 = {7,3,5,7,3};
    int[] c2 = {4,2,6,3,7};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear);

        final Context context = this;
        //initialize all the textview in the activity
        Text_Eqn = (TextView) findViewById(R.id.textView2);
        Text_X = (TextView) findViewById(R.id.textView3);
        Text_Y = (TextView) findViewById(R.id.textView4);
        num = (TextView) findViewById(R.id.textView_ex_num);
        Edit_a1 = (EditText) findViewById(R.id.editText1);
        Edit_b1 = (EditText) findViewById(R.id.editText2);
        //Edit_c1 = (EditText) findViewById(R.id.editText3);
        //Edit_a2 = (EditText) findViewById(R.id.editText4);
        //Edit_b2 = (EditText) findViewById(R.id.editText5);
        //Edit_c2 = (EditText) findViewById(R.id.editText6);

        Butt_Get = (Button) findViewById(R.id.button1);


        //change
        changequestion();
            /* Str_Eqn = "Consider our equation is like below<br/><br/>" +
                    "<font color = '#FF0000'>  3 x - y = 8" +
                    "<br/> x + 2 y = 5</font><br/>" + "<font color = '#0000FF'> Then Solution will be: x = 3" +
                    "and y = 1</font> <br/> <br/> <br/>" + "Solve the following equation and enter the value of x & y<br/><br/>" +
                    "<font color = '#FF0000'>" + a1[i]+ "x "+"+ " + b1[i] + "y " + "= " + c1[i] +
                    "<br/>"+a2[i]+ "x "+"+ " + b2[i] + "y " + "= " + c2[i]+"</font><br/>" +
                    " <br/> <br/> <br/>";

            Text_Eqn.setText(Html.fromHtml(Str_Eqn)); */

      /*  a1 = 1;
        b1 = 1;
        c1 = 10;
        a2 = 1;
        b2 = -1;
        c2 = 8; */


        Butt_Get.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                try {

                    //throw an error if user has not entered any value
                    x = Float.parseFloat(Edit_a1.getText().toString());
                    y = Float.parseFloat(Edit_b1.getText().toString());


                    linear();

                    //check if the answer is correct and also the exercise number is less than or equal to 5
                    //we have 5 exercises only
                    if(x == xsol  ){

                        //answer is correct
                      if(i <=5){
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                    builder1.setMessage("Result " + System.getProperty("line.separator")+ System.getProperty("line.separator")+ "Correct :)");

                    builder1.setCancelable(true);


                          //if user is taken to the next exercise
                    builder1.setPositiveButton("Next Exercise",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    i++;

                                    changequestion();
                                    Edit_a1.setText("");
                                    Edit_b1.setText("");
                                    num.setText(String.valueOf(i));
                                    Text_X.setText(Html.fromHtml(""));
                                    Text_Y.setText(Html.fromHtml(""));
                                    dialog.cancel();
                                }
                            });
                          //if user wish to do nothing
                    builder1.setNegativeButton("Ok!",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Edit_a1.setText("");
                                    Edit_b1.setText("");
                                    dialog.cancel();
                                    //Text_X.setText(Html.fromHtml(Str_X));
                                    //Text_Y.setText(Html.fromHtml(Str_Y));

                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show(); }
                        else{
                          i=1;
                          Intent l = new Intent(LinearActivity.this, MainActivity.class);
                          startActivity(l);
                      }
                    } else {
                        //if user enters wrong answer
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                        builder1.setMessage("Result " + System.getProperty("line.separator")+ System.getProperty("line.separator")+ "Wrong Answer!");

                        builder1.setCancelable(true);


                        //for try again
                        builder1.setPositiveButton("Try Again",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Edit_a1.setText("");
                                        Edit_b1.setText("");
                                        dialog.cancel();
                                    }
                                });
                        //to show the answer
                        builder1.setNegativeButton("Show Answer",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Edit_a1.setText("");
                                        Edit_b1.setText("");
                                        dialog.cancel();
                                        Text_X.setText(Html.fromHtml(Str_X));
                                        Text_Y.setText(Html.fromHtml(Str_Y));

                                    }
                                });

                        AlertDialog alert11 = builder1.create();
                        alert11.show();

                    }

                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), "Enter The Values Correctly",
                            Toast.LENGTH_LONG).show();
                    Log.e("df", e.toString());
                }
            }
        });
    }

    public void linear() {

        float da1, da2, db1, db2, dc1, dc2;

        //all the numerical calculations are done here
        //logic for the calculation can be reused
        da1 = a1[i]; da2 = a2[i];
        db1 = b1[i]; db2 = b2[i];
        dc1 = c1[i]; dc2 = c2[i];

        da1 = a1[i] * b2[i];
        db1 = b1[i] * b2[i];
        dc1 = c1[i] * b2[i];

        da2 = a2[i] * b1[i];
        db2 = b2[i] * b1[i];
        dc2 = c2[i] * b1[i];

        if(db1 == db2) {

            da2 = da2 * -1;
            db2 = db2 * -1;
            dc2 = dc2 * -1;
        }

        float sum_a = da1 + da2;
        float sum_c = dc1 + dc2;

        xsol = sum_c / sum_a;
        ysol = (c1[i] - (a1[i] * xsol)) / b1[i];

        //color customization
        Str_X = "x value : <font color = '#FF0000'>" + xsol + "</font>";
        Str_Y = "y value : <font color = '#FF0000'>" + ysol + "</font>";


    }

    public void changequestion(){

        //change question once the user answers correct
        Str_Eqn = "Consider our equation is like below<br/><br/>" +
                "<font color = '#FF0000'>  3 x - y = 8" +
                "<br/> x + 2 y = 5</font><br/>" + "<font color = '#0000FF'> Then Solution will be: x = 3" +
                "and y = 1</font> <br/> <br/> <br/>" + "Solve the following equation and enter the value of x & y<br/><br/>" +
                "<font color = '#FF0000'>" + a1[i]+ "x "+"+ " + b1[i] + "y " + "= " + c1[i] +
                "<br/>"+a2[i]+ "x "+"+ " + b2[i] + "y " + "= " + c2[i]+"</font><br/>" +
                " <br/> <br/> <br/>";

        Text_Eqn.setText(Html.fromHtml(Str_Eqn));

    }

    public void error() {

        Toast.makeText(getBaseContext(), "Enter the values", Toast.LENGTH_LONG).show();
    }
}