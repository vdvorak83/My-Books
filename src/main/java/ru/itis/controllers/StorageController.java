package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;
import ru.itis.services.AuthenticationService;
import ru.itis.services.FileStorageService;
import ru.itis.services.UsersService;

import javax.servlet.http.HttpServletResponse;

@Controller
public class StorageController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/user/profile")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile multipartFile,
                                                   Authentication authentication) {
        String filePath = fileStorageService.saveFile(multipartFile);

        usersService.updateProfilePhoto(filePath,
                authenticationService.getUserByAuthentication(authentication).getId());

        return ResponseEntity.ok().body(filePath);
    }

    @GetMapping("/files/{file-name:.+}")
    public void getFile(@PathVariable("file-name") String fileName, HttpServletResponse response) {
        fileStorageService.writeFileToResponse(fileName, response);
    }
}
