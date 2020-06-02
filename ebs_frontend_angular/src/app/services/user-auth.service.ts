import { Injectable, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { LoginModel } from '../shared/login-model';
import { ACCESS_TOKEN } from 'src/constants';

@Injectable({
  providedIn: 'root'
})
export class UserAuthService {

  constructor(@Inject('API_URL') private apiUrl: string, private router: Router, private http: HttpClient) { }
  public login(loginDetails: LoginModel) {
    console.log(loginDetails);
    let path: string = 'auth/login';
    this.http.post<any>(this.apiUrl + path, loginDetails).subscribe(data => {
      console.log(data);
      localStorage.setItem(ACCESS_TOKEN, data.accessToken);
      this.router.navigateByUrl('/user');
    },
      error => { throw error; });
  }

  public isLoggedIn() {
    return !!localStorage.getItem(ACCESS_TOKEN);

  }

  public logout() {
    localStorage.removeItem(ACCESS_TOKEN);
  }

  public getToken() {
    return localStorage.getItem(ACCESS_TOKEN);
  }
}
