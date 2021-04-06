import { Pipe } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';


@Pipe({name: 'safeImage'})
export class SafeImage {
    constructor(private sanitizer:DomSanitizer){}

    transform(product_image) {
      return this.sanitizer.bypassSecurityTrustHtml(product_image);
    }
}



