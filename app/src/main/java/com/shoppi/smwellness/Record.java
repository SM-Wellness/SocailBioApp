package com.shoppi.smwellness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.Manifest;

import com.chibde.visualizer.LineBarVisualizer;
import com.chibde.visualizer.LineVisualizer;

public class Record extends AppCompatActivity {

    public MediaPlayer mediaPlayer;

    public static final String DIRECTORY_NAME_TEMP = "AudioTemp";
    public static final int REPEAT_INTERVAL = 40;
    /**오디오 파일 관련 변수*/
    // 오디오 권한

    private TextView txtRecord;
    private String audioFileName; // 오디오 녹음 생성 파일 이름

    VisualizerView visualizerView;
    Bitmap bitmap = Bitmap.createBitmap(800,800, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    private MediaRecorder recorder = null;

    File audioDirTemp;
    private boolean isRecording = false;


    private Handler handler; // Handler for updating the visualizer
    // private boolean recording; // are we currently recording?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        visualizerView = (VisualizerView) findViewById(R.id.visualizer);

        txtRecord = (TextView) findViewById(R.id.txtRecord);
        txtRecord.setOnClickListener(recordClick);

        audioDirTemp = new File(Environment.getExternalStorageDirectory(),
                DIRECTORY_NAME_TEMP);

        if (audioDirTemp.exists()) {
            deleteFilesInDir(audioDirTemp);
        } else {
            audioDirTemp.mkdirs();
        }

        // create the Handler for visualizer update
        handler = new Handler();
    }

    View.OnClickListener recordClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (!isRecording) {
                //isRecording = true;

                String recordPath = getExternalFilesDir("/").getAbsolutePath();
                // 파일 이름 변수를 현재 날짜가 들어가도록 초기화. 그 이유는 중복된 이름으로 기존에 있던 파일이 덮어 쓰여지는 것을 방지하고자 함.
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                audioFileName = recordPath + "/" +"RecordExample_" + timeStamp + "_"+"audio.mp4";

                txtRecord.setText("Stop Recording");
                recorder = new MediaRecorder();

                recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                recorder.setOutputFile(audioFileName);
                recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

                MediaRecorder.OnErrorListener errorListener = null;
                recorder.setOnErrorListener(errorListener);
                MediaRecorder.OnInfoListener infoListener = null;
                recorder.setOnInfoListener(infoListener);
                //initializing the media player

                try {



                    recorder.prepare();
//                    recorder.start();
//                    String a = Integer.toString(recorder.getMaxAmplitude());
//                    Log.d("sd", a);
                    isRecording = true; // we are currently recording
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                recorder.start();
                String a = Integer.toString(recorder.getMaxAmplitude());
                Log.d("sd", a);
                handler.post(updateVisualizer);

            } else {

                txtRecord.setText("Start Recording");
                releaseRecorder();
            }

        }
    };

    private void releaseRecorder() {
        if (recorder != null) {
            isRecording = false; // stop recording
            handler.removeCallbacks(updateVisualizer);
            recorder.stop();
            recorder.reset();
            recorder.release();
            recorder = null;
        }
    }

    public static boolean deleteFilesInDir(File path) {

        if( path.exists() ) {
            File[] files = path.listFiles();
            if (files == null) {
                return true;
            }
            for(int i=0; i<files.length; i++) {

                if(files[i].isDirectory()) {

                }
                else {
                    files[i].delete();
                }
            }
        }
        return true;
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        releaseRecorder();
    }

    // updates the visualizer every 50 milliseconds
    Runnable updateVisualizer = new Runnable() {
        @Override
        public void run() {
            if (isRecording) // if we are already recording
            {
                // get the current amplitude
                int x = recorder.getMaxAmplitude();
                visualizerView.addAmplitude(x); // update the VisualizeView
                visualizerView.invalidate(); // refresh the VisualizerView
                // update in 40 milliseconds
                handler.postDelayed(this, REPEAT_INTERVAL);
            }
        }
    };

//    public void lineVisualization(View view) {
//        //LineVisualizer lineVisualizer = findViewById(R.id.visualizerLine);
//
//        lineVisualizer.setVisibility(View.VISIBLE);
//
//        // set a custom color to the line.
//        lineVisualizer.setColor(ContextCompat.getColor(this, R.color.myColor1));
//
//        // set the line width for the visualizer between 1-10 default is  1.
//        lineVisualizer.setStrokeWidth(1);
//
//        // Setting the media player to the visualizer.
//        lineVisualizer.setPlayer(recorder. getMaxAmplitude());
//    }
//
//    public void lineBarVisualization(View view) {
//        LineBarVisualizer lineBarVisualizer = findViewById(R.id.visualizerLineBar);
//
//        lineBarVisualizer.setVisibility(View.VISIBLE);
//
//        // setting the custom color to the line.
//        lineBarVisualizer.setColor(ContextCompat.getColor(this, R.color.myColor6));
//
//        // define the custom number of bars we want in the visualizer between (10 - 256).
//        lineBarVisualizer.setDensity(60);
//
//        // Setting the media player to the visualizer.
//        lineBarVisualizer.setPlayer(recorder.getMaxAmplitude());
//    }
}