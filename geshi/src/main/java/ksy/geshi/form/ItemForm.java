package ksy.geshi.form;

import ksy.geshi.domain.ItemEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
public class ItemForm {

    private Long id;
    @NotBlank(message = "상품명을 입력하세요")
    private String itemname;

    @PositiveOrZero(message = "가격을 입력하세요")
    private int price;
    @PositiveOrZero(message = "수량을 입력하세요")
    private int stock;

    public ItemForm(ItemEntity item) {
        this.id=item.getId();
        this.itemname = item.getItemname();
        this.price = item.getPrice();
        this.stock = item.getStock();
    }
}
