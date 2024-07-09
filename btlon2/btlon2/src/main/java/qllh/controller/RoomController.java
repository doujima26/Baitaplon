package qllh.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import qllh.view.RoomView;

import qllh.entity.Room;

public class RoomController implements ActionListener{
    
    private RoomView roomView;

    public RoomController(RoomView view) {
        this.roomView = view;
        
        
}

    void showRoomForm() {
        roomView.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      
    }

   
}