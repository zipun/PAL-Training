package io.pivotal.pal.tracker;

import java.util.List;

public interface TimeEntryRepository {
    public TimeEntry create(TimeEntry tentry);
    public TimeEntry find(long id);
    public List<TimeEntry> list();
    public TimeEntry update(long id, TimeEntry tentry);
    public boolean delete(long id);
}
