package com.front.end.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.front.end.APICALL.ApiInterface;
import com.front.end.DTO.EmpDto;
import com.front.end.DTO.UsersDto;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public TextField UserPassword;
    public TextField UserLogin;
    public Button loginButton;
    public Text errorLogin;
    static EmpDto userAut;
    List<EmpDto> empl;
    ObjectMapper objectMapper = new ObjectMapper();
    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        empl = Arrays.asList(objectMapper.readValue(ApiInterface.
                sendGet("/emp/getlist"), EmpDto[].class));
    }

    public void login(MouseEvent mouseEvent) throws IOException {
        for (EmpDto emp: empl){
            if (emp.getName().equals(UserLogin.getText()) && emp.getPassword().equals(UserPassword.getText())){
                userAut= emp;
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/1234.fxml")));
                UserPassword.getScene().setRoot(root);
                root.getScene().getWindow().setWidth(923);
                root.getScene().getWindow().setHeight(635);
                System.out.println("Login Success - "+emp);
                break;
            }
            else errorLogin.setVisible(true);
        }
    }
}
