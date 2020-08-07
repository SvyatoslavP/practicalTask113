package web.model;

public class Car {
    private String name;
    private String series;
    private int number;

    public Car(String name, String series, int number) {
        this.name = name;
        this.series = series;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
