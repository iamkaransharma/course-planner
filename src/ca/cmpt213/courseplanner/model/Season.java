package ca.cmpt213.courseplanner.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Season represents the three seasonal semesters: Spring, Summer and Fall and acts a bridge between semesters and
 * offerings
 */
public enum Season {
    SPRING(1),
    SUMMER(4),
    FALL(7);

    private static Map<Integer, Season> seasonIdToSeason = new HashMap<>();

    static {
        for (Season season : Season.values()) {
            seasonIdToSeason.put(season.seasonID, season);
        }
    }

    private int seasonID;

    Season(final int seasonId) {
        this.seasonID = seasonId;
    }

    public static Season valueOf(int seasonID) {
        return seasonIdToSeason.get(seasonID);
    }

    public String value() {
        return String.valueOf(seasonID);
    }
}
