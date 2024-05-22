package game.model.commands;

/**A parancsok interfésze*/
public interface iCommand {
    /**Parancs végrehajtása*/
    public void execute(String[] cmd);
}
