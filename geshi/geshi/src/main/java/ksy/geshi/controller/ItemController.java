package ksy.geshi.controller;

import ksy.geshi.form.ItemForm;
import ksy.geshi.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/item")
@RequiredArgsConstructor
@Slf4j
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/main")
    public String main(Model model) {
        List<ItemForm> list = itemService.findAll();
        model.addAttribute("list",list);
        return "item/item_main";
    }

    @GetMapping("/register")
    public String register(@ModelAttribute("itemForm")ItemForm itemForm){
        return "item/item_register";
    }

    @PostMapping("/register")
    public String register_pro(@Valid @ModelAttribute("itemForm") ItemForm itemForm, BindingResult result) {
        if (result.hasErrors()) {
            return "item/item_register";
        }else {
            itemService.itemRegister(itemForm);
            return "item/register_success";
        }
    }

    @GetMapping("/{itemId}/edit")
    public String edit(@PathVariable("itemId") Long itemId,Model model) {
        ItemForm result = itemService.getItemInfo(itemId);
        model.addAttribute("itemForm",result);
        return "item/item_update";
    }

    @PostMapping("/{itemId}/edit")
    public String edit_pro(@ModelAttribute("itemForm") ItemForm itemForm) {
        log.info(itemForm.getId()+" "+itemForm.getItemname()+" "+itemForm.getPrice()+" "+itemForm.getStock());
        itemService.updateItem(itemForm);
        return "item/edit_success";
    }

}
