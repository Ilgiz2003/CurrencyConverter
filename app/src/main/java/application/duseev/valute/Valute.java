package application.duseev.valute;

import android.os.Parcel;
import android.os.Parcelable;

public class Valute implements Parcelable {
    private double Value;
    private String CharCode;
    private String Name;
    private int Nominal;
    public Valute(double Value, String CharCode, String Name, int Nominal) {
        this.Value = Value;
        this.CharCode = CharCode;
        this.Name = Name;
        this.Nominal = Nominal;
    }

    public static final Creator<Valute> CREATOR = new Creator<Valute>() {
        @Override
        public Valute createFromParcel(Parcel in) {
            return new Valute(in);
        }

        @Override
        public Valute[] newArray(int size) {
            return new Valute[size];
        }
    };

    public Valute() {

    }

    public double getValue() {
        return Value;
    }
    public void setValue(double value) {
        Value = value;
    }
    public String getCharCode() {
        return CharCode;
    }
    public void setCharCode(String charCode) {
        CharCode = charCode;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public int getNominal() {
        return Nominal;
    }
    public void setNominal(int nominal) {
        Nominal = nominal;
    }


    public Valute(Parcel source) {
        Value = source.readDouble();
        CharCode = source.readString();
        Name = source.readString();
        Nominal = source.readInt();
    }


    @Override
    public String toString() {
        return  CharCode +' '+ Name + " Количество: " + Nominal + " Стоимость: " + Value + " рублей";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.Value);
        parcel.writeString(this.CharCode);
        parcel.writeString(this.Name);
        parcel.writeInt(this.Nominal);
    }
}
