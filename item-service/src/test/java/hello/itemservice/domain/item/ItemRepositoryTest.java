package hello.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("itemA", 10000, 10);
        //when
        Item savedItem = itemRepository.save(item);
        //then
        Item byId = itemRepository.findById(item.getId());
        assertThat(byId).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        //given
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);
        //when
        itemRepository.save(item1);
        itemRepository.save(item2);
        //then
        List<Item> result = itemRepository.findAll();
        assertThat(result.get(0)).isEqualTo(item1);
    }

    @Test
    void updateItem() {
        //given
        Item item1 = new Item("item1", 10000, 10);
        Item savedItem = itemRepository.save(item1);
        Long id = savedItem.getId();
        //when

        Item item = new Item("item2", 10000, 30);
        itemRepository.update(id, item);
        //then
        Item byId = itemRepository.findById(id);
        assertThat(byId.getItemName()).isEqualTo(item.getItemName());
    }
}