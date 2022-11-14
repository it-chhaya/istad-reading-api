package co.istad.bmsapi.api.file;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import co.istad.bmsapi.api.file.web.FileDto;

public interface FileService {
    
    FileDto uploadOne(MultipartFile file);

    List<FileDto> uploadAll(List<MultipartFile> files);

    boolean existsFileID(Long id);

    FileDto getFileById(Long id);

}
