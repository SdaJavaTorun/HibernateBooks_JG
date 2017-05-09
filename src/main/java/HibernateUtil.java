import Entity.BooksEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Created by RENT on 2017-05-08.
 */
public class HibernateUtil {

    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

     public static void saveObjectToDataBase(BooksEntity booksEntity){
        Session session = getSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            session.save(booksEntity);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }



    public static void showList(){
        Session session = getSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();

            List<BooksEntity> books =
                    session.createQuery("FROM " + BooksEntity.class.getName()).list();

            for (BooksEntity book: books){
                System.out.println("id = " + book.getId() + " Autor: " + book.getAutor() + "  Tytul: " + book.getTytul());
            }

            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public static void editBook() throws ParseException {
        Session session = getSession();
        Transaction tx = null;

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        showList();
        System.out.println("Podaj id ksiazki");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        System.out.println("Co chcesz edytowac edytowac [podaj cyfre]");
        System.out.println("1 - Tytul");
        System.out.println("2 - Opis");
        System.out.println("3 - Rok wydania");
        System.out.println("4 - Autor");
        int wybor = sc.nextInt();
        Scanner sc1 = new Scanner(System.in);
        try{
            tx = session.beginTransaction();

            List<BooksEntity> books =
                    session.createQuery("FROM " + BooksEntity.class.getName()).list();

            switch (wybor) {
                case 1 : System.out.println("Podaj nowy tytul");
                         BooksEntity book1 = books.get(id);
                         String tytul = sc1.nextLine();
                         book1.setTytul(tytul);
                         session.update(book1);
                         break;
                case 2 : System.out.println("Podaj nowy opis");
                         BooksEntity book2 = books.get(id);
                         String opis = sc1.nextLine();
                         book2.setOpis(opis);
                         session.update(book2);
                         break;
                case 3 : System.out.println("Podaj zmieniony rok_wydania");
                         BooksEntity book3 = books.get(id);
                         String data = sc1.nextLine();
                         book3.setRok_wydania(df.parse(data));
                         session.update(book3);
                         break;
                case 4 : System.out.println("Podaj nowego autora");
                         BooksEntity book4 = books.get(id);
                         String autor = sc1.nextLine();
                         book4.setAutor(autor);
                         session.update(book4);
                         break;
            }
            sc1.close();
            sc.close();
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }


}
