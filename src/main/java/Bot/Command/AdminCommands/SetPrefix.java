package Bot.Command.AdminCommands;

import Bot.Command.CommandContext;
import Bot.Command.ICommand;
import Bot.Database.IDataBaseManager;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

public class SetPrefix implements ICommand {

    @Override
    public void handle(CommandContext commandContext) {
        final TextChannel channel = commandContext.getChannel();
        final List<String> args = commandContext.getArgs();
        final Member member = commandContext.getMember();

        if (!member.hasPermission(Permission.MANAGE_SERVER)) {
            channel.sendMessage("<:RedCross:782229279312314368> You are missing the `MANAGE_SERVER` permission").queue();
            return;
        }

        if (args.isEmpty()) {
            channel.sendMessage("<:RedCross:782229279312314368> Missing arguments, could not resolve the new prefix").queue();
            return;
        }

        String newPrefix = String.join("", args);
        IDataBaseManager.INSTANCE.setPrefix(commandContext.getGuild().getIdLong(), newPrefix);
        channel.sendMessage("<:GreenTick:782229268914372609> New prefix has been set to **" + newPrefix + "**").queue();
    }

    @Override
    public String getHelp(CommandContext commandContext) {
        return "`Usage: " + IDataBaseManager.INSTANCE.getPrefix(commandContext.getGuild().getIdLong()) + "setprefix [prefix]\n" +
                (this.getAliases().isEmpty() ? "`" : "Aliases: " + this.getAliases() + "`\n") +
                "Sets the prefix for this server";
    }

    @Override
    public String getName() {
        return "setprefix";
    }

    @Override
    public String getPermissionLevel() {
        return "admin";
    }
}
