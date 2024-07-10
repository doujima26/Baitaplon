package qllh.entity;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "classroom")
public class ClassRoom implements Serializable {
    private static final long serialVersionUID = 1L;
    private String className;
    private String subject;
    private int studentCount;

    public ClassRoom() {
    }

    public ClassRoom(String className, String subject, int studentCount) {
        this.className = className;
        this.subject = subject;
        this.studentCount = studentCount;
    }

    public String getClassName() {
        return className;
    }

    public String getSubject() {
        return subject;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }
    
 @Override
    public String toString() {
        return className + " (" + studentCount + " sinh viên)";
    }
}