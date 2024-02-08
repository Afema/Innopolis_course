package ru.inno.course.homework6.task2;

import java.time.*;

public class PublishDateTimeMain implements HumanReadableTimestamp {
    public static void main(String[] args) {
        PublishDateTimeMain date = new PublishDateTimeMain();
        System.out.println(date.getTimestamp(LocalDateTime.of(2024, 02, 7, 11, 12)));
    }

    @Override
    public String getTimestamp(LocalDateTime eventTimestamp) {
        Period date = Period.between(eventTimestamp.toLocalDate(), LocalDate.now());
        int years = date.getYears();
        int month = date.getMonths();
        Duration time = Duration.between(eventTimestamp, LocalDateTime.now());
        long days = time.toDays();
        long hours = time.toHours();
        long minutes = time.toMinutes();
        if (years > 0) {
            return "опубликовано более года назад";
        } else if (month > 0) {
            return "опубликовано более месяца назад";
        } else if (days > 1) {
            String d;
            switch ((int) days % 10) {
                case 2:
                case 3:
                case 4:
                    d = "дня";
                    break;
                default:
                    d = "дней";
            }
            return "опубликовано " + days + " " + d + " назад";
        } else if (days == 1) {
            return "опубликовано вчера";
        } else if (days < 1 && hours > 0) {
            String h;
            switch ((int) hours % 10) {
                case 1:
                    h = "час";
                    break;
                case 2:
                case 3:
                case 4:
                    h = "часа";
                    break;
                default:
                    h = "часов";
            }
            return "опубликовано " + hours + " " + h + " назад";
        } else if (hours < 1 && minutes > 0) {
            String m;
            switch ((int) minutes % 10) {
                case 1:
                    m = "минута";
                    break;
                case 2:
                case 3:
                case 4:
                    m = "минуты";
                    break;
                default:
                    m = "минут";
            }
            return "опубликовано " + minutes + " " + m + " назад";
        }
        return "опубликовано однажды :)";
    }
}
