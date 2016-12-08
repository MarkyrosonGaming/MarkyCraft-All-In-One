package me.Markyroson.MarkyCraft.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;

public class VersionUtils {
	/**
	* This method is responsible for getting the version of bukkit installed
	* @returns null if could not find version, otherwise the version of bukkit installed
	*/
	public static String getBukkitVersion()
	{
		Matcher matcher = Pattern.compile("v\\d+_\\d+_R\\d+").matcher(Bukkit.getServer().getClass().getPackage().getName());
		if (matcher.find()) {
			return matcher.group();
		}
		return null;
	}
	/**
	* This method returns the version of Minecraft used
	* @returns null if Minecraft version is not found, otherwise returns Minecraft version
	*/
	public static String getMinecraftVersion()
	{
		Matcher matcher = Pattern.compile("(\\(MC: ) )[\\d\\.]+) (\\))").matcher(Bukkit.getVersion());
		if (matcher.find()) {
			return matcher.group(2);
		}
		return null;
	}
}
