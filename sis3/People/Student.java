package sis3.People;

import sis3.Data;
import sis3.Enum.Departments;
import sis3.Objects.Course;
import sis3.Objects.Mark;

import java.io.*;
import java.util.TreeSet;
import java.util.Vector;

public class Student extends Employee implements Serializable {
    private Departments department;
    private TreeSet<Course> courses;
    private Vector<Mark> marks;

    public Student(){
        super();
        department=Departments.NONE;
        courses=new TreeSet<>();
        marks=new Vector<>();
    }
    public Student(String n,String s,String l,String p,int num,int year,Departments d,TreeSet<Course> c,Vector<Mark> m){
        super(n,s,l,p,num,year);
        department=d;
        courses=c;
        marks=m;
    }

    public Vector<Mark> getMarks() {
        return marks;
    }

    public void setMarks(Vector<Mark> marks) {
        this.marks = marks;
    }

    public void setDepartment(Departments department) {
        this.department = department;
    }

    public void setCourses(TreeSet<Course> courses) {
        if(Data.courses.containsAll(courses))
            this.courses = courses;
        else{
            System.out.println("Not all courses are registered in the system");
        }
    }

    public TreeSet<Course> getCourses() {
        return courses;
    }

    public Departments getDepartment() {
        return department;
    }
    public void registerToCourse(Course s){
        if(courses.contains(s))
            System.out.println("You have been already registered to this course");
        else{
            if(Data.courses.contains(s)){
                courses.add(s);
                System.out.println("Registration is successful");
            }else{
                System.out.println("This course isn't registered in the system");

            }
        }
    }
    public void viewOwnCourses(){
        for(Course s:courses){
            System.out.println(s.getCourseTitle());
        }
    }

    public void viewAllCourses(){
        for(Course s:Data.courses){
            System.out.println(s.getCourseTitle());
        }
    }
    public void viewCourseFiles(Course s){
        if(Data.courses.contains(s)){
            System.out.println(s.getFiles());
        }else{
            System.out.println("This course doesn't exist");
        }

    }
    public void viewTeacherFromCourse(Course s,String name){
        boolean found=false;
        for(Teacher t:s.getTutors()){
            if((t.getName()+" "+t.getSurname()).equals(name)){
                System.out.println(t);
                found =true;
                break;
            }
        }
        if(!found)
            System.out.println("This teacher isn't from this course");
    }
    public void viewMarkForCourse(Course s){
        if(courses.contains(s)){
            System.out.println(s.getCourseTitle());
            boolean b=false;
            for (Mark m:marks){
                if(m.getCourse().getCourseTitle().equals(s.getCourseTitle())){
                    System.out.print(m+" ");
                    b=true;
                }
            }
            if(!b){
                System.out.println("NO MARKS");
            }
        }else{
            System.out.println("You are not registered to this course");
        }
    }
    public void viewTranscript(){
        for(Course s:courses){
            System.out.println(s.getCourseTitle());
            for(Mark m:marks){
                if(m.getCourse().getCourseTitle().equals(s.getCourseTitle()))
                    System.out.print(m+" ");
            }
            System.out.println();
        }
    }

    public int hashcode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString()+department;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Student){
            Student s=(Student)obj;
            if(super.equals((Employee)s)){
                if(marks.toString().equals(s.marks.toString())&&department==s.department&&courses.toString().equals(s.courses.toString()))
                    return true;
            }
        }
        return false;
    }
    public void save(){
        Data.students.add(this);
        Data.save();
    }

}

