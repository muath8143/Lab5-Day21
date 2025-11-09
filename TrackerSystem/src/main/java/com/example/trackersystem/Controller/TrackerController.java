package com.example.trackersystem.Controller;

import com.example.trackersystem.Api.Apiresponse;
import com.example.trackersystem.Model.ProjectTrackerSystem;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/tracker")
public class TrackerController {
    ArrayList<ProjectTrackerSystem> projects=new ArrayList<>();

    @PostMapping("/create")
    public Apiresponse CreateProject(@RequestBody ProjectTrackerSystem project){
        projects.add(project);
        return new Apiresponse("The project has been successfully added");
    }

    @GetMapping("/display")
    public ArrayList<ProjectTrackerSystem> display(){
        return projects;
    }

    @PutMapping("/update/{index}")
    public Apiresponse updateProject(@PathVariable int index,@RequestBody ProjectTrackerSystem project){
        projects.set(index,project);
        return new Apiresponse("The project has been successfully updated");
    }

    @DeleteMapping("/delete/{index}")
    public Apiresponse deleteProject(@PathVariable int index){
        projects.remove(index);
        return new Apiresponse("The project have index: ("+index+") has been successfully deleted" );
    }

    @PutMapping("/updatestatus/{ID}")
    public Apiresponse updateStatus(@PathVariable int ID ){
        for (ProjectTrackerSystem t:projects){
            if(t.getID()==ID){
                if (!t.isStatus()){
                    t.setStatus(true);
                    return new Apiresponse("The project status has been changed to done");
                }
                else{
                    return new Apiresponse("The project is done you can't change the status to not done");
                }
            }

        }
        return new Apiresponse("The project have id: ("+ID+") is not existing");
    }

    @GetMapping("/search/{title}")
    public ProjectTrackerSystem SearchByTitle(@PathVariable String title){
        for (ProjectTrackerSystem project:projects){
            if (project.getTitle().equalsIgnoreCase(title)){
                return project;
            }
        }
        return null;
    }

    @GetMapping("/displayprojectscompany/{companyName}")
    public ArrayList<ProjectTrackerSystem> DisplayByCompanyName(@PathVariable String companyName){
        ArrayList<ProjectTrackerSystem> projectsByName=new ArrayList<>();
        for (ProjectTrackerSystem project:projects){
            if (project.getCompanyName().equalsIgnoreCase(companyName)){
                projectsByName.add(project);
            }
        }
        return projectsByName;
    }
}
