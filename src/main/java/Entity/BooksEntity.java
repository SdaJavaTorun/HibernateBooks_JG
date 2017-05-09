package Entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "ksiazka", schema = "sklepksiazki")
public class BooksEntity {

    private String tytul;
    private String opis;
    private String autor;
    private Date rok_wydania ;
    private int id;

    public BooksEntity(){
    }

    public BooksEntity(String tytul, String opis, String autor, Date rok_wydania, int id) {
        this.tytul = tytul;
        this.opis = opis;
        this.rok_wydania = rok_wydania;
        this.id = id;
        this.autor = autor;
    }

    @Column(name = "tytul", nullable = true, length = 128)
    public String getTytul() {
        return tytul;
    }

    @Column(name = "opis", length = 200)
    public String getOpis() {
        return opis;
    }

    @Column(name = "rok_wydania")
    public Date getRok_wydania() {
        return rok_wydania;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    @Column(name = "autor")
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setRok_wydania(Date rok_wydania) {
        this.rok_wydania = rok_wydania;
    }

    public void setId(int id) {
        this.id = id;
    }
}
