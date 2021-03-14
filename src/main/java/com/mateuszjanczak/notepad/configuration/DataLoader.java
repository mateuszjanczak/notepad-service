package com.mateuszjanczak.notepad.configuration;

import com.mateuszjanczak.notepad.notes.entity.Note;
import com.mateuszjanczak.notepad.notes.repository.NoteRepository;
import com.mateuszjanczak.notepad.users.dto.RegisterRequest;
import com.mateuszjanczak.notepad.users.entity.User;
import com.mateuszjanczak.notepad.users.service.AuthService;
import com.mateuszjanczak.notepad.users.service.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
public class DataLoader implements ApplicationRunner {

    private final AuthService authService;
    private final NoteRepository noteRepository;
    private final UserService userService;


    public DataLoader(AuthService authService, NoteRepository noteRepository, UserService userService) {
        this.authService = authService;
        this.noteRepository = noteRepository;
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) {
        createDemoAccount();
        createDemoNotes();
    }

    private void createDemoAccount() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("DEMO");
        registerRequest.setEmail("DEMO@LOCALHOST.COM");
        registerRequest.setPassword("DEMO123");
        authService.register(registerRequest);
    }

    private void createDemoNotes() {
        Optional<User> optionalUser = userService.findByUsername("DEMO");

        if(optionalUser.isPresent()) {
            User user = optionalUser.get();

            Note note1 = new Note();
            note1.setTitle("Lorem ipsum");
            note1.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed nec tortor nisl. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nulla in felis eget metus tristique ultrices. Donec pulvinar mi et est hendrerit, posuere ultrices justo vestibulum. In luctus ante eu nibh sodales rhoncus. Phasellus pharetra a urna vel pretium. Maecenas pharetra, magna at condimentum placerat, neque ex iaculis velit, ut iaculis justo augue fermentum leo.");
            note1.setUser(user);

            Note note2 = new Note();
            note2.setTitle("Praesent tempus");
            note2.setContent("Praesent tempus sem sem, quis vulputate sem tempor aliquam. In sed orci orci. In ex sem, maximus ac ultricies nec, viverra quis tortor. Nunc in fringilla dui. Fusce in accumsan metus. Fusce id lacus sit amet leo cursus interdum nec non dui. Nulla blandit ut nunc eu efficitur. Cras blandit est vitae euismod dapibus. Sed orci odio, volutpat sit amet nisi at, eleifend tristique sapien. Morbi quis nulla ac dolor rhoncus egestas eget sed arcu. Cras lorem est, euismod vel facilisis sit amet, sagittis in turpis. Ut eu neque justo. Vestibulum eget elit eros. Praesent diam neque, varius ac nunc ut, ultrices pulvinar nulla. Integer sodales dolor nec maximus egestas. Nam eu sodales sem.");
            note2.setUser(user);

            Note note3 = new Note();
            note3.setTitle("Mauris placerat");
            note3.setContent("Mauris placerat dictum nisi, at semper eros laoreet ultricies. Cras quis dolor vitae mi sollicitudin posuere. Aliquam semper egestas eros ac varius. Phasellus dignissim eros in elit dictum tristique. Sed tortor mauris, auctor at metus sit amet, convallis accumsan enim. Curabitur finibus magna in aliquet finibus. Nam eget sagittis urna, eget maximus ante.");
            note3.setUser(user);

            Note note4 = new Note();
            note4.setTitle("Morbi pharetra");
            note4.setContent("Morbi pharetra a massa sed rhoncus. Curabitur id iaculis risus. Ut posuere pretium risus, eget lacinia leo fermentum a. Proin eleifend nulla elit, sit amet hendrerit mauris suscipit ac. Mauris cursus placerat condimentum. Aliquam est arcu, viverra non elit ut, tincidunt pharetra ex. Cras elementum eu turpis quis cursus. Duis mi arcu, sollicitudin sit amet tristique at, dictum eu velit. Proin elit lectus, fermentum vitae orci ut, malesuada blandit sapien. Pellentesque blandit mollis dolor, id maximus lorem aliquet id. Sed condimentum urna ut nulla imperdiet porta. Maecenas nunc ex, scelerisque et mi quis, convallis varius erat.");
            note4.setUser(user);

            Note note5 = new Note();
            note5.setTitle("Konto testowe");
            note5.setContent("Jesteś zalogowany na koncie demonstracyjnym!");
            note5.setUser(user);

            noteRepository.saveAll(Arrays.asList(note1, note2, note3, note4, note5));
        }
    }
}
