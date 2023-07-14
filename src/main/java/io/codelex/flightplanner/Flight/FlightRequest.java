package io.codelex.flightplanner.Flight;

public class FlightRequest {
    String from;
    String to;
    String departureDate;

    public FlightRequest(String from, String to, String departureDate) {
        this.from = from;
        this.to = to;
        this.departureDate = departureDate;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlightRequest that)) return false;

        if (getFrom() != null ? !getFrom().equals(that.getFrom()) : that.getFrom() != null) return false;
        if (getTo() != null ? !getTo().equals(that.getTo()) : that.getTo() != null) return false;
        return getDepartureDate() != null ? getDepartureDate().equals(that.getDepartureDate()) : that.getDepartureDate() == null;
    }

    @Override
    public int hashCode() {
        int result = getFrom() != null ? getFrom().hashCode() : 0;
        result = 31 * result + (getTo() != null ? getTo().hashCode() : 0);
        result = 31 * result + (getDepartureDate() != null ? getDepartureDate().hashCode() : 0);
        return result;
    }
}
