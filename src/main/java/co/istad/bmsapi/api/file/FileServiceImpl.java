package co.istad.bmsapi.api.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import co.istad.bmsapi.api.file.web.FileDto;
import co.istad.bmsapi.data.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    private final FileMapper fileMapper;

    @Value("${file.server-path}")
    private String serverPath;

    @Value("${file.uri}")
    private String uri;

    @Override
    public FileDto uploadOne(MultipartFile file) {
        return this.save(file);
    }

    @Override
    public List<FileDto> uploadAll(List<MultipartFile> files) {
        
        List<FileDto> fileDtoList = new ArrayList<>();
        
        for (MultipartFile file : files) {
            fileDtoList.add(this.save(file));
        }

        return fileDtoList;
    }


    private FileDto save(MultipartFile file) {

        String randomUUID = UUID.randomUUID().toString();
        String extension = "";

        // randomUUID + extension
        // WERTYUIOIUGFDCFGHJ.jpg
        String fileName = "";

        if (!file.isEmpty()) {

            extension = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf(".") + 1);

            fileName = randomUUID + "." + extension;

            // Create path object for object
            Path path = Paths.get(serverPath + fileName);

            try {
                Files.copy(file.getInputStream(), path);
            } catch (IOException e) {
                log.error("uploadOne(MultipartFile file) = {}", e);
            }

        } else {
            String reason = "File is not found in body";
            System.out.println(reason);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, reason);
        }

        File fileModel = new File();
        fileModel.setUuid(randomUUID);
        fileModel.setExtension(extension);
        fileModel.setSize(Float.valueOf(file.getSize()));
        fileModel.setIsEnabled(true);

        fileRepository.insert(fileModel);

        FileDto fileDto = fileMapper.fromModel(fileModel);
        fileDto.setName(fileName);
        fileDto.setUri(uri + fileName);

        return fileDto;

    }


    @Override
    public boolean existsFileID(Long id) {
        return fileRepository.existsById(id);
    }


    @Override
    public FileDto getFileById(Long id) {

        File file = fileRepository.selectById(id);

        FileDto fileDto = fileMapper.fromModel(file);
        fileDto.setName(file.getUuid() + "." + file.getExtension());
        fileDto.setUri(uri + fileDto.getName());

        return fileDto;
    }
    
}
