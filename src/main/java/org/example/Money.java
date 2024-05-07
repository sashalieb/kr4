public class Money {
    private long rub;
    private byte kop;

    public Money(long rub, byte kop) {
        if (rub < 0 || kop < 0 || kop > 99) {
            throw new IllegalArgumentException("Неверно указана сумма денег");
        }
        this.rub = rub;
        this.kop = kop;
    }

    public Money add(Money other) {
        long newRub = this.rub + other.rub;
        int newKop = this.kop + other.kop;
        if (newKop >= 100) {
            newKop -= 100;
            newRub++;
        }
        return new Money(newRub, (byte) newKop);
    }

    public Money subtract(Money other) {
        long newRub = this.rub - other.rub;
        int newKop = this.kop - other.kop;
        if (newKop < 0) {
            newKop += 100;
            newRub--;
        }
        if (newRub < 0 || newKop < 0) {
            throw new IllegalArgumentException("Отрицательный результат после вычитания");
        }
        return new Money(newRub, (byte) newKop);
    }

    public Money divide(double divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("На ноль делить нельзя");
        }
        double amount = (double) this.rub + (double) this.kop / 100;
        double result = amount / divisor;
        long newRub = (long) result;
        byte newKop = (byte) ((result - newRub) * 100);
        return new Money(newRub, newKop);
    }

    public Money multiply(double multiplier) {
        if (multiplier < 0) {
            throw new IllegalArgumentException("Умножение на отрицательное число");
        }
        double amount = (double) this.rub + (double) this.kop / 100;
        double result = amount * multiplier;
        long newRub = (long) result;
        byte newKop = (byte) ((result - newRub) * 100);
        return new Money(newRub, newKop);
    }

    public int sravnenie(Money other) {
        if (this.rub == other.rub && this.kop == other.kop) {
            return 0;
        } else if (this.rub > other.rub || (this.rub == other.rub && this.kop > other.kop)) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return rub + "," + (kop < 10 ? "0" + kop : kop);
    }

    public static void main(String[] args) {
        try {
            Money amount1 = new Money(100, (byte) 50);
            Money amount2 = new Money(50, (byte) 25);

            System.out.println("Сумма 1: " + amount1);
            System.out.println("Сумма 2: " + amount2);

            Money sum = amount1.add(amount2);
            System.out.println("Сумма 1 + Сумма2 = " + sum);

            Money difference = amount1.subtract(amount2);
            System.out.println("Сумма 1 - Сумма2 = " + difference);

            Money divisionResult = amount1.divide(2);
            System.out.println("Сумма 1 / 2 = " + divisionResult);

            Money multipliedAmount = amount1.multiply(1.5);
            System.out.println("Сумма 1 * 1.5 = " + multipliedAmount);

            int sravnenie = amount1.sravnenie(amount2);
            System.out.println("Сравнение денежных сумм: " + sravnenie);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
