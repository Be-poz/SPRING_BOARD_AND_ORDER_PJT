package ksy.geshi.domain;

import ksy.geshi.exception.NotEnoughStockException;
import ksy.geshi.form.ItemForm;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name="item")
@NoArgsConstructor
public class ItemEntity {

    @Id @GeneratedValue
    @Column(name="item_Id")
    private Long id;

    private String itemname;
    private int price;
    private int stock;

    public void itemSetting(ItemForm itemForm) {
        this.itemname=itemForm.getItemname();
        this.price=itemForm.getPrice();
        this.stock=itemForm.getStock();
    }

    public void changeThings(ItemForm itemForm) {
        this.itemname=itemForm.getItemname();
        this.price=itemForm.getPrice();
        this.stock=itemForm.getStock();
    }

    public  void addStock(int quantity){
        this.stock+=quantity;
    }

    public void removeStock(int quantity){
        int restStock = this.stock-quantity;
        if(restStock<0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stock=restStock;
    }
}
