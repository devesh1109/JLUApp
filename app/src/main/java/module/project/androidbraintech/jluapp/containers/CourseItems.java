package module.project.androidbraintech.jluapp.containers;

/**
 * Created by Tushar-PC on 20-08-2016.
 */
public class CourseItems  {

    int c_id,s_id,c_years;
    String c_name;
    Double c_fees;

    public CourseItems(int c_id,int s_id, String c_name,  int c_years,Double c_fees) {
        this.c_fees = c_fees;
        this.c_id = c_id;
        this.c_name = c_name;
        this.s_id = s_id;
        this.c_years = c_years;
    }

    public Double getC_fees() {
        return c_fees;
    }

    public int getC_id() {
        return c_id;
    }

    public String getC_name() {
        return c_name;
    }

    public int getC_years() {
        return c_years;
    }

    public int getS_id() {
        return s_id;
    }
}
