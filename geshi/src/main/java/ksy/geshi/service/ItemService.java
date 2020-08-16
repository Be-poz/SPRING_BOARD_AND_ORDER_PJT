package ksy.geshi.service;

import ksy.geshi.domain.ItemEntity;
import ksy.geshi.form.ItemForm;
import ksy.geshi.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public List<ItemForm> findAll(){
        List<ItemEntity> find = itemRepository.findAll();
        return find.stream()
                .map(f->new ItemForm(f))
                .collect(Collectors.toList());
    }

    public void itemRegister(ItemForm itemForm){
        ItemEntity itemEntity=new ItemEntity();
        itemEntity.itemSetting(itemForm);
        itemRepository.save(itemEntity);
    }

    public ItemForm getItemInfo(Long itemId) {
        ItemEntity findOne = itemRepository.findById(itemId).get();
        ItemForm itemForm=new ItemForm(findOne);
        return itemForm;
    }

    public void updateItem(ItemForm itemForm) {
        ItemEntity findOne = itemRepository.getOne(itemForm.getId());
        findOne.changeThings(itemForm);
    }
}
