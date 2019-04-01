package jdbcdao.models;

import jdbcdao.interfaces.DtoInterface;

import java.util.Objects;

public class TextbookDto implements DtoInterface {
    private Long id;
    private String isbn;
    private String title;
    private String author;
    private Short year;

    public TextbookDto(Long id, String isbn, String title, String author, Short year) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public TextbookDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public TextbookDto setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public TextbookDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public TextbookDto setAuthor(String author) {
        this.author = author;
        return this;
    }

    public Short getYear() {
        return year;
    }

    public TextbookDto setYear(Short year) {
        this.year = year;
        return this;
    }

    @Override
    public String toString() {
        return "TextbookDto{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextbookDto that = (TextbookDto) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(isbn, that.isbn)) return false;
        if (!Objects.equals(title, that.title)) return false;
        if (!Objects.equals(author, that.author)) return false;
        return Objects.equals(year, that.year);
    }
}
