package io.codelex.flightplanner.Flight;

import jakarta.validation.constraints.NotBlank;

public class Airport {
    @NotBlank
    private String country;
    @NotBlank
    private String city;
    @NotBlank
    private String airport;

    public Airport(String country, String city, String airport) {
        this.country = country;
        this.city = city;
        this.airport = airport;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airport airport1)) return false;

        if (getCountry() != null ? !getCountry().equals(airport1.getCountry()) : airport1.getCountry() != null)
            return false;
        if (getCity() != null ? !getCity().equals(airport1.getCity()) : airport1.getCity() != null) return false;
        return getAirport() != null ? getAirport().equals(airport1.getAirport()) : airport1.getAirport() == null;
    }

    @Override
    public int hashCode() {
        int result = getCountry() != null ? getCountry().hashCode() : 0;
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getAirport() != null ? getAirport().hashCode() : 0);
        return result;
    }
}
