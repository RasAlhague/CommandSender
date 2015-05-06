package com.rasalhague.commandsender.volumebtncontrol;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;

import java.io.IOException;

class BlankInfinityPlayer
{
    private String blank1hourMp3FilePath = "/Music/1hour.mp3";
    private String blank1secMp3FilePath  = "/Music/1sec.mp3";
    private MediaPlayer mediaPlayer;

    public BlankInfinityPlayer()
    {
        mediaPlayer = new MediaPlayer();
        Uri blankMp3FileUri = Uri.parse(Environment.getExternalStorageDirectory().getPath() + blank1hourMp3FilePath);

        try
        {
            mediaPlayer.setDataSource(blankMp3FileUri.getPath());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void start()
    {
        try
        {
            mediaPlayer.prepare();
            mediaPlayer.setLooping(true);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
            {
                @Override
                public void onPrepared(MediaPlayer mp)
                {
                    mediaPlayer.start();
                }
            });
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void stop()
    {
        mediaPlayer.stop();
    }
}
