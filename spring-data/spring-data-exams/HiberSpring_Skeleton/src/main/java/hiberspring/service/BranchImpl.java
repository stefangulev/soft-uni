package hiberspring.service;

import com.google.gson.Gson;
import hiberspring.domain.dtos.BranchesSeedDto;
import hiberspring.domain.entities.Branch;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.TownRepository;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static hiberspring.common.Constants.PATH_TO_FILES;

@Service
public class BranchImpl implements BranchService{

    private final String FILE_NAME = "branches.json";
    private final BranchRepository branchRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;
    private final ModelMapper modelMapper;
    private final TownRepository townRepository;

    public BranchImpl(BranchRepository branchRepository, Gson gson, ValidationUtil validationUtil, FileUtil fileUtil, ModelMapper modelMapper, TownRepository townRepository) {
        this.branchRepository = branchRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
        this.modelMapper = modelMapper;
        this.townRepository = townRepository;
    }


    @Override
    public Boolean branchesAreImported() {
        return branchRepository.count() > 0;
    }

    @Override
    public String readBranchesJsonFile() throws IOException {
        return fileUtil.readFile(PATH_TO_FILES + FILE_NAME);
    }

    @Override
    public String importBranches(String branchesFileContent) throws IOException {
        StringBuilder sb = new StringBuilder();
       Arrays.stream(gson.fromJson(readBranchesJsonFile(), BranchesSeedDto[].class)).filter(b -> {
            boolean isValid = validationUtil.isValid(b);
            sb.append(isValid ? String.format("Successfully imported Branch %s.", b.getName()) :
                    "Error: Invalid data.").append(System.lineSeparator());
            return isValid;
        }).map(b -> {
            Branch branch = modelMapper.map(b, Branch.class);
            branch.setTown(townRepository.findTownByName(b.getTown()));
            return branch;

        }).forEach(branchRepository::save);
        return sb.toString();
    }
}
