package com.yuzeduan.lovesong.music.view;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.yuzeduan.lovesong.R;
import com.yuzeduan.lovesong.music.bean.Song;
import com.yuzeduan.lovesong.util.LocalMusicUtil;
import com.yuzeduan.lovesong.util.LoveSongApplication;

/**
 * 通知的视图
 * author: Allen
 * date: On 2018/10/3
 */
@Deprecated
public class DeNotificationView {
    private NotificationManager mManager;
    private NotificationCompat.Builder mBuilder;
    private Context mContext;

    DeNotificationView() {
        mContext = LoveSongApplication.getContext();
        mManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    /**
     * 显现通知,并且根据flag判断是否取消通知
     * @param song
     * @param flag
     */
    public void initNotification(Song song, int flag){
        Log.d("Notification", "initNotification: "+"进入了");
        Bitmap bitmap = null;
        final RemoteViews contentViews;
        if(song != null && flag == 1){
            contentViews = new RemoteViews(mContext.getPackageName(), R.layout.notification);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                String channelId = "channel_song";
                NotificationChannel channel = mManager.getNotificationChannel(channelId);
                if(channel == null){
                    channel = new NotificationChannel(channelId, "My Notifications", NotificationManager.IMPORTANCE_HIGH);
                    mManager.createNotificationChannel(channel);
                }
                mBuilder = new NotificationCompat.Builder(mContext, channelId);
            }else{
                mBuilder = new NotificationCompat.Builder(mContext);
            }
            contentViews.setTextViewText(R.id.notification_name_tv, song.getmSongName());
            contentViews.setTextViewText(R.id.notification_artist_tv, song.getmArtist());
            if(song.isLocal()){
               bitmap = LocalMusicUtil.getLocalityMusicBitmap(song.getmSongId(), song.getmSmallPicPath(), 150);
               contentViews.setImageViewBitmap(R.id.notification_iv, bitmap);
            }
            mBuilder.setContentTitle("LoveSong");
            mBuilder.setContentText("topView");
            mBuilder.setSmallIcon(R.drawable.ic_lovesong);
            mBuilder.setStyle(new NotificationCompat.BigTextStyle()
                    .bigText("loveSong"));
            mBuilder.setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_lovesong));
            mBuilder.setContent(contentViews);
            mManager.notify(1, mBuilder.build());
        }else if(mBuilder != null && flag == 0){
            mManager.cancel(1);
        }
    }
}
