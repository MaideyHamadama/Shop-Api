package com.shop.backend.dto;

import com.shop.backend.entity.Stock;
import com.shop.backend.dto.StockDTO;
import java.util.Date;

public class StockDTO {

    private int idStock;
    private int availableQuantity;
    private Date lastUpdateStock;

    // Constructeur sans argument
    public StockDTO() {
    }

    /**
     * Constructeur qui initialise le DTO à partir de l'entité Stock.
     *
     * @param stock L'entité Stock à mapper.
     */
    public StockDTO(Stock stock) {
        this.idStock = stock.getStockID(); // Assurez-vous que getStockID() existe dans l'entité Stock
        this.availableQuantity = stock.getAvailableQuantity(); // Assurez-vous que getAvailableQuantity() existe dans l'entité Stock
        this.lastUpdateStock = stock.getLastUpdateStock(); // Assurez-vous que getLastUpdateStock() existe dans l'entité Stock
    }

    // ============================
    // Getters & Setters
    // ============================

    public int getIdStock() {
        return idStock;
    }

    public void setIdStock(int idStock) {
        this.idStock = idStock;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public Date getLastUpdateStock() {
        return lastUpdateStock;
    }

    public void setLastUpdateStock(Date lastUpdateStock) {
        this.lastUpdateStock = lastUpdateStock;
    }
}