package com.yuzeduan.lovesong.music.view;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.yuzeduan.lovesong.R;
import com.yuzeduan.lovesong.music.bean.Song;
import com.yuzeduan.lovesong.util.LocalMusicUtil;
import com.yuzeduan.lovesong.util.LoveSongApplication;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

/**
 * author: Allen
 * date: On 2018/10/4
 */
public class NotificationView {
    private NotificationManager mManager;
    private NotificationCompat.Builder mBuilder;
    private Context mContext;

    NotificationView() {
        mContext = LoveSongApplication.getContext();
        mManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    /**
     * 显现通知,并且根据flag判断是否取消通知
     * @param song
     * @param flag
     */
    public void initNotification(Song song, int flag){
        final RemoteViews contentViews;
        if(song != null && flag == 1){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                String channelId = "channel_song";
                NotificationChannel channel = mManager.getNotificationChannel(channelId);
                if(channel == null){
                    channel = new NotificationChannel(channelId, "My Notifications", NotificationManager.IMPORTANCE_DEFAULT);
                    channel.setSound(null,null);
                    mManager.createNotificationChannel(channel);
                }
                mBuilder = new NotificationCompat.Builder(mContext, channelId);
            }else{
                mBuilder = new NotificationCompat.Builder(mContext);
            }
            if(song.isLocal()){
                Bitmap bitmap = LocalMusicUtil.getLocalityMusicBitmap(song.getmSongId(), song.getmSmallPicPath(), 150);
                mBuilder.setLargeIcon(bitmap);
            }else{
                SimpleTarget<Bitmap> target = new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        if(resource != null) {
                            mBuilder.setLargeIcon(resource);
                        }else {
                            mBuilder.setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_lovesong ));
                        }
                    }
                };
                Glide.with(mContext).load(song.getmSmallPicPath()).asBitmap().into(target);
            }
            mBuilder.setContentTitle(song.getmSongName());
            mBuilder.setContentText(song.getmArtist());
            mBuilder.setSmallIcon(R.drawable.ic_lovesong);
            mManager.notify(1, mBuilder.build());
        }else if(mBuilder != null && flag == 0){
            mManager.cancel(1);
        }
    }
}
