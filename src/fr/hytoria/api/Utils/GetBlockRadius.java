package fr.hytoria.api.Utils;

/**
 * Created by Sywoo on 06/02/2017.
 */

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class GetBlockRadius {


    public static Set<Block> cubeblock(final Location center, final double radius) {
        final Set<Block> blocks = new HashSet<Block>();
        for (double x = -radius; x <= radius; x++)
            for (double y = -radius; y <= radius; y++)
                for (double z = -radius; z <= radius; z++)
                    blocks.add(center.clone().add(x, y, z).getBlock());
        return blocks;

    }
    public static Set<Block> sphereblock(final Location center, final double radius) {
        final Set<Block> blocks = new HashSet<Block>();
        for (double x = -radius; x <= radius; x++)
            for (double y = -radius; y <= radius; y++)
                for (double z = -radius; z <= radius; z++)
                    if (center.clone().add(x, y, z).distance(center) <= radius)
                        blocks.add(center.clone().add(x, y, z).getBlock());
        return blocks;

    }

    public static Set<Player> cubePlayer(final Location center, final double radius) {
        final Set<Player> blocks = new HashSet<Player>();
        for (double x = -radius; x <= radius; x++)
            for (double y = -radius; y <= radius; y++)
                for (double z = -radius; z <= radius; z++)
                    blocks.add((Player) center.clone().add(x, y, z).getBlock());
        return blocks;

    }
    public static Set<Player> spherePlayer(final Location center, final double radius) {
        final Set<Player> blocks = new HashSet<Player>();
        for (double x = -radius; x <= radius; x++)
            for (double y = -radius; y <= radius; y++)
                for (double z = -radius; z <= radius; z++)
                    if (center.clone().add(x, y, z).distance(center) <= radius)
                        blocks.add((Player) center.clone().add(x, y, z).getBlock());
        return blocks;

    }


}
