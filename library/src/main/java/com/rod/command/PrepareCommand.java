package com.rod.command;

import com.rod.Player;

/**
 * @author Rod
 * @date 2018/7/18
 */
public class PrepareCommand implements Command {
    private final Player mPlayer;

    public PrepareCommand(Player player) {
        mPlayer = player;
    }

    @Override
    public void execute() {
        mPlayer.prepare();
    }
}
