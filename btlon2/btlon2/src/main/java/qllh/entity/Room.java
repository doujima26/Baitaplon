package qllh.entity;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "room")
@XmlAccessorType(XmlAccessType.FIELD)
public class Room implements Serializable {
    private static final long serialVersionUID = 1L;
    private String roomName;
    private int numberOfComputers;
    private boolean hasProjector;
    private boolean hasWhiteboard;
    private boolean hasMicrophone;
    private boolean hasSpeaker;
    private boolean hasInternet;
    public Room(String roomName, int numberOfComputers, boolean hasProjector, boolean hasWhiteboard, boolean hasMicrophone, boolean hasSpeaker, boolean hasInternet) {
        this.roomName = roomName;
        this.numberOfComputers = numberOfComputers;
        this.hasProjector = hasProjector;
        this.hasWhiteboard = hasWhiteboard;
        this.hasMicrophone = hasMicrophone;
        this.hasSpeaker = hasSpeaker;
        this.hasInternet = hasInternet;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getNumberOfComputers() {
        return numberOfComputers;
    }

    public boolean isHasProjector() {
        return hasProjector;
    }

    public boolean isHasWhiteboard() {
        return hasWhiteboard;
    }

    public boolean isHasMicrophone() {
        return hasMicrophone;
    }

    public boolean isHasSpeaker() {
        return hasSpeaker;
    }

    public boolean isHasInternet() {
        return hasInternet;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setNumberOfComputers(int numberOfComputers) {
        this.numberOfComputers = numberOfComputers;
    }

    public void setHasProjector(boolean hasProjector) {
        this.hasProjector = hasProjector;
    }

    public void setHasWhiteboard(boolean hasWhiteboard) {
        this.hasWhiteboard = hasWhiteboard;
    }

    public void setHasMicrophone(boolean hasMicrophone) {
        this.hasMicrophone = hasMicrophone;
    }

    public void setHasSpeaker(boolean hasSpeaker) {
        this.hasSpeaker = hasSpeaker;
    }

    public void setHasInternet(boolean hasInternet) {
        this.hasInternet = hasInternet;
    }
        @Override
    public String toString() {
        return roomName + " (" + numberOfComputers + " máy)";
    }
}