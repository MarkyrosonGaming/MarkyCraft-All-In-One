package me.Markyroson.MarkyCraft.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;

public class VersionUtils {
	public static String getBukkitVersion()	//get the version of installed bukkit used
	{
		Matcher matcher = Pattern.compile("v\\d+_\\d+_R\\d+").matcher(Bukkit.getServer().getClass().getPackage().getName());
		if (matcher.find()) {
			return matcher.group();
		}
		return null;
	}
	public static String getMinecraftVersion()	//get the version of minecraft used
	{
		Matcher matcher = Pattern.compile("(\\(MC: ) )[\\d\\.]+) (\\))").matcher(Bukkit.getVersion());
		if (matcher.find()) {
			return matcher.group(2);
		}
		return null;
	}
}
