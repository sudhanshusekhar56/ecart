import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../model/product.model';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  private apiUrl = 'http://localhost:8080/api/admin/products';

  constructor(private http: HttpClient) {}
  getProducts(): Observable<any[]> {
    return this.http.get<Product[]>(this.apiUrl);
  }

  addProduct(product: any): Observable<any> {
    return this.http.post(this.apiUrl, product);
  }

  deleteProduct(productId: number): Observable<any> {
    const deleteUrl = `http://localhost:8080/api/admin/products/${productId}`;
    return this.http.delete(deleteUrl);
  }
}
