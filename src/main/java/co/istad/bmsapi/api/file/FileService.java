package co.istad.bmsapi.api.file;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    /**
     * Upload single file
     * @param file from request part
     * @return FileDto
     */
    FileDto uploadOne(MultipartFile file);


    /**
     * Upload multiple files
     * @param files from request part
     * @return List<FileDto>
     */
    List<FileDto> uploadAll(List<MultipartFile> files);

}
