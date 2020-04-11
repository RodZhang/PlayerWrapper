package com.rod;

import android.media.AudioAttributes;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.rod.listener.OnProgressChangeListener;
import com.rod.listener.OnStateChangeListener;
import com.rod.videohost.VideoHost;

/**
 * @author Rod
 * @date 2018/7/14
 */
public interface PlayerUserInterface {

    void attachToContainer(@NonNull ViewGroup container);

    void detachFromContainer();

    void setVideoHost(@NonNull VideoHost videoHost);

    void addOnStateChangeListener(@NonNull OnStateChangeListener listener);

    void removeOnStateChangeListener(@NonNull OnStateChangeListener listener);

    void addOnProgressChangeListener(@NonNull OnProgressChangeListener listener);

    void removeOnProgressChangeListener(@NonNull OnProgressChangeListener listener);

    void play(String url);

    void pause();

    void release();

    boolean isPlaying();

    void onStartSeek();

    void onEndSeek(int progress);

    int getCurrentPosition();

    int getDuration();

    int getVideoHeight();

    int getVideoWidth();

    void setAudioAttributes(AudioAttributes attributes);

    void setLooping(boolean looping);

    void setVolume(float leftVolume, float rightVolume);

}
