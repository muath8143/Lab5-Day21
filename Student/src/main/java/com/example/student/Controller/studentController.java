package com.example.student.Controller;

import com.example.student.Api.ApiResponse;
import com.example.student.Model.StudentSystem;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/student")
public class studentController {
    ArrayList<StudentSystem> students=new ArrayList<>();

    @PostMapping("/create")
    public ApiResponse CreateStudent (@RequestBody StudentSystem student){
        if(student.getGPA()>=0 && student.getGPA()<=4){
            students.add(student);
            return new ApiResponse("The student has been successfully added");
        }
        return new ApiResponse("invalid GPA please enter a GPA between 0.00 and 4");
    }

    @GetMapping("/display")
    public ArrayList<StudentSystem> displayStudents(){
        return students;
    }

    @PutMapping("/update/{index}")
    public ApiResponse UpdateInfoStudent(@PathVariable int index,@RequestBody StudentSystem student){
        if(student.getGPA()>=0 && student.getGPA()<=4){
            students.set(index,student);
            return new ApiResponse("The student information has been successfully updated");
        }
        return new ApiResponse("invalid GPA please enter a GPA between 0.00 and 4");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse DeleteStudent (@PathVariable int index){
        students.remove(index);
        return new ApiResponse("The student has been successfully deleted");
    }

    //1: >=3.75
    //2: >=3.25 and <3.75
    @GetMapping("/classifybyhonor/{id}")
    public ApiResponse ClassifyByHonorCategories(@PathVariable int id){
        for (StudentSystem student:students){
            if (student.getID()==id){
                if (student.getGPA()>=3.75){
                    return new ApiResponse("This student graduated with first-class honor");
                } else if (student.getGPA()>=3.25) {
                    return new ApiResponse("This student graduated with seconde-class honor");
                }
                else{
                    return new ApiResponse("This student graduated with no class honor");
                }
            }
        }
        return new ApiResponse("invalid id ");
    }




    @GetMapping("/displaygroupofstudent")
    public ArrayList<StudentSystem> DisplayStudentsByAverageGPA(){
        ArrayList<StudentSystem>greaterThanAvg=new ArrayList<>();
        double avg=0;
        for (StudentSystem s:students){
            avg=avg+s.getGPA();
        }
        avg=avg/students.size();

        for (StudentSystem s:students){
            if (s.getGPA()>avg){
                greaterThanAvg.add(s);
            }
        }
        return greaterThanAvg;
    }


}
