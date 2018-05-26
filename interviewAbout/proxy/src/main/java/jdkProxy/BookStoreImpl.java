package jdkProxy;

public class BookStoreImpl implements BookStore{

	@Override
	public void addBook() {
		System.out.println("BookStoreImpl.addBook()");
	}
}
