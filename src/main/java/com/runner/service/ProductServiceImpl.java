package com.runner.service;

import com.runner.dao.model.Card;
import com.runner.dao.model.Product;
import com.runner.dao.ProductDAO;
import com.runner.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Long create(Product entity) {
        return productDAO.create(entity);
    }

    @Override
    public Optional<Product> get(Long id) {
        return productDAO.get(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    @Override
    public void update(Product entity) {

    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Double getTotal(Map<Long, Long> mapProductIdCount) {
        double total = 0.0;
        for (Map.Entry<Long, Long> entry : mapProductIdCount.entrySet()) {
            Long key = entry.getKey();
            Long quantity = entry.getValue();
            Double sum = calculateSum(key, quantity);
            total = total + sum;
        }
        return total;
    }

    @Override
    public Double getPriceWithDiscount(Double price, Long quantity, Double discount) {
        if (discount > 0 & quantity > 5) {
            return price * discount / 100;
        }
        return price;
    }

    @Override
    public boolean doExistsById(Long productId) {
        return productDAO.get(productId) != null;
    }

    @Override
    public void createReceipt(Map<Long, Long> mapWithProductsFromRequest, Card card) throws ServiceException {
        StringBuilder receipt = getReceiptHeader();
        DecimalFormat df = new DecimalFormat("#.##");
        for (Map.Entry<Long, Long> entry : mapWithProductsFromRequest.entrySet()) {
            Long key = entry.getKey();
            Long quantity = entry.getValue();
            addProductsToReceipt(receipt, key, quantity);
        }
        double total = getTotal(mapWithProductsFromRequest);
        double cardDiscount = Double.parseDouble(df.format(getCardDiscount(total, card)));
        double totalWithCardDiscount = Double.parseDouble(df.format(total - cardDiscount));
        StringBuilder receiptWithFooter = addFooterToReceipt(receipt, total, cardDiscount, totalWithCardDiscount);
        copyStringToFile(receiptWithFooter);
    }

    @Override
    public Double getCardDiscount(Double total, Card card) {
        Double cardDiscount = card.getCardDiscount() / 100;
        return total * cardDiscount;
    }

    private void addProductsToReceipt(StringBuilder receipt, Long key, Long quantity) {
        DecimalFormat df = new DecimalFormat("#.##");
        Optional<Product> productOptional = productDAO.get(key);
        Product product = productOptional.get();
        Double discount = product.getDiscount().getDiscountPercent();
        Double price = product.getPrice();
        String productName = product.getName();
        receipt.append("<tr><td>").append(quantity).append("</td><td>").append(productName).append("</td><td>").append("$").append(price).append("</td><td>").append("$").append(Double.valueOf(df.format(getPriceWithDiscount(price, quantity, discount) * quantity))).append("</td></tr>");
    }

    private Double calculateSum(Long key, Long quantity) {
        Optional<Product> productOptional = productDAO.get(key);
        Product product = productOptional.get();
        Double discount = product.getDiscount().getDiscountPercent();
        Double price = product.getPrice();
        Double priceWithDiscount = getPriceWithDiscount(price, quantity, discount);
        return priceWithDiscount * quantity;
    }

    private StringBuilder addFooterToReceipt(StringBuilder receipt, Double total, Double cardDiscount, Double totalWithCardDiscount) {
        receipt.append("</table><br>").append("tot: " + "$").append(total).append("<br>").append("card discount: " + "$").append(cardDiscount).append("<br>").append("Total: " + "$").append(totalWithCardDiscount).append("<br>").append("</html>");
        return receipt;
    }

    private void copyStringToFile(StringBuilder receipt) throws ServiceException {
        String html = receipt.toString();
        File f = new File("receipt.html");
        try (FileWriter fw = new FileWriter(f); BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(html);
        } catch (IOException e) {
            throw new ServiceException(e);
        }
    }

    private StringBuilder getReceiptHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>" + "<head>" + "<style>" + "table, th, td " + "th, td {" + "padding: 5px;" + "}" + "th {" + "text-align: left;" + " }" + "</style>" + "</head>" + "<body>" + "<table>" + "<caption><b>Cash Receipt</b>" + "<br>Supermarket 123</b>" + "<br>Tel: 123-456-789</b>" + "</caption>" + "<tr>" + "<th>Qty</th>" + "<th>Description</th>" + "<th>Price</th>" + "<th>Total</th>" + "</tr>");
        return sb;
    }
}



