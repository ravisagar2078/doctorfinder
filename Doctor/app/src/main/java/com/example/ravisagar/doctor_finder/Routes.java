package com.example.ravisagar.doctor_finder;

/**
 * Created by Ravi Sagar on 5/9/2017.
 */
public class Routes {
    private String rname;
    private String mnumber;
    private String special;

    private String lname;
    private String add;
    private String ftiming;
    private String ttiming;


    public void setfname(String rname)
    {
        this.rname = rname;
    }
    public void setmobilenumber(String arrival)
    {
        this.mnumber = arrival;
    }
    public void setspecial(String depart)
    {
        this.special = depart;
    }
    public String getRname()
    {
        return rname;
    }
    public String getMnumber()
    {
        return mnumber;
    }
    public String getSpecial() {return special; }

    public void setlname(String lname)
    {
        this.lname = lname;
    }
    public void setadd(String arrival)
    {
        this.add = arrival;
    }
    public void setftiming(String depart)
    {
        this.ftiming = depart;
    }
    public String getlname()
    {
        return lname;
    }
    public String getadd()
    {
        return add;
    }
    public String getftiming() {return ftiming; }

    public String getttiming() {return ttiming; }

    public void setttiming(String lname)
    {
        this.ttiming = lname;
    }


}
