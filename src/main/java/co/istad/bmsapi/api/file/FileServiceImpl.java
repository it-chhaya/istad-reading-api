package co.istad.bmsapi.api.file;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public FileDto uploadOne(MultipartFile file) {
        return null;
    }



    @Override
    public List<FileDto> uploadAll(List<MultipartFile> files) {
        return null;
    }

}
