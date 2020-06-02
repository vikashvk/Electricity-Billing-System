import { Injectable, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(@Inject('API_URL') private apiUrl: string, private http: HttpClient) { }
  public getCustomerProfile() {
    let path: string = 'user/me';
    return this.http.get<any>(this.apiUrl + path);
  }
}
