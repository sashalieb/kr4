public class Time {
    private int hour;
    private int minute;
    private int second;

    public Time(int hour, int minute, int second) {
        anotherTime(hour, minute, second);
    }

    public void anotherTime(int hour, int minute, int second) {
        if (hour < 0 || hour >= 24 || minute < 0 || minute >= 60 || second < 0 || second >= 60) {
            throw new IllegalArgumentException("Недопустимое значение времени");
        }
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public void anotherHour(int hour) {
        if (hour < 0 || hour >= 24) {
            throw new IllegalArgumentException("Недопустимое значение часа");
        }
        this.hour = hour;
    }

    public void anotherMinute(int minute) {
        if (minute < 0 || minute >= 60) {
            throw new IllegalArgumentException("Недопустимое значение минуты");
        }
        this.minute = minute;
    }

    public void anotherSecond(int second) {
        if (second < 0 || second >= 60) {
            throw new IllegalArgumentException("Недопустимое значение секунды");
        }
        this.second = second;
    }

    public void addHours(int hours) {
        int newHour = (this.hour + hours) % 24;
        if (newHour < 0) {
            newHour += 24;
        }
        this.hour = newHour;
    }

    public void addMinutes(int minutes) {
        int totalMinutes = this.hour * 60 + this.minute + minutes;
        int newHour = (totalMinutes / 60) % 24;
        int newMinute = totalMinutes % 60;

        this.hour = newHour;
        this.minute = newMinute;
    }

    public void addSeconds(int seconds) {
        int totalSeconds = this.hour * 3600 + this.minute * 60 + this.second + seconds;
        int newHour = (totalSeconds / 3600) % 24;
        int newMinute = (totalSeconds % 3600) / 60;
        int newSecond = totalSeconds % 60;

        this.hour = newHour;
        this.minute = newMinute;
        this.second = newSecond;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    public static void main(String[] args) {
        try {
            Time time = new Time(10, 30, 0);
            System.out.println("Текущее время: " + time);

            time.anotherHour(16);
            time.anotherMinute(50);
            time.anotherSecond(10);
            System.out.println("Измененное время: " + time);

            time.addHours(2);
            System.out.println("Добавлено 2 часа: " + time);

            time.addMinutes(10);
            System.out.println("Добавлено 10 минут: " + time);

            time.addSeconds(5);
            System.out.println("Добавлено 5 секунд: " + time);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}

