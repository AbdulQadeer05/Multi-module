package main;

import java.util.Scanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import entity.Book;
import entity.User;
import entity.Author;
import entity.Publisher;
import entity.IssueBook;
import service.AuthorService;
import service.BookService;
import service.IssueBookService;
import service.PublisherService;
import service.UserService;

@SpringBootApplication(scanBasePackages = {"main", "service", "repository", "entity"})
@EntityScan("entity")
@EnableJpaRepositories("repository")
public class LibraryConsoleApp {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(LibraryConsoleApp.class, args);
        BookService bookService = ctx.getBean(BookService.class);
        UserService userService = ctx.getBean(UserService.class);
        AuthorService authorService = ctx.getBean(AuthorService.class);
        PublisherService publisherService = ctx.getBean(PublisherService.class);
        IssueBookService issueBookService = ctx.getBean(IssueBookService.class);

        Scanner sc = new Scanner(System.in);
        int aa=0;
        
        do {
        	
        
        System.out.println("1.BOOK \n2. User \n3. Author \n4. Publisher \n5. Issue-Book \nEnter Selection");
        int main=sc.nextInt();
        
        if(main==1) { //Book
        	System.out.print("1. Add Book \n2. Update \n3. Delete Book \n4. View All Books \nEnter Selection : ");
        	int book=sc.nextInt();
        	if(book==1) {
        		 System.out.print("Enter Book Name: ");
                 String name = sc.next();
                 System.out.print("Enter Title: ");
                 String title = sc.next();
                 System.out.print("Enter Author: ");
                 String author = sc.next();

                 Book b = new Book();
                 b.setName(name);
                 b.setTitle(title);
                 b.setAuthor(author);

                 bookService.addBook(b);
                 System.out.println("Book added!");
        	}
        	if(book==2) {
        		 System.out.print("Enter Book ID to update: ");
                 Long id = sc.nextLong();
                 //sc.next();
                 System.out.print("Enter new Name: ");
                 String name = sc.next();
                 System.out.print("Enter new Title: ");
                 String title = sc.next();
                 System.out.print("Enter new Author: ");
                 String author = sc.next();

                 try {
                     bookService.updateBook(id, name, title, author);
                     System.out.println("Book updated!");
                 } catch (Exception e) {
                     System.out.println(e.getMessage());
                 }
             }
        	if(book==3) {
        		System.out.print("Enter Book ID to delete: ");
                Long id = sc.nextLong();
               // sc.next();

                try {
                    bookService.deleteBook(id);
                    System.out.println("Book deleted!");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
        	}
        	if(book==4) {
        		bookService.getAllBooks()
                .forEach(b -> 
                System.out.println(b.getId() + " - " + b.getTitle() + " by " + b.getAuthor()));
        	}
        	
        }//Book End....
        if(main==2) {    //User Start
        	System.out.println("1. Add User \n2. Update User \n3. Delete User \n4. View All Users \nEnter Selection :");
        	int user=sc.nextInt();
        	if(user==1) {
        		 System.out.print("Enter User Name: ");
                 String name = sc.next();
                 System.out.print("Enter User Email: ");
                 String email = sc.next();

                 User u = new User();
                 u.setName(name);
                 u.setEmail(email);

                 userService.addUser(u);
                 System.out.println("User added!");
        	}
        	if(user==2) {
        		System.out.print("Enter User ID to Update: ");
                Long id = sc.nextLong();
               // sc.next();
                System.out.print("Enter New Name: ");
                String name = sc.next();
                System.out.print("Enter New Email: ");
                String email = sc.next();

                User newUser = new User();
                newUser.setName(name);
                newUser.setEmail(email);

                User updated = userService.updateUser(id, newUser);
                if (updated != null)
                    System.out.println("User updated!");
                else
                    System.out.println(" User not found!");
        	}
        	
        	if(user==3) {
        		 System.out.print("Enter User ID to Delete: ");
                 Long id = sc.nextLong();
                 //sc.next();

                 if (userService.deleteUser(id))
                     System.out.println("User deleted!");
                 else
                     System.out.println("User not found!");
        	}
        	
        	if(user==4) {
        		 userService.getAllUsers()
                 .forEach(u -> System.out.println(u.getId() + " - " + u.getName() + " (" + u.getEmail() + ")"));
        	}
        } //End User
        
        if(main==3) { // Start Author
        	
        	System.out.println("1. Add Author \n2. Update Author \n3. Delete Author \n4. View All Author \nEnter Selection :");
        	int author = sc.nextInt();
        	
        	if(author==1) {
        		 System.out.print("Enter Author Name: ");
                 String name = sc.next();
                 System.out.print("Enter Email");
                 String email=sc.next();
                 Author a = new Author();
                 a.setName(name);
                 a.setEmail(email);
                 authorService.addAuthor(a);
                 System.out.println("Author added!");
        	}
        	if(author==2) {
        		System.out.print("Enter Author ID to Update: ");
                Long id = sc.nextLong();
                sc.nextLine();
                System.out.print("Enter New Name: ");
                String name = sc.nextLine();
                System.out.println("Enter the Gmail");
                String gmail  = sc.next();
                try {
                    authorService.updateAuthor(id, name, gmail);
                    System.out.println("Author updated!");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
        	}
        	if(author==3) {
        		System.out.print("Enter Author ID to Delete: ");
                Long id = sc.nextLong();
                sc.nextLine();
                try {
                    authorService.deleteAuthor(id);
                    System.out.println("Author deleted!");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
        	}
        	
        	if(author==4) {
        		authorService.getAllAuthors()
                .forEach(a -> System.out.println(a.getId() + " - " + a.getName()+" - "+a.getEmail()));
        	}
        	
        }//End Author
        
        if(main==4) { // Start Publisher
        	System.out.println("1. Add Publisher \n2. Update Publisher \n3. Delete Publisher \n4. View All Publihser \nEnter Selection :");
        	int pub = sc.nextInt();
        	
        	if(pub==1) {
        		System.out.print("Enter Publisher Name: ");
                String name = sc.next();
                System.out.println("Enter Address :");
                String address = sc.next();
                Publisher p = new Publisher();
                p.setName(name);
                p.setAddress(address);
                publisherService.addPublisher(p);
                System.out.println("Publisher added!");
        	}
        	if(pub==2) {
        		System.out.print("Enter Publisher ID to Update: ");
                Long id = sc.nextLong();
                sc.nextLine();
                System.out.print("Enter New Name: ");
                String name = sc.nextLine();
                System.out.println("Enter Address :");
                String address = sc.next();
                try {
                    publisherService.updatePublisher(id, name, address);
                    System.out.println("Publisher updated!");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
        	}
        	if(pub==3) {
        		System.out.print("Enter Publisher ID to Delete: ");
                Long id = sc.nextLong();
                sc.nextLine();
                try {
                    publisherService.deletePublisher(id);
                    System.out.println("Publisher deleted!");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
        	}
        	if(pub==4) {
        		publisherService.getAllPublishers()
                .forEach(p -> System.out.println(p.getId() + " - " + p.getName()+" - "+p.getAddress()));
        	}
        	
        }// End Publisher
        if(main==5) { 
        	System.out.println("1. Issue Book \n2. View All issue-Book \n. Enter Selection :");
        	int issue=sc.nextInt();
        	
        	if(issue==1) {
        		System.out.print("Enter User ID: ");
                Long userId = sc.nextLong();
                System.out.print("Enter Book ID: ");
                Long bookId = sc.nextLong();
                sc.nextLine();

                try {
                    issueBookService.issueBook(userId, bookId);
                    System.out.println("Book issued!");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
        	}
        	if(issue==2) {
        		issueBookService.getAllIssues()
                .forEach(i -> System.out.println("IssueID: " + i.getId() + " User: " + i.getUser().getName() +
                        " Book: " + i.getBook().getTitle()));
        
        	}
        }
                
        }while(aa==0);
    }
}
