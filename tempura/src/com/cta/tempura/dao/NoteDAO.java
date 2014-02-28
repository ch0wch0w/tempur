package com.cta.tempura.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.cta.tempura.model.Note;

public class NoteDAO {
	
private EntityManager em;
	
	public NoteDAO(EntityManager em){
		this.em=em;
	}
	
	public Integer insert(Note note) {
		em.persist(note);
		return note.getId();
	}
	
	public Note findById(String id) {
		Note note = em.find(Note.class, id);
		return note;
	}
	
	public List<Note>findAll() {
		TypedQuery<Note> query = em.createQuery("FROM Note", Note.class);
		List<Note> note = query.getResultList();
		return note;
	}
	
	public void update(Note note) {
		em.merge(note);
	}
	
	public void delete(Note note) {
		em.remove(note);
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}










