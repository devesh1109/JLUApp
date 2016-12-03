package module.project.androidbraintech.jluapp.Utilities;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Tushar-PC on 30-11-2016.
 */
public class SchoolAndCourseResolver {

    public static String GetSchoolName(String o) {

        String school="";


        try {
            switch (o){
                case "1":school="School Of Engineering and Technology";
                    break;
                case "2":school="School Of Management";
                    break;
                case "3":school="School Of Media And Communication";
                    break;
                case "4":school="School Of Commerce And Economics";
                    break;
                case "5":school="School Of Banking And Finance";
                    break;
                case "6":school="School Of Hospitality And Tourism";
                    break;
                case "7":school="School Of Humanities And Arts";
                    break;
                case "8":school="School Of Law";
                    break;
                case "9":school="School Of Education";
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return school;

    }


    public static String getCourseName(String o) {
        String course="";

        try {
            switch (o){
                case "1":course="BTECH Mechanical Engineering";
                    break;
                case "2":course="BCA";
                    break;
                case "3":course="BMS";
                    break;
                case "4":course="BBA";
                    break;
                case "5":course="MBA";
                    break;
                case "6":course="BA";
                    break;
                case "7":course="BFA";
                    break;
                case "8":course="Diploma In Journalism";
                    break;
                case "9":course="Diploma In Audio & Visual";
                    break;
                case "10":course="BCOM";
                    break;
                case "11":course="BCOM(Hons)";
                    break;
                case "12":course="BSC Economics";
                    break;
                case "13":course="MPHIL";
                    break;
                case "14":course="PHD";
                    break;
                case "15":course="MA Applied Economics";
                    break;
                case "16":course="BTECH Civil Engineering";
                    break;
                case "17":course="BTECH Computer Science";
                    break;
                case "18":course="BTECH Computer Science (Mobile Application & Cloud Computing)";
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return course;

    }




}
