package edu.se.extweb.service;


/*
  @author   george
  @project   proj-test
  @class  ItemService
  @version  1.0.0 
  @since 09.09.24 - 12.16
*/


import edu.se.extweb.model.Item;
import edu.se.extweb.repository.ItemRepository;
import edu.se.extweb.request.ItemCreateRequest;
import edu.se.extweb.request.ItemUpdateRequest;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 *
 */
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    private List<Item> items = new ArrayList<>();

    {
        items.add(new Item( "Freddie Mercury", "Queen","vocal, piano"));
        items.add(new Item( "Paul McCartney", "Beatles","guitar"));
        items.add(new Item( "Till Lindemann", "Rammstein","vocal"));
        items.add(new Item( "John Lennon", "Beatles","piano"));
        items.add(new Item( "Brian May", "Queen","solo guitar"));
        items.add(new Item( "Tarja Turunen", "Nightwish","vocal"));
        items.add(new Item( "Roger Waters", "Pink Floyd","poet"));
    }

//    @PostConstruct
public void init() {
        this.itemRepository.deleteAll();

        List<Item> items = List.of(
                new Item("Freddie Mercury", "Queen", "vocal, piano"),
                new Item("Paul McCartney", "Beatles", "guitar, bass"),
                new Item("John Lennon", "Beatles", "vocal, rhythm guitar"),
                new Item("George Harrison", "Beatles", "lead guitar"),
                new Item("Ringo Starr", "Beatles", "drums"),
                new Item("Brian May", "Queen", "lead guitar"),
                new Item("Roger Taylor", "Queen", "drums"),
                new Item("John Deacon", "Queen", "bass"),
                new Item("Kurt Cobain", "Nirvana", "vocal, guitar"),
                new Item("Dave Grohl", "Nirvana", "drums"),
                new Item("Krist Novoselic", "Nirvana", "bass"),
                new Item("James Hetfield", "Metallica", "vocal, rhythm guitar"),
                new Item("Lars Ulrich", "Metallica", "drums"),
                new Item("Kirk Hammett", "Metallica", "lead guitar"),
                new Item("Robert Trujillo", "Metallica", "bass"),
                new Item("Till Lindemann", "Rammstein", "vocal"),
                new Item("Richard Kruspe", "Rammstein", "lead guitar"),
                new Item("Paul Landers", "Rammstein", "rhythm guitar"),
                new Item("Oliver Riedel", "Rammstein", "bass"),
                new Item("Christoph Schneider", "Rammstein", "drums"),
                new Item("Tarja Turunen", "Nightwish", "vocal"),
                new Item("Tuomas Holopainen", "Nightwish", "keyboard"),
                new Item("Emppu Vuorinen", "Nightwish", "guitar"),
                new Item("Marco Hietala", "Nightwish", "bass, vocal"),
                new Item("Jukka Nevalainen", "Nightwish", "drums"),
                new Item("David Gilmour", "Pink Floyd", "guitar, vocal"),
                new Item("Roger Waters", "Pink Floyd", "bass, lyrics"),
                new Item("Nick Mason", "Pink Floyd", "drums"),
                new Item("Richard Wright", "Pink Floyd", "keyboard"),
                new Item("Axl Rose", "Guns N' Roses", "vocal")
        );

        itemRepository.saveAll(items);
    }
    //  CRUD   - create read update delete

    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    public Item getById(String id) {
        return itemRepository.findById(id).get();
    }

    public Item create(Item item) {
        return itemRepository.save(item);
    }

    public Item create(ItemCreateRequest request) {
        return mapToItem(request);
    }

    public  Item update(Item item) {
        return itemRepository.save(item);
    }


    public void delById(String id) {
        itemRepository.deleteById(id);
    }

    private Item mapToItem(ItemCreateRequest request) {
        Item item = new Item(request.name(), request.code(), request.description());
        return item;
    }

    public Item update(ItemUpdateRequest request) {
        Item itemPersisted = itemRepository.findById(request.id()).orElse(null);
        if (itemPersisted != null) {
            Item itemToUpdate =
                    Item.builder()
                            .id(request.id())
                            .name(request.name())
                            .code(request.code())
                            .description(request.description())
                            .build();
            return itemRepository.save(itemToUpdate);

        }
        return null;
    }

    public List<Item> createAll(List<Item> items) {
        return itemRepository.saveAll(items);
    }

    public void deleteAll() {
       itemRepository.deleteAll();
    }



}
