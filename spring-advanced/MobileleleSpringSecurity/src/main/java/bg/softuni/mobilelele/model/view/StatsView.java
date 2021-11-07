package bg.softuni.mobilelele.model.view;

public class StatsView {
    private int anonymousRequests;
    private int authRequests;

    public StatsView(int anonymousRequests, int authRequests) {
        this.anonymousRequests = anonymousRequests;
        this.authRequests = authRequests;
    }
    public int getAnonymousRequests() {
        return anonymousRequests;
    }

    public int getAuthRequests() {
        return authRequests;
    }
    public int getTotalRequests() {
        return anonymousRequests + authRequests;
    }
}
