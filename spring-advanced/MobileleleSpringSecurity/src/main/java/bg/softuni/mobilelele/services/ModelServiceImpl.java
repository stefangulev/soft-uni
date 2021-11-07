package bg.softuni.mobilelele.services;

import bg.softuni.mobilelele.model.entities.ModelEntity;
import bg.softuni.mobilelele.repositories.ModelRepository;
import org.springframework.stereotype.Service;

@Service
public class ModelServiceImpl implements ModelService{

    private final ModelRepository modelRepository;

    public ModelServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public ModelEntity findModelByName(String name) {
        return modelRepository.findByName(name).orElse(null);
    }
}
