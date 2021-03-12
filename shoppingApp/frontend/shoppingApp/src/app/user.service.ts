import { Injectable } from '@angular/core';
import{ HttpClient} from '@angular/common/http';
import { Name } from '../app/name';
import { Observable } from 'rxjs';
import {environment} from  '../environments/environment';
import { Product } from '../app/product';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  environment
  url:string = 'http://localhost:8888';
  

  constructor(private http : HttpClient) {
    
   }

  register(info):Observable<any>{
    const requestOptions: Object = {
      /* other options here */
      responseType: 'text'
    }
    return this.http.post(environment.apiUrl+'/registerNewCustomer',info,requestOptions);
  }


  UserDetails(id):Observable<any>{
    return this.http.post(environment.apiUrl+'/printAccountInfo',id);
  }

  // loadHome(id):Observable<any>{
  //   return this.http.get(this.url+id);
  // }



  



}
