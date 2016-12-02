package module.project.androidbraintech.jluapp.containers;

/**
 * Created by Tushar-PC on 19-09-2016.
 */
public class ContentRegisteredStudent {

    int sp_id,sp_year,sp_sem;
    String sp_roll_no,sp_name,sp_url,sp_house,sp_contact_no,sp_address,sp_school,sp_course,sp_section,sp_attendance;


    public ContentRegisteredStudent(int sp_id, String sp_school, String sp_course, int sp_year, int sp_sem, String sp_roll_no, String sp_name, String sp_url, String sp_house, String sp_contact_no, String sp_address,String sp_section,String sp_attendance) {
        this.sp_id = sp_id;
        this.sp_school = sp_school;
        this.sp_course = sp_course;
        this.sp_year = sp_year;
        this.sp_sem = sp_sem;
        this.sp_roll_no = sp_roll_no;
        this.sp_name = sp_name;
        this.sp_url = sp_url;
        this.sp_house = sp_house;
        this.sp_contact_no = sp_contact_no;
        this.sp_address = sp_address;
        this.sp_section=sp_section;
        this.sp_attendance=sp_attendance;
    }

    public int getSp_id() {
        return sp_id;
    }

    public int getSp_year() {
        return sp_year;
    }

    public int getSp_sem() {
        return sp_sem;
    }

    public String getSp_roll_no() {
        return sp_roll_no;
    }

    public String getSp_name() {
        return sp_name;
    }

    public String getSp_url() {
        return sp_url;
    }

    public String getSp_house() {
        return sp_house;
    }

    public String getSp_contact_no() {
        return sp_contact_no;
    }

    public String getSp_address() {
        return sp_address;
    }

    public String getSp_school() {
        return sp_school;
    }

    public String getSp_course() {
        return sp_course;
    }

    public String getSp_section(){return sp_section;}

    public String getSp_attendance() {
        return sp_attendance;
    }
}
