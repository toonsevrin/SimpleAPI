/*
 *    Copyright 2016 Exorath
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.exorath.simpleapi.api.lib;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

/**
 * Created by Toon Sevrin on 5/29/2016.
 */
public class VectorSerializer {
    /**
     * Deserializes a String formatted as x,y,z to a location with the provided world.
     *
     * @param world world the location should be returned in
     * @param vectorText vector string that should be deserialized to a vector formatted as x,y,z
     * @return location made with the given String and world
     */
    public static Location deserializeLocation(World world, String vectorText) {
        Vector vector = deserialize(vectorText);
        return new Location(world, vector.getX(), vector.getY(), vector.getZ());
    }

    /**
     * Deserializes a String formatted as x,y,z to a vector.
     *
     * @param vectorText vector string that should be deserialized to a vector formatted as x,y,z
     * @return vector made with the given String
     */
    public static Vector deserialize(String vectorText) {
        String[] coordinates = vectorText.split(",");
        if (coordinates.length != 3)
            return null;
        return new Vector(Double.valueOf(coordinates[0]), Double.valueOf(coordinates[1]), Double.valueOf(coordinates[2]));
    }

    /**
     * Serializes a vector to a string (x,y,z with x,y and z integer numbers)
     * @param vector vector to serialize
     * @return String serialized from given vector
     */
    public static String serializeToInts(Vector vector){
        return vector.getBlockX() + "," + vector.getBlockY() + "," + vector.getBlockZ();
    }

    /**
     * Serializes a vector to a string (x,y,z with x,y and z double numbers)
     * @param vector vector to serialize
     * @return String serialized from given vector
     */
    public static String serializeToDoubles(Vector vector){
        return vector.getX() + "," + vector.getY() + "," + vector.getZ();
    }
}
