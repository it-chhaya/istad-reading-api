package co.istad.bmsapi.api.file;

import org.springframework.web.multipart.MultipartFile;

import co.istad.bmsapi.api.file.web.FileDto;

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



    /**
     * Get all files from database
     * @return List<FileDto>
     */
    List<FileDto> getAllFiles();


    /**
     * Get a file from database by ID
     * @param id is the unique identifier of file in database
     * @return FileDto
     */
    FileDto getFileByID(Long id);


    /**
     * Get a file from database by UUID
     * @param uuid is the unique identifier of file in database
     * @return FileDto
     */
    FileDto getFileByUUID(String uuid);



    /**
     * Delete a file from database by UUID
     * @param uuid is the unique identifier of file in database
     */
    void deleteFileByUUID(String uuid);


    /**
     * Check file ID in database exist or not
     * @param ID is the unique identifier of file in database
     * @return true if exists and false if not exists
     */
    boolean existsFileID(Long id);

}
