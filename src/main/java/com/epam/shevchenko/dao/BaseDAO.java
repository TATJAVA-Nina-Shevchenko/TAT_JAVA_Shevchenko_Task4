package com.epam.shevchenko.dao;

import java.util.List;

import com.epam.shevchenko.dao.exception.DAOException;

public interface BaseDAO<T> {
	
	void delete (T t) throws DAOException;
	
	void delete (int id) throws DAOException;
	
	void add (T t) throws DAOException;
	
	void update (T t) throws DAOException;
	
	List<T> showAll() throws DAOException;
	
	T getById(long id) throws DAOException;
	

}
