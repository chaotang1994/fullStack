import { Pipe, PipeTransform } from '@angular/core';
import { Product } from '../app/product';


@Pipe({
  name: 'filterList'
})
export class FilterListPipe implements PipeTransform {

  transform(products: Product[], filterProduct: Product[]): Product[] {
    if(!products || !filterProduct){
      return products;
    }else{
      return products.filter(
        product=> filterProduct.indexOf(product) < 0);
    }
  }

}
