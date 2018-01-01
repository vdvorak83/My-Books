package ru.itis.validators;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itis.dto.ProfileImageDto;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;

@Component
public class ProfileImageValidator implements Validator {
    @Value("${profile.image.upload.size}")
    private Long maxImageSize;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(ProfileImageDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProfileImageDto profileImage = (ProfileImageDto) target;
        validateFileType(errors, profileImage);
        validateForMinMaxFileSize(errors, profileImage);
    }

    private void validateForMinMaxFileSize(Errors errors, ProfileImageDto profileImage) {
        if (profileImage.getFile().getSize() == 0) {
            errors.reject("file.empty");
        }
        if (profileImage.getFile().getSize() > maxImageSize) {
            errors.reject("file.too.large");
        }
    }

    private void validateFileType(Errors errors, ProfileImageDto profileImage) {
        try (InputStream inputStream = profileImage.getFile().getInputStream()) {
            try {
                ImageIO.read(inputStream);
            } catch (Exception e) {
                errors.reject("file.type.not.supported");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
