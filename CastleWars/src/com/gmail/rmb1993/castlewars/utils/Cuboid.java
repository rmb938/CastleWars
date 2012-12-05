package com.gmail.rmb1993.castlewars.utils;

import java.util.Random;
import org.bukkit.Location;
import org.bukkit.World;

public class Cuboid {

    private final Location highPoints;
    private final Location lowPoints;

    /**
     * Constructs a new cuboid.
     *
     * @param owner the owner of the cuboid
     * @param startLoc the first point
     * @param endLoc the second point
     */
    public Cuboid(Location startLoc, Location endLoc) {

        final int lowx = Math.min(startLoc.getBlockX(), endLoc.getBlockX());
        final int lowy = Math.min(startLoc.getBlockY(), endLoc.getBlockY());
        final int lowz = Math.min(startLoc.getBlockZ(), endLoc.getBlockZ());

        final int highx = Math.max(startLoc.getBlockX(), endLoc.getBlockX());
        final int highy = Math.max(startLoc.getBlockY(), endLoc.getBlockY());
        final int highz = Math.max(startLoc.getBlockZ(), endLoc.getBlockZ());

        highPoints = new Location(startLoc.getWorld(), highx, highy, highz);
        lowPoints = new Location(startLoc.getWorld(), lowx, lowy, lowz);
    }

    /**
     * Determines whether the passed area is within this area.
     *
     * @param area the area to check
     * @return true if the area is within this area, otherwise false
     */
    public boolean isAreaWithinArea(Cuboid area) {
        return (containsLoc(area.highPoints) && containsLoc(area.lowPoints));
    }

    /**
     * Determines whether the this cuboid contains the passed location.
     *
     * @param loc the location to check
     * @return true if the location is within this cuboid, otherwise false
     */
    public boolean containsLoc(Location loc) {
        if (loc == null || !loc.getWorld().equals(highPoints.getWorld())) {
            return false;
        }

        return lowPoints.getBlockX() <= loc.getBlockX()
                && highPoints.getBlockX() >= loc.getBlockX()
                && lowPoints.getBlockZ() <= loc.getBlockZ()
                && highPoints.getBlockZ() >= loc.getBlockZ()
                && lowPoints.getBlockY() <= loc.getBlockY()
                && highPoints.getBlockY() >= loc.getBlockY();
    }

    /**
     * Returns the volume of this cuboid.
     *
     * @return the volume of this cuboid
     */
    public long getSize() {
        return Math.abs(getXSize() * getYSize() * getZSize());
    }

    /**
     * Determines a random location inside the cuboid and returns it.
     *
     * @return a random location within the cuboid
     */
    public Location getRandomLocation() {
        final World world = getWorld();
        final Random randomGenerator = new Random();

        Location result = new Location(world, highPoints.getBlockX(), highPoints.getBlockY(), highPoints.getZ());

        if (getSize() > 1) {
            final double randomX = lowPoints.getBlockX() + randomGenerator.nextInt(getXSize());
            final double randomY = lowPoints.getBlockY() + randomGenerator.nextInt(getYSize());
            final double randomZ = lowPoints.getBlockZ() + randomGenerator.nextInt(getZSize());

            result = new Location(world, randomX, randomY, randomZ);
        }

        return result;
    }

    /**
     * Determines a random location inside the cuboid that is suitable for mob spawning and returns it.
     *
     * @return a random location inside the cuboid that is suitable for mob spawning
     */
    public Location getRandomLocationForMobs() {
        final Location temp = getRandomLocation();

        return new Location(temp.getWorld(), temp.getBlockX() + 0.5, temp.getBlockY() + 0.5, temp.getBlockZ() + 0.5); 
    }

    /**
     * Returns the x span of this cuboid.
     *
     * @return the x span of this cuboid
     */
    public int getXSize() {
        return (highPoints.getBlockX() - lowPoints.getBlockX()) + 1;
    }

    /**
     * Returns the y span of this cuboid.
     *
     * @return the y span of this cuboid
     */
    public int getYSize() {
        return (highPoints.getBlockY() - lowPoints.getBlockY()) + 1;
    }

    /**
     * Returns the z span of this cuboid.
     *
     * @return the z span of this cuboid
     */
    public int getZSize() {
        return (highPoints.getBlockZ() - lowPoints.getBlockZ()) + 1;
    }

    /**
     * Returns the higher location of this cuboid.
     *
     * @return the higher location of this cuboid
     */
    public Location getHighLoc() {
        return highPoints;
    }

    /**
     * Returns the lower location of this cuboid.
     *
     * @return the lower location of this cuboid
     */
    public Location getLowLoc() {
        return lowPoints;
    }

    /**
     * Returns the world this cuboid is in.
     *
     * @return the world this cuboid is in
     */
    public World getWorld() {
        return highPoints.getWorld();
    }
}