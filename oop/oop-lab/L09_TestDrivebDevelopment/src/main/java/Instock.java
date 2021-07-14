import jdk.jshell.spi.ExecutionControl;

import java.util.*;
import java.util.stream.Collectors;

public class Instock implements ProductStock {
   private List<Product> stock;

   public Instock() {
       stock = new ArrayList<>();
   }

    @Override
    public int getCount() {
        return this.stock.size();
    }

    @Override
    public boolean contains(Product product) {
        for (Product product1 : stock) {
            if (product1.getLabel().equals(product.getLabel())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void add(Product product) {
        for (Product product1 : stock) {
            if (product1.getLabel().equals(product.getLabel())) {
                return;
            }
        }
        this.stock.add(product);
    }

    @Override
    public void changeQuantity(String product, int quantity) {
        for (Product product1 : stock) {
            if (product1.getLabel().equals(product)) {
                product1.setQuantity(product1.getQuantity() + quantity);
                return;
            }
        }
        throw new IllegalArgumentException();

    }

    @Override
    public Product find(int index) {
        if (index <0 || index > this.stock.size() - 1) {
            throw new IndexOutOfBoundsException();
        } else {
            return stock.get(index);
        }

    }

    @Override
    public Product findByLabel(String label) {
        for (Product product : stock) {
            if (product.getLabel().equals(label)) {
                return product;
            }
        }
        throw new IllegalArgumentException();

    }

    @Override
    public Iterable<Product> findFirstByAlphabeticalOrder(int count) {
       if (count < 0 || count > this.stock.size() - 1) {
           return new ArrayList<>();
       }
      return this.stock.stream()
               .sorted(Comparator.comparing(Product::getLabel)).limit(count).collect(Collectors.toList());


    }

    @Override
    public Iterable<Product> findAllInRange(double lo, double hi) {
     return  this.stock.stream().
        filter(p -> p.getPrice() > lo && p.getPrice() <= hi).
        sorted((l, r)-> Double.compare(r.getPrice(), l.getPrice())).collect(Collectors.toList());

    }

    @Override
    public Iterable<Product> findAllByPrice(double price) {
      return this.stock.stream().filter(p -> p.getPrice() == price).collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findFirstMostExpensiveProducts(int count) {
       if (count > this.stock.size() - 1) {
           throw new IllegalArgumentException();
       }

       Iterable<Product> mostExpensive = this.stock.stream().sorted((l,r) -> Double.compare(r.getPrice(), l.getPrice())).limit(count).collect(Collectors.toList());
       return mostExpensive;
    }

    @Override
    public Iterable<Product> findAllByQuantity(int quantity) {
        return this.stock.stream().filter(p -> p.getQuantity() == quantity).collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> getIterable() {
        return this.stock;
    }

    @Override
    public Iterator<Product> iterator() {
        return null;
    }
}
