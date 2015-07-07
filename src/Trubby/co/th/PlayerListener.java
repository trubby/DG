package Trubby.co.th;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class PlayerListener implements Listener{

	Vector vMin = null;
	Vector vMax = null;
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		
		Player p = e.getPlayer();
		if(p.getItemInHand().getType() == Material.APPLE){
			
			vMin = p.getLocation().toVector();
			p.sendMessage("vMin Set!");
		}else if(p.getItemInHand().getType() == Material.GOLDEN_APPLE){
			
			vMax = p.getLocation().toVector();
			p.sendMessage("vMax Set!");
		}
		
		
	}
	
	@EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        final Player p = e.getPlayer();
        final Location pLoc = p.getLocation();
        final Vector pVec = pLoc.toVector();
 
        if ((e.getFrom().getBlockX() != e.getTo().getBlockX()) || (e.getFrom().getBlockZ() != e.getTo().getBlockZ())) {
 
            if (vMin != null && vMax != null) {
                if (pVec.isInAABB(vMin, vMax)) {
                    System.out.println("player is in region");
                } else {
                    System.out.println(" player is not in region ");
                }
            }else{
            	System.out.println("null");
            }
 
        }
    }
	
}
