import { Component, OnInit } from '@angular/core';
import { UserAuthService } from '../services/user-auth.service';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
@Component({
  selector: 'app-navigationbar',
  templateUrl: './navigationbar.component.html',
  styleUrls: ['./navigationbar.component.css']
})
export class NavigationbarComponent implements OnInit {

  constructor(private location: Location, private userAuthService: UserAuthService, private router: Router) { }

  ngOnInit(): void {

  }
  isLoggedIn(): boolean {
    return this.userAuthService.isLoggedIn();
  }
  logout() {
    this.userAuthService.logout();
  }
  back() {
    if (window.history.length > 1) {
      this.location.back()
    } else {
      this.router.navigate(['/user']);
    }
  }
}
