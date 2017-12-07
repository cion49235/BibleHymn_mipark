package book.bible.hymn.mipark;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import book.bible.hymn.mipark.R;
import book.bible.hymn.mipark.favorite.user.db.DBHelper;
import book.bible.hymn.mipark.util.PreferenceUtil;

/**
 * Created by Daniel on 12/28/2014.
 */
public class RecordingService extends Service {

    private static final String LOG_TAG = "RecordingService";

    private String mFileName = null;
    private String mFilePath = null;

    private MediaRecorder mRecorder = null;

    private DBHelper mDatabase;

    private long mStartingTimeMillis = 0;
    private long mElapsedMillis = 0;
    private int mElapsedSeconds = 0;
    private OnTimerChangedListener onTimerChangedListener = null;
    private static final SimpleDateFormat mTimerFormat = new SimpleDateFormat("mm:ss", Locale.getDefault());

    private Timer mTimer = null;
    private TimerTask mIncrementTimerTask = null;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public interface OnTimerChangedListener {
        void onTimerChanged(int seconds);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mDatabase = new DBHelper(getApplicationContext());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startRecording();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (mRecorder != null) {
            stopRecording();
        }

        super.onDestroy();
    }

    public void startRecording() {
    	PreferenceUtil.setBooleanSharedData(this, PreferenceUtil.PREF_DO_RECORDING, true);
    	setFileNameAndPath();
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mRecorder.setOutputFile(mFilePath);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mRecorder.setAudioChannels(1);
        try {
            mRecorder.prepare();
            mRecorder.start();
            mStartingTimeMillis = System.currentTimeMillis();

            //startTimer();
            //startForeground(1, createNotification());

        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    public void setFileNameAndPath(){
    	int count = 0;
        File f;
        do{
            count++;
            mFileName = getString(R.string.default_file_name)
                    + " #" + (mDatabase.getCount() + count) + ".mp4";
//            mFileName = String.valueOf(System.currentTimeMillis() + ".mp4");
            mFilePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            mFilePath += getString(R.string.path_folder) + mFileName;
            f = new File(mFilePath);
        }while (f.exists() && !f.isDirectory());
    }

    public void stopRecording() {
        /*mRecorder.stop();
        mElapsedMillis = (System.currentTimeMillis() - mStartingTimeMillis);
        mRecorder.release();*/
        Toast.makeText(this, getString(R.string.toast_recording_finish) + " " + mFilePath, Toast.LENGTH_LONG).show();
        //remove notification
        if (mIncrementTimerTask != null) {
            mIncrementTimerTask.cancel();
            mIncrementTimerTask = null;
        }
        try {
            mRecorder.stop();
            mElapsedMillis = (System.currentTimeMillis() - mStartingTimeMillis);
        } catch(RuntimeException e) {
        } finally {
            mRecorder.release();
            mRecorder = null;
            mDatabase.addRecording(mFileName, mFilePath, mElapsedMillis);
        }
        /*mRecorder = null;
        try {
            mDatabase.addRecording(mFileName, mFilePath, mElapsedMillis);
        } catch (Exception e){
            Log.e(LOG_TAG, "exception", e);
        }*/
        PreferenceUtil.setBooleanSharedData(this, PreferenceUtil.PREF_DO_RECORDING, false);
        PreferenceUtil.setStringSharedData(this, PreferenceUtil.PREF_NAME_RECORDING, mFilePath);
    }
}
