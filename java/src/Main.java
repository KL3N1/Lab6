public class Main {
    public static void main(String[] args) {
        try {
            // Создаём меню
            Menu menu = new Menu();
            menu.addDish(new Dish("Борщ", 250.0f, 1));
            menu.addDish(new Dish("Пельмени", 300.0f, 1));
            menu.addDish(new SpecialDish("Специальный борщ", 350.0f, 1, "Трюфель"));
            menu.print();

            // Создаём клиента и сотрудника
            Client client = new Client("Иван Иванов", "89005555555");
            Employee employee = new Employee("Сергей Петров", 101);

            // Работа с одномерным массивом заказов
            System.out.println("Работа с одномерным массивом заказов:");
            Order[] orders = new Order[3];
            for (int i = 0; i < orders.length; i++) {
                orders[i] = new Order(client, employee);
                try {
                    orders[i].addDish(menu.getDish(i % menu.getDishCount()));
                } catch (IndexOutOfBoundsException e) {
                    System.out.printf("Ошибка добавления блюда в заказ %d: %s%n", i + 1, e.getMessage());
                }
                orders[i].print();
            }

            // Работа с двумерным массивом заказов
            System.out.println("Работа с двумерным массивом заказов:");
            Order[][] orderMatrix = new Order[3][3]; // 3 дня, 3 столика

            for (int day = 0; day < 3; day++) {
                for (int table = 0; table < 3; table++) {
                    orderMatrix[day][table] = new Order(client, employee);
                    try {
                        orderMatrix[day][table].addDish(menu.getDish((day + table) % menu.getDishCount()));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.printf("Ошибка добавления блюда: День %d, Стол %d: %s%n", day + 1, table + 1, e.getMessage());
                    }
                }
            }

            // Печать двумерного массива заказов
            for (int day = 0; day < 3; day++) {
                System.out.printf("День %d:%n", day + 1);
                for (int table = 0; table < 3; table++) {
                    System.out.printf("  Стол %d:%n", table + 1);
                    orderMatrix[day][table].print();
                }
            }

            // Использование абстрактного класса
            PaymentMethod cashPayment = new CashPayment();
            PaymentMethod cardPayment = new CardPayment();
            cashPayment.processPayment(500.0f);
            cardPayment.processPayment(700.0f);

            // Использование обобщенного класса
            DataHolder<Integer> intHolder = new DataHolder<>(42);
            intHolder.print();
            DataHolder<String> stringHolder = new DataHolder<>("Пример строки");
            stringHolder.print();

            // Клонирование
            CloneableDish originalDish = new CloneableDish("Оригинальное блюдо", 100.0f, 1);
            CloneableDish shallowClone = (CloneableDish) originalDish.clone();
            CloneableDish deepClone = originalDish.deepClone();

            System.out.println("Оригинальное блюдо: " + originalDish);
            System.out.println("Мелкое клонирование: " + shallowClone);
            System.out.println("Глубокое клонирование: " + deepClone);

            // Использование интерфейса
            Printable printableDish = new Dish("Интерфейсное блюдо", 200.0f, 2);
            printableDish.print();

            Printable printableSpecialDish = new SpecialDish("Интерфейсное специальное блюдо", 250.0f, 2, "Трюфель");
            printableSpecialDish.print();

        } catch (Exception e) {
            System.out.println("Общая ошибка: " + e.getMessage());
        }
    }
}