package Bot.Command;

import java.util.List;

public interface ICommand {

    void execute(CommandContext commandContext);

    String getHelp(String prefix);

    String getName();

    Enum<PermissionLevel> getPermissionLevel();

    default List<String> getAliases() {
        return List.of();
    }
}
