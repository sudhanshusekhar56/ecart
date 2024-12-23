import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer } from '../model/customer.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private loginUrl = 'http://localhost:8080/api/login';
  private registerUrl = 'http://localhost:8080/api/register';

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<any> {
    const payload = { username, password };
    return this.http.post<any>(this.loginUrl, payload);
  }

  register(requestBody: Customer): Observable<any> {
    return this.http.post<any>(this.registerUrl, requestBody);
  }
}
