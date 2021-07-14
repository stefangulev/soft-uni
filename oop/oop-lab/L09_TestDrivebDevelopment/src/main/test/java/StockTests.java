import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StockTests {
    Product product;
    ProductStock stock;

    @Before
    public void initializeObject() {
        this.stock = new Instock();
    }

    @Test
    public void testIfCountReturnsCorrectResult() {
        Assert.assertEquals(0, this.stock.getCount());
        this.stock.add(createProduct());
        Assert.assertEquals(1, this.stock.getCount());
    }

    @Test
    public void testIfContainsReturnsCorrectResult() {
        Product product = createProduct();
        Product product1 = new Product("other_label", 3.00, 1);
       //When empty
        Assert.assertFalse(this.stock.contains(product));
        //When containing the product
        this.stock.add(product);
        Assert.assertTrue(this.stock.contains(product));
        //When not empty but containing different products
        Assert.assertFalse(this.stock.contains(product1));
    }
    @Test
    public void testAdd() {
        Product product = createProduct();
        stock.add(product);
        Assert.assertEquals(1, stock.getCount());
        Assert.assertTrue(stock.contains(product));
        stock.add(product);
        Assert.assertEquals(1, stock.getCount());

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIfFindWorksWithAnIndexTooBig() {
        addItemsToStock(3);
        stock.find(3);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void testIfFindWorksWithANegativeIndex() {
       addItemsToStock(2);
       stock.find(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIfFindWorksWithEmptyCollection() {
        stock.find(0);
    }


    @Test
    public void testIfFindWorksWithValidIndex() {
        addItemsToStock(6);
        Product productInMiddle = stock.find(stock.getCount() / 2);
        Assert.assertNotNull(productInMiddle);
        Assert.assertEquals("test_label" + stock.getCount()/2, productInMiddle.getLabel());
        Product productInBeginning = stock.find(0);
        Assert.assertNotNull(productInBeginning);
        Assert.assertEquals("test_label0", productInBeginning.getLabel());
        Product productAtEnd = stock.find(stock.getCount() -1);
        Assert.assertNotNull(productAtEnd);
        Assert.assertEquals("test_label" + (stock.getCount() -1), productAtEnd.getLabel());
    }

    @Test
    public void testChangeQuantityIfProductExist() {
        Product product = createProduct();
        int initialProductQuantity = product.getQuantity();
        stock.add(product);
        stock.changeQuantity(product.getLabel(), 10);
        Assert.assertEquals(initialProductQuantity + 10, product.getQuantity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeQuantityIfProductIfCollectionIsEmpty() {
        stock.changeQuantity("test_label", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeQuantityIfProductIfCollectionHasItemsButNotThisOne() {
        addItemsToStock(5);
        stock.changeQuantity("label_that_does_not_exist", 10);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testFindByLabelIfCollectionIsEmpty() {
        stock.findByLabel("test_label");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testFindByLabelIfCollectionHasItemsButNotThisOne() {
        addItemsToStock(5);
        stock.findByLabel("label_that_does_not_exist");
    }
    @Test
    public void testIfFindByLabelIfCollectionHasThisItem() {
        addItemsToStock(4);
        Product product = createProduct();
        Assert.assertNotNull(product);
        stock.add(product);
      Product found = stock.findByLabel(product.getLabel());
        Assert.assertNotNull(found);
      Assert.assertEquals(product.getLabel(), found.getLabel());
    }

    @Test
    public void testIfFindFirstByAlphabeticalOrderReturnsCorrectSize() {
        addItemsToStock(5);
        Iterable<Product> firstByAlphabeticalOrder = stock.findFirstByAlphabeticalOrder(3);
        Assert.assertNotNull(firstByAlphabeticalOrder);
        List<Product> itemsFromStock = createListFromIterable(firstByAlphabeticalOrder);
        Assert.assertNotNull(itemsFromStock);
        Assert.assertEquals(3, itemsFromStock.size());
    }

    @Test
    public void testIfFindFirstByAlphabeticalOrderReturnsCorrectOrder() {
        addItemsToStock(4);
        Iterable<Product> firstByAlphabeticalOrder = stock.findFirstByAlphabeticalOrder(3);
        Assert.assertNotNull(firstByAlphabeticalOrder);
        List<Product> collectedItems = createListFromIterable(firstByAlphabeticalOrder);
        Assert.assertNotNull(collectedItems);

        Set<String> expectedLabels = collectedItems.stream().map(Product::getLabel).collect(Collectors.toCollection(TreeSet::new));
       int index = 0;
        for (String expectedLabel : expectedLabels) {
            Assert.assertEquals(expectedLabel, collectedItems.get(index++).getLabel());
        }
    }
    @Test
    public void testIfFindFirstByAlphabeticalOrderReturnsEmptyCollectionsWhenIndexOutOfBounds() {
        addItemsToStock(5);
        Iterable<Product> firstByAlphabeticalOrder = stock.findFirstByAlphabeticalOrder(10);
        Assert.assertNotNull(firstByAlphabeticalOrder);
        List<Product> collectedItems = createListFromIterable(firstByAlphabeticalOrder);
        Assert.assertNotNull(collectedItems);
        Assert.assertTrue(collectedItems.isEmpty());
    }

    @Test
    public void testIfFindAllProductsWithinAGivenPriceRangeReturnsCorrect() {
        Product product = new Product("test0", 0.01, 0);
        Product product1 = new Product("test1", 1.00, 1);
        Product product2 = new Product("test2", 3.00, 2);
        Product product3 = new Product("test3", 2.00, 3);
        Product product4 = new Product("test4", 5.00, 4);
        Product product5 = new Product("test5", 4.00, 5);
        stock.add(product);
        stock.add(product1);
        stock.add(product2);
        stock.add(product3);
        stock.add(product4);
        stock.add(product5);
        double lowBar = 1.00;
        double highBar = 4.00;
        Iterable<Product> allInRange = stock.findAllInRange(lowBar, highBar);
        Assert.assertNotNull(allInRange);
        List<Product> collectedItems = createListFromIterable(allInRange);
        Assert.assertNotNull(collectedItems);
        Assert.assertEquals("test5", collectedItems.get(0).getLabel());
        Assert.assertEquals("test2", collectedItems.get(1).getLabel());
        Assert.assertEquals("test3", collectedItems.get(2).getLabel());

        }

    @Test
    public void testIfFindAllProductsWithinAGivenPriceRangeReturnsEmptyCollectionIfNoSuchItems() {
        Product product = new Product("test0", 0.01, 0);
        Product product1 = new Product("test1", 1.00, 1);
        Product product2 = new Product("test2", 3.00, 2);
        Product product3 = new Product("test3", 2.00, 3);
        Product product4 = new Product("test4", 5.00, 4);
        Product product5 = new Product("test5", 4.00, 5);
        stock.add(product);
        stock.add(product1);
        stock.add(product2);
        stock.add(product3);
        stock.add(product4);
        stock.add(product5);
        double lowBar = 10.34;
        double highBar = 15.32;
        Iterable<Product> allInRange = stock.findAllInRange(lowBar, highBar);
        Assert.assertNotNull(allInRange);
        List<Product> collectedItems = createListFromIterable(allInRange);
        Assert.assertTrue(collectedItems.isEmpty());
    }


    @Test
    public void findAllProductsInStockWithGivenPrice() {
        Product product = new Product("test0", 0.01, 0);
        Product product1 = new Product("test1", 3.00, 1);
        Product product2 = new Product("test2", 3.00, 2);
        Product product3 = new Product("test3", 2.00, 3);
        Product product4 = new Product("test4", 5.00, 4);
        Product product5 = new Product("test5", 4.00, 5);
        stock.add(product);
        stock.add(product1);
        stock.add(product2);
        stock.add(product3);
        stock.add(product4);
        stock.add(product5);
        double desiredPrice = 3.00;
        Iterable<Product> allByPrice = stock.findAllByPrice(desiredPrice);
        Assert.assertNotNull(allByPrice);
        List<Product> collectedItems = createListFromIterable(allByPrice);
        Assert.assertEquals(2, collectedItems.size());
        for (Product collectedItem : collectedItems) {
            Assert.assertEquals(desiredPrice, collectedItem.getPrice(), 0);
        }
    }
    @Test
    public void testIfFindAllProductsWithAGivenPriceReturnsEmptyCollectionIfNoSuchItems() {
        Product product = new Product("test0", 0.01, 0);
        Product product1 = new Product("test1", 1.00, 1);
        Product product2 = new Product("test2", 3.00, 2);
        Product product3 = new Product("test3", 2.00, 3);
        Product product4 = new Product("test4", 5.00, 4);
        Product product5 = new Product("test5", 4.00, 5);
        stock.add(product);
        stock.add(product1);
        stock.add(product2);
        stock.add(product3);
        stock.add(product4);
        stock.add(product5);
        double desiredPrice = 10.34;
        Iterable<Product> allInRange = stock.findAllByPrice(desiredPrice);
        Assert.assertNotNull(allInRange);
        List<Product> collectedItems = createListFromIterable(allInRange);
        Assert.assertTrue(collectedItems.isEmpty());
    }


    @Test
    public void findAllProductsInStockWithGivenQuantity() {
        Product product = new Product("test0", 0.01, 0);
        Product product1 = new Product("test1", 3.00, 1);
        Product product2 = new Product("test2", 3.00, 2);
        Product product3 = new Product("test3", 2.00, 2);
        Product product4 = new Product("test4", 5.00, 2);
        Product product5 = new Product("test5", 4.00, 5);
        stock.add(product);
        stock.add(product1);
        stock.add(product2);
        stock.add(product3);
        stock.add(product4);
        stock.add(product5);
        int desiredQuantity = 2;
        Iterable<Product> allByQuantity = stock.findAllByQuantity(desiredQuantity);
        Assert.assertNotNull(allByQuantity);
        List<Product> collectedItems = createListFromIterable(allByQuantity);
        Assert.assertEquals(3, collectedItems.size());
        for (Product collectedItem : collectedItems) {
            Assert.assertEquals(desiredQuantity, collectedItem.getQuantity());
        }
    }
    @Test
    public void testIfFindAllProductsWithAGivenQuantityReturnsEmptyCollectionIfNoSuchItems() {
        addItemsToStock(5);
        int desiredQuantity = 6;
        Iterable<Product> allInRange = stock.findAllByPrice(desiredQuantity);
        Assert.assertNotNull(allInRange);
        List<Product> collectedItems = createListFromIterable(allInRange);
        Assert.assertTrue(collectedItems.isEmpty());
    }

    @Test
    public void testFindMostExpensiveProductsReturnsCorrectResult() {
        Product product = new Product("test0", 0.01, 0);
        Product product1 = new Product("test1", 1.00, 1);
        Product product2 = new Product("test2", 3.00, 2);
        Product product3 = new Product("test3", 2.00, 3);
        Product product4 = new Product("test4", 5.00, 4);
        Product product5 = new Product("test5", 4.00, 5);
        stock.add(product);
        stock.add(product1);
        stock.add(product2);
        stock.add(product3);
        stock.add(product4);
        stock.add(product5);
        Iterable<Product> firstMostExpensiveProducts = stock.findFirstMostExpensiveProducts(3);
        Assert.assertNotNull(firstMostExpensiveProducts);
        List<Product> collected = createListFromIterable(firstMostExpensiveProducts);
        Assert.assertEquals(3, collected.size());
        Assert.assertEquals("test4", collected.get(0).getLabel());
        Assert.assertEquals("test5", collected.get(1).getLabel());
        Assert.assertEquals("test2", collected.get(2).getLabel());

    }
    @Test(expected = IllegalArgumentException.class)
    public void testFindMostExpensiveProductsThrowsExceptionIfArgumentIsMoreThanExistingItems() {
        addItemsToStock(5);
        Iterable<Product> firstMostExpensiveProducts = stock.findFirstMostExpensiveProducts(6);
        Assert.assertNotNull(firstMostExpensiveProducts);

    }
    @Test
    public void testIfGetIterableReturnsAllElements() {
        Product product = new Product("test0", 0.01, 0);
        Product product1 = new Product("test1", 1.00, 1);
        Product product2 = new Product("test2", 3.00, 2);
        Product product3 = new Product("test3", 2.00, 3);
        Product product4 = new Product("test4", 5.00, 4);
        Product product5 = new Product("test5", 4.00, 5);
        stock.add(product);
        stock.add(product1);
        stock.add(product2);
        stock.add(product3);
        stock.add(product4);
        stock.add(product5);
        Iterable<Product> iterable = stock.getIterable();
        Assert.assertNotNull(iterable);
        List<Product>collectedItems = createListFromIterable(iterable);
        Assert.assertEquals(6, collectedItems.size());
    }

    @Test
    public void testIfGetIterableReturnsEmptyCollectionsWhenStockHasNoProducts() {
        Iterable<Product> iterable = stock.getIterable();
        Assert.assertNotNull(iterable);
        List<Product> collected = createListFromIterable(iterable);
        Assert.assertTrue(collected.isEmpty());
    }










    private <T> List<T> createListFromIterable(Iterable<T> products) {
        List<T> result = new ArrayList<>();
        for (T t : products) {
            result.add(t);
        }
        return result;
    }


    public void addItemsToStock(int n) {
        for (int i = 0; i <n ; i++) {
            Product product = new Product("test_label" + i, 3.00, 1);
            stock.add(product);
        }
    }

    public Product createProduct() {
        return new Product("test_label", 3.00, 1);
    }

}
