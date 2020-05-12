package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowAnimationFrameStats;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {

int []gameState= {2,2,2,2,2,2,2,2,2};
   //gameState Meaning is
    //x-o
    //o-1
    //null-2
    int [][] winningConditions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
int activePlayer =0;
    //x-o
    //o-1
    int demo=0;
    boolean gameActive=true;
    public void playerTap(View view)
    {
        demo++;
        ImageView img=(ImageView) view;
        int tapOnImage=Integer.parseInt(img.getTag().toString());
//        if(gameState[tapOnImage]==2) {
//            gameState[tapOnImage] = activePlayer;
//            activePlayer = 1;
//            img.setTranslationY(-1000f);
//            if (activePlayer == 0) {
//                img.setImageResource(R.drawable.o);
//                activePlayer=1;
//            }
//        }
//       else
//        {
//            img.setImageResource(R.drawable.x);
//            activePlayer=0;
//        }
//       img.animate().translationYBy(1000f).setDuration(300);
//        Toast.makeText(this,"hello",Toast.LENGTH_LONG).show();;
        if(!gameActive){
            gameReset(view);
        }
        if(gameState[tapOnImage] == 2) {
            gameState[tapOnImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                TextView status =findViewById(R.id.textView);
                img.setImageResource(R.drawable.x);
                status.setText(String.valueOf(gameState[activePlayer]));
                activePlayer = 1;
                status.setText("O's Turn - Tap to play");
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.textView);
                status.setText("X's Turn - Tap to play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
            // Check if any player has won
            for(int[] winPosition : winningConditions){
                if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                        gameState[winPosition[1]] == gameState[winPosition[2]] &&
                        gameState[winPosition[0]]!=2){
                    // Somebody has won! - Find out who!
                    String winnerStr;
                    gameActive = false;
                    if(gameState[winPosition[0]] == 0)
                    {
                        winnerStr = "X has won";
                    }
                    else {
                        winnerStr = "O has won";
                         }
                    // Update the status bar for winner announcement
                    TextView status = findViewById(R.id.textView);
                    status.setText(winnerStr);

                }



            }
//            if(demo==9)
//            {
//                String str="";
//                for(int a:gameState)
//                {
//                    str+=String.valueOf(a);
//                }
//                    Toast.makeText(this,str,Toast.LENGTH_LONG).show();
//
//            }

        }





    }
    public void gameReset(View view) {
        gameActive = true;
        activePlayer = 0;
        for(int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.image1)).setImageResource(0);
        ((ImageView)findViewById(R.id.image2)).setImageResource(0);
        ((ImageView)findViewById(R.id.image3)).setImageResource(0);
        ((ImageView)findViewById(R.id.image4)).setImageResource(0);
        ((ImageView)findViewById(R.id.image5)).setImageResource(0);
        ((ImageView)findViewById(R.id.image6)).setImageResource(0);
        ((ImageView)findViewById(R.id.image7)).setImageResource(0);
        ((ImageView)findViewById(R.id.image8)).setImageResource(0);
        ((ImageView)findViewById(R.id.image9)).setImageResource(0);

        TextView status = findViewById(R.id.textView);
        status.setText("X's Turn - Tap to play");

    }

private AdView MadView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        MadView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        MadView.loadAd(adRequest);


        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-5361831732696393/1765362569");


    }
}
