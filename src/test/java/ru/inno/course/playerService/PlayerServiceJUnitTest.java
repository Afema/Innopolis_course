package ru.inno.course.playerService;

import ru.inno.course.homework7.task1.PlayerService;
import ru.inno.course.homework7.task1.PlayerServiceJSON;
import ru.inno.course.homework7.task1.Player;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerServiceJUnitTest {

    private ObjectMapper mapper = new ObjectMapper();
    private Path dataFile = Path.of("src/main/resources/players.json");
    private PlayerService service;

    @BeforeEach
    public void setUp() throws IOException {
        Files.deleteIfExists(dataFile);
        service = new PlayerServiceJSON();
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.deleteIfExists(dataFile);
    }

    @Test
    @Tags({@Tag("добавление"), @Tag("чистаяСистема")})
    @DisplayName("Создать первого игрока (файла не существует)")
    public void newPlayerCreation() throws IOException {
        // 2. Создать игрока
        int alexId = service.createPlayer("Alex");
        // 3. Прочитать содержимое файла
        List<Player> playersFromFile = mapper.readValue(dataFile.toFile(), new TypeReference<List<Player>>() {
        });
        // 4. Проверить, что в файле есть наш игрок
        Player playerToBe = service.getPlayerById(alexId);
        Player playerAsIs = playersFromFile.get(0);

        assertEquals(playerToBe, playerAsIs);
        //assertEquals(playersFromFile.size(), 3);
    }

    @Test
    @Tag("добавление")
    @DisplayName("Добавить нового игрока в существующую коллекцию")
    public void addPlayerToCollection() throws IOException {
        service.createPlayer("Billy");
        service.createPlayer("Willy");
        service.createPlayer("Dilly");
        // 1. Проверить, что файл есть и он не пустой. -> запомнить, сколько было игроков
        List<Player> listBeforeAdd = mapper.readValue(Path.of("src/main/resources/players.json").toFile(), new TypeReference<List<Player>>() {
        });
        Player billy = listBeforeAdd.get(0);

        // 2. Создать нового игрока
        int martaId = service.createPlayer("Marta");

        // 3. Прочитать содержимое фала -> список игроков
        List<Player> listAfterAdd = mapper.readValue(dataFile.toFile(), new TypeReference<List<Player>>() {
        });

        // 4. Проверить, что список стал на 1 больше
        System.out.println(listAfterAdd.size() == listBeforeAdd.size() + 1);
        // 5. Проверить, что в списке ест старый и новый игроки.
        Player marta = service.getPlayerById(martaId);
        assertTrue(listAfterAdd.contains(marta));
        assertTrue(listAfterAdd.contains(billy));
    }

    @Test
    @DisplayName("Удалить игрока из списка")
    public void deletePlayer() throws IOException {
        // 1. Посмотреть список игроков --> сохранить размер
        service.createPlayer("Kenny");

        List<Player> listBeforeDelete = mapper.readValue(dataFile.toFile(), new TypeReference<List<Player>>() {
        });
        // 2. id любого (первого) игрока
        Player willBeDeleted = listBeforeDelete.get(0);
        // 3. Удалить
        Player deleted = service.deletePlayer(willBeDeleted.getId());
        // 4. Проверить, что размер файла стал на 1 меньше
        List<Player> listAfterDelete = mapper.readValue(dataFile.toFile(), new TypeReference<List<Player>>() {
        });

        assertEquals(listBeforeDelete.size() - 1, listAfterDelete.size());

        // 5. Список НЕ содержит нашего игрока
        assertFalse(listAfterDelete.contains(deleted));

    }

    @Test
    @DisplayName("Удалить несуществующего игрока из списка")
    public void deleteNotExistedPlayer() throws IOException {
        // 1. Создать игрока
        service.createPlayer("Kenny");

        List<Player> list = mapper.readValue(dataFile.toFile(), new TypeReference<List<Player>>() {
        });

        // 2. Удалить несуществующего игрока по id

        service.deletePlayer(1000);

        // 3. Проверить, что размер файла остался прежним, 1 игрок "Kenny", ничего не удалилось, exception
        List<Player> listAfterDelete = mapper.readValue(dataFile.toFile(), new TypeReference<List<Player>>() {
        });

        assertEquals(list.size(), listAfterDelete.size());
    }

    @Test
    @DisplayName("Накинуть очков игроку - проверить, что состояние пересохранилось")
    public void addPoints() throws IOException {
        // 1. Создать игрока
        service.createPlayer("Kenny");

        List<Player> listBefore = mapper.readValue(dataFile.toFile(), new TypeReference<List<Player>>() {
        });

        int initPoints = listBefore.get(0).getPoints();

        // 2. Добавить очков

        service.addPoints(listBefore.get(0).getId(), 10);
        List<Player> listAfter = mapper.readValue(dataFile.toFile(), new TypeReference<List<Player>>() {
        });
        int afterAddPoints = listAfter.get(0).getPoints();

        // 3. Проверить, что очки добавились

        assertEquals(initPoints + 10, afterAddPoints);
    }

    @Test
    @DisplayName("Запросить данные игрока по id")
    public void getPlayerById() throws IOException {
        // 1. Создать игрока
        service.createPlayer("Kenny");

        List<Player> list = mapper.readValue(dataFile.toFile(), new TypeReference<List<Player>>() {
        });

        int playerId = list.get(0).getId();

        // 2. Запросить данные игрока по id

        Player returnedPlayer = service.getPlayerById(playerId);

        // 3. Проверить, что вернулся именно запрашиваемый Player

        assertEquals(list.get(0), returnedPlayer);
    }

    @Test
    @DisplayName("Запросить данные игрока по id (игрока не существует)")
    public void getNotExistedPlayerById() throws IOException {
        // 1. Создать игрока
        service.createPlayer("Kenny");

        List<Player> list = mapper.readValue(dataFile.toFile(), new TypeReference<List<Player>>() {
        });

        // 2. Запросить данные несуществующего игрока по id

        Player returnedPlayer = service.getPlayerById(1000);

        // 3. Проверить, что вернулся именно запрашиваемый Player

        assertNotEquals(list.get(0), returnedPlayer);
    }

    @Test
    @DisplayName("Создание дубликата – не должно быть одинаковых ников")
    public void checkDublicates() throws IOException {
        // 1. Создать игрока
        service.createPlayer("Kenny");
        service.createPlayer("Kenny");
        List<Player> list = mapper.readValue(dataFile.toFile(), new TypeReference<List<Player>>() {
        });

        //list.get(1).setId(list.get(0).getId());

    }

    @Test
    @DisplayName("Удалить и создать с тем же ником")
    public void createDeletedPlayer() throws IOException {
        // 1. Посмотреть список игроков --> сохранить размер
        service.createPlayer("Kenny");

        List<Player> listBeforeDelete = mapper.readValue(dataFile.toFile(), new TypeReference<List<Player>>() {
        });
        // 2. id любого (первого) игрока
        Player willBeDeleted = listBeforeDelete.get(0);
        // 3. Удалить
        Player deleted = service.deletePlayer(willBeDeleted.getId());
        // 4. Проверить, что размер файла стал на 1 меньше
        List<Player> listAfterDelete = mapper.readValue(dataFile.toFile(), new TypeReference<List<Player>>() {
        });

        assertEquals(listBeforeDelete.size() - 1, listAfterDelete.size());

        // 5. Список НЕ содержит нашего игрока
        assertFalse(listAfterDelete.contains(deleted));

        // 6. Создать игрока
        int alexId = service.createPlayer("Kenny");
        // 7. Прочитать содержимое файла
        List<Player> playersFromFile = mapper.readValue(dataFile.toFile(), new TypeReference<List<Player>>() {
        });
        // 8. Проверить, что в файле есть наш игрок
        Player playerToBe = service.getPlayerById(alexId);
        Player playerAsIs = playersFromFile.get(0);

        assertEquals(playerToBe, playerAsIs);

    }

    @Test
    @DisplayName("1. Добавить очков несуществующему игроку")
    public void addPointsNotExistedPlayer() throws IOException {
        // 1. Создать игрока
        service.createPlayer("Kenny");

        List<Player> listBefore = mapper.readValue(dataFile.toFile(), new TypeReference<List<Player>>() {
        });

        int existedPlayersPoints = listBefore.get(0).getPoints();

        // 2. Добавить очков несуществующему игроку, проверить что выбросилось исключение NullPointerException т.к. такого игрока нет
        Throwable exception = assertThrows(NullPointerException.class, () -> service.addPoints(1000, 10));

        List<Player> listAfter = mapper.readValue(dataFile.toFile(), new TypeReference<List<Player>>() {
        });
        int afterAddPoints = listAfter.get(0).getPoints();

        // 3. Проверить, что очки не добавились существующему игроку

        assertEquals(existedPlayersPoints, afterAddPoints);
    }

}