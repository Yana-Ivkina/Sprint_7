package Order;

public class Orders extends Order {
    private Number id;
    private Number courierId;
    private Number track;
    private String createdAt;
    private String updatedAt;
    private Number status;

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public Number getCourierId() {
        return courierId;
    }

    public void setCourierId(Number courierId) {
        this.courierId = courierId;
    }

    public Number getTrack() {
        return track;
    }

    public void setTrack(Number track) {
        this.track = track;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Number getStatus() {
        return status;
    }

    public void setStatus(Number status) {
        this.status = status;
    }
}
