package io.codelex.flightplanner.Dto;

import io.codelex.flightplanner.Flight.Flight;

import java.util.List;

public class PageResult<T> {

    private int page;
    private int totalItems;
    private List<Flight> items;
    

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public List<Flight> getItems() {
        return items;
    }

    public void setItems(List<Flight> items) {
        this.items = items;
    }
}
