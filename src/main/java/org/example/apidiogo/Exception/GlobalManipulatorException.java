package org.example.apidiogo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalManipulatorException {
    @ExceptionHandler(AlunoNotFoundException.class)
    public ResponseEntity<String> handleAlunoNotFoundException(AlunoNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Aluno not found "+ex.getMessage());
    }

    @ExceptionHandler(AdminNotFoundException.class)
    public ResponseEntity<String> handleAdminNotFoundException(AdminNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Admin not found "+ex.getMessage());
    }

    @ExceptionHandler(DisciplinaNotFoundException.class)
    public ResponseEntity<String> handleDisciplinaNotFoundException(DisciplinaNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Disciplina not found "+ex.getMessage());
    }


    @ExceptionHandler(ProfessorNotFoundException.class)
    public ResponseEntity<String> handleProfessorNotFoundException(ProfessorNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Professor not found "+ex.getMessage());
    }

    @ExceptionHandler(BoletimNotFoundException.class)
    public ResponseEntity<String> handleBoletimNotFoundException(BoletimNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Boletim not found "+ex.getMessage());
    }

    @ExceptionHandler(ObservacaoNotFoundException.class)
    public ResponseEntity<String> handleObservacaoNotFoundException(ObservacaoNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Observacao not found "+ex.getMessage());
    }

}
