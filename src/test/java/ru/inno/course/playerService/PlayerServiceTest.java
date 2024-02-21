package ru.inno.course.playerService;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.inno.course.homework7.task1.PlayerService;
import ru.inno.course.homework7.task1.PlayerServiceJSON;
import ru.inno.course.homework7.task1.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class PlayerServiceTest {
    private ObjectMapper mapper = new ObjectMapper();
    private Path dataFile = Path.of("src/main/resources/players.json");
    private PlayerService service;

    public void setUp() throws IOException {
        Files.deleteIfExists(dataFile);
        service = new PlayerServiceJSON();
    }

    public  void tearDown() throws IOException {
        Files.deleteIfExists(dataFile);
    }
    public boolean newPlayerCreation() throws IOException {
        // Создать первого игрока (файла не существует)
        // 1. Убедиться, что файла нет
        try {
            setUp();

            // 2. Создать игрока
            int alexId = service.createPlayer("Alex");

            // 3. Прочитать содержимое файла
            List<Player> playersFromFile = mapper.readValue(dataFile.toFile(), new TypeReference<List<Player>>() {
            });

            // 4. Проверить, что в файле есть наш игрок
            System.out.println(playersFromFile.size() == 1);
            Player playerToBe = service.getPlayerById(alexId);
            Player playerAsIs = playersFromFile.get(0);
            System.out.println(playerToBe.equals(playerAsIs));

            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            tearDown();
        }
    }


    public boolean addPlayerToCollection() throws IOException {
        try {
            setUp();
            service.createPlayer("Billy");
            service.createPlayer("Willy");
            service.createPlayer("Dilly");
            // Добавить нового игрока в существующую коллекцию
            // 1. Проверить, что файл есть и он не пустой. -> запомнить, сколько было игроков
            List<Player> listBeforeAdd = mapper.readValue(dataFile.toFile(), new TypeReference<List<Player>>() {
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
            System.out.println(listAfterAdd.contains(marta));
            System.out.println(listAfterAdd.contains(billy));

            return true;
        } catch (Exception ex){
            return false;
        } finally {
            tearDown();
        }
    }

    public boolean deletePlayer() throws IOException {
        try {
            setUp();
            // Удалить игрока из списка
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
            System.out.println(listAfterDelete.size() == listBeforeDelete.size() - 1);
            // 5. Список НЕ содержит нашего игрока
            System.out.println(!listAfterDelete.contains(deleted));
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            tearDown();
        }
    }
}