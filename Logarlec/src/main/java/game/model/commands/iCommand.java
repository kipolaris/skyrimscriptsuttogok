package game.model.commands;

public interface iCommand {
    public void execute(String[] cmd);

    public String getName();
}
