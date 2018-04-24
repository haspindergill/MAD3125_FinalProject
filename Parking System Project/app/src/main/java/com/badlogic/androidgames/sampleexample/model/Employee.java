package com.badlogic.androidgames.sampleexample.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by macstudent on 2018-04-06.
 */

public class Employee implements Parcelable
{
    private int employeeID;
    private String employeeName;
    private double salary;
    private boolean gender; // TRUE = MALE, FALSE = FEMALE

    public Employee() {
    }

    protected Employee(Parcel in) {
        employeeID = in.readInt();
        employeeName = in.readString();
        salary = in.readDouble();
        gender = in.readByte() != 0;
    }

    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(employeeID);
        dest.writeString(employeeName);
        dest.writeDouble(salary);
        dest.writeByte((byte) (gender ? 1 : 0));
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeID=" + employeeID +
                ", employeeName='" + employeeName + '\'' +
                ", salary=" + salary +
                ", gender=" + gender +
                '}';
    }
}
