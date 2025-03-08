package SolidPrinciple;

public class Marker {

    private String name;
    private String colour;
    private String year;
    private Integer price;

    public Marker(String name, String colour, String year, Integer price) {
        this.name = name;
        this.colour = colour;
        this.year = year;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
