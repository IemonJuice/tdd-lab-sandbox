package edu.se.extweb;

import edu.se.extweb.model.Item;
import edu.se.extweb.request.ItemCreateRequest;
import edu.se.extweb.service.ItemService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ItemServiceTest {


    @Autowired
    private ItemService underTest;

    List<Item> items = new ArrayList<>();

    @BeforeAll
    static void beforeAll() {
    }

    @BeforeEach
    void setUp() {
        if (underTest.getAll().isEmpty()) {
            underTest.init();
        }
    }
   @AfterEach
    void tearsDown(){
    }


    @Test
    void whenGetAllItemsListThenSizeIs30() {
        int size = underTest.getAll().size();
        assertEquals(30, size);
    }
}
