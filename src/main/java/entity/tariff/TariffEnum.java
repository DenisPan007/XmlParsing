package entity.tariff;

    public enum TariffEnum {
        ID("ID"),
        OLD_TARIFF("old_tariff"),
        TARIFFS("tariffs"),
        TARIFF("tariff"),
        NAME("name"),
        OPERATOR_NAME("operator_name"),
        PAYROLL("payroll"),
        CALL_PRICE_INSIDE_NET("call_price_inside_net"),
        CALL_PRICE_OUTSIDE_NET("call_price_outside_net"),
        CALL_PRICE_TO_STATIC_PHONES("call_price_to_static_phones"),
        SMS_PRICE("sms_price"),
        FAVORITE_NUMBER_AMOUNT("favorite_number_amount"),
        PRICE_FOR_GETTING_TARIFF("price_for_getting_tariff");
        private String value;
        TariffEnum(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }
