// Ambroise RENE

package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Bien;

public interface IBienService {

	/**Saves a given bien in the database
	 * @param bien - the given entity
	 * @return bien if the given bien didn't exist in the database - else, returns null
	 */
	public Bien saveBien(Bien bien);

	/**
	 * Finds all biens in database
	 * 
	 * @return List of biens - in case no biens has been inserted, it returns an
	 *         empty list
	 */
	public List<Bien> findAll();

	/**
	 * Updates the values of a given bien
	 * 
	 * @param bien - the given entity
	 * @return true if the given bien has been modified - else, returns false
	 */
	public boolean updateBien(Bien bien);

	/**
	 * Modifies attribute "deleted" from given bien in database
	 * 
	 * @param bien
	 * @return bien - the given bien
	 */
	public Bien deleteBien(Bien bien);

	public Bien FindParId(Long id);
	

}
