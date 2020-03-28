package com.rod.command;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import com.rod.log.PL;
import com.rod.state.StateContext;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;

/**
 * @author Rod
 * @date 2018/7/18
 */
public class CommandInvoker extends HandlerThread {
    private static final String TAG = "CommandInvoker";

    private CommandHandler mHandler;

    public CommandInvoker() {
        super("command_thread", THREAD_PRIORITY_BACKGROUND);
        start();
        mHandler = new CommandHandler(getLooper());
    }

    public void attach(StateContext context) {
        mHandler.attach(context);
    }

    public void commit(Command command) {
        if (command == null) {
            return;
        }

        ArrayList<Command> list = new ArrayList<>();
        list.add(command);
        commit(list);
    }

    public void commit(List<Command> commands) {
        if (commands == null || commands.isEmpty()) {
            return;
        }
        mHandler.obtainMessage(CommandHandler.MSG_EXECUTE_COMMANDS, commands).sendToTarget();
    }

    public void invokeNextCommand() {
        mHandler.obtainMessage(CommandHandler.MSG_NEXT).sendToTarget();
    }

    public void clearCommand() {
        mHandler.obtainMessage(CommandHandler.MSG_CLEAR).sendToTarget();
    }

    static class CommandHandler extends Handler {

        final static int MSG_CLEAR = 1;
        final static int MSG_EXECUTE_COMMANDS = 2;
        final static int MSG_NEXT = 3;

        private final Queue<Command> mCommands = new LinkedList<>();
        private StateContext mStateContext;

        CommandHandler(Looper looper) {
            super(looper);
        }

        public void attach(StateContext context) {
            mStateContext = context;
        }

        @SuppressWarnings("unchecked")
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case MSG_CLEAR:
                    PL.d(TAG, "handle clear");
                    mCommands.clear();
                    break;
                case MSG_EXECUTE_COMMANDS:
                    addCommands((List<Command>) msg.obj);
                    break;
                case MSG_NEXT:
                    executeNext();
                    break;
                default:
                    break;
            }
        }

        private void addCommands(List<Command> commands) {
            PL.d(TAG, "addCommands");
            if (commands == null || commands.isEmpty()) {
                return;
            }

            boolean execute = mCommands.isEmpty();
            for (Command command : commands) {
                if (command.isInvokeImmediately()) {
                    mCommands.clear();
                    PL.d(TAG, "addCommands, %s isInvokeImmediately, clear mCommands", command);
                    execute = true;
                }
                mCommands.add(command);
            }
            if (execute) {
                executeNext();
            }
        }

        private void executeNext() {
            if (mStateContext == null) {
                return;
            }
            Command command = mCommands.poll();
            if (command != null) {
                PL.d(TAG, "executeNext, execute %s", command);
                command.execute(mStateContext);
            }
        }
    }
}
