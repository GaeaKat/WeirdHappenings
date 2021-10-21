package dev.katcodes.weirdhappenings.happenings;

import com.google.common.collect.Collections2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NavigableMap;

public class HappeningManager {
    private static List<HappeningBase> happenings=new ArrayList<>();

    public static HappeningBase GetHappening() {
        Collections.shuffle(happenings);
        double completeWeight=0.0;
        for(HappeningBase happening:happenings)
            completeWeight+=happening.getWeight();
        double r=Math.random()*completeWeight;
        double countWeight=0.0f;
        for(HappeningBase happening:happenings) {
            countWeight+=happening.getWeight();
            if(countWeight>=r)
                return happening;
        }
        // If all happenings have been disabled in the config.
        return null;
    }
    public static void register(HappeningBase happeningBase) {
        if(happenings.contains(happeningBase))
            return;
        if(getHappenings().contains(happeningBase.getName()))
            throw new RuntimeException("Can not add happening with same name as previous");
        happenings.add(happeningBase);
    }

    public static List<String> getHappenings() {
        List<String> retVal=new ArrayList<>();
        for(HappeningBase happeningBase:happenings)
            retVal.add(happeningBase.getName());
        return retVal;
    }
    public static HappeningBase getHappeningByName(String name) {
        for(HappeningBase happeningBase:happenings)
            if(happeningBase.getName().equalsIgnoreCase(name))
                return happeningBase;
        return null;
    }
}
