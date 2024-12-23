import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Order } from '../model/order.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CartServiceService {
  constructor(private http: HttpClient) {}

  getCartItems(customerId: number): Observable<Order[]> {
    let baseUrl = `http://localhost:8080/api/customer/cart/${customerId}`;
    return this.http.get<Order[]>(baseUrl);
  }

  // Method to update the item in the cart
  updateCartItem(orderId: number, orderQty: number): Observable<any> {
    const requestBody = {
      orderId,
      orderQty,
    };
    return this.http.put(
      `http://localhost:8080/api/customer/updateCart`,
      requestBody
    );
  }

  deleteCartItem(orderId: number, customerId: number): Observable<any> {
    const body = {
      orderId: orderId,
      customerId: customerId,
    };

    return this.http.delete(
      'http://localhost:8080/api/customer/deleteCartItem',
      { body: body }
    );
  }
}
