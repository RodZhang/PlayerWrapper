package com.rod.command;

import android.os.Handler;
import android.os.HandlerThread;

import androidx.annotation.NonNull;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author Rod
 * @date 2018/7/18
 */
public class Invoker {

    private static final int IDLE = 251;
    private static final int BUSY = 425;

    private final Queue<Command> mCommands = new LinkedBlockingDeque<>();
    private final Handler mWorkHandler;
    private int mState;

    public Invoker() {
        HandlerThread workThread = new HandlerThread("Invoker_worker");
        workThread.start();
        mWorkHandler = new Handler(workThread.getLooper());
    }

    public void commit(@NonNull Command command) {
        if (mState == BUSY) {
            mCommands.offer(command);
        } else {

        }
    }
}
