package com.front.end.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.front.end.APICALL.ApiInterface;
import com.front.end.DTO.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class UserController implements Initializable {


    public TabPane TabPanel;
    public TextField searchUserSearchListPhone;
    public static EmpDto loginEmp;
    public Button butt1;
    public Button butt2;
    public Button butt3;
    public Tab rabTab;

    ObjectMapper objectMapper = new ObjectMapper();
    public ChoiceBox<String> filterUser;

    public ListView<UsersDto> userList;
    public ListView<EmpDto> empList;

    public ListView<ItemDto> itemList;
    public ListView<OrderDto> orderList;
    //
    //
    public void FillUserList() throws Exception {
        List<UsersDto> users = Arrays.asList(objectMapper.readValue(ApiInterface.
                sendGet("/user/getlist"), UsersDto[].class));
        for (UsersDto user:users){
            userList.getItems().add(user);
        }
    }
    public void FillEmpList() throws Exception {
        List<EmpDto> empl = Arrays.asList(objectMapper.readValue(ApiInterface.
                sendGet("/emp/getlist"), EmpDto[].class));
        for (EmpDto emp:empl){
            empList.getItems().add(emp);
        }
        currentEmp.getItems().addAll(empList.getItems());
    }
    public void FillItemList() throws Exception {
        List<ItemDto> items = Arrays.asList(objectMapper.readValue(ApiInterface.
                sendGet("/item/getlist"), ItemDto[].class));
        for (ItemDto item:items){
            itemList.getItems().add(item);
        }
    }
    public void FillOrderList() throws Exception {
        List<OrderDto> orders = Arrays.asList(objectMapper.readValue(ApiInterface.
                sendGet("/order/getlist"), OrderDto[].class));
        for (OrderDto order:orders){
            orderList.getItems().add(order);
        }
    }
    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonBasedOnBoth.setDisable(true);
        FillUserList();
        FillEmpList();
        FillItemList();
        FillRoleList();
        fillTypeList();
        FillOrderList();

        searchUserText.textProperty().addListener((observable, oldValue, newValue) -> {
            for (UsersDto user: userList.getItems()){
                if (user.getName().contains(newValue)) userList.getSelectionModel().select(user);
            }
        });

        {
            filterUser.getItems().add("Имя");
            filterUser.getItems().add("Телефон");
            filterUser.getItems().add("E-mail");
        }

        TabPanel.getSelectionModel().selectedIndexProperty().addListener(
                (ov, oldTab, newTab) -> {
                    resetFilters();
                }
        );
        loginEmp=LoginController.userAut;
        currentEmp.getSelectionModel().select(loginEmp);
        System.out.println(loginEmp);
        SelectedEmp = loginEmp;
        System.out.println("Initialization done");
        if (SelectedEmp.getPriv().getAccess()!=1){
            butt1.setDisable(true);
            butt2.setDisable(true);
            butt3.setDisable(true);
            currentEmp.setDisable(true);
            rabTab.setDisable(true);
            TabPanel.getTabs().remove(rabTab);
        }
    } //!!!

    public TextField userNameText;
    public TextField userPhoneText;
    public TextField userMailText;
    public Text userId;
    public TextField searchUserText;


    public void UserChose(MouseEvent mouseEvent) {
        userNameText.setText(userList.getSelectionModel().getSelectedItem().getName());
        userMailText.setText(userList.getSelectionModel().getSelectedItem().getEmail());
        userPhoneText.setText(userList.getSelectionModel().getSelectedItem().getPhone());
        userId.setText("ID: "+userList.getSelectionModel().getSelectedItem().getUserId());

    }
    public void UserSave(MouseEvent mouseEvent) throws IOException {
        userList.getSelectionModel().getSelectedItem().setName(userNameText.getText());
        userList.getSelectionModel().getSelectedItem().setEmail(userMailText.getText());
        userList.getSelectionModel().getSelectedItem().setPhone(userPhoneText.getText());
        ApiInterface.sendPost(userList.getSelectionModel().getSelectedItem(), "user/upd");
    }
    public void UserNew(MouseEvent mouseEvent) throws Exception {
        UsersDto blankUser= new UsersDto();
        userList.getItems().add(blankUser);
        userList.getSelectionModel().select(blankUser);
        String newId= ApiInterface.sendGet("user/addb");
        userList.getSelectionModel().getSelectedItem().setUserId(Integer.parseInt(newId));
        userId.setText("ID:"+newId);
    }
    public void UserDel(MouseEvent mouseEvent) throws IOException {
        TextDto text = new TextDto();
        text.setText(userList.getSelectionModel().getSelectedItem().getUserId()+"");
        text.setStatus("DELETE");
        ApiInterface.sendPost(text, "user/delete");
        userList.getItems().remove(userList.getSelectionModel().getSelectedItem());
    }

    public ChoiceBox<PrivDto> empRoleChose;
    public TextField empPhoneText;
    public TextField empNameText;
    public Text EmpIdText;

    public void FillRoleList() throws Exception {
        List<PrivDto> roles = Arrays.asList(objectMapper.readValue(ApiInterface.
                sendGet("/role"), PrivDto[].class));
        System.out.println(roles);
        for (PrivDto role:roles){
            empRoleChose.getItems().add(role);
        }
    }
    public void EmpChose(MouseEvent mouseEvent) {
        EmpDto empDto= empList.getSelectionModel().getSelectedItem();
        empPhoneText.setText(empDto.getPassword());
        empNameText.setText(empDto.getName());
        empRoleChose.getSelectionModel().select(empDto.getPriv());
        EmpIdText.setText("ID: "+empDto.getId());

    }
    public void EmpNew(MouseEvent mouseEvent) throws Exception {
        EmpDto blankEmp= new EmpDto();
        empList.getItems().add(blankEmp);
        empList.getSelectionModel().select(blankEmp);
        EmpChose(mouseEvent);
        String newId= ApiInterface.sendGet("emp/addb");
        empList.getSelectionModel().getSelectedItem().setId((long) Integer.parseInt(newId));
        EmpIdText.setText("ID:"+newId);
    }
    public void EmpSave(MouseEvent mouseEvent) throws IOException {
       empList.getSelectionModel().getSelectedItem().setName(empNameText.getText());
       empList.getSelectionModel().getSelectedItem().setPassword(empPhoneText.getText());
       empList.getSelectionModel().getSelectedItem().setPriv(empRoleChose.getSelectionModel().getSelectedItem());
       ApiInterface.sendPost(empList.getSelectionModel().getSelectedItem(), "emp/upd");
    }
    public void EmpDel(MouseEvent mouseEvent) throws IOException {
        TextDto text = new TextDto();
        text.setText(empList.getSelectionModel().getSelectedItem().getId()+"");
        text.setStatus("DELETE");
        empList.getItems().remove(empList.getSelectionModel().getSelectedItem());
        ApiInterface.sendPost(text, "emp/delete");
    }

    public ChoiceBox<TypeDto> itemTypeChose;
    public TextField itemNameText;

    public void fillTypeList() throws Exception {
        List<TypeDto> types = Arrays.asList(objectMapper.readValue(ApiInterface.
                sendGet("/type"), TypeDto[].class));
        System.out.println(types);
        for (TypeDto type: types){
            itemTypeChose.getItems().add(type);
        }
    }
    public Text itemId;

    public void itemNew(MouseEvent mouseEvent) throws Exception {
        ItemDto blankItem= new ItemDto();
        itemList.getItems().add(blankItem);
        itemList.getSelectionModel().select(blankItem);
        itemChose(mouseEvent);
        String newId= ApiInterface.sendGet("item/addb");
        itemList.getSelectionModel().getSelectedItem().setItemId((long) Integer.parseInt(newId));
        EmpIdText.setText("ID:"+newId);
    }
    public void itemSave(MouseEvent mouseEvent) throws IOException {
        itemList.getSelectionModel().getSelectedItem().setName(itemNameText.getText());
        itemList.getSelectionModel().getSelectedItem().setType(itemTypeChose.getSelectionModel().getSelectedItem());
        ApiInterface.sendPost(itemList.getSelectionModel().getSelectedItem(), "item/upd");
    }
    public void itemDel(MouseEvent mouseEvent) throws IOException {
        TextDto text = new TextDto();
        text.setText(itemList.getSelectionModel().getSelectedItem().getItemId()+"");
        text.setStatus("DELETE");
        itemList.getItems().remove(itemList.getSelectionModel().getSelectedItem());
        ApiInterface.sendPost(text, "item/delete");
    }
    public void itemChose(MouseEvent mouseEvent) {
        ItemDto item = itemList.getSelectionModel().getSelectedItem();
        itemTypeChose.getSelectionModel().select(item.getType());
        itemNameText.setText(item.getName());
        itemId.setText("ID: "+ item.getItemId());
    }

    public TextField orderNameText;
    public DatePicker datePicker;
    public ChoiceBox<ItemDto> itemListChose;
    public ListView<ItemDto> itemMonitor;
    public ChoiceBox<UsersDto> UserListChose;
    public Text OrderTextId;

    public void orderNew(MouseEvent mouseEvent) throws Exception {
        OrderDto blankOrd= new OrderDto();
        orderList.getItems().add(blankOrd);
        orderList.getSelectionModel().select(blankOrd);
        orderChose(mouseEvent);
        String newId= ApiInterface.sendGet("order/addb");
        orderList.getSelectionModel().getSelectedItem().setId((long) Integer.parseInt(newId));
        OrderTextId.setText("ID:"+newId);
    }
    public void orderSave(MouseEvent mouseEvent) throws IOException {
        orderList.getSelectionModel().getSelectedItem().setUser(UserListChose.getSelectionModel().getSelectedItem());
        orderList.getSelectionModel().getSelectedItem().setEmployee(SelectedEmp);
        orderList.getSelectionModel().getSelectedItem().setDate(datePicker.getEditor().getText());
        ArrayList<ItemDto> listOfItems= new ArrayList<>(itemMonitor.getItems());
        orderList.getSelectionModel().getSelectedItem().setItemList(listOfItems);
        //orderDto.setPayment();
        ApiInterface.sendPost(orderList.getSelectionModel().getSelectedItem(), "order/upd");
    }
    public void orderDel(MouseEvent mouseEvent) throws IOException {
        TextDto text = new TextDto();
        text.setText(orderList.getSelectionModel().getSelectedItem().getId()+"");
        text.setStatus("DELETE");
        orderList.getItems().remove(orderList.getSelectionModel().getSelectedItem());
        ApiInterface.sendPost(text, "order/del");
    }
    public void deleteItemFromMonitor(MouseEvent mouseEvent) {
        itemMonitor.getItems().remove(itemMonitor.getSelectionModel().getSelectedItem());
    }
    public void addItemToMonitor(MouseEvent mouseEvent) {
        itemMonitor.getItems().add(itemListChose.getSelectionModel().getSelectedItem());
    }
    public void orderChose(MouseEvent mouseEvent) {
        itemListChose.getItems().clear();
        itemListChose.getItems().setAll(itemList.getItems());
        UserListChose.getItems().clear();
        UserListChose.getItems().setAll(userList.getItems());
        UserListChose.getSelectionModel().select(orderList.getSelectionModel().getSelectedItem().getUser());
        datePicker.getEditor().setText(orderList.getSelectionModel().getSelectedItem().getDate());
        orderNameText.setText(orderList.getSelectionModel().getSelectedItem().toString());
        itemMonitor.getItems().clear();
        OrderTextId.setText("ID: "+orderList.getSelectionModel().getSelectedItem().getId());
        try {
            itemMonitor.getItems().addAll(orderList.getSelectionModel().getSelectedItem().getItemList());
        } catch (Exception e){
            System.out.println("No items");
        }
    }

    public ChoiceBox<EmpDto> currentEmp;
    public EmpDto SelectedEmp;
    public void setSystemEmp(Event mouseEvent) {
        SelectedEmp=currentEmp.getSelectionModel().getSelectedItem();
        System.out.println(SelectedEmp);
    }

    public ListView<UsersDto> userListSearch;
    public ListView<ItemDto> itemListSearch;

    public TextField searchUserSearchList;
    public TextField searchItemSearchList;

    public void updateSearchLists(Event mouseEvent) {
        userListSearch.getItems().clear();
        itemListSearch.getItems().clear();
        userListSearch.getItems().addAll(userList.getItems());
        itemListSearch.getItems().addAll(itemList.getItems());

        searchUserSearchList.textProperty().addListener((observable, oldValue, newValue) -> {
            for (UsersDto user: userList.getItems()){
                if (user.toString().contains(newValue)) {
                    userListSearch.getSelectionModel().select(user);
                    userListSearch.scrollTo(user);
                    System.out.println(user.getName());
                    break;
                }

            }
        });

        searchItemSearchList.textProperty().addListener((observable, oldValue, newValue) -> {
            for (ItemDto item: itemList.getItems()){
                if (item.getName().contains(newValue)) {
                    itemListSearch.getSelectionModel().select(item);
                    itemListSearch.scrollTo(item);
                    System.out.println(item.getName());
                    break;
                }
            }
        });

    }

    UsersDto selectedUser;
    ItemDto selectedItem;
    public Button buttonBasedOnBoth;

    public void createBasedItem(MouseEvent mouseEvent) throws Exception {
        TabPanel.getSelectionModel().selectPrevious();
        orderNew(mouseEvent);
        OrderDto order=orderList.getSelectionModel().getSelectedItem();
        ArrayList<ItemDto> list= new ArrayList<>();
        list.add(selectedItem);
        order.setItemList(list);
        orderChose(mouseEvent);
        orderNameText.setText("Based on item \""+selectedItem.getName()+"\"");
    }
    public void createBasedUser(MouseEvent mouseEvent) throws Exception {
        TabPanel.getSelectionModel().selectPrevious();
        orderNew(mouseEvent);
        OrderDto order=orderList.getSelectionModel().getSelectedItem();
        order.setUser(selectedUser);
        orderChose(mouseEvent);
        orderNameText.setText("Based on item \""+selectedItem.getName()+"\"");
    }
    public void createBasedBoth(MouseEvent mouseEvent) throws Exception {
        TabPanel.getSelectionModel().selectPrevious();
        orderNew(mouseEvent);
        OrderDto order=orderList.getSelectionModel().getSelectedItem();
        ArrayList<ItemDto> list= new ArrayList<>();
        list.add(selectedItem);
        order.setItemList(list);
        order.setUser(selectedUser);
        orderChose(mouseEvent);
        orderNameText.setText("Based on Item -\""+selectedItem.getName()+"\" and User -\""+selectedUser+"\"");

    }
    public Text selectedUserText;
    public Text selectedItemText;
    public void selectSearchUser(MouseEvent mouseEvent) {
    selectedUser=userListSearch.getSelectionModel().getSelectedItem();
    selectedUserText.setText("Выбраный пользователь - \n"+selectedUser);
        System.out.println(selectedUser);
        if (selectedUser!=null && selectedItem!=null){
            buttonBasedOnBoth.setDisable(true);
        }
    }
    public void selectSearchItem(MouseEvent mouseEvent) {
    selectedItem=itemListSearch.getSelectionModel().getSelectedItem();
    selectedItemText.setText("Выбраный предмет - \n"+selectedItem);
        System.out.println(selectedItem);
        if (selectedUser!=null && selectedItem!=null){
            buttonBasedOnBoth.setDisable(false);
        }
    }

    public void changeFilterUser(Event mouseEvent) {
        UsersDto.filter=filterUser.getSelectionModel().getSelectedIndex();
        updateSearchLists(mouseEvent);

    }

    public void resetFilters() {
        UsersDto.resetFilter();
    }
    ///Search

}
