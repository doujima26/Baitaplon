package qllh.entity;

import java.util.Date;
import qllh.entity.Room;

public class RoomInfo {
    private Room room;
    private Date startTime;
    private Date endTime;

    public RoomInfo(Room room, Date startTime, Date endTime) {
        this.room = room;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // getters
    public Room getRoom() {
        return room;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    // setters nếu cần
}
