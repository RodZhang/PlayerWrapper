package com.rod;

import android.os.Handler;
import android.os.Looper;

/**
 * @author Rod
 * @date 2020/4/11
 */
class PlayerTimer {
    private int mInterval = 1000;

    private OnTimeIntervalListener mListener;
    private Handler mTimerHandler;
    private Runnable mCallListener = new Runnable() {
        @Override
        public void run() {
            if (mListener != null) {
                mListener.onTimeArrive();
            }
            mTimerHandler.postDelayed(this, mInterval);
        }
    };

    public void start() {
        if (mTimerHandler == null) {
            mTimerHandler = new Handler(Looper.getMainLooper());
        } else {
            stop();
        }
        mTimerHandler.postDelayed(mCallListener, mInterval);
    }

    public void stop() {
        if (mTimerHandler == null) {
            return;
        }
        mTimerHandler.removeCallbacks(mCallListener);
    }

    public void setListener(OnTimeIntervalListener listener) {
        mListener = listener;
    }

    public interface OnTimeIntervalListener {
        void onTimeArrive();
    }
}
