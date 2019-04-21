package com.mcdonalds.sdk.services.data.proxy;

import android.content.Context;
import com.mcdonalds.sdk.modules.models.Ingredient;
import com.mcdonalds.sdk.modules.models.Product;
import com.mcdonalds.sdk.services.data.repository.ProductRepository;

public class IngredientProxy extends Ingredient {
    private Context mContext;

    public IngredientProxy(Context context) {
        this.mContext = context;
    }

    public Product getProduct() {
        Product product = super.getProduct();
        if (product != null) {
            return product;
        }
        product = ProductRepository.getByProductCode(this.mContext, 0, false);
        setProduct(product);
        return product;
    }
}
