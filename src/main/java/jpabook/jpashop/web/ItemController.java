package jpabook.jpashop.web;

import jakarta.validation.Valid;
import jpabook.jpashop.domain.item.*;
import jpabook.jpashop.exception.NotHasDiscriminator;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;

    @GetMapping(value = "/items/new")
    public String newItemsForm(Model model) { // model로 data를 실어 뷰로 넘김
        log.info("call get /items/new");
        model.addAttribute("itemForm", new ItemForm());  // form 이름에 BookForm()를 넣을 것임
        return "items/newItemForm"; // 화면 이름.html templates에 렌더링
    }

    @PostMapping(value = "/items/new")
    public String create(@Valid ItemForm itemForm, BindingResult result, Model model) throws NotHasDiscriminator { // 실무에서는 setter 모두 날림 -> static 생성자 메서드 사용
        log.info("call post members/new");

        if(result.hasErrors()){
            return "items/newItemForm";
        }
        Item item = null;
        if("A".equals(itemForm.getDtype())){
            item = new Album().createItem(itemForm);
        } else if("B".equals(itemForm.getDtype())){
            item = new Book().createItem(itemForm); // 책 생성
        }else if("M".equals(itemForm.getDtype())){
            item = new Movie().createItem(itemForm);  // 영화 생성
        }else{
            throw new NotHasDiscriminator("Not Has Discriminator");
        }
        itemService.saveItem(item); // item persist()

        return "redirect:/items";
    }
    /**
     * 상품 목록
     */
    @GetMapping(value = "/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }
    /**
     * 상품 수정 폼
     */
    @GetMapping(value = "/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model
            model) {
        Book item = (Book) itemService.findOne(itemId);
        BookForm form = new BookForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());
        model.addAttribute("form", form);
        return "items/updateItemForm";
    }
    /**
     * 상품 수정
     */
    // 외부에서 정보 조작 가능 있음 -> 보안적으로 취약
    @PostMapping(value = "/items/{itemId}/edit")
    public String updateItem(@PathVariable Long itemId, @ModelAttribute("form")
    BookForm form) {
        itemService.updateItem(itemId, form.getName(), form.getPrice(),
                form.getStockQuantity());
        return "redirect:/items";
    }
}