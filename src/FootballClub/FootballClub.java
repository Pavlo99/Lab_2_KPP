package FootballClub;

public class FootballClub {
    private String name;
    private int yearOfFoundation;
    private String city;

    public FootballClub() {
    }

    public FootballClub(String name, int yearOfFoundation, String city) {
        this.name = name;
        this.yearOfFoundation = yearOfFoundation;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfFoundation() {
        return yearOfFoundation;
    }

    public void setYearOfFoundation(int yearOfFoundation) {
        this.yearOfFoundation = yearOfFoundation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    @Override
    public String toString() {
        return "Name - " + this.name + "; Year - " + this.yearOfFoundation + "; City - " + this.city;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public boolean equalsFC(FootballClub fc) {
        if(this.city.equals(fc.city) && this.name.equals(fc.name) && (this.yearOfFoundation == fc.getYearOfFoundation()))
            return true;
        else return false;
    }
}
