import { Pipe, PipeTransform } from '@angular/core';
import { Product } from '../app/product';

@Pipe({
  name: 'itemFilter'
})
export class ItemFilterPipe implements PipeTransform {

  transform(Product: Product[], searchProduct: any) : Product[] {
    if(!Product || !searchProduct){
      return Product;
    }else{
      return Product.filter(product=>
      product.id == searchProduct ||
      product.name.toLowerCase().includes(searchProduct.toLowerCase()) ||
      product.category.toLowerCase().includes(searchProduct.toLowerCase()) ||
      product.condition.toLowerCase().includes(searchProduct.toLowerCase()));
    }
  }

}
