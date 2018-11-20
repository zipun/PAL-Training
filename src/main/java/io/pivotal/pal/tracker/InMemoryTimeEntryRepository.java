package io.pivotal.pal.tracker;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private long counter = 0l;

    private HashMap<String, TimeEntry> entrycache = new HashMap<String, TimeEntry>();

    public TimeEntry create(TimeEntry timeEntry){
        //System.out.println("Input entry:" + timeEntry.getProjectId());
        if (timeEntry.getId() == 0l) {
            counter = counter + 1;
            timeEntry.setId(counter);
        }
        entrycache.put(String.valueOf(timeEntry.getId()), timeEntry);
        TimeEntry entry = entrycache.get(String.valueOf(timeEntry.getId()));
        //System.out.println("Create entry:" + entry.toJsonString());
        return  entry;
    }

    public TimeEntry find(long id){
        //System.out.println("Input for find:" + id);
        return entrycache.getOrDefault(String.valueOf(id), null);
    }

    public List<TimeEntry> list(){
        List<TimeEntry> entryList = new ArrayList<>();
        for (Map.Entry<String, TimeEntry> stringTimeEntryEntry : entrycache.entrySet()) {
            entryList.add(stringTimeEntryEntry.getValue());
        }
        return entryList;
    }

    public TimeEntry update(long id, TimeEntry tentry){
        String lid = String.valueOf(id);
        if (tentry.getId() == 0l) {
            tentry.setId(id);
        }
        if (entrycache.containsKey(lid)){
            System.out.println("Found entry for ID:" + lid);
            System.out.println("Updated as entry:" + tentry.toJsonString());
            entrycache.replace(lid, tentry);
        } else {
            //entrycache.put(lid, tentry);
        }
        return entrycache.get(lid);
    }

    public boolean delete(long id){
        String lid = String.valueOf(id);
        if (entrycache.containsKey(lid)){
            entrycache.remove(lid);
            return true;
        }
        return false;
    }
}
