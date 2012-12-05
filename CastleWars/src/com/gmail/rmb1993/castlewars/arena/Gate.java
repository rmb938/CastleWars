package com.gmail.rmb1993.castlewars.arena;

import com.gmail.rmb1993.castlewars.utils.Cuboid;
import java.util.ArrayList;
import org.bukkit.Location;
import org.bukkit.Material;

/**
 *
 * @author Ryan
 */
public class Gate extends Cuboid {

    private int healthPoints;

    public Gate(int healthPoints, Location startLoc, Location endLoc) {
        super(startLoc, endLoc);
        this.healthPoints = healthPoints;
    }

    private ArrayList<Location> locationsInCuboid() {
        ArrayList<Location> locations = new ArrayList();
        for (int i = this.getLowLoc().getBlockX(); i <= this.getHighLoc().getBlockX(); i++) {
            for (int j = this.getLowLoc().getBlockY(); j <= this.getHighLoc().getBlockY(); j++) {
                for (int k = this.getLowLoc().getBlockZ(); k <= this.getHighLoc().getBlockZ(); k++) {
                    locations.add(new Location(this.getWorld(), i, j, k));
                }
            }
        }
        return locations;
    }

    public boolean isBlockInGate(Location loc) {
        ArrayList<Location> locations = locationsInCuboid();
        for (Location l : locations) {
            if (l.equals(loc) == true) {
                return true;
            }
        }
        return false;
    }
    
    private void removeGate() {
        ArrayList<Location> locations = locationsInCuboid();
        for (Location l : locations) {
            l.getBlock().setType(Material.AIR);
        }
    }
    
    public int getHealthPoints() {
        return healthPoints;
    }

    public void removeHP(int i) {
        if (healthPoints > 0) {
            healthPoints -= 1;
        } else if (healthPoints == 0) {
            removeGate();
        }
    }
}
