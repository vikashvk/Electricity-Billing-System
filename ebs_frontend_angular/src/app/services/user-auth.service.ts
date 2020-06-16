import { Injectable, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { LoginModel } from '../models/login-model';
import { ACCESS_TOKEN } from 'src/constants';
import { RegistrationModel } from '../models/registration-model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserAuthService {
  registerCustomer(regDetails: RegistrationModel): Observable<any> {
    let path: string = "auth/signup";
    return this.http.post<any>(this.apiUrl + path, regDetails);
  }

  constructor(@Inject('API_URL') private apiUrl: string, private router: Router, private http: HttpClient) { }
  public login(loginDetails: LoginModel) {
    console.log(loginDetails);
    let path: string = 'auth/login';
    this.http.post<any>(this.apiUrl + path, loginDetails).subscribe(data => {
      console.log(data);
      localStorage.setItem(ACCESS_TOKEN, data.accessToken);
      console.log('Logged In');
      this.router.navigateByUrl('/user');
    },
      error => { alert(error.error?.message); });
  }

  public isLoggedIn() {
    return !!localStorage.getItem(ACCESS_TOKEN);

  }

  public logout() {
    localStorage.removeItem(ACCESS_TOKEN);
    this.router.navigateByUrl('/login');
  }

  public getToken() {
    return localStorage.getItem(ACCESS_TOKEN);
  }
}
