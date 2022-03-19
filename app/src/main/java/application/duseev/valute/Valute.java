package application.duseev.valute;

public class Valute {
    String CharCode;
    String Name;
    int Nominal;
    double Value;
    @Override
    public String toString() {
        return  CharCode +' '+ Name + " Количество: " + Nominal + " Стоимость: " + Value;
    }

}
