package module.project.androidbraintech.jluapp.containers;

/**
 * Created by Tushar-PC on 01-12-2016.
 */
public class ContentFaculty {

    String fid, name, designation, department, school, contact, email, qualification, description, url,course;

    public ContentFaculty(String fid, String name, String designation, String department, String school, String contact, String email, String qualification, String description, String url,String course) {
        this.fid = fid;
        this.name = name;
        this.designation = designation;
        this.department = department;
        this.school = school;
        this.contact = contact;
        this.email = email;
        this.qualification = qualification;
        this.description = description;
        this.url = url;
        this.course=course;
    }

    public String getFid() {
        return fid;
    }

    public String getName() {
        return name;
    }

    public String getDesignation() {
        return designation;
    }

    public String getDepartment() {
        return department;
    }

    public String getSchool() {
        return school;
    }

    public String getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }

    public String getQualification() {
        return qualification;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getCourse() {
        return course;
    }
}
