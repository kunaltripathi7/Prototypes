package DesignPatterns;

public class Car {
    private int horsePower;
    private double price;
    private boolean electric;

    private Car(CarBuilder carBuilder) {
        this.horsePower = carBuilder.horsePower;
        this.price = carBuilder.price;
        this.electric = carBuilder.electric;
    }

    public static class CarBuilder {
        private int horsePower = 0;
        private double price = 0.00;
        private boolean electric = false;

        public Car build() {
            return new Car(this);
        }

        public CarBuilder setQuantity(int horsePower) {
            this.horsePower = horsePower;
            return this;
        }
    }
}
