package com.example.easynotes.repository;

import com.example.easynotes.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by JS on 25/05/21.
 */

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

}
