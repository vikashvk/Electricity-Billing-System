import { Component, OnInit } from '@angular/core';
import { UserAuthService } from '../services/user-auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navigationbar',
  templateUrl: './navigationbar.component.html',
  styleUrls: ['./navigationbar.component.css']
})
export class NavigationbarComponent implements OnInit {

  constructor(private userAuthService: UserAuthService) { }

  ngOnInit(): void {

  }
  isLoggedIn(): boolean {
    return this.userAuthService.isLoggedIn();
  }
  logout() {
    this.userAuthService.logout();
  }
}
