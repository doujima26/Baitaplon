package qllh.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import qllh.view.RoomView;

import qllh.func.UserFunc;
import qllh.entity.User;
import qllh.view.LoginView;

public class LoginController {
    private UserFunc userDao;
    private LoginView loginView;
    private RoomView roomView;
    
    public LoginController(LoginView view) {
        this.loginView = view;
        this.userDao = new UserFunc();
        view.addLoginListener(new LoginListener());
    }
    
    public void showLoginView() {
        loginView.setVisible(true);
    }
    
    /**
     * Lớp LoginListener chứa cài đặt cho sự kiện click button "Login"
     * 
     * @author viettuts.vn
     */
    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            User user = loginView.getUser();
            if (userDao.checkUser(user)) {
                // nếu đăng nhập thành công, mở màn hình quản lý sinh viên
                roomView = new RoomView();
                RoomController roomController = new RoomController(roomView);
                roomController.showRoomForm();
                loginView.setVisible(false);
            } else {
                loginView.showMessage("username hoặc password không đúng.");
            }
        }
    }
}