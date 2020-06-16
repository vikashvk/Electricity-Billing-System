import { Component, OnInit } from '@angular/core';
import { UserAuthService } from '../services/user-auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private userAuthService: UserAuthService, private router: Router) { }

  ngOnInit(): void {
    if (this.isLoggedIn()) {
      this.router.navigateByUrl('/user')
    }
  }
  isLoggedIn(): boolean {
    return this.userAuthService.isLoggedIn();
  }
}
