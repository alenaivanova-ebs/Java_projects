package com.runner.service;

import com.runner.dao.model.Card;
import com.runner.dao.model.Product;
import com.runner.dao.ProductDAO;
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

@Service
public class ProductServiceImpl implements ProductService {

    private ProductDAO productDAO;


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
    public Product get(Long id) {
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
    public Double getTotal(Map<Long, Long> mapIdQty) {
        mapIdQty.forEach((key, value) -> System.out.println(key + " " + value));
        double total = 0.0;
        for (Map.Entry<Long, Long> entry : mapIdQty.entrySet()) {
            Long key = entry.getKey();
            Long quantity = entry.getValue();
            Double discount = productDAO.get(key).getDiscount().getDiscountPercent();
            Double price = productDAO.get(key).getPrice();
            Double priceWithDiscount = getPriceWithDiscount(price, quantity, discount);
            Double sum = priceWithDiscount * quantity;
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
    public void createReceipt(Map<Long, Long> mapIdQty, Card card) throws IOException {
        StringBuilder buf = new StringBuilder();
        buf.append("<html>" +
                "<head>" +
                "<style>" +
                "table, th, td " +
                "th, td {" +
                "padding: 5px;" +
                "}" +
                "th {" +
                "text-align: left;" +
                " }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<table>" +
                "<caption><b>Cash Receipt</b>" +
                "<br>Supermarket 123</b>" +
                "<br>Tel: 123-456-789</b>" +
                "</caption>" +
                "<tr>" +
                "<th>Qty</th>" +
                "<th>Description</th>" +
                "<th>Price</th>" +
                "<th>Total</th>" +
                "</tr>");
        DecimalFormat df = new DecimalFormat("#.##");
        for (Map.Entry<Long, Long> entry : mapIdQty.entrySet()) {
            Long key = entry.getKey();
            Long quantity = entry.getValue();
            Double discount = productDAO.get(key).getDiscount().getDiscountPercent();
            Double price = productDAO.get(key).getPrice();
            String productName = productDAO.get(key).getName();
            buf.append("<tr><td>")
                    .append(quantity)
                    .append("</td><td>")
                    .append(productName)
                    .append("</td><td>")
                    .append("$")
                    .append(price)
                    .append("</td><td>")
                    .append("$")
                    .append(Double.valueOf(df.format(getPriceWithDiscount(price, quantity, discount) * quantity)))
                    .append("</td></tr>");
        }
        double total = getTotal(mapIdQty);
        double cardDiscount = Double.parseDouble(df.format(getCardDiscount(total, card)));
        double totalWithCardDiscount = Double.parseDouble(df.format(total - cardDiscount));
        buf.append("</table><br>")
                .append("tot: " + "$")
                .append(total)
                .append("<br>")
                .append("card discount: " + "$")
                .append(cardDiscount)
                .append("<br>")
                .append("Total: " + "$")
                .append(totalWithCardDiscount)
                .append("<br>")
                .append("</html>");
        String html = buf.toString();
        File f = new File("receipt.html");
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        bw.write(html);
        bw.close();
    }

    @Override
    public Double getCardDiscount(Double total, Card card) {
        Double cardDiscount = card.getCardDiscount() / 100;
        return total * cardDiscount;
    }
}



