package entity;

public class Tariffs {
    private String name;
    private String operatorName;
    private double payroll;
    private double callPriceInsideNet;
    private double callPriceOutsideNet;
    private  double callPriceToStaticPhones;
    private  double smsPrice;
    private  int favoriteNumbersAmount;
    private  double priceForGettingTariff;

    public Tariffs(String name, String operatorName, double payroll, double callPriceInsideNet,
                   double callPriceOutsideNet, double callPriceToStaticPhones, double smsPrice,
                   int favoriteNumbersAmount, double priceForGettingTariff) {
        this.name = name;
        this.operatorName = operatorName;
        this.payroll = payroll;
        this.callPriceInsideNet = callPriceInsideNet;
        this.callPriceOutsideNet = callPriceOutsideNet;
        this.callPriceToStaticPhones = callPriceToStaticPhones;
        this.smsPrice = smsPrice;
        this.favoriteNumbersAmount = favoriteNumbersAmount;
        this.priceForGettingTariff = priceForGettingTariff;
    }

    public Tariffs() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public double getPayroll() {
        return payroll;
    }

    public void setPayroll(double payroll) {
        this.payroll = payroll;
    }

    public double getCallPriceInsideNet() {
        return callPriceInsideNet;
    }

    public void setCallPriceInsideNet(double callPriceInsideNet) {
        this.callPriceInsideNet = callPriceInsideNet;
    }

    public double getCallPriceOutsideNet() {
        return callPriceOutsideNet;
    }

    public void setCallPriceOutsideNet(double callPriceOutsideNet) {
        this.callPriceOutsideNet = callPriceOutsideNet;
    }

    public double getCallPriceToStaticPhones() {
        return callPriceToStaticPhones;
    }

    public void setCallPriceToStaticPhones(double callPriceToStaticPhones) {
        this.callPriceToStaticPhones = callPriceToStaticPhones;
    }

    public double getSmsPrice() {
        return smsPrice;
    }

    public void setSmsPrice(double smsPrice) {
        this.smsPrice = smsPrice;
    }

    public int getFavoriteNumbersAmount() {
        return favoriteNumbersAmount;
    }

    public void setFavoriteNumbersAmount(int favoriteNumbersAmount) {
        this.favoriteNumbersAmount = favoriteNumbersAmount;
    }

    public double getPriceForGettingTariff() {
        return priceForGettingTariff;
    }

    public void setPriceForGettingTariff(double priceForGettingTariff) {
        this.priceForGettingTariff = priceForGettingTariff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tariffs)) return false;

        Tariffs tariffs = (Tariffs) o;

        if (Double.compare(tariffs.getPayroll(), getPayroll()) != 0) return false;
        if (Double.compare(tariffs.getCallPriceInsideNet(), getCallPriceInsideNet()) != 0) return false;
        if (Double.compare(tariffs.getCallPriceOutsideNet(), getCallPriceOutsideNet()) != 0) return false;
        if (Double.compare(tariffs.getCallPriceToStaticPhones(), getCallPriceToStaticPhones()) != 0) return false;
        if (Double.compare(tariffs.getSmsPrice(), getSmsPrice()) != 0) return false;
        if (getFavoriteNumbersAmount() != tariffs.getFavoriteNumbersAmount()) return false;
        if (Double.compare(tariffs.getPriceForGettingTariff(), getPriceForGettingTariff()) != 0) return false;
        if (!getName().equals(tariffs.getName())) return false;
        return getOperatorName().equals(tariffs.getOperatorName());
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getName().hashCode();
        result = 31 * result + getOperatorName().hashCode();
        temp = Double.doubleToLongBits(getPayroll());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getCallPriceInsideNet());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getCallPriceOutsideNet());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getCallPriceToStaticPhones());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getSmsPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getFavoriteNumbersAmount();
        temp = Double.doubleToLongBits(getPriceForGettingTariff());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tariffs{");
        sb.append("name='").append(name).append('\'');
        sb.append(", operatorName='").append(operatorName).append('\'');
        sb.append(", payroll=").append(payroll);
        sb.append(", callPriceInsideNet=").append(callPriceInsideNet);
        sb.append(", callPriceOutsideNet=").append(callPriceOutsideNet);
        sb.append(", callPriceToStaticPhones=").append(callPriceToStaticPhones);
        sb.append(", smsPrice=").append(smsPrice);
        sb.append(", favoriteNumbersAmount=").append(favoriteNumbersAmount);
        sb.append(", priceForGettingTariff=").append(priceForGettingTariff);
        sb.append('}');
        return sb.toString();
    }

}
