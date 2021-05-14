import { Injectable } from '@angular/core';
import{ HttpClient} from '@angular/common/http';
import { Name } from '../app/name';
import { Observable } from 'rxjs';
import {environment} from  '../environments/environment';
import { Product } from '../app/product';
import { User} from '../app/user';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  
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


  UserDetails(id:string):Observable<any>{
    return this.http.post(environment.apiUrl+'/getAccountInfo',id);
  }


  UpdateUser(user:User):Observable<any>{
    return this.http.put(environment.apiUrl+'/updateUser',user);
  }

  getNameByEmail(id:string):Observable<any>{
    return this.http.get(environment.apiUrl+'/getNameByEmail/'+id,{responseType: 'text'});
  }

  // loadHome(id):Observable<any>{
  //   return this.http.get(this.url+id);
  // }



  



}
