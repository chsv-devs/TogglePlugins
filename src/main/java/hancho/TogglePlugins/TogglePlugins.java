package hancho.TogglePlugins;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.Listener;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginBase;

public class TogglePlugins extends PluginBase implements Listener{
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equals("loadplugin")) {
			if(args.length < 1) {
				sender.sendMessage("§f§l[ §c! §f] /loadplugin <PluginFileName>");
				return false;
			}
			if(this.getServer().getPluginManager().loadPlugin(this.getServer().getPluginPath() + "/" + args[0] + ".jar") == null) {
				sender.sendMessage("§f§l[ §c! §f] Could not load " + args[0] + ".jar");
			}else {
				sender.sendMessage("§f§l[ §6! §f] Successfully loaded.");
			}
			return true;
		}else if (cmd.getName().equals("unloadplugin")) {
			if(args.length < 1) {
				sender.sendMessage("§f§l[ §c! §f] /unloadplugin <PluginName>");
				return false;
			}
			Plugin plugin = this.getServer().getPluginManager().getPlugin(args[0]);
			if(plugin == null) {
				sender.sendMessage("§f§l[ §c! §f] Could not find " + args[0]);
				return false;
			}
			plugin.onDisable();
			this.getServer().getPluginManager().disablePlugin(plugin);
			sender.sendMessage("§f§l[ §6! §f] Successfully unloaded.");
		}else if (cmd.getName().equals("forceunloadplugin")) {
			if(args.length < 1) {
				sender.sendMessage("§f§l[ §c! §f] /forceunloadplugin <PluginName>");
				return false;
			}
			Plugin plugin = this.getServer().getPluginManager().getPlugin(args[0]);
			if(plugin == null) {
				sender.sendMessage("§f§l[ §c! §f] Could not find " + args[0]);
				return false;
			}
			this.getServer().getPluginManager().disablePlugin(plugin);
			sender.sendMessage("§f§l[ §6! §f] Successfully unloaded.");
		}
		return true;
	}
}
