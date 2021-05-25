package com.example.easynotes.service;

import com.example.easynotes.model.Category;
import com.example.easynotes.model.Note;
import com.example.easynotes.repository.CategoryRepository;
import com.example.easynotes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by JS.
 */

@Service
public class NoteService {

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    CategoryRepository categoryRepository;

    // Get all
    public List<Note> getAllNotes(){
        return noteRepository.findAll();
    }

    // Create new note
    public Note createNote(Note note){
        Category findCategory = note.getCategory();
        Category inputCategory = categoryRepository.findOne(findCategory.getId());
        note.setCategory(inputCategory);

        return noteRepository.save(note);
    }

    // Get a single note by ID
    public Note getNoteById(Long noteId){
        Note note = noteRepository.findOne(noteId);
        return note;
    }

    // Update note by ID
    public Note updateNote(Long noteId, Note fresh_note){
        Note note = noteRepository.findOne(noteId);

        if(note==null){
            return null;
        }
        note.setTitle(fresh_note.getTitle());
        note.setContent(fresh_note.getContent());

        Note updatedNote = noteRepository.save(note);
        return updatedNote;
    }

    // Delete note by ID
    public Boolean deleteNote(Long noteId){
        Note note = noteRepository.findOne(noteId);
        if(note==null){
            return false;
        }

        noteRepository.delete(note);
        return true;
    }
}
