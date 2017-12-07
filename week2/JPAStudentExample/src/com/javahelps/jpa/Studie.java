package com.javahelps.jpa;

import java.io.Serializable;
import javax.persistence.Column;

public class Studie implements Serializable {
    @Column(name = "studie_name", nullable = false)
    private String studie_name;

    @Column(name = "school_name", nullable = false)
    private String school_name;

    @Column(name = "student_nummer", nullable = false)
    private int student_nummer;

    public String getStudie_name() {
        return studie_name;
    }

    public void setStudie_name(String studie_name) {
        this.studie_name = studie_name;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public int getStudent_nummer() {
        return student_nummer;
    }

    public void setStudent_nummer(int student_nummer) {
        this.student_nummer = student_nummer;
    }

    @Override
    public String toString() {
        return studie_name + "\t" + school_name + "\t" + student_nummer;
    }
}
