import Entity.BooksEntity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {


    public static void main(final String[] args) throws Exception {

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date d1 = df.parse("06-05-1950");

        BooksEntity ksiazka = new BooksEntity("Krzyzacy", "sredniowieczna powiesc", "Sienkiwicz",
                                                 d1, 6 );

        HibernateUtil baza = new HibernateUtil();
       //baza.saveObjectToDataBase(ksiazka);
        baza.editBook();

    }
}
