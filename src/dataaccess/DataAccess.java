package dataaccess;

import java.util.HashMap;

import business.Book;
import business.LibraryMember;
import business.Role;
import business.User;
import dataaccess.DataAccessFacade.StorageType;

public interface DataAccess { 
	public HashMap<String,Book> readBooksMap();
	public HashMap<String, User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public void saveNewMember(LibraryMember member);
	public User verifyUser(String id, String password);
}
