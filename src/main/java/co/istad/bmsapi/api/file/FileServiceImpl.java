package co.istad.bmsapi.api.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import co.istad.bmsapi.api.file.web.FileDto;
import co.istad.bmsapi.data.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    private final FileMapper fileMapper;

    @Value("${file.server-path}")
    private String serverPath;

    @Value("${file.uri}")
    private String uri;


    @Override
    public FileDto uploadOne(MultipartFile file) {
        return save(file);
    }


    @Override
    public List<FileDto> uploadAll(List<MultipartFile> files) {

        List<FileDto> fileModels = new ArrayList<>();

        for (MultipartFile file : files) {
            FileDto fileDto = save(file);
            fileModels.add(fileDto);
        }

        return fileModels;

    }



    /**
     * Process of saving file by inserting into database and create a DTO response
     * @param file
     * @return FileDto
     */
    private FileDto save(MultipartFile file) {

        String randomUUID = UUID.randomUUID().toString();
        String extension = "";
        String fileName = "";

        if (!file.isEmpty()) {

            extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, reason);
        }

        File fileModel = new File();
        fileModel.setUuid(randomUUID);
        fileModel.setExtension(extension);
        fileModel.setSize(file.getSize());
        fileModel.setIsEnabled(true);

        fileRepository.insert(fileModel);

        FileDto fileDto = fileMapper.toFileDto(fileModel);
        fileDto.setName(fileName);
        fileDto.setUri(uri + fileName);

        log.info("Final FileDto = {}", fileDto);

        return fileDto;

    }


    @Override
    public List<FileDto> getAllFiles() {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public FileDto getFileByUUID(String uuid) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public void deleteFileByUUID(String uuid) {
        // TODO Auto-generated method stub
        
    }

}
