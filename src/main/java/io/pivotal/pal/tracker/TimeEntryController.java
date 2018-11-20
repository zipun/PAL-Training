package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class TimeEntryController {

    private TimeEntryRepository repo;

    public TimeEntryRepository getRepo() {
        return repo;
    }

    public void setRepo(TimeEntryRepository repo) {
        this.repo = repo;
    }

    public TimeEntryController(TimeEntryRepository repo){
        this.repo = repo;
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry){
        TimeEntry te = this.repo.create(timeEntry);
        return new ResponseEntity<TimeEntry>(te, HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id){
        TimeEntry te = this.repo.find(id);
        if (te != null){
            return new ResponseEntity<TimeEntry>(te, HttpStatus.OK);
        }
        return new ResponseEntity<TimeEntry>(new TimeEntry(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list(){
        List<TimeEntry> listte = this.repo.list();
        return new ResponseEntity<List<TimeEntry>>(listte, HttpStatus.OK);
    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable long id, @RequestBody TimeEntry tenrty){
        TimeEntry te = this.repo.update(id, tenrty);
        if (te != null) {
            return new ResponseEntity<TimeEntry>(te, HttpStatus.OK);
        }
        return new ResponseEntity<TimeEntry>(te, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity delete(@PathVariable  long id){
        this.repo.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
