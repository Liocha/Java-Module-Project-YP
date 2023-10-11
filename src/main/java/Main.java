import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int peopleCount;
        String productName;
        double productPrice;
        Product product;
        double result;
        String answer;
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();
        while (true) {
            System.out.println("На скольких человек необходимо разделить счёт?");
            if (!scanner.hasNextInt()) {
                System.out.println("Извините, но это явно не целое число.");
                scanner.nextLine();
                continue;
            }
            peopleCount = scanner.nextInt();
            if (peopleCount < 1) {
                System.out.println("Количество человек не может быть меньше 1.");
            } else if (peopleCount == 1) {
                System.out.println("Количество человек не может быть равно 1.");
            } else {
                scanner.nextLine();
                break;
            }
            scanner.nextLine();
        }
        do {
            while (true) {
                System.out.println("Укажите название товара:");
                productName = scanner.nextLine();
                if (productName.length() > 1) {
                    break;
                }
                System.out.println("Извините, но название товара не может быть пустой строкой.");
            }
            while (true) {
                System.out.println("Укажите стоимость товара:");
                if (!scanner.hasNextDouble()) {
                    System.out.println("Извините, но это явно не число.");
                    scanner.nextLine();
                    continue;
                }
                productPrice = scanner.nextDouble();
                if (productPrice < 0) {
                    System.out.println("Извините, но стоимость товара не может быть отрицательной.");
                } else {
                    scanner.nextLine();
                    break;
                }
                scanner.nextLine();
            }
            product = new Product(productName, productPrice);
            calculator.add(product);
            System.out.println("Вы хотите добавить еще один товар? Если нет, укажите 'завершить'");
            answer = scanner.nextLine();
        } while (!answer.equalsIgnoreCase("завершить"));
        System.out.println("Добавленные товары:" + calculator.getProducts());
        result = calculator.getTotal() / peopleCount;
        System.out.println("Каждый человек должен заплатить: " + Formatter.getFormatPrice(result) + " " + Formatter.getEnding(result));
        scanner.close();
    }
}

class Calculator {
    private double total = 0;
    private String products = "";

    public void add(Product product) {
        this.total = this.total + product.price;
        this.products = this.products + "\n" + product.name;
        System.out.println("Товар успешно добавлен!");
    }

    String getProducts() {
        return products;
    }

    double getTotal() {
        return this.total;
    }
}

class Product {
    final String name;
    final double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class Formatter {
    public static String getFormatPrice(double price) {
        return String.format("%.2f", price);
    }

    public static String getEnding(double price) {
        double priceRoundedDown = Math.floor(price);
        double remainderPerHundred = priceRoundedDown % 100;
        if (remainderPerHundred >= 11 && remainderPerHundred <= 19) {
            return "рублей";
        }
        double remainderByTen = priceRoundedDown % 10;
        if (remainderByTen == 1) {
            return "рубль";
        } else if (remainderByTen >= 2 && remainderByTen <= 4) {
            return "рубля";
        } else {
            return "рублей";
        }
    }
}
