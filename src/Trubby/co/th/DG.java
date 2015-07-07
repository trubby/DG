package Trubby.co.th;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.World.Environment;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.material.EnderChest;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.schematic.SchematicFormat;

@SuppressWarnings("deprecation")
public class DG extends JavaPlugin {
	
	ArrayList<CuboidClipboard> ccList = new ArrayList<>();
	CuboidClipboard ccFirst;
	
	public static DG plugin;
	
	@Override
	public void onEnable() {
		plugin = this;
		
		Bukkit.getPluginManager().registerEvents(new MobsListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
		
		File dataDirectory = this.getDataFolder();
		File schematicsDirectory = new File(dataDirectory, "schematics");
		
		File[] schematics = schematicsDirectory.listFiles();
		
		for (File schematic : schematics) {
			if (schematic.getName().endsWith(".schematic")) {

				SchematicFormat sf = SchematicFormat.getFormat(schematic);
				CuboidClipboard cc = null;
				
				//IS FIRST
				if(schematic.getName().endsWith("1_.schematic")){
					//ccFirst = FIRST
					try {
						cc = sf.load(schematic);
						ccFirst = cc;
						
					} catch (IOException | DataException e) {
					}
					
					
				}else{
					//ADD TO ccList
					try {
						cc = sf.load(schematic);
						ccList.add(cc);
						
					} catch (IOException | DataException e) {
					}
				}
			}
		}
		
	}
	
	Random ran = new Random();
	

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {

		if (label.equalsIgnoreCase("dgori")) {

			Location loc = create();
			BlockFace bf = BlockFace.NORTH;
			boolean first = true;
			
			for (int i = 0; i < 5; i++) {
				
				CuboidClipboard cc;
				//GET FROM LIST
				if(first){
					cc = ccFirst;
					first = false;
				}else{
					cc = ccList.get(ran.nextInt(ccList.size()));
				}
				
				//ROTATE
				if(getDirection(bf) > 0){
			    	cc.rotate2D(getDirection(bf));
			    }

				/**
				 * 
				 * 
				 */
				for (int y = 0; y < cc.getSize().getBlockY(); y++) {
					for (int x = 0; x < cc.getSize().getBlockX(); x++) {
						for (int z = 0; z < cc.getSize().getBlockZ(); z++) {
							Vector currentPoint = new Vector(x, y, z);
							int currentBlock = cc.getPoint(currentPoint).getType();
							if (currentBlock != 0) {
								if ((currentBlock == Material.TRAPPED_CHEST.getId())){
									
									/**/
								    
								}else if (currentBlock == Material.ENDER_CHEST.getId()){
									
									//get enderchest
									int offsetX = cc.getOffset().getBlockX();
								    int offsetZ = cc.getOffset().getBlockZ();
								    int offsetY = cc.getOffset().getBlockY();
									//int offset = offsetX < offsetZ ? offsetX : offsetZ;
									
								    //rotate
								    
								    pasteSchematic(loc, cc);
								    
									loc = loc.add(x + offsetX, y + offsetY, z + offsetZ);
									
									loc.add(0, 1, 0).getBlock().setType(Material.DIAMOND_BLOCK);
									EnderChest ec = (EnderChest) loc.add(0, -1, 0).getBlock().getState().getData();
									bf = ec.getFacing();
									
									//loc.add(0, 1, 0).getBlock().setType(Material.DIAMOND_BLOCK);
									
									System.out.println(bf);
									
								}
							} 
						}
					}
				}
			}
			
		}else if(label.equalsIgnoreCase("rs")){
			
			Player p = (Player) sender;
			for (int i = 0; i < 4; i++) {
				Location loc = p.getLocation();
				Location ranloc = new Location(loc.getWorld(), loc.getX() + (ran.nextInt(9) - 4), loc.getY(), loc.getZ() + (ran.nextInt(9) - 4));
				ranloc.getWorld().spawnEntity(ranloc, EntityType.CREEPER);
				
			}
			
			
		}else if (label.equalsIgnoreCase("dg")) {

			Player p = (Player) sender;
			Location loc = p.getLocation();
			BlockFace bf = BlockFace.NORTH;
			boolean first = true;
			
			for (int i = 0; i < 5; i++) {
				
				CuboidClipboard cc;
				//GET FROM LIST
				if(first){
					cc = ccFirst;
					first = false;
				}else{
					cc = ccList.get(ran.nextInt(ccList.size()));
				}
				
				//ROTATE
				if(getDirection(bf) > 0){
			    	cc.rotate2D(getDirection(bf));
			    }

				/**
				 * 
				 * 
				 */
				for (int y = 0; y < cc.getSize().getBlockY(); y++) {
					for (int x = 0; x < cc.getSize().getBlockX(); x++) {
						for (int z = 0; z < cc.getSize().getBlockZ(); z++) {
							Vector currentPoint = new Vector(x, y, z);
							int currentBlock = cc.getPoint(currentPoint).getType();
							if (currentBlock != 0) {
								if ((currentBlock == Material.TRAPPED_CHEST.getId())){
									
									/**/
								    
								}else if (currentBlock == Material.ENDER_CHEST.getId()){
									
									//get enderchest
									int offsetX = cc.getOffset().getBlockX();
								    int offsetZ = cc.getOffset().getBlockZ();
								    int offsetY = cc.getOffset().getBlockY();
									//int offset = offsetX < offsetZ ? offsetX : offsetZ;
									
								    //rotate
								    
								    pasteSchematic(loc, cc);
								    
									loc = loc.add(x + offsetX, y + offsetY, z + offsetZ);
									
									loc.add(0, 1, 0).getBlock().setType(Material.DIAMOND_BLOCK);
									EnderChest ec = (EnderChest) loc.add(0, -1, 0).getBlock().getState().getData();
									bf = ec.getFacing();
									
									//loc.add(0, 1, 0).getBlock().setType(Material.DIAMOND_BLOCK);
									
									System.out.println(bf);
									
								}
							} 
						}
					}
				}
			}
			
		}

		return super.onCommand(sender, command, label, args);
	}
	
	public BlockFace getBlockFace(Block b){
		System.out.println(b.getData());
		
		switch (b.getData()) {
		case (byte)2:return BlockFace.NORTH;
		case (byte)3:return BlockFace.SOUTH;
		case (byte)1:return BlockFace.WEST;
		case (byte)0:return BlockFace.EAST;
		default:return BlockFace.NORTH;
		}
	}
	
	public int getDirection(BlockFace face){
		if(face == BlockFace.NORTH){
			return 0;
		}else if(face == BlockFace.EAST){
			return 90;
		}else if(face == BlockFace.WEST){
			return 270;
		}else if(face == BlockFace.SOUTH){
			return 180;
		}else{
			return 0;
		}
	}

	public Location create() {
		World world = Bukkit.getServer().getWorld("test");
		if (world == null) {
			this.getLogger().info("Loading world '" + "test" + "'.");
			WorldCreator arenaWorldCreator = new WorldCreator("test");
			arenaWorldCreator.generateStructures(false);
			arenaWorldCreator.generator(new VoidGenerator());
			arenaWorldCreator.type(WorldType.FLAT);
			arenaWorldCreator.environment(Environment.NETHER);
			arenaWorldCreator.seed(0);
			world = arenaWorldCreator.createWorld();
			this.getLogger().info("Done loading world '" + "test" + "'.");
		} else {
			this.getLogger().info(
					"The world '" + "test" + "' was already loaded.");
		}
		world.setGameRuleValue("doMobSpawning", "false");
		world.setGameRuleValue("doMobLoot", "false");
		world.setGameRuleValue("keepInventory", "true");
		world.setGameRuleValue("mobGriefing", "false");
		world.setAutoSave(false);
		world.getBlockAt(-5000, 45, -5000).setType(Material.STONE);
		world.setSpawnLocation(-5000, 50, -5000);
		
		return new Location(world, -5000, 45, -5000);
	}

	public static boolean pasteSchematic(Location origin,
			CuboidClipboard schematic) {
		EditSession editSession = new EditSession(new BukkitWorld(
				origin.getWorld()), 2147483647);
		editSession.setFastMode(true);
		try {
			schematic.paste(
					editSession,
					new Vector(origin.getBlockX(), origin.getBlockY(), origin
							.getBlockZ()), true);
		} catch (MaxChangedBlocksException ignored) {
			return false;
		}
		return true;
	}

}
