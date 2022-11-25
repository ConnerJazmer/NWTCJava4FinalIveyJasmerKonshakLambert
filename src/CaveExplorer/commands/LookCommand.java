package CaveExplorer.commands;

import CaveExplorer.Game;

import java.util.ArrayList;

public class LookCommand extends MasterCommand implements SingleCommand
{

    public LookCommand(Game game, String name, ArrayList<String> synonyms) {
        super(game,name,synonyms);
    }

    public LookCommand(Game game, String name) {
        super(game,name);
    }

    @Override
    public String execute() {
        return game.playerLook();
    }
}