package qllh.view;

import qllh.entity.Room;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import qllh.entity.ClassRoom;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import qllh.entity.RoomInfo;

    public class RoomView extends JFrame {
    private final List<Room> rooms = new ArrayList<>();
    private final List<ClassRoom> classrooms = new ArrayList<>();
    private final StringBuilder assignedList = new StringBuilder();
    private Map<ClassRoom, RoomInfo> assignedRoomsMap = new HashMap<>();
    private JTextArea classListDisplay = new JTextArea(10, 30);

    public RoomView() {
    setTitle("Quản lý phòng học");
    setSize(1000, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

  
    JButton addRoomButton = new JButton("Nhập thông tin phòng học");
    addRoomButton.addActionListener(e -> showRoomForm());

    JButton addClassButton = new JButton("Nhập thông tin lớp học");
    addClassButton.addActionListener(e -> showClassForm());
    
    JButton assignRoomButton = new JButton("Phân phòng");
    assignRoomButton.addActionListener(e -> showAssignForm());
    
    JButton btnStatistics = new JButton("Thống Kê");
    btnStatistics.addActionListener(e -> showStatistics());
    
    JButton showRoomInfoButton = new JButton("Thông tin phòng");
    showRoomInfoButton.addActionListener(e -> showRoomInfo());

    JButton showClassInfoButton = new JButton("Thông tin lớp học");
    showClassInfoButton.addActionListener(e -> showClassInfo());
  
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); 
    buttonPanel.add(addRoomButton);
    buttonPanel.add(addClassButton);
    buttonPanel.add(assignRoomButton);
    buttonPanel.add(btnStatistics);
    buttonPanel.add(showRoomInfoButton);
    buttonPanel.add(showClassInfoButton);
    

    JLabel welcomeLabel = new JLabel("Chào mừng đến với hệ thống quản lý phòng học");
    welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));


    setLayout(new FlowLayout(FlowLayout.CENTER)); 
    add(welcomeLabel);
    add(buttonPanel);
getContentPane().setBackground(Color.getHSBColor(0.5f, 0.95f, 1f)); //

    setVisible(true);
    
}

    private void showRoomForm() {
        JPanel panel = new JPanel(new GridLayout(8, 2));
        JTextField roomNameField = new JTextField();
        JTextField numberOfComputersField = new JTextField();
        JCheckBox hasProjectorBox = new JCheckBox();
        JCheckBox hasWhiteboardBox = new JCheckBox();
        JCheckBox hasMicrophoneBox = new JCheckBox();
        JCheckBox hasSpeakerBox = new JCheckBox();
        JCheckBox hasInternetBox = new JCheckBox();

        panel.add(new JLabel("Tên phòng học:"));
        panel.add(roomNameField);
        panel.add(new JLabel("Số lượng máy tính:"));
        panel.add(numberOfComputersField);
        panel.add(new JLabel("Máy chiếu:"));
        panel.add(hasProjectorBox);
        panel.add(new JLabel("Bảng trắng:"));
        panel.add(hasWhiteboardBox);
        panel.add(new JLabel("Micrô:"));
        panel.add(hasMicrophoneBox);
        panel.add(new JLabel("Loa:"));
        panel.add(hasSpeakerBox);
        panel.add(new JLabel("Internet:"));
        panel.add(hasInternetBox);
        

        int result = JOptionPane.showConfirmDialog(null, panel, "Nhập thông tin phòng học", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            Room room = new Room(
                roomNameField.getText(),
                Integer.parseInt(numberOfComputersField.getText()),
                hasProjectorBox.isSelected(),
                hasWhiteboardBox.isSelected(),
                hasMicrophoneBox.isSelected(),
                hasSpeakerBox.isSelected(),
                hasInternetBox.isSelected()
            );
            rooms.add(room);
            JOptionPane.showMessageDialog(null, "Thông tin phòng học đã được lưu.");
        }
    }
    private void showClassForm() {
        JPanel panel = new JPanel(new GridLayout(0, 2));

        JTextField classNameField = new JTextField();
        JTextField subjectField = new JTextField();
        JTextField studentCountField = new JTextField();

        panel.add(new JLabel("Tên lớp:"));
        panel.add(classNameField);
        panel.add(new JLabel("Môn học:"));
        panel.add(subjectField);
        panel.add(new JLabel("Sĩ số:"));
        panel.add(studentCountField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Nhập thông tin lớp học", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                ClassRoom classRoom = new ClassRoom(
                    classNameField.getText(),
                    subjectField.getText(),
                    Integer.parseInt(studentCountField.getText())
                );
                classrooms.add(classRoom);
                JOptionPane.showMessageDialog(null, "Thông tin lớp học đã được lưu.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Xin hãy nhập số hợp lệ cho sĩ số.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }
private void showAssignForm() {
    JTextArea assignedListArea = new JTextArea(10, 30);
    assignedListArea.setEditable(false);
    assignedListArea.setText(assignedList.toString());
    
    JPanel panel = new JPanel(new BorderLayout());
    
    SpinnerDateModel spinnerDateModelStart = new SpinnerDateModel();
    JSpinner startDateTimeSpinner = new JSpinner(spinnerDateModelStart);
    JSpinner.DateEditor startDateEditor = new JSpinner.DateEditor(startDateTimeSpinner, "dd/MM/yyyy HH:mm:ss");
    startDateTimeSpinner.setEditor(startDateEditor);

    SpinnerDateModel spinnerDateModelEnd = new SpinnerDateModel();
    JSpinner endDateTimeSpinner = new JSpinner(spinnerDateModelEnd);
    JSpinner.DateEditor endDateEditor = new JSpinner.DateEditor(endDateTimeSpinner, "dd/MM/yyyy HH:mm:ss");
    endDateTimeSpinner.setEditor(endDateEditor);

    JComboBox<ClassRoom> classRoomComboBox = new JComboBox<>();
    for (ClassRoom classRoom : classrooms) {
        classRoomComboBox.addItem(classRoom);
    }

    JComboBox<Room> roomComboBox = new JComboBox<>();
    for (Room room : rooms) {
        roomComboBox.addItem(room);
    }

    assignedListArea.setEditable(false);

    JButton assignRoomButton = new JButton("Phân phòng");

    assignRoomButton.addActionListener(e -> {
        ClassRoom selectedClassRoom = (ClassRoom) classRoomComboBox.getSelectedItem();
        Room selectedRoom = (Room) roomComboBox.getSelectedItem();
        Date startTime = (Date) startDateTimeSpinner.getValue();
        Date endTime = (Date) endDateTimeSpinner.getValue();

       
        if (startTime.after(endTime) || startTime.equals(endTime)) {
            JOptionPane.showMessageDialog(this, "Ngày hoặc giờ bắt đầu phải trước ngày hoặc giờ kết thúc. Xin hãy chọn lại.", "Ngày hoặc giờ không hợp lệ", JOptionPane.ERROR_MESSAGE);
            return; 
        }

        if (selectedClassRoom != null && selectedRoom != null) {
           
            if (assignedRoomsMap.containsKey(selectedClassRoom)) {
                JOptionPane.showMessageDialog(this, "Lớp học đã được phân phòng trước đó!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return; 
            }

           
            for (RoomInfo roomInfo : assignedRoomsMap.values()) {
                if (roomInfo.getRoom().equals(selectedRoom)) {
                    JOptionPane.showMessageDialog(this, "Phòng học đã được phân cho lớp khác!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

           
            if (selectedClassRoom.getStudentCount() <= selectedRoom.getNumberOfComputers()) {
                RoomInfo newRoomInfo = new RoomInfo(selectedRoom, startTime, endTime);
                assignedRoomsMap.put(selectedClassRoom, newRoomInfo);

                assignedList.append("Phòng ").append(selectedRoom.getRoomName())
                             .append(" được phân cho lớp ").append(selectedClassRoom.getClassName())
                             .append(" từ ").append(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(startTime))
                             .append(" đến ").append(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(endTime))
                             .append("\n");
                assignedListArea.setText(assignedList.toString());
            } else {
                JOptionPane.showMessageDialog(this, "Không đủ máy tính trong phòng cho sĩ số của lớp", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            }
        }
    });

    JButton clearListButton = new JButton("Xóa danh sách");
    clearListButton.addActionListener(e -> {
        assignedList.setLength(0); 
        assignedListArea.setText(""); 
        assignedRoomsMap.clear(); //
        JOptionPane.showMessageDialog(this, "Danh sách phân phòng đã được xóa.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    });

    JPanel topPanel = new JPanel(new GridLayout(1, 2));
    topPanel.add(classRoomComboBox);
    topPanel.add(roomComboBox);

    JPanel dateTimePanel = new JPanel(new GridLayout(2, 2));
    dateTimePanel.add(new JLabel("Thời gian bắt đầu:"));
    dateTimePanel.add(startDateTimeSpinner);
    dateTimePanel.add(new JLabel("Thời gian kết thúc:"));
    dateTimePanel.add(endDateTimeSpinner);

    JPanel centerPanel = new JPanel(new BorderLayout());
    centerPanel.add(new JScrollPane(assignedListArea), BorderLayout.CENTER);
    centerPanel.add(dateTimePanel, BorderLayout.SOUTH);

    JPanel actionPanel = new JPanel();
    actionPanel.add(assignRoomButton);
    actionPanel.add(clearListButton); 

    panel.add(topPanel, BorderLayout.NORTH);
    panel.add(centerPanel, BorderLayout.CENTER);
    panel.add(actionPanel, BorderLayout.SOUTH);

    JOptionPane.showMessageDialog(this, panel, "Phân phòng cho lớp", JOptionPane.PLAIN_MESSAGE);
}
   
private void showStatistics() {
    
    String[] columnNames = {"Tên phòng", "Tên lớp", "Thời gian bắt đầu", "Thời gian kết thúc"};

    
    Object[][] data = {};

    
    DefaultTableModel model = new DefaultTableModel(data, columnNames){
        @Override
        public boolean isCellEditable(int row, int column) {
           
           return false;
        }
    };
    JTable table = new JTable(model);
    
   
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    for (Map.Entry<ClassRoom, RoomInfo> entry : assignedRoomsMap.entrySet()) {
        ClassRoom classRoom = entry.getKey();
        RoomInfo roomInfo = entry.getValue();
        
        model.addRow(new Object[]{
            roomInfo.getRoom().getRoomName(),
            classRoom.getClassName(),
            dateFormat.format(roomInfo.getStartTime()),
            dateFormat.format(roomInfo.getEndTime())
        });
    }

    
    JTextField searchField = new JTextField(20);
    JButton searchButton = new JButton("Tìm kiếm");

    searchButton.addActionListener(e -> {
        String searchText = searchField.getText().toLowerCase();
        DefaultTableModel filteredModel = new DefaultTableModel(new Object[][]{}, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        for (int i = 0; i < model.getRowCount(); i++) {
            String roomName = model.getValueAt(i, 0).toString().toLowerCase();
            
            if (roomName.contains(searchText)) {
                filteredModel.addRow(new Object[]{
                    model.getValueAt(i, 0),
                    model.getValueAt(i, 1),
                    model.getValueAt(i, 2),
                    model.getValueAt(i, 3)
                });
            }
        }

        
        if (filteredModel.getRowCount() == 0) {
            
            JOptionPane.showMessageDialog(null, "Không tìm thấy phòng tương ứng.", "Không có kết quả", JOptionPane.INFORMATION_MESSAGE);
        } else {
            
            table.setModel(filteredModel);
        }
    });

    
    table.setPreferredScrollableViewportSize(new Dimension(500, 70));
    table.setFillsViewportHeight(true);
    JScrollPane scrollPane = new JScrollPane(table);
    JPanel searchPanel = new JPanel();
    searchPanel.add(new JLabel("Tìm kiếm theo tên phòng:"));
    searchPanel.add(searchField);
    searchPanel.add(searchButton);

    
    JDialog dialog = new JDialog((Frame) null, "Thống kê phân phòng", true);
    dialog.setLayout(new BorderLayout());
    dialog.add(searchPanel, BorderLayout.NORTH);
    dialog.add(scrollPane, BorderLayout.CENTER);
    dialog.pack();
    dialog.setLocationRelativeTo(null);
    dialog.setVisible(true);
}
private void showRoomInfo() {
    String[] columnNames = {"Tên phòng", "Số lượng máy tính", "Máy chiếu", "Bảng trắng", "Micrô", "Loa", "Internet"};
   
    
    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

    
    for (Room room : rooms) {
        tableModel.addRow(new Object[]{
                room.getRoomName(),
                String.valueOf(room.getNumberOfComputers()),
                room.isHasProjector() ? "Có" : "Không",
                room.isHasWhiteboard() ? "Có" : "Không",
                room.isHasMicrophone() ? "Có" : "Không",
                room.isHasSpeaker() ? "Có" : "Không",
                room.isHasInternet() ? "Có" : "Không"
        });
    }
    
   
    JTable roomTable = new JTable(tableModel);

    
    JScrollPane scrollPane = new JScrollPane(roomTable);

   
    JDialog roomInfoDialog = new JDialog((Frame) null, "Thông tin phòng học", true);
    roomInfoDialog.add(scrollPane); 
    roomInfoDialog.pack(); 
    roomInfoDialog.setLocationRelativeTo(null); 
    roomInfoDialog.setVisible(true);
}
private void showClassInfo() {
    String[] columnNames = {"Tên lớp", "Môn học", "Sĩ số"};

    
    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

   
    for (ClassRoom classRoom : classrooms) {
        tableModel.addRow(new Object[]{
                classRoom.getClassName(),
                classRoom.getSubject(),
                String.valueOf(classRoom.getStudentCount())
        });
    }
    
    
    JTable classTable = new JTable(tableModel);

    
    JScrollPane scrollPane = new JScrollPane(classTable);

    
    JDialog classInfoDialog = new JDialog((Frame) null, "Thông tin lớp học", true);
    classInfoDialog.add(scrollPane); 
    classInfoDialog.pack(); 
    classInfoDialog.setLocationRelativeTo(null); 
    classInfoDialog.setVisible(true); 
}
}