package CaveExplorer.commands;

import CaveExplorer.Direction;
import CaveExplorer.Game;

import java.util.ArrayList;

public class MoveCommand extends MasterCommand implements CommandWithParameter {

    public MoveCommand(Game game, String name, ArrayList<String> synonyms) {
        super(game,name,synonyms);
    }

    public MoveCommand(Game game, String name) {
        super(game,name);
    }

    @Override
    public String execute(String parameter) {
        Direction d;
        String returnString = "";

        d = Direction.findByString(parameter);

        if (d != null) {
            returnString = game.MovePlayer(d);
        } else {
            returnString = parameter + " is not a direction to move.";
        }

        return returnString;
    }
}