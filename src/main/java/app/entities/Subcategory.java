package app.entities;

public enum Subcategory {
        ECONOMY_CLASS("Эконом"),
        COMFORT_CLASS("Комфорт"),
        BUSINESS_CLASS("Бизнес")
        ;

        private String translation;

        Subcategory(String translation) {this.translation = translation;
        }
    }
