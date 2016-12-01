package module.project.androidbraintech.jluapp.containers;

/**
 * Created by Tushar-PC on 01-12-2016.
 */
public class ContentAppointments {

    String a_id,a_sid,a_fid,a_sname,a_fname,a_subject,a_date,fdate;

    public ContentAppointments(String a_id, String a_sid, String a_fid, String a_sname, String a_fname, String a_subject, String a_date,String fdate) {
        this.a_id = a_id;
        this.a_sid = a_sid;
        this.a_fid = a_fid;
        this.a_sname = a_sname;
        this.a_fname = a_fname;
        this.a_subject = a_subject;
        this.a_date = a_date;
        this.fdate=fdate;
    }

    public String getA_id() {
        return a_id;
    }

    public String getA_sid() {
        return a_sid;
    }

    public String getA_fid() {
        return a_fid;
    }

    public String getA_sname() {
        return a_sname;
    }

    public String getA_fname() {
        return a_fname;
    }

    public String getA_subject() {
        return a_subject;
    }

    public String getA_date() {
        return a_date;
    }

    public String getFdate() {
        return fdate;
    }
}
