import { Component, OnInit, Input } from '@angular/core';
import {ProductService} from '../product.service';
import {Product} from '../product';
import {ActivatedRoute, Router} from '@angular/router';
import{environment} from '../../environments/environment';
import { SafeImage } from '../safe-image';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent implements OnInit {


  id:string;
  products:Product[]
  url:string;
  emptyList:boolean=false;
  serveDisconnected:boolean=false;
  p:number=1;


  constructor(
    private productService:ProductService,
    private route:ActivatedRoute,
    private router:Router

  ) { }

  ngOnInit(): void {

    // this.productService.getAllAdminProduct(this.id).subscribe(
    //   products=>{
    //     console.log("response data: ", products);
    //     this.products=products;
    //   }
    // );

    this.loadAdminHome();

  }

  loadAdminHome(){

    this.route.paramMap.subscribe(
      param=>{
        this.id=param.get('id');
        console.log("admin-home id: "+this.id);
      }
    );
    if(this.id!=null){
      this.productService.getAllAdminProduct(this.id).subscribe(//backend product return check 
        products=>{
          console.log("response data: ", products);
          this.products=products;
          if(this.products===null || this.products.length==0){
              this.emptyList=true;
          }
        },
        (rep:Response)=>{
          if(rep.status==200){
            console.log("Successfully!!!");
          }else if(rep.status==404){
            this.emptyList=true;
          }else{
            this.serveDisconnected=true;
          }
        }
      );
    }


  }

    setMessageStyles() {
    let styles = {
      'color':'#65EB49',
      'padding-left':'100px',
      'padding-top':'100px',
      'font-size':'300%',
    };
    return styles;
  }






}
