package com.example.demo.Controller;

import com.example.demo.Dao.ItemRep;
import com.example.demo.Dao.TypeRep;
import com.example.demo.Dto.ItemDto;
import com.example.demo.Dto.TextDto;
import com.example.demo.Entity.Employee;
import com.example.demo.Entity.Item;
import com.example.demo.Entity.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    ItemRep itemRep;
    @Autowired
    TypeRep typeRep;
    @PostMapping("/add")
    public void addItem(@RequestBody ItemDto itemDto){
        Item item= new Item();
        item.setName(itemDto.getName());
        Type type= typeRep.getTypeByName(itemDto.getType().getName());
        item.setType(type);
        itemRep.save(item);
    }
    @GetMapping("/addb")
    public String addBlankItem(){
        Item item=new Item();
        itemRep.save(item);
        return item.getItemId()+"";
    }
    @PostMapping("/delete")
    public void deleteItem(@RequestBody TextDto textDto){
        itemRep.deleteById((long) Integer.parseInt(textDto.getText()));
    }

    @GetMapping("/getlist")
    public List<Item> getItemList(){
        return itemRep.findAll();
    }

    @PostMapping("/upd")
    public void updItem(@RequestBody ItemDto itemDto){
        Item item = itemRep.getById(itemDto.getItemId());
        item.setItemId(itemDto.getItemId());
        item.setName(itemDto.getName());
        item.setType(typeRep.getById(itemDto.getType().getTypeId()));
        itemRep.save(item);
    }
}
