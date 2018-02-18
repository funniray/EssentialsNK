package cn.yescallop.essentialsnk.command.defaults.teleport;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import cn.yescallop.essentialsnk.EssentialsAPI;
import cn.yescallop.essentialsnk.Language;
import cn.yescallop.essentialsnk.command.CommandBase;

public class TPACommand extends CommandBase {

    public TPACommand(EssentialsAPI api) {
        super("tpa", api);
        this.setAliases(new String[]{"call", "tpask"});
    }

    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!this.testPermission(sender)) {
            return false;
        }
        if (!this.testIngame(sender)) {
            return false;
        }
        if (args.length != 1) {
            this.sendUsage(sender);
            return false;
        }
        Player player = api.getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage(TextFormat.RED + Language.translate("commands.generic.player.notfound", args[0]));
            return false;
        }
        if (sender == player) {
            sender.sendMessage(TextFormat.RED + Language.translate("commands.tpa.self"));
            return false;
        }
        api.requestTP((Player) sender, player, true);
        player.sendMessage(Language.translate("commands.tpa.invite", ((Player) sender).getName()));
        sender.sendMessage(Language.translate("commands.tpa.success", player.getDisplayName()));
        return true;
    }
}
