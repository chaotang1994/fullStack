import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from './../environments/environment';
import { Observable } from 'rxjs';
import { Product } from '../app/product';
import { map } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http:HttpClient) {

   }

  getAllAdminProduct(adminName: string):Observable<Product[]>{
   return this.http.post<Product[]>(environment.apiUrl+'/getProductsFromAdmin',adminName);
  }

  getShoppingCartList(id:string):Observable<Product[]>{
    return this.http.get<Product[]>(environment.apiUrl+'/getProductsFromShoppingCart/'+id);
  }

  addProductToUser(product: Product, user_id : string):Observable<number>{
    console.log("Service check: "+product.id);
    console.log("Service check: "+user_id);
    return this.http.post<number>(environment.apiUrl+'/addProductToCustomer/'+user_id, product);
  }

  removeProductFromAdmin(product_id:number, admin_id:string):Observable<boolean>{
    console.log("product_id: "+product_id);
    console.log("admin_id: "+admin_id);
    return this.http.post<boolean>(environment.apiUrl+'/removeProductFromAdmin/'+admin_id, product_id);
  }

  removeProductFromUser(product_id:number, user_id:string):Observable<number>{
    // const url = `${environment.apiUrl}/${user_id}/${product_id}`;
    return this.http.delete<number>(environment.apiUrl+'/removeProductFromUser/'+user_id+'/'+product_id);
  }
  
  //AdminModifyProductComponent
  editProductFromAdmin(product:Product, admin_id:string):Observable<boolean>{
    return this.http.post<boolean>(environment.apiUrl+'/editProductFromAdmin/'+admin_id,product);
  }

  //add product from admin account
  addProductFromAdmin(admin_name:string, product:Product):Observable<boolean>{
    return this.http.post<boolean>(environment.apiUrl+'/addProductFromAdmin/'+admin_name,product);
  }

  updateQuantityFromUser(user_id:string,product:Product):Observable<number>{
    return this.http.put<number>(environment.apiUrl+'/updateQuantityFromUser/'+user_id,product);
  }

}
