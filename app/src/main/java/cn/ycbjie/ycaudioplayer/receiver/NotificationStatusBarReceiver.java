package cn.ycbjie.ycaudioplayer.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;


import cn.ycbjie.ycaudioplayer.base.BaseAppHelper;
import cn.ycbjie.ycaudioplayer.model.MusicPlayAction;
import cn.ycbjie.ycaudioplayer.service.PlayService;
import cn.ycbjie.ycaudioplayer.util.other.LogUtils;

/**
 * Created by yc on 2018/1/25.
 */

public class NotificationStatusBarReceiver extends BroadcastReceiver {

    public static final String ACTION_STATUS_BAR = "YC_ACTION_STATUS_BAR";
    public static final String EXTRA = "extra";


    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || TextUtils.isEmpty(intent.getAction())) {
            return;
        }

        String extra = intent.getStringExtra(EXTRA);
        if (TextUtils.equals(extra, MusicPlayAction.TYPE_NEXT)) {
            PlayService.startCommand(context, MusicPlayAction.TYPE_NEXT);
            LogUtils.e("NotifiyStatusBarReceiver"+"下一首");
        } else if (TextUtils.equals(extra, MusicPlayAction.TYPE_START_PAUSE)) {
            boolean playing = BaseAppHelper.get().getPlayService().isPlaying();
            if(playing){
                LogUtils.e("NotifiyStatusBarReceiver"+"暂停");
            }else {
                LogUtils.e("NotifiyStatusBarReceiver"+"播放");
            }
            PlayService.startCommand(context, MusicPlayAction.TYPE_START_PAUSE);
        }else if(TextUtils.equals(extra, MusicPlayAction.TYPE_PRE)){
            PlayService.startCommand(context, MusicPlayAction.TYPE_PRE);
            LogUtils.e("NotifiyStatusBarReceiver"+"上一首");
        }
    }
}
