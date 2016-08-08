package ca.cmpt213.courseplanner.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public enum Season {
    SPRING(1),
    SUMMER(4),
    FALL(7);

    private int seasonID;
    private static Map<Integer, Season> seasonIdToSeason = new HashMap<>();

    static {
        for (Season season : Season.values()) {
            seasonIdToSeason.put(season.seasonID, season);
        }
    }

    Season(final int seasonId) {
        this.seasonID = seasonId;
    }

    public static Season valueOf(int seasonID) {
        return seasonIdToSeason.get(seasonID);
    }
}
