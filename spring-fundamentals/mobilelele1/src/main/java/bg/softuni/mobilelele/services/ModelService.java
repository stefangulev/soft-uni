package bg.softuni.mobilelele.services;

import bg.softuni.mobilelele.model.entities.ModelEntity;

public interface ModelService {
    ModelEntity findModelByName(String name);
}
