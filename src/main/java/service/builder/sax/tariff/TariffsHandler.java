package service.builder.sax.tariff;

import entity.tariff.Tariff;
import entity.tariff.TariffEnum;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class TariffsHandler extends DefaultHandler {
    private Set<Tariff> tariffs;
    private Tariff currentTariff = null;
    private TariffEnum currentEnum = null;
    private EnumSet<TariffEnum> withText;

    public TariffsHandler() {
        tariffs = new LinkedHashSet<>();
        withText = EnumSet.range(TariffEnum.NAME,TariffEnum.PRICE_FOR_GETTING_TARIFF);
    }

    public Set<Tariff> getTariffs() {
        return tariffs;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if ("tariff".equals(localName)) {
            currentTariff = new Tariff();
            currentTariff.setId(attrs.getValue(0));
        } else {
            TariffEnum temp = TariffEnum.valueOf(localName.toUpperCase());
            if (withText.contains(temp)) {
                currentEnum = temp;
            }
        }
    }
    @Override
    public void characters(char[ ] ch, int start, int length) {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                case NAME:
                    currentTariff.setName(s);
                    break;
                case OPERATOR_NAME:
                    currentTariff.setOperatorName(s);
                    break;
                case PAYROLL:
                    currentTariff.setPayroll(Double.parseDouble(s));
                    break;
                case CALL_PRICE_INSIDE_NET:
                    currentTariff.setCallPriceInsideNet(Double.parseDouble(s));
                    break;
                case CALL_PRICE_OUTSIDE_NET:
                    currentTariff.setCallPriceOutsideNet(Double.parseDouble(s));
                    break;
                case CALL_PRICE_TO_STATIC_PHONES:
                    currentTariff.setCallPriceToStaticPhones(Double.parseDouble(s));
                    break;
                case SMS_PRICE:
                    currentTariff.setSmsPrice(Double.parseDouble(s));
                    break;
                case FAVORITE_NUMBER_AMOUNT:
                    currentTariff.setFavoriteNumbersAmount(Integer.parseInt(s));
                    break;
                case PRICE_FOR_GETTING_TARIFF:
                    currentTariff.setPriceForGettingTariff(Double.parseDouble(s));
                    break;
                default:
                    throw new EnumConstantNotPresentException(
                            currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum = null;
    }
    @Override
    public void endElement(String uri, String localName, String qName) {
        if ("tariff".equals(localName)) {
           tariffs.add(currentTariff);
        }
    }
}

