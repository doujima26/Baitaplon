package qllh.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rooms")
@XmlAccessorType(XmlAccessType.FIELD)
public class RoomXML {
    
    private List<Room> room;

    public List<Room> getRooms() {
        return room;
    }

    public void setRooms(List<Room> room) {
        this.room = room;
    }
}