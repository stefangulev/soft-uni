package bg.softuni.mobilelele.services;

import bg.softuni.mobilelele.model.view.StatsView;

public interface StatsService {
    void onRequest();
    StatsView getStats();
}
