package book.bible.hymn.mipark.podcast.mediaplayer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import com.actionbarsherlock.app.SherlockActivity;
import com.admixer.AdAdapter;
import com.admixer.AdInfo;
import com.admixer.AdMixerManager;
import com.admixer.AdView;
import com.admixer.AdViewListener;
import com.admixer.InterstitialAd;
import com.admixer.InterstitialAdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.NativeExpressAdView;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import book.bible.hymn.mipark.R;
import book.bible.hymn.mipark.common.Const;
import book.bible.hymn.mipark.db.helper.DBOpenHelper;
import book.bible.hymn.mipark.util.NotificationUtil;
import book.bible.hymn.mipark.util.PreferenceUtil;
import book.bible.hymn.mipark.util.TimeUtil;

public class ContinueMediaPlayer_Podcast extends SherlockActivity implements OnClickListener, AdViewListener, InterstitialAdListener{
	private LinearLayout layout_progress;
	private Context context;
	private SeekBar mediacontroller_progress;
	private Handler navigator_handler = new Handler();
	private TextView max_time, current_time,txt_title, txt_pubDate;
	private ImageButton bt_duration_rew, bt_pause, bt_duration_ffwd, bt_rew, bt_ffwd;
	private int seekBackwardTime = 5000; // 5000 milliseconds
	private int seekForwardtime = 5000; // 5000 milliseconds
	private int duration_check = 0;
	private MediaPlayer mediaPlayer = new MediaPlayer();
//	private String title,enclosure,pubDate,image,description_title;
	private ArrayList<String> array_title,array_enclosure,array_pubDate,array_image,array_description_title;
	private ImageView img_image, btn_media_continue;
	private MediaPlayAsync mediaPlayAsync = null;
	private boolean down_buffer = false;
	private LinearLayout control_panel_layout;
	private RelativeLayout ad_layout;
	private InterstitialAd interstialAd;
	private Handler handler = new Handler();
	private ImageButton btnLeft;
	private TextView main_title;
	private final DBOpenHelper dbhelper = DBOpenHelper.getInstance();
	private int video_num = 0;
	private NativeExpressAdView admobNative;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.continue_mediaplayer);
		AdMixerManager.getInstance().setAdapterDefaultAppCode(AdAdapter.ADAPTER_ADMIXER, "d298y2jj");
    	AdMixerManager.getInstance().setAdapterDefaultAppCode(AdAdapter.ADAPTER_ADMOB, "ca-app-pub-4637651494513698/5298614013");
    	AdMixerManager.getInstance().setAdapterDefaultAppCode(AdAdapter.ADAPTER_ADMOB_FULL, "ca-app-pub-4637651494513698/2289307299");
		context = this;
		if(!PreferenceUtil.getStringSharedData(context, PreferenceUtil.PREF_ISSUBSCRIBED, Const.isSubscribed).equals("true")){
        	addBannerView();    		
    	}
//		init_admob_naive();
		init_ui();
		telephony_manager();
		get_data();
		set_titlebar();
		
		mediaPlayAsync = new MediaPlayAsync();
		mediaPlayAsync.execute();
	}
	
	private void init_admob_naive(){
		RelativeLayout nativeContainer = (RelativeLayout) findViewById(R.id.admob_native);
		AdRequest adRequest = new AdRequest.Builder().build();	    
		admobNative = new NativeExpressAdView(this);
		admobNative.setAdSize(new AdSize(360, 100));
		admobNative.setAdUnitId("ca-app-pub-4637651494513698/5845408923");
		nativeContainer.addView(admobNative);
		admobNative.loadAd(adRequest);
	}
	
	private void init_ui(){
		layout_progress = (LinearLayout)findViewById(R.id.layout_progress);
		control_panel_layout = (LinearLayout)findViewById(R.id.control_panel_layout);
		mediacontroller_progress = (SeekBar)findViewById(R.id.mediacontroller_progress);
		max_time = (TextView)findViewById(R.id.max_time);
		current_time = (TextView)findViewById(R.id.current_time);
		txt_title = (TextView)findViewById(R.id.txt_title);
		txt_title.setSelected(true);
		txt_pubDate = (TextView)findViewById(R.id.txt_pubDate);
		img_image = (ImageView)findViewById(R.id.img_image);
		btn_media_continue = (ImageView)findViewById(R.id.btn_media_continue);
		btn_media_continue.setOnClickListener(this);
		bt_duration_rew = (ImageButton)findViewById(R.id.bt_duration_rew);
		bt_pause = (ImageButton)findViewById(R.id.bt_pause);
		bt_duration_ffwd = (ImageButton)findViewById(R.id.bt_duration_ffwd);
		bt_rew = (ImageButton)findViewById(R.id.bt_rew);
		bt_ffwd = (ImageButton)findViewById(R.id.bt_ffwd);
		bt_duration_rew.setOnClickListener(this);
		bt_pause.setOnClickListener(this);
		bt_duration_ffwd.setOnClickListener(this);
		bt_rew.setOnClickListener(this);
		bt_ffwd.setOnClickListener(this);
	}
	
	private void set_titlebar(){
		btnLeft = (ImageButton)findViewById(R.id.btnLeft);
		btnLeft.setOnClickListener(this);
		main_title = (TextView)findViewById(R.id.main_title);
		main_title.setText(array_description_title.get(video_num));
		main_title.setTextSize(12);
		main_title.setSingleLine();
	}
	
	private void get_data(){
		array_title = getIntent().getStringArrayListExtra("array_title");
		array_enclosure = getIntent().getStringArrayListExtra("array_enclosure");
		array_pubDate = getIntent().getStringArrayListExtra("array_pubDate");
		array_image = getIntent().getStringArrayListExtra("array_image");
		array_description_title = getIntent().getStringArrayListExtra("array_description_title");
		
		video_num = getIntent().getIntExtra("video_num",array_title.size()-1);
		
		down_buffer = getIntent().getBooleanExtra("down_buffer", false);
		if(down_buffer == true){
			mediacontroller_progress.setSecondaryProgress(100);
		}
	}
	
	private void telephony_manager(){
		TelephonyManager telephonymanager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		telephonymanager.listen(new PhoneStateListener() {
			public void onCallStateChanged(int state, String incomingNumber) {
				switch (state) {
				case TelephonyManager.CALL_STATE_IDLE: 
					
				case TelephonyManager.CALL_STATE_OFFHOOK:
					if ((mediaPlayer != null) && (mediaPlayer.isPlaying())){
						mediaPlayer.pause();
						duration_check = mediaPlayer.getCurrentPosition();
					}
				case TelephonyManager.CALL_STATE_RINGING:
					if ((mediaPlayer != null) && (mediaPlayer.isPlaying())){
						mediaPlayer.pause();
						duration_check = mediaPlayer.getCurrentPosition();
					}
				default: break;
				} 
			}
		}, PhoneStateListener.LISTEN_CALL_STATE);
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		invokeFragmentManagerNoteStateNotSaved();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void invokeFragmentManagerNoteStateNotSaved() {
	    /**
	     * For post-Honeycomb devices
	     */
	    if (Build.VERSION.SDK_INT < 11) {
	        return;
	    }
	    try {
	        Class cls = getClass();
	        do {
	            cls = cls.getSuperclass();
	        } while (!"Activity".equals(cls.getSimpleName()));
	        Field fragmentMgrField = cls.getDeclaredField("mFragments");
	        fragmentMgrField.setAccessible(true);

	        Object fragmentMgr = fragmentMgrField.get(this);
	        cls = fragmentMgr.getClass();

	        Method noteStateNotSavedMethod = cls.getDeclaredMethod("noteStateNotSaved", new Class[] {});
	        noteStateNotSavedMethod.invoke(fragmentMgr, new Object[] {});
	        Log.d("dsu", "Successful call for noteStateNotSaved!!!");
	    } catch (Exception ex) {
	        Log.e("dsu", "Exception on worka FM.noteStateNotSaved", ex);
	    }
	}
	
	@Override
	protected void onPause() {
		super.onPause();
//		admobNative.pause();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
//		admobNative.resume();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
//		admobNative.destroy();
		navigator_handler.removeCallbacks(UpdateTimetask);
		dbhelper.podcast_db_pause_task(array_title.get(video_num), mediaPlayer.getCurrentPosition(), array_pubDate.get(video_num));
		if(mediaPlayAsync != null){
			mediaPlayAsync.cancel(true);
		}
		super.onDestroy();
	}
	@Override
	protected void onRestart() {
		super.onRestart();
		try{
			updateProgressBar();
	    	if(!mediaPlayer.isPlaying()){
	    		if(duration_check > 0){
	    			mediaPlayer.seekTo(duration_check);
	    			mediaPlayer.start();
	    		}
	    		return;
	    	}
	    }catch (IllegalStateException localIllegalStateException){
	    }
	    catch (IllegalArgumentException localIllegalArgumentException){
	    }
	    catch (NullPointerException localNullPointerException){
	    }
	}
	@Override
	protected void onUserLeaveHint() {
		NotificationUtil.setNotification_continue_podcast(context, array_title, array_enclosure, array_pubDate, array_image, array_description_title, video_num );
		super.onUserLeaveHint();
	}
	
	public void addBannerView() {
    	AdInfo adInfo = new AdInfo("d298y2jj");
    	adInfo.setTestMode(false);
        AdView adView = new AdView(this);
        adView.setAdInfo(adInfo, this);
        adView.setAdViewListener(this);
        
        ad_layout = (RelativeLayout)findViewById(R.id.ad_layout);
        if(ad_layout != null){
        	RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            ad_layout.addView(adView, params);	
        }
    }
	
	public void addInterstitialView() {
    	if(interstialAd == null) {
        	AdInfo adInfo = new AdInfo("d298y2jj");
//        	adInfo.setTestMode(false);
        	interstialAd = new InterstitialAd(this);
        	interstialAd.setAdInfo(adInfo, this);
        	interstialAd.setInterstitialAdListener(this);
        	interstialAd.startInterstitial();
    	}
    }
    
    public void updateProgressBar(){
		navigator_handler.postDelayed(UpdateTimetask, 100);
	}
    
    public Runnable UpdateTimetask = new Runnable() {
		@Override
		public void run() {
			if(mediaPlayer != null){
				if(mediaPlayer.isPlaying()){
					layout_progress.setVisibility(View.GONE);
					bt_pause.setImageResource(R.drawable.ic_action_pause);
				}else{
					bt_pause.setImageResource(R.drawable.ic_action_play);
				}
				long totalDuration = mediaPlayer.getDuration();
				long currentDuration = mediaPlayer.getCurrentPosition();
				// Displaying Total Duration time
				max_time.setText(""+TimeUtil.milliSecondsToTimer(totalDuration));
				// Displaying time completed playing
				current_time.setText(""+TimeUtil.milliSecondsToTimer(currentDuration));
				// Updating progress bar
				int progress = (int)(TimeUtil.getProgressPercentage(currentDuration, totalDuration));
				mediacontroller_progress.setProgress(progress);
				navigator_handler.postDelayed(this, 100);	
			}
		}
	};
	
	public class MediaPlayAsync extends AsyncTask<String, Long, Integer> implements OnBufferingUpdateListener, OnCompletionListener, OnPreparedListener,android.widget.SeekBar.OnSeekBarChangeListener, OnErrorListener {
		public int result = -1;
		public MediaPlayAsync() {
		}
		
		@Override
        protected void onPreExecute() {
			try{
				layout_progress.setVisibility(View.VISIBLE);
				dbhelper.podcast_db_current_position(array_title.get(video_num), array_pubDate.get(video_num));
	            navigator_handler.removeCallbacks(UpdateTimetask);
	            if(mediaPlayer.isPlaying()){
	            	mediaPlayer.stop();
	            }
			}catch(Exception e) {
			}
            super.onPreExecute();
            
		}

		@Override
		protected Integer doInBackground(String... params) {
			try{
				mediaPlayer.setOnBufferingUpdateListener(this);
				mediaPlayer.setOnCompletionListener(this);
				mediaPlayer.setOnErrorListener(this);
				mediaPlayer.setOnPreparedListener(this);
				mediacontroller_progress.setOnSeekBarChangeListener(this);
				
				mediaPlayer.reset();
	            mediaPlayer.setDataSource(array_enclosure.get(video_num));
	            mediaPlayer.prepare();
	            
	            mediacontroller_progress.setProgress(0);
				mediacontroller_progress.setMax(100);
				mediaPlayer.seekTo(Const.current_position_podcast);
				updateProgressBar();
				return result = 1;
			}catch (Exception e) {
			}
			return result;
		}
		
		@Override
        protected void onProgressUpdate(Long... values) {
        	super.onProgressUpdate(values);
        }
		@Override
		protected void onPostExecute(Integer result) {
			super.onPostExecute(result);
			if(mediaPlayer != null){
				if(mediaPlayer.isPlaying()){
					layout_progress.setVisibility(View.GONE);
					bt_pause.setImageResource(R.drawable.ic_action_pause);
				}else{
					layout_progress.setVisibility(View.VISIBLE);
					bt_pause.setImageResource(R.drawable.ic_action_play);
				}
			}
			if(result == 1){
				if(mediaPlayAsync != null){
					mediaPlayAsync.cancel(true);
				}
				NotificationUtil.setNotification_continue_podcast(context, array_title, array_enclosure, array_pubDate, array_image, array_description_title, video_num );
				mediaPlayer.start();
				layout_progress.setVisibility(View.GONE);
			}else{
				mediaPlayAsync = new MediaPlayAsync();
				mediaPlayAsync.execute();
			}
		}
        
        @Override
		public void onPrepared(MediaPlayer mp) {
    		txt_title.setText(array_title.get(video_num));
    		txt_pubDate.setText(array_pubDate.get(video_num));

    		Picasso.with(context)
		    .load(array_image.get(video_num))
		    .placeholder(R.drawable.empty_image)
		    .error(R.drawable.empty_image)
		    .into(img_image);
    		PreferenceUtil.setStringSharedData(context, PreferenceUtil.PREF_PODCAST_TITLE, array_title.get(video_num));
    		PreferenceUtil.setStringSharedData(context, PreferenceUtil.PREF_PODCAST_PUBDATE, array_pubDate.get(video_num));
//    		((PodcastActivity_Sub)PodcastActivity_Sub.context).datasetchanged();
		}
        
		@Override
		public void onBufferingUpdate(MediaPlayer mediaPlayer, int buffering) {
			if(down_buffer == false){
				mediacontroller_progress.setSecondaryProgress(buffering);	
			}
		}
		@Override
		public void onCompletion(MediaPlayer mp) {
			mediaPlayer.seekTo(0);
			onDestroy();
			if(mediaPlayer.isPlaying()){
				mediaPlayer.stop();
			}
			if(video_num > 0){
				video_num = video_num - 1;
				mediaPlayAsync = new MediaPlayAsync();
				mediaPlayAsync.execute();
			}else{
				video_num = array_title.size()-1;
				mediaPlayAsync = new MediaPlayAsync();
				mediaPlayAsync.execute();
			}
		}
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			navigator_handler.removeCallbacks(UpdateTimetask);
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			if(mediaPlayer != null){
				int totalDuration = mediaPlayer.getDuration();
				int currentPosition = TimeUtil.progressToTimer(seekBar.getProgress(), totalDuration);
				// forward or backward to certain seconds
				mediaPlayer.seekTo(currentPosition);
				if (mediaPlayer.isPlaying()){
					mediaPlayer.start();
					bt_pause.setImageResource(R.drawable.ic_action_pause);
					updateProgressBar();
			    }else{
			    	mediaPlayer.start();
					bt_pause.setImageResource(R.drawable.ic_action_pause);
					updateProgressBar();
			    }
			}
		}

		@Override
		public boolean onError(MediaPlayer mp, int what, int extra) {
			layout_progress.setVisibility(View.VISIBLE);
			navigator_handler.removeCallbacks(UpdateTimetask);
			return false;
		}
	}
	@Override
	public void onClick(View view) {
		if(view == btnLeft){
			exit_action();
		}else if(view == bt_pause){
			if(mediaPlayer.isPlaying()){
				mediaPlayer.pause();
				bt_pause.setImageResource(R.drawable.ic_action_play);
			}else{
				mediaPlayer.start();
				bt_pause.setImageResource(R.drawable.ic_action_pause);
			}
		}else if(view == bt_duration_rew){
			int currentPosition = mediaPlayer.getCurrentPosition();
			// check if seekBackward time is greater than 0 sec
			if(currentPosition - seekBackwardTime >= 0){
				// forward song
				mediaPlayer.seekTo(currentPosition - seekBackwardTime); 
			}else{
				// backward to starting position
				mediaPlayer.seekTo(0);
			}
		}else if(view == bt_duration_ffwd){
			int currentPosition = mediaPlayer.getCurrentPosition();
			if(currentPosition + seekForwardtime <= mediaPlayer.getDuration()){
				// forward song
				mediaPlayer.seekTo(currentPosition + seekForwardtime);
			}else{
				// forward to end position
				mediaPlayer.seekTo(mediaPlayer.getDuration());
			}
		}else if(view == bt_rew){
			if(video_num < array_title.size() -1){
				onDestroy();
				mediaPlayer.pause();
				navigator_handler.removeCallbacks(UpdateTimetask);
				video_num = video_num + 1;
				mediaPlayAsync = new MediaPlayAsync();
				mediaPlayAsync.execute();
			}else{
				Toast.makeText(context, context.getString(R.string.activity_podcast_20), Toast.LENGTH_SHORT).show();
			}
		}else if(view == bt_ffwd){
			onDestroy();
			if(video_num > 0){
				mediaPlayer.pause();
				navigator_handler.removeCallbacks(UpdateTimetask);
				video_num = video_num - 1;
				mediaPlayAsync = new MediaPlayAsync();
				mediaPlayAsync.execute();
			}else{
				mediaPlayer.pause();
				navigator_handler.removeCallbacks(UpdateTimetask);
				video_num = array_title.size()-1;
				mediaPlayAsync = new MediaPlayAsync();
				mediaPlayAsync.execute();
			}
		}else if(view == btn_media_continue){
			Toast.makeText(context, context.getString(R.string.activity_podcast_12), Toast.LENGTH_LONG).show();
			if(!PreferenceUtil.getStringSharedData(context, PreferenceUtil.PREF_ISSUBSCRIBED, Const.isSubscribed).equals("true")){
				addInterstitialView();				
			}else{
				home_action();
			}
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.KEYCODE_BACK){
			if(mediaPlayer.isPlaying() == true){
				mediaPlayer.pause();
			}
			if(!PreferenceUtil.getStringSharedData(context, PreferenceUtil.PREF_ISSUBSCRIBED, Const.isSubscribed).equals("true")){
				Toast.makeText(context, context.getString(R.string.txt_after_ad), Toast.LENGTH_SHORT).show();
				addInterstitialView();	
			}
			 handler.postDelayed(new Runnable() {
				 @Override
				 public void run() {
					 if(mediaPlayer.isPlaying() == true){
						 mediaPlayer.pause();
					 }
					 onDestroy();
					 NotificationUtil.setNotification_Cancel();
					 finish();
				 }
			 },1500);
			 return false;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	private void exit_action(){
		if(mediaPlayer.isPlaying() == true){
			mediaPlayer.pause();
		}
		if(!PreferenceUtil.getStringSharedData(context, PreferenceUtil.PREF_ISSUBSCRIBED, Const.isSubscribed).equals("true")){
			Toast.makeText(context, context.getString(R.string.txt_after_ad), Toast.LENGTH_SHORT).show();
			addInterstitialView();			
		}
		 handler.postDelayed(new Runnable() {
			 @Override
			 public void run() {
				 if(mediaPlayer.isPlaying() == true){
					 mediaPlayer.pause();
				 }
				 onDestroy();
				 NotificationUtil.setNotification_Cancel();
				 finish();
			 }
		 },1500);
	}
	
	private void home_action(){
		if(mediaPlayer.isPlaying()){
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			startActivity(intent);
		}
	}
	
	@Override
	public void onClickedAd(String arg0, AdView arg1) {
	}

	@Override
	public void onFailedToReceiveAd(int arg0, String arg1, AdView arg2) {
	}

	@Override
	public void onReceivedAd(String arg0, AdView arg1) {
	}
	
	@Override
	public void onInterstitialAdClosed(InterstitialAd arg0) {
		interstialAd = null;
		home_action();
		
	}

	@Override
	public void onInterstitialAdFailedToReceive(int arg0, String arg1,
			InterstitialAd arg2) {
		interstialAd = null;
		home_action();
	}

	@Override
	public void onInterstitialAdReceived(String arg0, InterstitialAd arg1) {
		interstialAd = null;
	}

	@Override
	public void onInterstitialAdShown(String arg0, InterstitialAd arg1) {
	}

	@Override
	public void onLeftClicked(String arg0, InterstitialAd arg1) {
	}

	@Override
	public void onRightClicked(String arg0, InterstitialAd arg1) {
	}
}
