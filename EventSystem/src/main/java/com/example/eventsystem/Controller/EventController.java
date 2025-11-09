package com.example.eventsystem.Controller;

import com.example.eventsystem.Api.ApiResponse;
import com.example.eventsystem.Model.EventSystem;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {
    ArrayList<EventSystem> events=new ArrayList<>();

    @PostMapping("/create")
    public ApiResponse crateEvent(@RequestBody EventSystem event){
        event.setStartDate(LocalDateTime.now());
        events.add(event);
        return new ApiResponse("The event has been successfully added");
    }

    @GetMapping("/get")
    public ArrayList<EventSystem> getAllEvents(){
        return events;
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateEvent(@PathVariable int index,@RequestBody EventSystem event){
        LocalDateTime oldTime=events.get(index).getStartDate();
        event.setStartDate(oldTime);
        events.set(index,event);
        return new ApiResponse("The event have index: ("+index+") has been successfully updated");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteEvent(@PathVariable int index){
        events.remove(index);
        return new ApiResponse("The event have index: ("+index+") has been successfully removed");
    }

    @PutMapping("/updatecapacity/{id}/{capacity}")
    public ApiResponse updateCapacity(@PathVariable int id,@PathVariable int capacity){
        int oldCapacity;
        for (EventSystem event:events){
            if (event.getId()==id){
                oldCapacity= event.getCapacity();
                event.setCapacity(capacity);
                return new ApiResponse("The capacity has changed from: "+oldCapacity+" to "+event.getCapacity());
            }
        }
        return new ApiResponse("invalid id");
    }


    @GetMapping("/search/{id}")
    public EventSystem searchEventById(@PathVariable int id){
        for (EventSystem event:events){
            if (event.getId()==id){
                return event;
            }
        }
        return null;
    }

}
