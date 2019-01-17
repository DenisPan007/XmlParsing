package service.entity_builder.sax.tariffs_parse;

    public enum TariffEnum {
        STUDENTS("students"),
        LOGIN("login"),
        FACULTY("faculty"),
        STUDENT("student"),
        NAME("name"),
        TELEPHONE("telephone"),
        COUNTRY("country"),
        CITY("city"),
        STREET("street"),
        PRICE_FOR_GETTING_TARIFF("address");
        private String value;
        TariffEnum(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }
