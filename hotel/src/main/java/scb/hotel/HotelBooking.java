package scb.hotel;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class HotelBooking {
    @JsonIgnore
    private Integer id;
    private String name;
    private Integer amount;
    private boolean confirmed;
    private boolean cancelled;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void confirm() {
        this.confirmed = true;
        this.cancelled = false;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void cancel() {
        this.confirmed = false;
        this.cancelled = true;
    }
}
