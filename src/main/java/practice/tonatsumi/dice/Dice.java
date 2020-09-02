package practice.tonatsumi.dice;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public final class Dice extends JavaPlugin {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        int sixDice = 6;
        Random rdm = new Random();
        int diceFace;
        int result;

        if (!(sender instanceof Player)) {
            return false;
        }
        //コマンドがdiceのみ
        if (args.length == 0) {
            result = rdm.nextInt(sixDice) + 1;
            p.sendMessage("あなたは6面ダイスを振って" + result + "が出た。");
            return true;
        }

        //コマンドがdice　面数のとき
        if (args.length == 1) {
            try {
                diceFace = Integer.parseInt(args[0]);
                result = rdm.nextInt(diceFace) + 1;
                Bukkit.getServer().broadcastMessage(p.getDisplayName() + "は" + diceFace + "面ダイスを振って" + result + "が出た。");
            } catch (NumberFormatException e) {
                p.sendMessage("面数は数字で入力してください");
                return false;
            }
        }

        //コマンドの引数が多いとき
        if (args.length > 2) {
            p.sendMessage("引数が正しくありません");
            return false;
        }
        return false;
    }


    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("dice起動");
        getCommand("dice").setExecutor(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("dice終了");
    }
}
