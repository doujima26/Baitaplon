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
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Tên phòng học");
        tableModel.addColumn("Số lượng máy tính");
        tableModel.addColumn("Máy chiếu");
        tableModel.addColumn("Bảng trắng");
        tableModel.addColumn("Micrô");
        tableModel.addColumn("Loa");
        tableModel.addColumn("Internet");

        for (Room room : rooms) {
            tableModel.addRow(new Object[]{
                room.getRoomName(),
                room.getNumberOfComputers(),
                room.isHasProjector() ? "Có" : "Không",
                room.isHasWhiteboard() ? "Có" : "Không",
                room.isHasMicrophone() ? "Có" : "Không",
                room.isHasSpeaker() ? "Có" : "Không",
                room.isHasInternet() ? "Có" : "Không"
});

        }

        JTable roomTable = new JTable(tableModel);
        roomTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(roomTable);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton editButton = new JButton("Sửa");
        editButton.addActionListener(e -> {
            int selectedRow = roomTable.getSelectedRow();
            if (selectedRow != -1) {
                String roomName = tableModel.getValueAt(selectedRow, 0).toString();
                int numberOfComputers = Integer.parseInt(tableModel.getValueAt(selectedRow, 1).toString());
                boolean hasProjector = tableModel.getValueAt(selectedRow, 2).toString().equals("Có");
                boolean hasWhiteboard = tableModel.getValueAt(selectedRow, 3).toString().equals("Có");
                boolean hasMicrophone = tableModel.getValueAt(selectedRow, 4).toString().equals("Có");
                boolean hasSpeaker = tableModel.getValueAt(selectedRow, 5).toString().equals("Có");
                boolean hasInternet = tableModel.getValueAt(selectedRow, 6).toString().equals("Có");

                JPanel editPanel = new JPanel(new GridLayout(8, 2));
                JTextField editRoomNameField = new JTextField(roomName);
                JTextField editNumberOfComputersField = new JTextField(String.valueOf(numberOfComputers));
                JCheckBox editHasProjectorBox = new JCheckBox("", hasProjector);
                JCheckBox editHasWhiteboardBox = new JCheckBox("", hasWhiteboard);
                JCheckBox editHasMicrophoneBox = new JCheckBox("", hasMicrophone);
                JCheckBox editHasSpeakerBox = new JCheckBox("", hasSpeaker);
                JCheckBox editHasInternetBox = new JCheckBox("", hasInternet);

                editPanel.add(new JLabel("Tên phòng học:"));
                editPanel.add(editRoomNameField);
                editPanel.add(new JLabel("Số lượng máy tính:"));
                editPanel.add(editNumberOfComputersField);
                editPanel.add(new JLabel("Máy chiếu:"));
                editPanel.add(editHasProjectorBox);
                editPanel.add(new JLabel("Bảng trắng:"));
                editPanel.add(editHasWhiteboardBox);
                editPanel.add(new JLabel("Micrô:"));
                editPanel.add(editHasMicrophoneBox);
                editPanel.add(new JLabel("Loa:"));
                editPanel.add(editHasSpeakerBox);
                editPanel.add(new JLabel("Internet:"));
                editPanel.add(editHasInternetBox);

                int editResult = JOptionPane.showConfirmDialog(null, editPanel, "Sửa thông tin phòng học", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (editResult == JOptionPane.OK_OPTION) {
                    tableModel.setValueAt(editRoomNameField.getText(), selectedRow, 0);
                    tableModel.setValueAt(editNumberOfComputersField.getText(), selectedRow, 1);
                    tableModel.setValueAt(editHasProjectorBox.isSelected() ? "Có" : "Không", selectedRow, 2);
                    tableModel.setValueAt(editHasWhiteboardBox.isSelected() ? "Có" : "Không", selectedRow, 3);
                    tableModel.setValueAt(editHasMicrophoneBox.isSelected() ? "Có" : "Không", selectedRow, 4);
                    tableModel.setValueAt(editHasSpeakerBox.isSelected() ? "Có" : "Không", selectedRow, 5);
                    tableModel.setValueAt(editHasInternetBox.isSelected() ? "Có" : "Không", selectedRow, 6);

                    rooms.get(selectedRow).setRoomName(editRoomNameField.getText());
                    rooms.get(selectedRow).setNumberOfComputers(Integer.parseInt(editNumberOfComputersField.getText()));
                    rooms.get(selectedRow).setHasProjector(editHasProjectorBox.isSelected());
                    rooms.get(selectedRow).setHasWhiteboard(editHasWhiteboardBox.isSelected());
                    rooms.get(selectedRow).setHasMicrophone(editHasMicrophoneBox.isSelected());
                    rooms.get(selectedRow).setHasSpeaker(editHasSpeakerBox.isSelected());
                    rooms.get(selectedRow).setHasInternet(editHasInternetBox.isSelected());


                    JOptionPane.showMessageDialog(null, "Thông tin phòng học đã được cập nhật.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Xin hãy chọn một phòng học để sửa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        });

        JButton deleteButton = new JButton("Xóa");
        deleteButton.addActionListener(e -> {
            int selectedRow = roomTable.getSelectedRow();
            if (selectedRow != -1) {
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa phòng học này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    tableModel.removeRow(selectedRow);
                    rooms.remove(selectedRow);
                    JOptionPane.showMessageDialog(null, "Phòng học đã được xóa khỏi danh sách.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Xin hãy chọn một phòng học để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        JOptionPane.showMessageDialog(this, panel, "Thông tin phòng học", JOptionPane.PLAIN_MESSAGE);
    }
private void showClassInfo() {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Tên lớp học");
        tableModel.addColumn("Môn học");
        tableModel.addColumn("Sĩ số");

        for (ClassRoom classRoom : classrooms) {
            tableModel.addRow(new Object[]{
                    classRoom.getClassName(),
                    classRoom.getSubject(),
                    classRoom.getStudentCount()
            });
        }

        JTable classTable = new JTable(tableModel);
        classTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(classTable);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton editButton = new JButton("Sửa");
        editButton.addActionListener(e -> {
            int selectedRow = classTable.getSelectedRow();
            if (selectedRow != -1) {
                String className = tableModel.getValueAt(selectedRow, 0).toString();
                String subject = tableModel.getValueAt(selectedRow, 1).toString();
                int studentCount = Integer.parseInt(tableModel.getValueAt(selectedRow, 2).toString());

                JPanel editPanel = new JPanel(new GridLayout(3, 2));
                JTextField editClassNameField = new JTextField(className);
                JTextField editSubjectField = new JTextField(subject);
                JTextField editStudentCountField = new JTextField(String.valueOf(studentCount));

                editPanel.add(new JLabel("Tên lớp học:"));
                editPanel.add(editClassNameField);
                editPanel.add(new JLabel("Môn học:"));
                editPanel.add(editSubjectField);
                editPanel.add(new JLabel("Sĩ số:"));
                editPanel.add(editStudentCountField);

                int editResult = JOptionPane.showConfirmDialog(null, editPanel, "Sửa thông tin lớp học", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (editResult == JOptionPane.OK_OPTION) {
                    tableModel.setValueAt(editClassNameField.getText(), selectedRow, 0);
                    tableModel.setValueAt(editSubjectField.getText(), selectedRow, 1);
                    tableModel.setValueAt(editStudentCountField.getText(), selectedRow, 2);

                    classrooms.get(selectedRow).setClassName(editClassNameField.getText());
                    classrooms.get(selectedRow).setSubject(editSubjectField.getText());
                    classrooms.get(selectedRow).setStudentCount(Integer.parseInt(editStudentCountField.getText()));

                    JOptionPane.showMessageDialog(null, "Thông tin lớp học đã được cập nhật.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Xin hãy chọn một lớp học để sửa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        });

        JButton deleteButton = new JButton("Xóa");
        deleteButton.addActionListener(e -> {
            int selectedRow = classTable.getSelectedRow();
            if (selectedRow != -1) {
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa lớp học này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    tableModel.removeRow(selectedRow);
                    classrooms.remove(selectedRow);
                    JOptionPane.showMessageDialog(null, "Lớp học đã được xóa khỏi danh sách.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Xin hãy chọn một lớp học để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        JOptionPane.showMessageDialog(this, panel, "Thông tin lớp học", JOptionPane.PLAIN_MESSAGE);
    }
   }