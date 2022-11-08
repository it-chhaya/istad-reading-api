package co.istad.bmsapi.shared.annotation.fileexist;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import co.istad.bmsapi.api.file.FileServiceImpl;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConstraintFileIdValidator implements ConstraintValidator<ConstraintFileId, Long> {

    private final FileServiceImpl fileService;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        return fileService.existsFileID(id);
    } 
    
}
