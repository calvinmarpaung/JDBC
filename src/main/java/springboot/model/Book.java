package springboot.model;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

public class Book {

    private static final AtomicLong counter = new AtomicLong();

    private long id;

    private String name;

    private BigDecimal price;

    public Book() {
        this.id = counter.incrementAndGet();
    }

    public Book(long id,String name,  BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Book other = (Book) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", name=" + name
                + ", price=" + price + "]";
    }


}
